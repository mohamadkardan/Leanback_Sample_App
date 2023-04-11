package com.example.leanbackpaging.presentation.view.itempresenter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.leanback.widget.Presenter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.leanbackpaging.R
import com.example.leanbackpaging.model.Movie


class MoviesCardViewPresenter : Presenter() {

    private val CARD_WIDTH = 400
    private val CARD_HEIGHT = 200

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        mContext = parent.context

        val viewHolder = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false)
//
//        val cardView = CustomImageCardView(mContext)
//
//        cardView.mainImageView.apply {
//            val posterWidth = CARD_WIDTH
//            val posterHeight = CARD_HEIGHT
//            layoutParams = BaseCardView.LayoutParams(posterWidth, posterHeight)
//        }
//
//        // Set a custom focus highlight drawable
//        val res: Resources = mContext.getResources()
//        val resId: Int = R.drawable.lb_card_foreground
//        val drawable: Drawable = res.getDrawable(resId)
//        cardView.infoAreaBackground = drawable
//
//        val color = res.getColor(R.color.purple_500)
//        val thickness = res.getDimensionPixelSize(R.dimen.lb_basic_card_selected_overlay_thickness)
//        cardView.isMotionEventSplittingEnabled = false
//        cardView.isActivated = false
//        cardView.isSelected = false
//        cardView.setBorderColor(color)
//        cardView.setBorderWidth(thickness)
//
//
//        cardView.infoVisibility = GONE
//        cardView.isFocusable = true
//        cardView.isFocusableInTouchMode = true
//        cardView.setMainImageAdjustViewBounds(false)
        return CustomViewHolder(viewHolder)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
//        val cardView = viewHolder.view as ImageCardView
//
//        if (item != null) {
//            val movie = item as Movie
//            cardView.mainImageView.scaleType = ImageView.ScaleType.CENTER_CROP
//
//            Glide.with(viewHolder.view)
//                .load(movie.poster)
//                .placeholder(com.example.leanbackpaging.R.drawable.placeholder)
//                .into(cardView.mainImageView)
//
//            setOnItemClicked(cardView, movie)
//        }
        val customViewHolder: CustomViewHolder = viewHolder as CustomViewHolder
        customViewHolder.cardView.isFocusable = true
        customViewHolder.cardView.isFocusableInTouchMode = true

        if (item != null) {
            val movie = item as Movie
            customViewHolder.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(viewHolder.view)
                .load(movie.poster)
                .placeholder(R.drawable.placeholder)
                .into(customViewHolder.imageView)

            setOnItemClicked(customViewHolder.cardView, movie)
//            setOnItemSelected(customViewHolder)
        }
    }

    private fun setOnItemClicked(view: CardView, item: Movie?) {
        view.setOnClickListener {
            Log.d("TEST", "setOnItemClicked: ${item?.poster}")
        }
    }

    private fun setOnItemSelected(customViewHolder: CustomViewHolder) {
        customViewHolder.cardView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                v.setBackgroundColor(mContext.getColor(R.color.white))

            } else {
                v.setBackgroundColor(mContext.getColor(R.color.lb_basic_card_selected_overlay_color))
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val customViewHolder: CustomViewHolder = viewHolder as CustomViewHolder
        customViewHolder.imageView.background = null
    }

    class CustomViewHolder(view: View) : ViewHolder(view) {
        // your UI elements here
        var cardView: CardView
        var imageView: ImageView

        init {
            // find and initialize your UI elements here
            cardView = view.findViewById(R.id.cardView_movie_item)
            imageView = view.findViewById(R.id.imageView_movie_item)

        }
    }

}
