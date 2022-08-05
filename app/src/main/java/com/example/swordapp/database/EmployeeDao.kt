package com.example.swordapp.database

import androidx.room.*
import com.example.swordapp.model.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Employee)

    @Query("Select * from employee")
    fun gelAllUsers(): List<Employee>

    @Update
    fun updateUser(users: Employee)

    @Delete
    fun deleteUser(users: Employee)

}