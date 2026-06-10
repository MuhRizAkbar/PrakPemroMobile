package com.example.movielistxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movielistxml.databinding.FragmentMovieDetailBinding
import com.example.movielistxml.model.MovieData

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt("movieId") ?: return
        val movie = MovieData.movies.find { it.id == movieId }

        movie?.let {
            binding.imgDetailPoster.setImageResource(it.imageRes)
            binding.tvDetailTitle.text = it.title.replace("\n", " ")
            binding.tvDetailYear.text = it.year
            binding.tvDetailPlot.text = it.plot
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}