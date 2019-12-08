package com.brunoalmeida.movies.presentation.movies.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.brunoalmeida.movies.R
import com.brunoalmeida.movies.presentation.movies.MoviesAdapter
import com.brunoalmeida.movies.ui.main.MovieDetailsActivity
import com.brunoalmeida.movies.presentation.movies.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        viewModel.moviesLiveData.observe(this, Observer {
            it?.let { movies ->
                with(recycleMovies) {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    addOnScrollListener(object : OnScrollListener() {
//                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                            super.onScrollStateChanged(recyclerView, newState)
//                            val totalItemCount = recyclerView.layoutManager!!.itemCount
//                            if (!imageRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
//                                requestPhoto()
//                            }
//                        }
                    })

                    adapter = MoviesAdapter(movies) { movie ->
                        val intent =
                            MovieDetailsActivity.getStartIntent(
                                context,
                                movie
                            )

                        this@MoviesFragment.startActivity(intent)
//                        buttonSearchMovie.setOnClickListener{
//                            Log.e("TESTE", editText.text.toString())
//                        }

                    }
                }
            }
        })
        viewModel.getMovies()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}