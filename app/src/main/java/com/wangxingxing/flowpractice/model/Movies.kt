package com.wangxingxing.flowpractice.model

import com.google.gson.annotations.SerializedName

/**
 * author : 王星星
 * date : 2021/7/23 14:59
 * email : 1099420259@qq.com
 * description :
 */
data class Movies(
    @SerializedName("subjects")
    val movieList: List<Movie>,

    @SerializedName("has_more")
    val hasMore: Boolean
)
