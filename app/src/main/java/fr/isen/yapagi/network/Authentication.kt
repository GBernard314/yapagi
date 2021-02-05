package fr.isen.yapagi.network

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authentication(private val parentActivity: Activity) {
    companion object{
        private const val TAG = "AUTHENTICATION"
        private var auth: FirebaseAuth = Firebase.auth

        fun getUserID(): String?{
            val user = auth.currentUser
            user?.let{
                return it.uid
            }?: run{
                return null
            }

        }
    }

    fun createUser(firstName: String, lastName: String, userName: String,
                   email: String, password: String){
        Log.d(TAG, "createUser::CREATING_EMAIL($email)_PASS($password)")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(parentActivity) { task ->
                if(task.isSuccessful){
                    Log.d(TAG, "createUser::CREATED_USER_WITH_SUCCESS")
                    val user = auth.currentUser
                    Database.saveUser(user?.uid.toString(), firstName, lastName, userName, email)
                }else{
                    Log.d(TAG, "createUser::FAILED_TO_CREATE_USER", task.exception)
                }
            }
    }

    fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(parentActivity) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "loginUser::SIGNED_IN_USER_WITH_SUCCESS")
                } else {
                    Log.d(TAG, "loginUser::SIGNED_IN_USER_FAILED", task.exception)
                }
            }
    }

    fun logoutUser(){
        Firebase.auth.signOut()
    }
}