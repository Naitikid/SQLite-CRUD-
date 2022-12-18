package com.example.sqlitetest.Adapter

import android.content.ComponentCallbacks
import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitetest.ModelClass.UserViewModel
import com.example.sqlitetest.R
import com.google.android.material.imageview.ShapeableImageView

class CustomAdapter(
    private val context: Context,
    private val data: ArrayList<UserViewModel>,
    val callbacks: (UserViewModel,Position:Int,View) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    class ViewHolder(IteamView: View) : RecyclerView.ViewHolder(IteamView) {
        val btnedit = itemView.findViewById<ShapeableImageView>(R.id.editbtn)
        val btndelet = itemView.findViewById<ShapeableImageView>(R.id.deletebtn)
        val username: TextView = itemView.findViewById((R.id.nameUserdesigh))
        val mobilenumber: TextView = itemView.findViewById((R.id.phoneNumberUserDesigh))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.userview_desigh, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val UserViewModel = data[position]
        holder.username.text = UserViewModel.username
        holder.mobilenumber.text = UserViewModel.phonenumber
        holder.btndelet.setOnClickListener {
            callbacks.invoke(UserViewModel,position,holder.btndelet)
        }
        holder.btnedit.setOnClickListener {
            callbacks.invoke(UserViewModel,position ,holder.btnedit)
        }

    }

    override fun getItemCount(): Int {
        checkEmpty();
        return data.size
    }

    private fun checkEmpty() {

    }


}
