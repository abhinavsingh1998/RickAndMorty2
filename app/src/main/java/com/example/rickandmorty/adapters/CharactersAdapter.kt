package com.example.rickandmorty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemCharacterRowBinding
import com.example.rickandmorty.models.CharacterData
import com.example.rickandmorty.viewmodels.RickAndMortyViewModel


class CharactersAdapter(
    private val list: ArrayList<CharacterData>,
    private val context: Context,
    private val viewmodel: RickAndMortyViewModel,
    private val navController: NavController
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_character_row
    }
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val binding = ItemCharacterRowBinding.bind(view)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.CharacterViewHolder, position: Int) {
        val item = list[holder.adapterPosition]
        holder.binding.let { binding ->
            binding.tvName.text = item.name.toString()
            binding.tvStatus.text = item.status.toString()
            Glide.with(context)
                .load(item.image)
                .into(binding.caracterImg)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    inner class CharacterViewHolder(val binding: ItemCharacterRowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.clCharacterRow.setOnClickListener {
                viewmodel.characterDetailsMutableLiveData.postValue(list[adapterPosition])
                navController.navigate(R.id.characterDetailsFragment)
            }
        }
    }

    fun addItems(newItems: ArrayList<CharacterData>) {
        list.addAll(newItems)
        notifyDataSetChanged() // Notify the adapter that data set has changed
    }
}
