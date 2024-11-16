package com.example.bookinghotelapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bookinghotelapp.data.Room
import com.example.bookinghotelapp.ui.BookingViewModel

@Composable
fun RoomListScreen(viewModel: BookingViewModel, onRoomSelected: (Room) -> Unit) {
    // Collect the state from the ViewModel using collectAsState()
    val rooms = viewModel.rooms.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Danh sách phòng",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        rooms.forEach { room ->
            RoomItem(room = room, onRoomSelected = { onRoomSelected(room) })
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun RoomItem(room: Room, onRoomSelected: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRoomSelected() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = room.type,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Giá: ${room.pricePerNight} VND / đêm",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Số phòng trống: ${room.availableRooms}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
