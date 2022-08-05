package com.example.swordapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swordapp.adapter.ListClickListener
import com.example.swordapp.adapter.UserListAdapter
import com.example.swordapp.databinding.ActivityMainBinding
import com.example.swordapp.model.Employee
import com.example.swordapp.repository.UserRepository

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserListAdapter
    val repo: UserRepository by lazy {
        UserRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserListAdapter()
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        adapter.setOnItemClick(object : ListClickListener<Employee> {
            override fun onClick(data: Employee, position: Int) {
                val intent = Intent(this@MainActivity, AddUserActivity::class.java)
                intent.putExtra("employee", data)
                startActivity(intent)
            }

            override fun onDelete(user: Employee) {
                repo.deleteUser(user)
                fetchUsers()
            }
        })


        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        val allUsers = repo.getAllUsers()
        adapter.setUsers(allUsers)
    }
}
