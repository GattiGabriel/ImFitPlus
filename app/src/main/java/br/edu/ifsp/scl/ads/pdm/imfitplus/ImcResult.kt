package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityImcResultBinding
import kotlin.math.pow

class ImcResult: AppCompatActivity() {

    private val aib: ActivityImcResultBinding by lazy {
        ActivityImcResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aib.root)

        val nome = intent.getStringExtra("nome") ?: ""
        val idade = intent.getIntExtra("idade", 12)
        val sexo = intent.getStringExtra("sexo") ?: ""
        val alturaCm = intent.getIntExtra("alturaCm", 170)
        val pesoKg = intent.getIntExtra("pesoKg", 70)
        val nivelAtividade = intent.getStringExtra("nivelAtividade") ?: ""
        val freqCardiacaMax = intent.getIntExtra("freqCardiacaMax", 10)
        val zonaTreinoLeve = "${freqCardiacaMax.times(0.5)} - ${freqCardiacaMax.times(0.6)}"
        val zonaTreinoQueimaGordura = "${freqCardiacaMax.times(0.6)} - ${freqCardiacaMax.times(0.7)}"
        val zonaTreinoAerobica = "${freqCardiacaMax.times(0.7)} - ${freqCardiacaMax.times(0.8)}"
        val zonaTreinoAnaerobica = "${freqCardiacaMax.times(0.8)} - ${freqCardiacaMax.times(0.9)}"
        val dataNasc = intent.getStringExtra("dataNasc")

        val alturaMetros = alturaCm / 100.0
        val imc = pesoKg / alturaMetros.pow(2)

        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }

        aib.nomeTv.text = "Nome: $nome"
        aib.imcTv.text = "IMC: %.2f".format(imc)
        aib.categoriaTv.text = "Categoria: $categoria"
        aib.freqCardiacaTv.text = "$freqCardiacaMax BPM"
        aib.zonaTreinoLeveTv.text = "$zonaTreinoLeve BPM"
        aib.zonaTreinoQueimaGorduraTv.text = "$zonaTreinoQueimaGordura BPM"
        aib.zonaTreinoAerobicaTv.text = "$zonaTreinoAerobica BPM"
        aib.zonaTreinoAnaerobicaTv.text = "$zonaTreinoAnaerobica BPM"

        aib.calcularBtn.setOnClickListener {
            val intent = Intent(this, DailyKcalBurn::class.java).apply {
                putExtra("nome", nome)
                putExtra("idade", idade)
                putExtra("sexo", sexo)
                putExtra("alturaCm", alturaCm)
                putExtra("pesoKg", pesoKg)
                putExtra("nivelAtividade", nivelAtividade)
                putExtra("imc", imc)
                putExtra("categoria", categoria)
                putExtra("freqCardiacaMax", freqCardiacaMax)
                putExtra("dataNasc", dataNasc)
                putExtra("zonaTreinoLeve", zonaTreinoLeve)
                putExtra("zonaTreinoQueimaGordura", zonaTreinoQueimaGordura)
                putExtra("zonaTreinoAerobica", zonaTreinoAerobica)
                putExtra("zonaTreinoAnaerobica", zonaTreinoAnaerobica)
            }

            Toast.makeText(
                this,
                "Calculando gasto calórico",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(intent)
        }

        aib.voltarBtn.setOnClickListener {
            finish()
        }
    }
}