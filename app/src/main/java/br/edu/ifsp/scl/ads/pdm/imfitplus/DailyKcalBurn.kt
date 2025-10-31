    package br.edu.ifsp.scl.ads.pdm.imfitplus

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityDailyKcalBurnBinding

    class DailyKcalBurn: AppCompatActivity() {
        private val adb: ActivityDailyKcalBurnBinding by lazy {
            ActivityDailyKcalBurnBinding.inflate(layoutInflater)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(adb.root)

            val nome = intent.getStringExtra("nome") ?: ""
            val idade = intent.getIntExtra("idade", 12)
            val sexo = intent.getStringExtra("sexo") ?: ""
            val alturaCm = intent.getIntExtra("alturaCm", 170)
            val pesoKg = intent.getIntExtra("pesoKg", 70)
            val nivelAtividade = intent.getStringExtra("nivelAtividade") ?: ""
            val imc = intent.getDoubleExtra("imc", 1.0)
            val categoriaImc = intent.getStringExtra("categoria")

            val tmb = if (sexo === "Masculino") {
                66 + (13.7 * pesoKg) + (5 * alturaCm) - (6.8 * idade)
            } else {
                655 + (9.6 * pesoKg) + (1.8 * alturaCm) - (4.7 * idade)
            }

            adb.nomeTmbTv.text = "Nome: $nome"
            adb.tmbTv.text = "TMB: %.2f kcal/dia".format(tmb)

            adb.calcularPesoBtn.setOnClickListener {
                val intent = Intent(this, IdealWeight::class.java).apply {
                    putExtra("nome", nome)
                    putExtra("idade", idade)
                    putExtra("sexo", sexo)
                    putExtra("alturaCm", alturaCm)
                    putExtra("pesoKg", pesoKg)
                    putExtra("nivelAtividade", nivelAtividade)
                    putExtra("imc", imc)
                    putExtra("categoria", categoriaImc)
                    putExtra("tmb", tmb)
                }

                Toast.makeText(
                    this,
                    "Calculando seu peso ideal",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(intent)
            }

            adb.voltarTmbBtn.setOnClickListener {
                finish()
            }
        }
    }