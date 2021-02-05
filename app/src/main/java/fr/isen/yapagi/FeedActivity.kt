package fr.isen.yapagi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.ActivityFeedBinding
import org.json.JSONException
import org.json.JSONObject
import java.util.EnumSet.range

lateinit var binding_feed: ActivityFeedBinding
class FeedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_feed = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_feed)
        title = "Feed"
        setContentView(binding_feed.root)
        title = intent.getStringExtra("username")

        binding_feed.postList.layoutManager = LinearLayoutManager(this)

        makeRequest()
    }
    private fun makeRequest() {

        var list: List<Post> = emptyList()
        list += Post("1st post")
        list += Post("2st post")
        list += Post("3st post")
        list += Post("4st post")
        list += Post("5st post")
        println("list = $list")

        binding_feed.postList.adapter = FeedRecyclerViewAdapter(list, this)

        /*
        val gson: Post =
        Gson().fromJson(response.toString(), DishDetailData::class.java)
        gson.data.firstOrNull { it.category == "Entrées" }?.dish?.let {
            binding.starterList.adapter = StarterRecycleViewAdapter(it, this);
        }
        val queue = Volley.newRequestQueue((this))
        var url = "http://test.api.catering.bluecodegames.com/menu"
        var postData = JSONObject()
        var jsonRet = ""
        try {
            postData.put("id_shop", "1")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {  response ->
                val gson: DishDetailData =
                    Gson().fromJson(response.toString(), DishDetailData::class.java)
                gson.data.firstOrNull { it.category == "Entrées" }?.dish?.let {
                    binding.starterList.adapter = StarterRecycleViewAdapter(it, this);
                }
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(jsonObjectRequest)

         */
    }


}