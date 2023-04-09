package com.example.leanbackpaging.presentation.view.itempresenter

import android.R
import android.content.Context
import android.view.View.GONE
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.leanbackpaging.model.Movie


class MoviesCardViewPresenter : Presenter() {

    private val CARD_WIDTH = 313
    private val CARD_HEIGHT = 176

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        mContext = parent.context

        val cardView = ImageCardView(mContext)

        cardView.mainImageView.apply {
            val posterWidth = CARD_WIDTH
            val posterHeight = CARD_HEIGHT
            layoutParams = BaseCardView.LayoutParams(posterWidth, posterHeight)
        }

        cardView.infoVisibility = GONE

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val movie = item as Movie
        val cardView = viewHolder.view as ImageCardView

//        val res = mContext.resources
//        val width: Int = res.getDimensionPixelSize(com.example.leanbackpaging.R.dimen.poster_width)
//        val height: Int =
//            res.getDimensionPixelSize(com.example.leanbackpaging.R.dimen.poster_height)
//        cardView.setMainImageDimensions(width, height)

        Glide.with(viewHolder.view).load(movie.poster_path).into(cardView.mainImageView)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        // Nothing to unbind for TextView, but if this viewHolder had
        // allocated bitmaps, they can be released here.
        val cardView = viewHolder.view as ImageCardView
        cardView.mainImage = null
    }
}
