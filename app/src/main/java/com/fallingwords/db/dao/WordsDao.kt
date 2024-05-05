package com.fallingwords.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fallingwords.home.model.datamodel.Word

/** This interface allows to perform database queries using functions for WordsDao **/
@Dao
interface WordsDao {

    /** This function performs the query of fetching all the words from database **/
    @Query("SELECT * FROM Words")
    suspend fun fetchWords(): List<Word>?

    /** This function performs the query of inserting and replacing all the words to the database **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<Word>?)

}