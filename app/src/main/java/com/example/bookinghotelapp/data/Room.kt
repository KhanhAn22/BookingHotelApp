package com.example.bookinghotelapp.data

data class Room(
    val id: Int,
    val type: String,
    val pricePerNight: Double,
    val amenities: List<String>,
    var availableRooms: Int
)
