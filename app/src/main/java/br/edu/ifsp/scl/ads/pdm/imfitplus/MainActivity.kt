package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.startBtn.setOnClickListener {
            val intent = Intent(this, PersonalData::class.java)
            startActivity(intent)
        }

        amb.savedBtn.setOnClickListener {
            val intent = Intent(this, ListaFichasActivity::class.java)
            startActivity(intent)
        }

    }
}