package fr.isen.yapagi.network

import android.util.Log
import com.airbnb.lottie.LottieCompositionFactory.fromJson
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.data.User

class Database {
    companion object{
        private const val TAG = "DATABASE"

        private const val URL = "https://yapagi-8c1ba-default-rtdb.europe-west1.firebasedatabase.app/"
        private const val USERS = "users"
        private const val POSTS = "posts"

        private val db = Firebase.database(URL)

        fun createPost(post: Post){
            val postsDb = db.getReference(POSTS)

            Log.d(TAG, "CREATING_POST...")
            val postId = postsDb.push().key
            postId?.let{
                postsDb.child(postId).setValue(post)
                Log.d(TAG, "POST_CREATED !")
            }?: run{
                Log.d(TAG, "COULD_NOT_CREATE_POST")
            }
        }

        fun updatePost(postId: String, post: Post){
            val postsDb = db.getReference(POSTS)
            Log.d(TAG, "UPDATING_POST_@$postId")
            postsDb.child(postId).child(postId).setValue(post)
            Log.d(TAG, "POST_UPDATED")
        }

        fun saveUser(userId: String, userFirstName: String, userLastName: String,
                     userUsername: String, userEmail: String){
            val usersDb = db.getReference(USERS)

            Log.d(TAG, "SAVING_USER...")
            val user = User(userFirstName, userLastName, userUsername, userEmail)
            usersDb.child(userId).setValue(user)
            Log.d(TAG, "USER_SAVED !")
        }

        fun getUser(userId: String, listener: UserDataListener){
            val usersDb = db.getReference(USERS)

            usersDb.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "GETTING_USER_@$userId")
                    listener.onSuccess(dataSnapshot.getValue<User>())
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "COULD_NOT_GET_USER_@$userId")
                }
            })
        }

        fun getPosts(listener: PostsDataListener){
            val postsDb = db.getReference(POSTS)

            postsDb.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot){
                    Log.d(TAG, "GETTING_POSTS_LIST")
                    val postsJson: Map<String, Any>? = dataSnapshot.getValue<HashMap<String, Any>>()
                    val posts: MutableMap<String, Post>? = mutableMapOf()
                    if (postsJson != null){
                        for(post in postsJson){
                            //IMPORTANT : We use Gson().toJson(str) instead of str.toString()
                            //Bc toString() wipes out "" and they are needed by the parser
                            val postModel: Post = Gson().fromJson(Gson().toJson(post.value), Post::class.java)
                            posts?.set(post.key, postModel)
                        }
                    }
                    listener.onSuccess(posts)
                }

                override fun onCancelled(error: DatabaseError){
                    Log.d(TAG, "COULD_NOT_GET_POSTS")
                }
            })
        }
    }
}