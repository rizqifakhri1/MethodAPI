package com.rizqi.methodapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rizqi.methodapi.databinding.ActivityLoginBinding
import com.rizqi.methodapi.model.LoginRequest
import com.rizqi.methodapi.model.LoginResponse
import com.rizqi.methodapi.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    companion object {
        val TOKEN = "token"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty()){
                Toast.makeText(this, "Harap isi semua form", Toast.LENGTH_SHORT).show()
            } else {
                val data = LoginRequest(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
                ApiClient.instance.loginUser(data).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val code = response.code()
                        val body = response.body()!!.dataLogin
                        if (code == 200) {
//                            AlertDialog.Builder(this@LoginActivity)
//                                .setTitle("Login Berhasil")
//                                .setMessage("""
//                                id : ${body.id}
//                                Username : ${body.username}
//                                Email : ${body.email}
//                                Token : ${body.token}
//                            """.trimIndent())
//                                .create()
//                                .show()
                            Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}