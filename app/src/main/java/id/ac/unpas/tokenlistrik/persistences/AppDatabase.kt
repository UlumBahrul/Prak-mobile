package id.ac.unpas.tokenlistrik.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.tokenlistrik.model.SetoranToken


@Database(entities = [SetoranToken::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun setoranTokenDao(): SetoranTokenDao
}