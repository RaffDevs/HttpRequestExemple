package com.example.httprequestexemple

import CarApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.httprequestexemple.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var carApi: CarApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRetrofit()
        setListeners()
    }

    private fun setListeners() {
        binding.buttonRequest.setOnClickListener {
            request()
        }
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carApi = retrofit.create(CarApi::class.java)
    }

    private fun request() {
        carApi.getCars().enqueue(object: Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    Log.d("REQUISICAO", "onResponse: ${response.body()}")
                } else {
                    Toast.makeText(applicationContext, "Erro ao faze request", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}