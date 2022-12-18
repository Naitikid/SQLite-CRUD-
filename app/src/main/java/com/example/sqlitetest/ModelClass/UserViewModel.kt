package com.example.sqlitetest.ModelClass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserViewModel
    (val id: Int?,
    val username: String,
    val surname: String,
    val companyname: String,
    val userremail: String,
    val phonenumber: String
) : Parcelable

