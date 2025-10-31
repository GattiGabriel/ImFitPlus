package br.edu.ifsp.scl.ads.pdm.imfitplus

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
        val alturaCm = intent.getIntExtra("alturaCm", 170)
        val pesoKg = intent.getIntExtra("pesoKg", 70)

        val alturaMetros = alturaCm / 100.0

        val pesoIdeal = 22 * (alturaMetros.pow(2))
        val diferenca = pesoKg.toDouble() - pesoIdeal

        aiwb.nomePiTv.text = "Nome: $nome"
        aiwb.pesoPiTv.text = "Peso Ideal: %.2f kg".format(pesoIdeal)
        aiwb.diferencaPiTv.text = "Diferença do peso atual: %.2f kg".format(diferenca)

        aiwb.voltarPiBtn.setOnClickListener {
            finish()
        }
    }
}