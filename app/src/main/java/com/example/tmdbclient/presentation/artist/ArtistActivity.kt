package com.example.tmdbclient.presentation.artist

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
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.movie.MovieAdapter
import com.example.tmdbclient.presentation.movie.MovieViewModel
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ArtistsViewModelFactory
    private lateinit var artistsViewModel: ArtistsViewModel

    private lateinit var adapter: ArtistsAdapter


    private lateinit var binding:ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_artist)
        (application as Injector).createArtistSubComponent()
            .inject(this)

        artistsViewModel = ViewModelProvider(this, factory).get(ArtistsViewModel::class.java)

        initRecyclerView()

    }


    private fun initRecyclerView() {
        binding.artistsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistsAdapter()
        binding.artistsRecyclerView.adapter = adapter
        displayPopularArtistsFunction()
    }

    private fun displayPopularArtistsFunction() {
        binding.artistsProgressBar.visibility = View.VISIBLE
        val responseLiveData = artistsViewModel.getArtists()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistsProgressBar.visibility = View.GONE
            } else {
                binding.artistsProgressBar.visibility = View.GONE
                Toast.makeText(this@ArtistActivity, "No Data Found", Toast.LENGTH_SHORT).show()
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
                updateArtists()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateArtists() {
        binding.artistsProgressBar.visibility = View.VISIBLE
        val response = artistsViewModel.updateArtistsList()
        response.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistsProgressBar.visibility = View.GONE
            } else {
                binding.artistsProgressBar.visibility = View.GONE
            }
        })
    }

}