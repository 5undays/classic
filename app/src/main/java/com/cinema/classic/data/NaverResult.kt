package com.cinema.classic.data

import com.google.gson.annotations.SerializedName

data class NaverResult(
    @field:SerializedName("lastBuildDate")  val lastBuildDate: String,
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("start") val start: Int,
    @field:SerializedName("display")  val display: Int,
    @field:SerializedName("items")  val items: List<NaverMovie>
)

data class NaverMovie(
    @field:SerializedName("title")  var title:String,
    @field:SerializedName("link")  val link:String,
    @field:SerializedName("image")  val image:String,
    @field:SerializedName("subtitle")  val subtitle:String,
    @field:SerializedName("pubDate")  val pubDate:String,
    @field:SerializedName("director") val director:String,
    @field:SerializedName("actor")  var actor:String,
    @field:SerializedName("userRating")  val userRating:String
)

