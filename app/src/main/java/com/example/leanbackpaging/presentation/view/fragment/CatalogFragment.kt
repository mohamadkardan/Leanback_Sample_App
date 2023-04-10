package com.example.leanbackpaging.presentation.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.paging.PagingDataAdapter
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.leanbackpaging.model.Movie
import com.example.leanbackpaging.presentation.view.getMoviePagingAdapter
import com.example.leanbackpaging.presentation.view.itempresenter.MoviesCardViewPresenter
import com.example.leanbackpaging.presentation.viewmodel.CatalogViewModel
import com.util.isAdapterNotEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogFragment : BrowseSupportFragment() {

    private lateinit var mRowsAdapter: ArrayObjectAdapter
    private val catalogViewModel: CatalogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElements()
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter
        getPopularTVShows()
    }

    private fun getPopularTVShows() {
        val pagingAdapter = getMoviePagingAdapter(MoviesCardViewPresenter())
        viewLifecycleOwner.lifecycleScope.launch {
            catalogViewModel.getPopularTvShowsWithPaging(1)
                .collectLatest {
                    pagingAdapter.submitData(it)
                }
        }
        addMovieRow(pagingAdapter)
    }

    private fun addMovieRow(pagingAdapter: PagingDataAdapter<Movie>) {
        viewLifecycleOwner.lifecycleScope.launch {
            val headerItem = HeaderItem("Popular TV Shows")
            val listRow = ListRow(headerItem, pagingAdapter)
            mRowsAdapter.add(listRow)
        }
    }

    private fun setupUIElements() {
        title = "NetBox"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
    }


    private fun buildRowsAdapter(moviesList: List<Movie>) {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val listRowAdapter = ArrayObjectAdapter(MoviesCardViewPresenter()).apply {
            for (movie in moviesList) {
                add(movie)
            }
        }
        val headerItem = HeaderItem("Popular Shows")
        mRowsAdapter.add(ListRow(headerItem, listRowAdapter))
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
