package com.example.bookinghotelapp.ui

import androidx.lifecycle.ViewModel
import com.example.bookinghotelapp.data.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookingViewModel : ViewModel() {
    private val _rooms = MutableStateFlow<List<Room>>(listOf(
        Room(1, "Standard Room", 100.0, listOf("Wifi", "TV"), 10),
        Room(2, "Deluxe Room", 150.0, listOf("Wifi", "TV", "Balcony"), 5),
        Room(3, "Suite", 200.0, listOf("Wifi", "TV", "Balcony", "Mini Bar"), 3),
        Room(4, "Executive Room", 250.0, listOf("Wifi", "TV", "Balcony", "Mini Bar", "Kitchen"), 2),
        Room(5, "Family Room", 180.0, listOf("Wifi", "TV", "Play Area"), 4)
    ))
    val rooms: StateFlow<List<Room>> = _rooms

    private val _selectedRoom = MutableStateFlow<Room?>(null)
    val selectedRoom: StateFlow<Room?> = _selectedRoom

    private val _bookingSummary = MutableStateFlow<String?>(null)
    val bookingSummary: StateFlow<String?> = _bookingSummary

    // Hàm chọn phòng
    fun selectRoom(room: Room) {
        _selectedRoom.value = room
    }

    // Hàm đặt phòng và giảm số lượng phòng
    fun bookRoom(quantity: Int) {
        val room = _selectedRoom.value ?: return
        if (quantity <= room.availableRooms) {
            // Cập nhật thông tin đặt phòng
            _bookingSummary.value = """
                Đặt phòng thành công!
                Loại phòng: ${room.type}
                Số lượng: $quantity
                Tổng giá: ${room.pricePerNight * quantity}
                Thanh toán trong vòng 3 giờ.
            """.trimIndent()

            // Giảm số lượng phòng sau khi đặt
            val updatedRooms = _rooms.value.map {
                if (it.id == room.id) {
                    it.copy(availableRooms = it.availableRooms - quantity)
                } else {
                    it
                }
            }
            _rooms.value = updatedRooms // Cập nhật lại danh sách phòng
        } else {
            // Nếu số lượng đặt lớn hơn số phòng còn lại
            _bookingSummary.value = "Số lượng phòng yêu cầu vượt quá số phòng có sẵn."
        }
    }

    // Hàm xóa thông tin đặt phòng
    fun clearBooking() {
        _selectedRoom.value = null
        _bookingSummary.value = null
    }
}
