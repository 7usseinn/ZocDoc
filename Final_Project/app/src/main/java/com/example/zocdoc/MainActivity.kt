package com.example.zocdoc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zocdoc.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Access RecyclerView using View Binding
        val recyclerViewDoctors = binding.recyclerViewDoctors

        // Set layout manager for RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerViewDoctors.layoutManager = layoutManager

        // Make API call to get list of doctors
        ApiClient.instance.getDoctors().enqueue(object : Callback<List<DoctorDataItem>> {
            override fun onResponse(call: Call<List<DoctorDataItem>>, response: Response<List<DoctorDataItem>>) {
                if (response.isSuccessful) {
                    val doctors: List<DoctorDataItem>? = response.body()
                    doctors?.let {
                        val adapter = DoctorAdapter(it)
                        recyclerViewDoctors.adapter = adapter // Set adapter to RecyclerView
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("MainActivity", "API call failed with code: ${response.code()}")
                    // You can handle error cases here, like displaying an error message
                }
            }

            override fun onFailure(call: Call<List<DoctorDataItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure: ${t.message}")
                // Handle failure in API call, such as displaying an error message or retrying the call
            }
        })
    }
}
