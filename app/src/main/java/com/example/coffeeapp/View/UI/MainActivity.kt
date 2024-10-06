package com.example.coffeeapp.View.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.Adapters.CategoryAdapter
import com.example.coffeeapp.Adapters.OfferAdapter
import com.example.coffeeapp.Adapters.PopularAdapter
import com.example.coffeeapp.R
import com.example.coffeeapp.ViewModel.MainViewModel
import com.example.coffeeapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initPopular()
        initOffers()
        bottomMenu()
    }

    private fun bottomMenu() {
        binding.cartBTN.setOnClickListener{
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initOffers() {
        viewModel.offer.observe(this, Observer {
            binding.progressBar4.visibility = View.VISIBLE

            binding.recyclerViewOffer.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewOffer.adapter = OfferAdapter(it)

            binding.progressBar4.visibility = View.GONE
        })
        viewModel.loadOffer()
    }

    private fun initPopular() {
        viewModel.popular.observe(this, Observer {ts->
            binding.progressBarPopular.visibility = View.VISIBLE
            binding.recyclerViewPopular.layoutManager = LinearLayoutManager (this@MainActivity, LinearLayoutManager.HORIZONTAL,false)

            binding.recyclerViewPopular.adapter = PopularAdapter(ts) //ts Observe ile gelen livedata verisi
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular() //bu viewmodeldeki işlemi çalıştırır bu işlemden sonra observe işlemi tekrar çalışır
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer{
            binding.recyclerViewCategory.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)

            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()

    }
}