package com.ivan.yaskovskyi.fanficapp.model

import com.google.gson.annotations.SerializedName

data class Post(
        @SerializedName("id")
        var id: String,

        @SerializedName("fanfic_image")
        var fImage: String,

        @SerializedName("fanfic_name")
        var fName: String,

        @SerializedName("fanfic_fandom")
        var fFandom: String,

        @SerializedName("fanfic_description")
        var fDescription: String,

        @SerializedName("fanfic_tags")
        var fTags: String,

        @SerializedName("fanfic_date")
        var fDate: String,

        @SerializedName("fanfic_text")
        var fText: String

)