package fr.epf.min1.deryne.myvelib;

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class StationVelib(
    @PrimaryKey val station_id:Long,
    val name:String,
    val lat: Double,
    val lon:Double,
    val capacity:Int,
    val nbrVelosDispo :Int,
    val nbrDockDispo:Int,
)//: Serializable




