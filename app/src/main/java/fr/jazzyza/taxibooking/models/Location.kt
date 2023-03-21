package fr.jazzyza.taxibooking.models

import androidx.room.Entity

@Entity(tableName = "t_currentLocation")
data class Location(val longitude : String, val latitude : String)
