package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityHealthPanelBinding
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaDTO.FichaDTO
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaRepo.FichaRepository

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
        val pesoKg = intent.getIntExtra("pesoKg", 70)
        val idade = intent.getIntExtra("idade", 20)
        val sexo = intent.getStringExtra("sexo") ?: ""
        val altura = intent.getIntExtra("alturaCm", 160)
        val nivelAtvFisica = intent.getStringExtra("nivelAtividade") ?: ""

        val ingestaoAgua = pesoKg * 0.35  // agora é Double

        ahp.healthNomeTv.text = "Nome: $nome"
        ahp.healthImcTv.text = "IMC: %.2f".format(imc)
        ahp.healthCategoryTv.text = "Categoria do IMC: $categoria"
        ahp.healthIdealTv.text = "Peso Ideal: %.2f".format(pesoIdeal)
        ahp.healthDailyTv.text = "TMB: %.2f kcal/dia".format(tmb)
        ahp.healthWaterIntakeTv.text =
            "Ingestão de Água: %.2f Litros/semana".format(ingestaoAgua)

        ahp.healthVoltarBtn.setOnClickListener {
            finish()
        }

        val repo = FichaRepository(this)

        ahp.healthSalvarBtn.setOnClickListener {
            val ficha = FichaDTO(
                nome = nome,
                idade = idade,
                sexo = sexo,
                altura = altura,
                peso = pesoKg,
                nivelAtvFisica = nivelAtvFisica,
                imc = imc,
                imcCategoria = categoria,
                tmb = tmb,
                pesoIdeal = pesoIdeal,
            )

            repo.inserir(ficha)

            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()

        }
    }
}