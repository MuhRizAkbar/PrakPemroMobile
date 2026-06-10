package com.example.movielistxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielistxml.R
import com.example.movielistxml.adapter.MovieAdapter
import com.example.movielistxml.databinding.FragmentMovieListBinding
import com.example.movielistxml.viewmodel.MovieViewModel
import com.example.movielistxml.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    // Initialize ViewModel with Factory
    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory("Horror/Thriller")
    }

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        // Pass click event to ViewModel instead of handling navigation directly here
        adapter = MovieAdapter(emptyList()) { movieId ->
            viewModel.onMovieClicked(movieId)
        }
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Collect List Data
                launch {
                    viewModel.movies.collect { movieList ->
                        adapter.updateData(movieList)
                    }
                }

                // Collect Navigation Event
                launch {
                    viewModel.navigateToDetail.collect { movie ->
                        movie?.let {
                            // Logging event C: Log data dari list yang dipilih ketika berpindah ke halaman Detail
                            Timber.i("Navigating to Detail Fragment. Selected Movie Data: ID=${it.id}, Title=${it.title}, Year=${it.year}")

                            val bundle = Bundle().apply { putInt("movieId", it.id) }
                            findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)

                            // Reset state to prevent re-navigation on configuration changes
                            viewModel.onNavigationDone()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}