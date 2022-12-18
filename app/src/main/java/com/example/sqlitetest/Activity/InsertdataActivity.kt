package com.example.sqlitetest.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitetest.DBHelper.DBHelper
import com.example.sqlitetest.ModelClass.UserViewModel
import com.example.sqlitetest.R
import com.example.sqlitetest.databinding.ActivityInsertdataBinding
import kotlinx.android.synthetic.main.activity_insertdata.*

class InsertdataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertdataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityInsertdataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        click()
    }

    private fun click() {

        binding.inserdataBtn.setOnClickListener {

            val db = DBHelper(this, null)
            val nameI = binding.insertName.text.toString()
            val surnameI = binding.insertSurname.text.toString()
            val companyI = binding.insertCompanyname.text.toString()
            val emailI = binding.insertEmail.text.toString()
            val mobilenumber = binding.insertphonenumber.text.toString()


            insertName.error=null
            insertSurname.error=null
            insertCompanyname.error=null
            insertEmail.error=null
            insertphonenumber.error=null
            insertphonenumber.error=null

            if (nameI!!.isEmpty()) {
                insertName.error = getString(R.string.error)
            } else if (surnameI!!.isEmpty()) {
                insertSurname.error = getString(R.string.error)
            } else if (companyI!!.isEmpty()) {
                insertCompanyname.error = getString(R.string.error)
            } else if (emailI!!.isEmpty()) {
                insertEmail.error = getString(R.string.error)
            } else if (mobilenumber!!.isEmpty()) {
                insertphonenumber.error = getString(R.string.error)
            } else {
                val insert =
                    db.addName(UserViewModel(0, nameI, surnameI, companyI, emailI, mobilenumber))
                if (insert > -1) {
                    Toast.makeText(this, R.string.insertdata, Toast.LENGTH_SHORT).show()
                    /*startActivity(Intent(this, MainActivity::class.java))*/
                    finish()
                }
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()}
}