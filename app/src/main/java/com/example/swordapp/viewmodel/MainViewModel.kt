package com.example.swordapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.swordapp.MyApplication
import com.example.swordapp.database.EmployeeDao
import com.example.swordapp.database.EmployeeDatabase
import com.example.swordapp.model.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    var db: EmployeeDao = EmployeeDatabase.getDatabase(MyApplication.getContext())?.Dao()!!

    val employeeListLiveData = MutableLiveData<List<Employee>>()

    var job: Job? = null

    //Fetch All the Employees
    fun getAllEmployees() {
        val employeeList = EmployeeDatabase.getDatabase(MyApplication.getContext()).Dao().gelAllUsers()

        //if (employeeList.isNotEmpty()) {
            employeeListLiveData.value = employeeList
        //}
    }

    // Insert new Employees
    fun insertUser(users: Employee) {
        //db.insertUser(users)
        job = CoroutineScope(Dispatchers.IO).launch {
            db.insertUser(users)
        }
    }

    // update Employees
    fun updateUser(users: Employee) {
        db.updateUser(users)
    }

    // Delete Employees
    fun deleteUser(users: Employee) {
        db.deleteUser(users)
    }

}