package com.example.coffeeapp.Adapters

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeapp.Model.ItemsModel
import com.example.coffeeapp.View.UI.DetailActivity
import com.example.coffeeapp.databinding.ViewholderPopularBinding

class PopularAdapter(val items: MutableList<ItemsModel>):RecyclerView.Adapter<PopularAdapter.Viewholder>() {
    private lateinit var context: Context

    inner class  Viewholder(val binding: ViewholderPopularBinding):RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.extraTxt.text = item.extra
        holder.binding.priceTxt.text = "$"+item.price.toString().trim()
        holder.binding.ratingBar.rating = item.rating.toFloat()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size


}