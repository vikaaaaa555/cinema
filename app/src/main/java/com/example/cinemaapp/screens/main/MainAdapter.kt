package com.example.cinemaapp.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapp.MAIN
import com.example.cinemaapp.POSTER_PATH
import com.example.cinemaapp.R
import com.example.cinemaapp.models.MovieItemModel

class MainAdapter: RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var listMovies = emptyList<MovieItemModel>()
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val dateTextView: TextView = itemView.findViewById(R.id.item_date)
        val myImageView: ImageView = itemView.findViewById(R.id.item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = listMovies[position].title
        holder.dateTextView.text = listMovies[position].release_date

        Glide.with(MAIN)
            .load("${POSTER_PATH}${listMovies[position].poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.myImageView)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setList(list: List<MovieItemModel>) {
        listMovies = list
        notifyDataSetChanged() //надо переделать
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.clickMovie(listMovies[holder.absoluteAdapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }
}