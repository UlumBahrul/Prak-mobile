package id.ac.unpas.tokenlistrik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SetoranToken(
    @PrimaryKey val id:String,
    val tanggal:String,
    val nama: String,
    val notoken: String,
    val nominal: String
)
