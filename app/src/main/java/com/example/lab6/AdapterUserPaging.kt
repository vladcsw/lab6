package com.example.lab6

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ryudith.lab6.room.EntityUser
import kotlinx.coroutines.InternalCoroutinesApi

class AdapterUserPaging : PagingDataAdapter<EntityUser, AdapterUserPaging.UserItem>(AdapterUserPaging.DIFF_CALLBACK) {
    inner class UserItem (v : View) : RecyclerView.ViewHolder(v)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityUser>() {
            override fun areItemsTheSame(oldItem: EntityUser, newItem: EntityUser): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: EntityUser, newItem: EntityUser): Boolean {
                return oldItem == newItem
            }

        }
    }

    @InternalCoroutinesApi
    override fun onBindViewHolder(holder: UserItem, position: Int) {
        val item = getItem(position)

        Log.d("DEBUG_DATA", "item : $item")

        holder.itemView.findViewById<TextView>(R.id.tv_activityMain_titulo).text = item?.titulo
        holder.itemView.findViewById<TextView>(R.id.tv_activityMain_descripcion).text = item?.descripcion
        holder.itemView.findViewById<TextView>(R.id.tv_activityMain_valoracion).text = item?.valoracion.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_activityMain_tipoTurismo).text = item?.tipoTurismo


        holder.itemView.findViewById<ConstraintLayout>(R.id.cl_activityMain_userItem).setOnClickListener {
            val intent = Intent(holder.itemView.context, ActivityEdit::class.java)
            intent.putExtra("DATA_ID", item?.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItem {
        return UserItem(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }
}