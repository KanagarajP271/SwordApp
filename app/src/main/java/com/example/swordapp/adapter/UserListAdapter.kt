package com.example.swordapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.swordapp.R
import com.example.swordapp.model.Employee

class UserListAdapter : RecyclerView.Adapter<MyViewHolder>() {

    var employeeList = mutableListOf<Employee>()

    var clickListener: ListClickListener<Employee>? = null

    fun setEmployees(employees: List<Employee>) {
        this.employeeList = employees.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val employee = employeeList[position]
        holder.tvLocation.text = employee.location
        holder.tvUsername.text = employee.userName
        holder.tvEmail.text = employee.email
        holder.layout.setOnClickListener {
            clickListener?.onClick(employee,position)
        }

        holder.imgDelete.setOnClickListener {
            clickListener?.onDelete(employee)
        }

    }

    fun setOnItemClick(listClickListener: ListClickListener<Employee>) {
        this.clickListener = listClickListener
    }

}

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
    val tvLocation = view.findViewById<TextView>(R.id.tvLocation)
    val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
    val layout = view.findViewById<ConstraintLayout>(R.id.layout)
    val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)
}


interface ListClickListener<T> {
    fun onClick(data: Employee, position: Int)
    fun onDelete(user: Employee)
}
