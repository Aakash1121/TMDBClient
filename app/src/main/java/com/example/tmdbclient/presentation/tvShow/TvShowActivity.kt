package com.example.tmdbclient.presentation.tvShow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityArtistBinding
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.movie.MovieAdapter
import com.example.tmdbclient.presentation.movie.MovieViewModel
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TvShowsViewModelFactory
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var adapter: TvShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_tv_show)
        (application as Injector).createTvShowsSubComponent()
            .inject(this)

        tvShowsViewModel = ViewModelProvider(this, factory).get(TvShowsViewModel::class.java)
        initRecyclerView()

    }


    private fun initRecyclerView() {
        binding.tvShowsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvShowsAdapter()
        binding.tvShowsRecyclerView.adapter = adapter
        displayPopularTvShowsFunction()
    }

    private fun displayPopularTvShowsFunction() {
        binding.tvShowsProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvShowsViewModel.getTvShows()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowsProgressBar.visibility = View.GONE
            } else {
                binding.tvShowsProgressBar.visibility = View.GONE
                Toast.makeText(this@TvShowActivity, "No Data Found", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateTvShows()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateTvShows() {
        binding.tvShowsProgressBar.visibility = View.VISIBLE
        val response = tvShowsViewModel.updateTvShows()
        response.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowsProgressBar.visibility = View.GONE
            } else {
                binding.tvShowsProgressBar.visibility = View.GONE
            }
        })
    }

}