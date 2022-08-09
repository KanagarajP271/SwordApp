package com.example.swordapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swordapp.adapter.ListClickListener
import com.example.swordapp.adapter.UserListAdapter
import com.example.swordapp.databinding.ActivityMainBinding
import com.example.swordapp.model.Employee
import com.example.swordapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserListAdapter
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddUserActivity::class.java)
            startActivity(intent)
        }

        //viewModel.getAllEmployees()
    }

    private fun setAdapter(list: List<Employee>) {
        if (list.size > 0) {
            adapter = UserListAdapter()
            adapter.setEmployees(list)
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
            binding.rvUsers.adapter = adapter
            binding.rvUsers.visibility = View.VISIBLE
            adapter.notifyDataSetChanged()

            adapter.setOnItemClick(object : ListClickListener<Employee> {
                override fun onClick(data: Employee, position: Int) {
                    val intent = Intent(this@MainActivity, AddUserActivity::class.java)
                    intent.putExtra("employee", data)
                    startActivity(intent)
                }

                override fun onDelete(user: Employee) {
                    viewModel.deleteUser(user)
                    fetchUsers()
                }
            })
        }
        else {
            binding.rvUsers.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getAllEmployees()
        viewModel.employeeListLiveData.observe(this) {
            if (it != null) {
                setAdapter(it)
            }
        }
    }
}
