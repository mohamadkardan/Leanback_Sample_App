package com.example.leanbackpaging.util

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.util.Log
import androidx.leanback.paging.PagingDataAdapter
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import java.lang.Integer.max
import kotlin.math.ceil


suspend fun PagingDataAdapter<*>.isAdapterNotEmpty(
    notEmpty: (Boolean) -> (Unit)
) {
    this.loadStateFlow.distinctUntilChangedBy {
        it.refresh
    }.filter { it.refresh is LoadState.NotLoading }
        .collectLatest {
            Log.d("distinctUntilChangedBy", "distinctUntilChangedBy")
            if (this.size() > 0) {
                notEmpty.invoke(true)
            } else {
                notEmpty.invoke(false)
            }
        }
}

suspend fun PagingDataAdapter<*>.itemsCount(
    size: (Int) -> (Unit)
) {
    this.loadStateFlow.distinctUntilChangedBy {
        it.refresh
    }.filter { it.refresh is LoadState.NotLoading }
        .collectLatest {
            Log.d("distinctUntilChangedBy", "distinctUntilChangedBy")
            size.invoke(this.size())
        }
}

fun getPageNumber(itemPosition: Int, pageSize: Int = PAGE_SIZE) =
    if (itemPosition == 0)
        1
    else
        ceil(itemPosition.toFloat().div(pageSize.toFloat())).toInt()

fun getItemPositionInPage(itemPosition: Int?) = itemPosition?.mod(PAGE_SIZE) ?: 1

fun getNumberOfPages(numberOfItems: Int, pageSize: Int = PAGE_SIZE) =
    ceil(numberOfItems.toFloat().div(pageSize.toFloat())).toInt()

fun getPagingOffset(
    numberOfVideos: Int,
    numberOfRelatedItems: Int,
    itemPosition: Int
): Int {
    val lastTarget = numberOfVideos - (numberOfRelatedItems / 2)
    return if (itemPosition <= lastTarget) {
        max(0, (itemPosition - (numberOfRelatedItems / 2)))
    } else {
        max(0, ((itemPosition - (numberOfRelatedItems / 2)) - (itemPosition - lastTarget)))
    }
}

/**
 * get offset whit no itemsCount.
 * note that it could returns results less than numberOfRelatedItems in last items
 */
fun getPagingOffsetWithNoEnd(
    numberOfRelatedItems: Int,
    itemPosition: Int
): Int {
    return max(0, (itemPosition - (numberOfRelatedItems / 2)))
}
