package com.fallingwords.home.model.datamodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.fallingwords.utils.FallingWordsAppConstants.DEFAULT_INT_VALUE
import com.fallingwords.utils.FallingWordsAppConstants.EMPTY_STRING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/** This data class is used to store the word and its translation in the database
 * as well as for parsing the word JSON data from the API **/

@Entity(tableName = "Words")
@JsonClass(generateAdapter = true)
@Parcelize
data class Word(
    /** This variable is used as a primary key to store the id to differentiate different words and is auto-generated  **/
    @PrimaryKey(autoGenerate = true)
    var id: Int = DEFAULT_INT_VALUE,
    /** This variable is used to store the word in english language **/
    @ColumnInfo(name = "english")
    @Json(name = "text_eng")
    var englishText: String = EMPTY_STRING,
    /** This variable is used to store the word in spanish language **/
    @ColumnInfo(name = "spanish")
    @Json(name = "text_spa")
    var spanishText: String = EMPTY_STRING,
    @Ignore
    var isCorrectTranslation: Boolean = true
) : Parcelable