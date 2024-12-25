package com.example.onlinestore.Data

import com.example.onlinestore.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

fun initFirebase() {
    AUTHFIREBASE = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTHFIREBASE.currentUser?.uid.toString()
}