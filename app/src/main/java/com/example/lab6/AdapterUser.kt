package com.example.lab6

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ryudith.lab6.room.EntityUser
import kotlinx.coroutines.InternalCoroutinesApi

class AdapterUser : RecyclerView.Adapter<AdapterUser.UserItem>() {
    private var userList = emptyList<EntityUser>()

    inner class UserItem (v : View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItem {
        return UserItem(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    @InternalCoroutinesApi
    override fun onBindViewHolder(holder: UserItem, position: Int) {
        val item = userList[position]

        holder.itemView.findViewById<TextView>(R.id.tv_activityMain_titulo).text = item.titulo
        holder.itemView.findViewById<TextView>(R.id.tv_activityMain_descripcion).text = item.descripcion

        holder.itemView.findViewById<ConstraintLayout>(R.id.cl_activityMain_userItem).setOnClickListener {
            val intent = Intent(holder.itemView.context, ActivityEdit::class.java)
            intent.putExtra("DATA_ID", item.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUserList (userListData : List<EntityUser>) {
        userList = userListData
        notifyDataSetChanged()
    }

}