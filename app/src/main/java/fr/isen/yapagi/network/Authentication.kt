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

class Authentication(val parentActivity: Activity) {
    companion object{
        private const val TAG = "AUTHENTICATION"
    }
    private var auth: FirebaseAuth = Firebase.auth

    private var user: FirebaseUser? = null
        get(){
            return user
        }

    fun createUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(parentActivity) { task ->
                if(task.isSuccessful){
                    Log.d(TAG, "createUser::CREATED_USER_WITH_SUCCESS")
                    user = auth.currentUser
                    //Snackbar.make(view, R.string.user_create_success, Snackbar.LENGTH_SHORT).show()
                    //DO WHAT IS NEEDED WITH THE NEWLY CREATED USER
                }else{
                    Log.d(TAG, "createUser::FAILED_TO_CREATE_USER", task.exception)
                    //Snackbar.make(view, R.string.user_create_failed, Snackbar.LENGTH_SHORT).show()
                    //DO WHAT IS NEEDED IN CASE OF AN ERROR
                }
            }
    }

    fun isSignedIn(): Boolean{
        var result = false

        user = auth.currentUser
        if(user != null){
            Log.d(TAG, "User is already signed in")
            result = true
        }

        return result
    }
}