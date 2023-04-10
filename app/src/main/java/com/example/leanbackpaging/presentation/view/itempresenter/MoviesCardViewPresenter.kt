package com.example.leanbackpaging.presentation.view.itempresenter

import android.R
import android.content.Context
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.leanbackpaging.model.Movie


class MoviesCardViewPresenter : Presenter() {

    private val CARD_WIDTH = 400
    private val CARD_HEIGHT = 200

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
        cardView.setMainImageAdjustViewBounds(false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val cardView = viewHolder.view as ImageCardView

        if (item != null) {

            val movie = item as Movie
            cardView.mainImageView.scaleType = ImageView.ScaleType.CENTER_CROP

            Glide.with(viewHolder.view).load("https://image.tmdb.org/t/p/w780" + movie.poster_path)
                .into(cardView.mainImageView)

        }

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        // Nothing to unbind for TextView, but if this viewHolder had
        // allocated bitmaps, they can be released here.
        val cardView = viewHolder.view as ImageCardView
        cardView.mainImage = null
    }
}
