package com.example.bookinghotelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookinghotelapp.ui.BookingViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun BookingSummaryScreen(viewModel: BookingViewModel, onCancel: () -> Unit) {
    val bookingSummary = viewModel.bookingSummary.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tóm tắt đặt phòng", style = MaterialTheme.typography.headlineMedium)
        Text(text = bookingSummary ?: "Chưa có thông tin đặt phòng")

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onCancel) {
            Text("Quay lại")
        }
    }
}
