package com.ivan.yaskovskyi.fanficapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "postroom", indices = [Index(value = ["fanfic_fandom"], unique = true)])
data class PostRoom(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

//        @ColumnInfo(name = "fanfic_image") var fImage: String,
        @ColumnInfo(name = "fanfic_name") var fName: String,
        @ColumnInfo(name = "fanfic_fandom") var fFandom: String,
        @ColumnInfo(name = "fanfic_description") var fDescription: String,
        @ColumnInfo(name = "fanfic_tags") var fTags: String,
        @ColumnInfo(name = "fanfic_date") var fDate: String,
        @ColumnInfo(name = "fanfic_text") var fText: String,
        @ColumnInfo(name = "fanfic_image", typeAffinity = ColumnInfo.BLOB)
        var fImage: ByteArray
) {

}
