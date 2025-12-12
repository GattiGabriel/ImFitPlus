package br.edu.ifsp.scl.ads.pdm.imfitplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.pdm.imfitplus.R
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaDTO.FichaDTO

class FichaAdapter(
    private val lista: List<FichaDTO>,
    private val onClick: (FichaDTO) -> Unit
) : RecyclerView.Adapter<FichaAdapter.FichaViewHolder>() {

    inner class FichaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvNome = view.findViewById<TextView>(R.id.tvNome)
        val tvIdade = view.findViewById<TextView>(R.id.tvIdade)
        val tvSexo = view.findViewById<TextView>(R.id.tvSexo)
        val tvAltura = view.findViewById<TextView>(R.id.tvAltura)
        val tvPeso = view.findViewById<TextView>(R.id.tvPeso)
        val tvNivel = view.findViewById<TextView>(R.id.tvNivel)
        val tvImc = view.findViewById<TextView>(R.id.tvImc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FichaViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ficha, parent, false)
        return FichaViewHolder(v)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: FichaViewHolder, position: Int) {
        val f = lista[position]

        holder.tvNome.text = f.nome
        holder.tvIdade.text = "Idade: ${f.idade}"
        holder.tvSexo.text = "Sexo: ${f.sexo}"
        holder.tvAltura.text = "Altura: ${f.altura}"
        holder.tvPeso.text = "Peso: ${f.peso}"
        holder.tvNivel.text = "Atividade: ${f.nivelAtvFisica}"
        holder.tvImc.text = "IMC: %.2f".format(f.imc)

        holder.itemView.setOnClickListener { onClick(f) }
    }
}

