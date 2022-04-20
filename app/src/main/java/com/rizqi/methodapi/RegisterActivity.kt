package com.rizqi.methodapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rizqi.methodapi.databinding.ActivityRegisterBinding
import com.rizqi.methodapi.model.RegisterRequest
import com.rizqi.methodapi.model.RegisterResponse
import com.rizqi.methodapi.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if (binding.etEmail.text.isNullOrEmpty() || binding.etUsername.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty()){
                Toast.makeText(this, "Harap isi semua form", Toast.LENGTH_SHORT).show()
            } else {
                val data = RegisterRequest(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etUsername.text.toString()
                )
                ApiClient.instance.registerUser(data).enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        val code = response?.code()
                        if (code == 201) {
                            Toast.makeText(this@RegisterActivity, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@RegisterActivity, "Kode tidak 201", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}