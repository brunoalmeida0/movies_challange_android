package com.brunoalmeida.movies.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brunoalmeida.movies.R
import kotlinx.android.synthetic.main.fragment_details.*



/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        bookDetailsTitle.text = getStringExtra(EXTRA_TITLE)
//        bookDetailsDescription.text =

    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, title: String, description: String): Intent {
            return Intent(context, DetailsFragment::class.java).apply {
                val bundle = Bundle()
                bundle.putString(EXTRA_TITLE, title)
                bundle.putString(EXTRA_DESCRIPTION, description)

//                DetailsFragment.setArguments(bundle)
            }
        }
    }

}
