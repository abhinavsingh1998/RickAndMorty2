package com.example.rickandmorty.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.activities.MainActivity
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.models.CharacterData
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.RetrofitClient
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.utils.Constants
import com.example.rickandmorty.viewmodels.RickAndMortyViewModel
import com.example.rickandmorty.viewmodels.factories.RickAndMortyViewModelFactory


class CharacterDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var viewmodel: RickAndMortyViewModel

    private var selectedCharacter: CharacterData? = null
    private var prefKey: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        val pref = activity?.application?.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)

        binding.ivFavIcon.setOnClickListener {
            pref?.let { pref ->
                selectedCharacter?.let { selectedChar ->

                    if (pref.contains(prefKey)){
                        binding.ivFavIcon.setImageDrawable(resources.getDrawable(R.drawable.icon_fav_true))
                        pref.edit().putBoolean(
                            prefKey,
                            !pref.getBoolean(prefKey, false)).apply()
                    } else {
                        pref.edit().putBoolean(prefKey, true).apply()
                    }

                    if (pref.getBoolean(prefKey, false)){
                        binding.ivFavIcon.setImageDrawable(resources.getDrawable(R.drawable.icon_fav_true))
                        Toast.makeText(requireContext(), "Added to Favourites", Toast.LENGTH_SHORT).show()
                    } else {
                        binding.ivFavIcon.setImageDrawable(resources.getDrawable(R.drawable.icon_fav_false))
                        Toast.makeText(requireContext(), "Removed from Favourites", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        (requireActivity() as MainActivity).showBackButton(false)
        (requireActivity() as MainActivity).addToolbarTitle("Details")


        //Setting up things
        val apiService = RetrofitClient.apiService().create(ApiService::class.java)
        val repository = CharactersRepository(apiService)
        viewmodel = ViewModelProvider(requireActivity(),
            RickAndMortyViewModelFactory(repository)
        )[RickAndMortyViewModel::class.java]

        viewmodel.characterDetailsMutableLiveData.observe(viewLifecycleOwner, Observer {data ->
            context?.let { context ->

                selectedCharacter = data
                prefKey = Constants.PREF_KEY + data.id

                pref?.let { pref->
                    if (pref.contains(prefKey) && pref.getBoolean(prefKey, false)){
                        binding.ivFavIcon.setImageDrawable(resources.getDrawable(R.drawable.icon_fav_true))
                    }
                }

                Glide.with(context)
                    .load(data.image)
                    .into(binding.ivCharacterImage)

                binding?.let{view->
                    view.tvName.text = data.name
                    view.tvGenderType.text = data.gender
                    view.tvLocationType.text = data.location?.name
                    view.tvStatusType.text = data.status
                    view.tvSpeciesType.text = data.species
                }

            }
        })

        return binding.root
    }

}