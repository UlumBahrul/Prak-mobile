package id.ac.unpas.tokenlistrik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.tokenlistrik.model.SetoranToken


@Dao
interface SetoranTokenDao {
    @Query("SELECT * FROM SetoranSampah")
    fun loadAll(): LiveData<List<SetoranToken>>
    @Query("SELECT * FROM SetoranSampah WHERE id = :id")
    fun find(id: String): SetoranToken?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: SetoranToken)
    @Delete
    fun delete(item: SetoranToken)
}