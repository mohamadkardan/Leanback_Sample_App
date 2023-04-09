package com.example.leanbackpaging.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.ObjectAdapter
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.leanbackpaging.R
import com.example.leanbackpaging.adapter.MoviesListAdapter
import com.example.leanbackpaging.model.Movie
import com.example.leanbackpaging.presentation.view.getMoviePagingAdapter
import com.example.leanbackpaging.presentation.view.itempresenter.MoviesCardViewPresenter
import com.example.leanbackpaging.presentation.viewmodel.CatalogViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogFragment : BrowseSupportFragment() {

    val catalogViewModel: CatalogViewModel by viewModels()
    var mRowsAdapter: ArrayObjectAdapter? = null
    val videoPagingAdapter = getMoviePagingAdapter(MoviesCardViewPresenter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        buildRowsAdapter(getMoviesList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElements()
    }

    private fun setupUIElements() {
        title = "NetBox"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
    }

//    private fun getPopularTvShows(pageNumber: Int) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            catalogViewModel.getPopularTvShows(pageNumber).collectLatest {
//                val moviePagingAdapter = getMoviePagingAdapter(MoviesCardViewPresenter())
//                catalogViewModel.getPopularTvShows(pageNumber)
//                    .collectLatest {
//                        moviePagingAdapter.submitData(it)
//                    }
//            }
//        }
//
//        val header = HeaderItem(i.toLong(), playLists[i].name ?: "")
//        val row = ListRow(header, videoPagingAdapter)
//        mRowsAdapter.add(row)
//
//    }

    private fun loadInitialRows(
        moviesList: List<Movie>?
    ) {

        if (moviesList.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "error happened", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val videoPagingAdapter = getMoviePagingAdapter(MoviesCardViewPresenter())

            catalogViewModel.getPopularTvShows(1)
                .collectLatest {
                    videoPagingAdapter.submitData(it)
                }
            }

            val header = HeaderItem("Popular Shows")
            val row = ListRow(header, videoPagingAdapter)
            mRowsAdapter?.add(row)

        // Add other playlist header (data will add on scroll)
//        for (i in maxFirstLoadingRows until playLists.size) {
//            val header = HeaderItem(i.toLong(), playLists[i].name ?: "")
//            val row = ListRow(header, ArrayObjectAdapter())
//            mRowsAdapter.add(row)
//        }

        adapter = mRowsAdapter
    }


    private fun buildRowsAdapter(moviesList: List<Movie>) {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val listRowAdapter = ArrayObjectAdapter(MoviesCardViewPresenter()).apply {
            for (movie in moviesList) {
                add(movie)
            }
        }
        val headerItem = HeaderItem("Popular Shows")
        mRowsAdapter?.add(ListRow(headerItem, listRowAdapter))
        this.adapter = mRowsAdapter
    }

    private fun getMoviesList(): List<Movie> {
        val list = mutableListOf<Movie>()
        list.add(
            Movie(
                "https://imageio.forbes.com/specials-images/imageserve/62f6441df08699d4509de20f/0x0.jpg?format=jpg&width=1200",
                1,
                "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
                "Pretty Little Liars"
            )
        )
        list.add(
            Movie(
                "https://www.whats-on-netflix.com/wp-content/uploads/2022/11/goosebumps-new-on-netflix-november-11th-2022-jpg.webp",
                2,
                "/8SAQqivlp74MZ7u55ccR1xa0Nby.jpg",
                "Suits"
            )
        )
        list.add(
            Movie(
                "https://en.janbharattimes.com/wp-content/uploads/2023/02/upcoming-sout-indian-movies.jpg",
                3,
                "/v8Y9yurHuI7MujWQMd8iL3Gy4B5.jpg",
                "Mr. Robot"
            )
        )
        return list
    }
}