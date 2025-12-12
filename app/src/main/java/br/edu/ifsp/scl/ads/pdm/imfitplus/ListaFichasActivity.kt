package br.edu.ifsp.scl.ads.pdm.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.imfitplus.adapter.FichaAdapter
import br.edu.ifsp.scl.ads.pdm.imfitplus.databinding.ActivityListaFichasBinding
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaRepo.FichaRepository

class ListaFichasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaFichasBinding
    private lateinit var repo: FichaRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaFichasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repo = FichaRepository(this)

        // Botão Voltar
        binding.voltarBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val lista = repo.listar()

        val adapter = FichaAdapter(lista) { ficha ->
            val intent = Intent(this, EditarFichaActivity::class.java)
            intent.putExtra("id", ficha.id)
            startActivity(intent)
        }

        binding.fichasRv.adapter = adapter
        binding.fichasRv.layoutManager = LinearLayoutManager(this)
    }
}
