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

    var userList = mutableListOf<Employee>()

    var clickListener: ListClickListener<Employee>? = null

    fun setUsers(users: List<Employee>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = userList[position]
        holder.tvLocation.text = user.location
        holder.tvUsername.text = user.userName
        holder.tvEmail.text = user.email
        holder.layout.setOnClickListener {
            clickListener?.onClick(user,position)
        }

        holder.imgDelete.setOnClickListener {
            clickListener?.onDelete(user)
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
    fun onClick(data: T, position: Int)
    fun onDelete(user: T)
}
