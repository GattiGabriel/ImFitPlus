package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityHealthPanelBinding

class HealthPanel: AppCompatActivity() {
    private val ahp: ActivityHealthPanelBinding by lazy {
        ActivityHealthPanelBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ahp.root)

        val nome = intent.getStringExtra("nome") ?: ""
        val imc = intent.getDoubleExtra("imc", 1.0)
        val categoria = intent.getStringExtra("categoria") ?: ""
        val pesoIdeal = intent.getDoubleExtra("pesoIdeal", 70.0)
        val tmb = intent.getDoubleExtra("tmb", 15.0)
        val pesoKg = intent.getDoubleExtra("pesoKg", 70.0)

        val ingestaoAgua = (pesoKg * 350) / 1000

        ahp.healthNomeTv.text = "Nome: $nome"
        ahp.healthImcTv.text = "IMC: %.2f".format(imc)
        ahp.healthCategoryTv.text = "Categoria do IMC: $categoria"
        ahp.healthIdealTv.text = "Peso Ideal: %.2f".format(pesoIdeal)
        ahp.healthDailyTv.text = "TMB: %.2f kcal/dia".format(tmb)
        ahp.healthWaterIntakeTv.text = "Ingestão de Água: %.2f Litros/semana".format(ingestaoAgua)

        ahp.healthVoltarBtn.setOnClickListener {
            finish()
        }
    }
}