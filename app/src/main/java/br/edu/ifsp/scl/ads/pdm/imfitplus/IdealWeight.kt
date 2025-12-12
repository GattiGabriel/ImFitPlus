package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityIdealWeightBinding
import kotlin.math.pow

class IdealWeight: AppCompatActivity() {
    private val aiwb: ActivityIdealWeightBinding by lazy {
        ActivityIdealWeightBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aiwb.root)

        val nome = intent.getStringExtra("nome") ?: ""
        val idade = intent.getIntExtra("idade", 12)
        val sexo = intent.getStringExtra("sexo") ?: ""
        val alturaCm = intent.getIntExtra("alturaCm", 170)
        val pesoKg = intent.getIntExtra("pesoKg", 70)
        val nivelAtividade = intent.getStringExtra("nivelAtividade") ?: ""
        val imc = intent.getDoubleExtra("imc", 1.0)
        val categoriaImc = intent.getStringExtra("categoria")
        val tmb = intent.getDoubleExtra("tmb", 15.0)
        val freqCardiacaMax = intent.getIntExtra("freqCardiacaMax", 10)
        val zonaTreinoLeve = intent.getStringExtra("zonaTreinoLeve")
        val zonaTreinoQueimaGordura = intent.getStringExtra("zonaTreinoQueimaGordura")
        val zonaTreinoAerobica = intent.getStringExtra("zonaTreinoAerobica")
        val zonaTreinoAnaerobica = intent.getStringExtra("zonaTreinoAnaerobica")
        val dataNasc = intent.getStringExtra("dataNasc")

        val alturaMetros = alturaCm / 100.0

        val pesoIdeal = 22 * (alturaMetros.pow(2))
        val diferenca = pesoKg.toDouble() - pesoIdeal

        aiwb.nomePiTv.text = "Nome: $nome"
        aiwb.pesoPiTv.text = "Peso Ideal: %.2f kg".format(pesoIdeal)
        aiwb.diferencaPiTv.text = "Diferença do peso atual: %.2f kg".format(diferenca)

        aiwb.resumoSaudeBtn.setOnClickListener {
            val intent = Intent(this, HealthPanel::class.java).apply {
                putExtra("nome", nome)
                putExtra("idade", idade)
                putExtra("sexo", sexo)
                putExtra("alturaCm", alturaCm)
                putExtra("pesoKg", pesoKg)
                putExtra("nivelAtividade", nivelAtividade)
                putExtra("imc", imc)
                putExtra("categoria", categoriaImc)
                putExtra("tmb", tmb)
                putExtra("pesoIdeal", pesoIdeal)
                putExtra("diferenca", diferenca)
                putExtra("freqCardiacaMax", freqCardiacaMax)
                putExtra("dataNasc", dataNasc)
                putExtra("zonaTreinoLeve", zonaTreinoLeve)
                putExtra("zonaTreinoQueimaGordura", zonaTreinoQueimaGordura)
                putExtra("zonaTreinoAerobica", zonaTreinoAerobica)
                putExtra("zonaTreinoAnaerobica", zonaTreinoAnaerobica)
            }

            startActivity(intent)
        }

        aiwb.voltarPiBtn.setOnClickListener {
            finish()
        }
    }
}