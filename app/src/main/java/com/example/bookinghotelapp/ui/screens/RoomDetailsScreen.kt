package com.example.bookinghotelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookinghotelapp.ui.BookingViewModel

@Composable
fun RoomDetailsScreen(
    viewModel: BookingViewModel,
    onBookingConfirmed: () -> Unit,
    onCancel: () -> Unit
) {
    val selectedRoom = viewModel.selectedRoom.collectAsState().value
    var quantity by remember { mutableStateOf(1) }

    selectedRoom?.let { room ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Chi tiết phòng",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Loại phòng: ${room.type}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Giá: ${room.pricePerNight} VND / đêm",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Tiện nghi: ${room.amenities.joinToString()}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantity.toString(),
                onValueChange = { quantity = it.toIntOrNull() ?: 1 },
                label = { Text("Số lượng phòng") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        viewModel.bookRoom(quantity)
                        onBookingConfirmed()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Đặt phòng", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Huỷ", fontSize = 16.sp)
                }
            }
        }
    }
}
