package com.example.superheroecomics.vista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroecomics.data.local.HeroeEntity
import com.example.superheroecomics.databinding.ItemHeroeBinding

class HeroeAdapter : RecyclerView.Adapter<HeroeAdapter.ItemHeroeViewHolder>() {

    lateinit var binding: ItemHeroeBinding
    private val listItemHeroes = mutableListOf<HeroeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHeroeViewHolder {
        binding = ItemHeroeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHeroeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemHeroes.size
    }

    override fun onBindViewHolder(holder: ItemHeroeViewHolder, position: Int) {
        val phone = listItemHeroes[position]
        holder.bind(phone)
    }

    fun setData(heroes: List<HeroeEntity>) {
        this.listItemHeroes.clear()
        this.listItemHeroes.addAll(heroes)
        notifyDataSetChanged()

    }


    class ItemHeroeViewHolder(val heroeVista: ItemHeroeBinding) :
        RecyclerView.ViewHolder(heroeVista.root) {
        fun bind(heroe: HeroeEntity) {
            heroeVista.imageViewHeroe.load(heroe.imagenLink)
            heroeVista.textViewName.text = heroe.nombre
            heroeVista.textViewAO.text = heroe.Año_creacion.toString()
        }
    }
}