package com.example.rickandmorty.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityMainBinding
    private var navController = NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
    }

    fun showBackButton(state:Boolean){
        if(state){
            activityBinding.backBtn.visibility= View.VISIBLE
        } else{
            activityBinding.backBtn.visibility= View.GONE
        }
    }

    fun popBack(){
        activityBinding.backBtn.setOnClickListener {

        }
    }

    fun addToolbarTitle(title:String){
        activityBinding.title.text = title
    }
}