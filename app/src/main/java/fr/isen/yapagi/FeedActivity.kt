package fr.isen.yapagi

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.ActivityFeedBinding
import java.util.*

lateinit var binding_feed: ActivityFeedBinding

class FeedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_feed = ActivityFeedBinding.inflate(layoutInflater)
        //title = "Feed"
        setContentView(binding_feed.root)

        title = intent.getStringExtra("username")

        binding_feed.postList.layoutManager = LinearLayoutManager(this)

        makeRequest()
    }




    private fun makeRequest() {

        var list: List<Post> = emptyList()


        var commentList: List<Comment> = emptyList()

        commentList += Comment(Date(1, 1, 2019), "FIRST !!!", "Billy Bob")
        commentList += Comment(Date(1, 1, 2019), "erufvyeryufvbhe", "Billy Bob")


        list += Post("Isis_love ?", Date(1, 1, 1970), "Wouf", 99, commentList, "https://instagram.fcdg2-1.fna.fbcdn.net/v/t51.2885-15/e35/51993659_572254839943780_6273454790177071551_n.jpg?_nc_ht=instagram.fcdg2-1.fna.fbcdn.net&_nc_cat=104&_nc_ohc=Jp3iQtqHAAwAX9pXCbm&tp=1&oh=7dfdd96c69a9985b86444b88eca3ea81&oe=604657E5")
        list += Post("PierroLeVeau", Date(1, 1, 1970), "GNGNGN j'ai eu une meilleure note que toi", 666, commentList, "https://instagram.fcdg2-1.fna.fbcdn.net/v/t51.2885-15/e35/71527670_785261731906367_7668804132584366294_n.jpg?_nc_ht=instagram.fcdg2-1.fna.fbcdn.net&_nc_cat=111&_nc_ohc=bDzoeRiw-7cAX8nplJo&tp=1&oh=9952ddcede9f0b6d6378cbfa20c19b9a&oe=6046FE0D")
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