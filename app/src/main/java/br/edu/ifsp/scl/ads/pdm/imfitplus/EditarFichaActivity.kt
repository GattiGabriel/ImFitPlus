package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityEditarFichaBinding
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaDTO.FichaDTO
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaRepo.FichaRepository
import kotlin.math.pow

class EditarFichaActivity : AppCompatActivity() {

    private lateinit var amb: ActivityEditarFichaBinding
    private lateinit var repo: FichaRepository
    private var fichaId = -1

    private val opcoesSexo = listOf("Masculino", "Feminino")
    private val opcoesNivelAtv = listOf("Sedentário", "Leve", "Moderado", "Intenso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityEditarFichaBinding.inflate(layoutInflater)
        setContentView(amb.root)

        repo = FichaRepository(this)

        // Spinner adapters
        amb.sexoSp.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcoesSexo)
        amb.nivelAtvFisicaSp.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcoesNivelAtv)

        fichaId = intent.getIntExtra("id", -1)
        val ficha = repo.buscarPorId(fichaId)

        if (ficha != null) {
            preencherCampos(ficha)
        }

        amb.salvarBtn.setOnClickListener {
            salvarAlteracoes()
        }

        amb.deletarBtn.setOnClickListener {
            repo.deletar(fichaId)
            finish()
        }
    }

    private fun preencherCampos(f: FichaDTO) {
        amb.nomeEt.setText(f.nome)
        amb.idadeEt.setText(f.idade.toString())
        amb.alturaEt.setText(f.altura.toString())
        amb.pesoEt.setText(f.peso.toString())

        amb.sexoSp.setSelection(opcoesSexo.indexOf(f.sexo))
        amb.nivelAtvFisicaSp.setSelection(opcoesNivelAtv.indexOf(f.nivelAtvFisica))

        amb.imcEt.setText(f.imc.toString())
        amb.imcCategoriaEt.setText(f.imcCategoria)
        amb.tmbEt.setText(f.tmb.toString())
        amb.pesoIdealEt.setText(f.pesoIdeal.toString())
    }

    private fun salvarAlteracoes() {
        val nome = amb.nomeEt.text.toString()
        val idade = amb.idadeEt.text.toString().toInt()
        val sexo = opcoesSexo[amb.sexoSp.selectedItemPosition]
        val altura = amb.alturaEt.text.toString().toInt()
        val peso = amb.pesoEt.text.toString().toInt()
        val nivel = opcoesNivelAtv[amb.nivelAtvFisicaSp.selectedItemPosition]

        // Recalcular IMC
        val alturaM = altura.toDouble() / 100
        val imc = peso / alturaM.pow(2)

        // Categoria IMC
        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }

        // Peso ideal (exemplo simples)
        val pesoIdeal = 22 * alturaM.pow(2)

        // TMB (exemplo Mifflin)
        val tmb = if (sexo == "Masculino")
            10 * peso + 6.25 * altura - 5 * idade + 5
        else
            10 * peso + 6.25 * altura - 5 * idade - 161

        val novaFicha = FichaDTO(
            id = fichaId,
            nome = nome,
            idade = idade,
            sexo = sexo,
            altura = altura,
            peso = peso,
            nivelAtvFisica = nivel,
            imc = imc,
            imcCategoria = categoria,
            tmb = tmb,
            pesoIdeal = pesoIdeal
        )

        repo.atualizar(novaFicha)
        finish()
    }
}
