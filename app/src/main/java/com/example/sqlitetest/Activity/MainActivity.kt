package com.example.sqlitetest.Activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitetest.Adapter.CustomAdapter
import com.example.sqlitetest.DBHelper.DBHelper
import com.example.sqlitetest.ModelClass.UserViewModel
import com.example.sqlitetest.R
import com.example.sqlitetest.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var userlist = ArrayList<UserViewModel>()
    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: CustomAdapter

    val db = DBHelper(this, null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DBHelper(this, null)
        click()
        showdata()
    }

    private fun showdata() {
        userlist = db.showdata()
        if (userlist.size>0){
            binding.datanotfound.visibility=View.GONE
        val recyclerview = findViewById<RecyclerView>(R.id.userListview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(this, userlist) { UserViewModel, Postion, view ->
            when (view.id) {
                R.id.editbtn -> {
                    editclick(UserViewModel)

                }

                R.id.deletebtn -> {
                    delectclick(Postion)

                }
            }
        }
        recyclerview.adapter = adapter
        }else{
            binding.datanotfound.visibility=View.VISIBLE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun delectclick(Postion: Int) {
        val dialog = MaterialAlertDialogBuilder(this)
        dialog.setTitle("Delete Data")
        dialog.setMessage("Are You Sure Delete Data")
        dialog.setNegativeButton("No",DialogInterface.OnClickListener { No,_ ->
            No.dismiss()
        })
        dialog.setPositiveButton("Ok", DialogInterface.OnClickListener
        { A, B ->
            val delet = dbHelper.delect(userlist[Postion].id!!)
            if (delet > -1) {
                Toast.makeText(this, "Deleted Data", Toast.LENGTH_SHORT).show()
                userlist.removeAt(Postion)
                adapter.notifyItemRemoved(Postion)
                if (userlist.isEmpty()){
                    binding.datanotfound.visibility=View.VISIBLE
                }
            } else {
                Toast.makeText(this, "not deleted data", Toast.LENGTH_SHORT).show()
            }
        })
        dialog.show()

    }

    private fun editclick(UserViewModel: UserViewModel) {
        val bundle = Bundle()
        bundle.putParcelable("Naitik", UserViewModel)
        var intent = Intent(this, EditdataActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun click() {
        binding.addUserbtn.setOnClickListener {
            val intent = Intent(this, InsertdataActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        showdata()
    }
}