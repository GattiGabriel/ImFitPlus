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

        aib.calcularBtn.setOnClickListener {
            val intent = Intent(this, DailyKcalBurn::class.java).apply {
                putExtra("nome", nome)
                putExtra("idade", idade)
                putExtra("sexo", sexo)
                putExtra("alturaCm", alturaCm)
                putExtra("pesoKg", pesoKg)
                putExtra("nivelAtividade", nivelAtividade)
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