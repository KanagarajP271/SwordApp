package com.example.swordapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.swordapp.databinding.ActivityAddUserBinding
import com.example.swordapp.model.Employee
import com.example.swordapp.viewmodel.MainViewModel

class AddUserActivity : AppCompatActivity() {

    var employee: Employee? = null
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        employee = intent.getParcelableExtra("employee")

        employee?.let {
            binding.etUsername.setText(it.userName)
            binding.etLocation.setText(it.location)
            binding.etEmail.setText(it.email)
        } ?: kotlin.run {

        }

        binding.btnSaveUser.setOnClickListener {

            if (binding.etUsername.text.isNotEmpty() && binding.etEmail.text.isNotEmpty() && binding.etLocation.text.isNotEmpty()) {

                employee?.let {
                    val user = Employee(userId = it.userId,
                        userName = binding.etUsername.text.toString(),
                        location = binding.etLocation.text.toString(),
                        email = binding.etEmail.text.toString()
                    )
                    viewModel.updateUser(user)
                } ?: kotlin.run {
                    val user = Employee(
                        userName = binding.etUsername.text.toString(),
                        location = binding.etLocation.text.toString(),
                        email = binding.etEmail.text.toString()
                    )
                    viewModel.insertUser(user)
                }

            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}