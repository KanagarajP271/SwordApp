package com.example.swordapp.repository

import android.content.Context
import com.example.swordapp.model.Employee
import com.example.swordapp.database.EmployeeDao
import com.example.swordapp.database.EmployeeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserRepository(context: Context) {

    var db: EmployeeDao = EmployeeDatabase.getDatabase(context)?.Dao()!!

    var job: Job? = null

    //Fetch All the Users
    fun getAllUsers(): List<Employee> {
        return db.gelAllUsers()
    }

    // Insert new user
    fun insertUser(users: Employee) {
        //db.insertUser(users)
        job = CoroutineScope(Dispatchers.IO).launch {
            db.insertUser(users)
        }
    }

    // update user
    fun updateUser(users: Employee) {
        db.updateUser(users)
    }

    // Delete user
    fun deleteUser(users: Employee) {
        db.deleteUser(users)
    }
}