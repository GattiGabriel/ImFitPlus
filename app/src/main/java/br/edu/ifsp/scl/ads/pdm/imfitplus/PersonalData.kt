package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityPersonalDataBinding
import java.time.LocalDate
import java.time.Period

class PersonalData: AppCompatActivity() {

    private val apb: ActivityPersonalDataBinding by lazy {
        ActivityPersonalDataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        var altura = 170
        var peso = 70

        apb.alturaSb.progress = altura
        apb.alturaSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                altura = progress
                apb.alturaValor.text =  "$progress cm"
                validarFormulario()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        apb.pesoSb.progress = peso
        apb.pesoSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                peso = progress
                apb.pesoValor.text = "$progress kg"
                validarFormulario()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val niveis = listOf("Sedentário", "Leve", "Moderado", "Intenso")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, niveis)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        apb.nivelSpinner.adapter = adapter

        apb.nomeEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                validarFormulario()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        apb.sexoRg.setOnCheckedChangeListener { _, _ -> validarFormulario() }

        apb.salvarBtn.isEnabled = false

        apb.salvarBtn.setOnClickListener {
            val nome = apb.nomeEt.text.toString().trim()
            val dataNasc = "${apb.diaNascEt.text}/${apb.mesNascEt}/${apb.anoNascEt}"
            val idade = calculateAge(LocalDate.of(apb.anoNascEt.text.toString().toInt(), apb.mesNascEt.text.toString().toInt(), apb.diaNascEt.text.toString().toInt()))
            val sexo = if (apb.sexoRg.checkedRadioButtonId == apb.sexoMasculino.id) "Masculino" else "Feminino"
            val alturaCm = apb.alturaSb.progress
            val pesoKg = apb.pesoSb.progress
            val nivelAtividade = apb.nivelSpinner.selectedItem.toString()
            val freqCardiacaMax = 220 - idade

            val intent = Intent(this, ImcResult::class.java).apply {
                putExtra("nome", nome)
                putExtra("idade", idade)
                putExtra("sexo", sexo)
                putExtra("alturaCm", alturaCm)
                putExtra("pesoKg", pesoKg)
                putExtra("nivelAtividade", nivelAtividade)
                putExtra("freqCardiacaMax", freqCardiacaMax)
                putExtra("dataNasc", dataNasc)
            }

            Toast.makeText(
                this,
                "Dados salvos com sucesso!",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(intent)
        }

        apb.voltarBtn.setOnClickListener {
            finish()
        }
    }

    private fun validarFormulario() {
        val nomeOk = apb.nomeEt.text.toString().trim().isNotEmpty()
        if (!nomeOk) apb.nomeEt.error = "Informe seu nome"

        val sexoOk = apb.sexoRg.checkedRadioButtonId != -1
        val alturaOk = apb.alturaSb.progress in 50..250
        val pesoOk = apb.pesoSb.progress in 20..300

        apb.salvarBtn.isEnabled = nomeOk && sexoOk && alturaOk && pesoOk
    }

    private fun calculateAge(dataNascimento: LocalDate): Int {
        val currentDate = LocalDate.now()
        return Period.between(dataNascimento, currentDate).years
    }

}