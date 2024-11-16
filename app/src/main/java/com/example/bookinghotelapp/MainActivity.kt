package com.example.bookinghotelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.bookinghotelapp.ui.BookingApp
import com.example.bookinghotelapp.ui.theme.BookingHotelAppTheme
import androidx.compose.foundation.layout.fillMaxSize


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookingHotelAppTheme {
                // Khởi tạo màn hình ứng dụng
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    // Hiển thị ứng dụng Booking
                    BookingApp()
                }
            }
        }
    }
}
