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
import com.brunoalmeida.movies.R
import com.brunoalmeida.movies.presentation.movies.MoviesAdapter
import com.brunoalmeida.movies.ui.main.MovieDetailsActivity
import com.brunoalmeida.movies.presentation.movies.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        viewModel.moviesLiveData.observe(this, Observer {
            it?.let { movies ->
                Log.d("log", "pegou o recycle do layout");
                with(recycleMovies) {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(movies) { movie ->
                        val intent =
                            MovieDetailsActivity.getStartIntent(
                                context,
                                movie.title,
                                movie.releaseDate
                            )

                        this@MoviesFragment.startActivity(intent)

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

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): MoviesFragment {
            return MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}