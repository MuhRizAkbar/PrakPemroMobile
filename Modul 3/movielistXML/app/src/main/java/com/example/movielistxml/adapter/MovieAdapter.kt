package com.example.movielistxml.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistxml.databinding.ItemMovieBinding
import com.example.movielistxml.model.Movie

class MovieAdapter(
    private val movieList: List<Movie>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                imgPoster.setImageResource(movie.imageRes)
                tvTitle.text = movie.title
                tvYear.text = movie.year
                tvPlot.text = movie.plot

                btnImdb.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.imdbUrl))
                    root.context.startActivity(intent)
                }

                btnDetail.setOnClickListener {
                    onDetailClick(movie.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size
}