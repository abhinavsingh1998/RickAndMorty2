package com.example.rickandmorty.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.rickandmorty.R
import com.example.rickandmorty.activities.MainActivity
import com.example.rickandmorty.adapters.CharactersAdapter
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import com.example.rickandmorty.models.CharacterData
import com.example.rickandmorty.models.CharacterResponse
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.RetrofitClient
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.viewmodels.RickAndMortyViewModel
import com.example.rickandmorty.viewmodels.factories.RickAndMortyViewModelFactory


class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var viewmodel: RickAndMortyViewModel
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var imageButton: ImageButton

    private val thresholdValue = 6


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(layoutInflater)


        (requireActivity() as MainActivity).showBackButton(false)
        (requireActivity() as MainActivity).addToolbarTitle("Characters")
        //Setting up things
        val apiService = RetrofitClient.apiService().create(ApiService::class.java)
        val repository = CharactersRepository(apiService)
        viewmodel = ViewModelProvider(requireActivity(),
            RickAndMortyViewModelFactory(repository))[RickAndMortyViewModel::class.java]

        //Set up adapter for very first time
        val navController = findNavController()
        val adapter = context?.let { CharactersAdapter(ArrayList<CharacterData>(),
            it,
            viewmodel,
            navController) }
        adapter?.let {
            setUpRecyclerView(it, ArrayList<CharacterData>())
        }

        //call for api for very first time
        viewmodel.getAllCharactersByPageNo(1)

        //Observe api response and store it in characterResponseVariable
        var characterResponse: CharacterResponse? = null
        viewmodel.charactersResponseLiveData.observe(viewLifecycleOwner, Observer { cResponse ->
            characterResponse = cResponse
            cResponse.results?.let { characterDataList ->
                adapter?.addItems(characterDataList)
            }
        })

        //Pagination
        binding.charactersRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if(lastVisibleItem > totalItemCount - thresholdValue) {
                    characterResponse?.info?.let { info ->
                        info.next?.let {
                            val urlStrArray = it.split("=")
                            val pageNo = Integer.parseInt(it.split("=")[urlStrArray.size - 1])
                            viewmodel.getAllCharactersByPageNo(pageNo)
                        }
                    }
                }
            }
        })

        return binding.root
    }

    private fun setUpRecyclerView(adapter: CharactersAdapter, characterDataList: ArrayList<CharacterData>) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.charactersRv.layoutManager = layoutManager
        binding.charactersRv.adapter = adapter
    }

}