package com.example.movielistxml.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistxml.databinding.ItemMovieBinding
import com.example.movielistxml.model.Movie
import timber.log.Timber

class MovieAdapter(
    private var movieList: List<Movie>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun updateData(newList: List<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                imgPoster.setImageResource(movie.imageRes)
                tvTitle.text = movie.title
                tvYear.text = movie.year
                tvPlot.text = movie.plot

                btnImdb.setOnClickListener {
                    // Logging event B: Log saat tombol Explicit Intent ditekan
                    Timber.d("Explicit Intent (IMDB) clicked for: ${movie.title} [URL: ${movie.imdbUrl}]")
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.imdbUrl))
                    root.context.startActivity(intent)
                }

                btnDetail.setOnClickListener {
                    // Logging event B: Log saat tombol Detail ditekan
                    Timber.d("Detail button clicked for Movie ID: ${movie.id}")
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