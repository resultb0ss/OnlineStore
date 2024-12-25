package com.example.onlinestore.Data

import com.example.onlinestore.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


lateinit var AUTHFIREBASE: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User

const val NODE_USERS = "users"
const val NODE_USERNAMES = "usernames"
const val NODE_PHONES = "phones"
const val NODE_PHONES_CONTACTS = "phones_contacts"

const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_EMAIL = "email"
const val CHILD_FIRSTNAME = "firstname"
const val CHILD_LASTNAME = "lastname"