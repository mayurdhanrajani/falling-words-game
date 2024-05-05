package com.fallingwords.db.app_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fallingwords.db.utils.DatabaseConstants.DATA_BASE_VERSION
import com.fallingwords.db.dao.WordsDao
import com.fallingwords.home.model.datamodel.Word

/** This class creates the Room Database used by the app **/
@Database(entities = [Word::class], version = DATA_BASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    /** This function provides the instance of WordsDao **/
    abstract fun wordsDao(): WordsDao

}