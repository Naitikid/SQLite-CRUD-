package com.example.sqlitetest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlitetest.DBHelper.DBHelper
import com.example.sqlitetest.ModelClass.UserViewModel
import com.example.sqlitetest.R
import com.example.sqlitetest.databinding.ActivityEditdataBinding
import kotlinx.android.synthetic.main.activity_editdata.*
import kotlinx.android.synthetic.main.activity_insertdata.*

class EditdataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditdataBinding
    private lateinit var userlist: UserViewModel
    private lateinit var dbHelper: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditdataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bundledata()
        dbHelper = DBHelper(this, null)
        binding.updatedataBtn.setOnClickListener {
            submitclick()
        }

    }

    private fun submitclick() {
        val name = binding.updateName.text.toString()
        val surname = binding.updateSurename.text.toString()
        val companyname = binding.updateCompanyname.text.toString()
        val email = binding.updateEmail.text.toString()
        val phonenumber = binding.updatephonenumber.text.toString()
        updateName.error = null
        updateSurename.error = null
        updateCompanyname.error = null
        updateEmail.error = null
        updatephonenumber.error = null



        if (name!!.isEmpty()) {
            updateName.error = getString(R.string.error)
        } else if (surname!!.isEmpty()) {
            updateSurename.error = getString(R.string.error)
        } else if (companyname!!.isEmpty()) {
            updateCompanyname.error = getString(R.string.error)
        } else if (email!!.isEmpty()) {
            updateEmail.error = getString(R.string.error)
        } else if (phonenumber!!.isEmpty()) {
            updatephonenumber.error = getString(R.string.error)

        } else {

            val update = dbHelper.updatedata(
                UserViewModel(
                    userlist.id,
                    name,
                    surname,
                    companyname,
                    email,
                    phonenumber
                )
            )
            if (update > -1) {
                Toast.makeText(this, "data updated", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "data not Updated", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun bundledata() {

        if (intent.extras != null) {
            userlist = intent.extras!!.getParcelable("Naitik")!!
            binding.apply {
                updateName.setText(userlist.username)
                updateSurename.setText(userlist.surname)
                updateCompanyname.setText(userlist.companyname)
                updateEmail.setText(userlist.userremail)
                updatephonenumber.setText(userlist.phonenumber)
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
