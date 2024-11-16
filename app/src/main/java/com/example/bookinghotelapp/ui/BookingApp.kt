package com.example.bookinghotelapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookinghotelapp.ui.screens.BookingSummaryScreen
import com.example.bookinghotelapp.ui.screens.RoomDetailsScreen
import com.example.bookinghotelapp.ui.screens.RoomListScreen

@Composable
fun BookingApp() {
    val navController = rememberNavController()
    val viewModel = remember { BookingViewModel() }

    NavHost(navController, startDestination = "roomList") {
        composable("roomList") {
            RoomListScreen(viewModel = viewModel, onRoomSelected = {
                viewModel.selectRoom(it)
                navController.navigate("roomDetails")
            })
        }
        composable("roomDetails") {
            RoomDetailsScreen(viewModel = viewModel, onBookingConfirmed = {
                navController.navigate("bookingSummary")
            }, onCancel = {
                navController.popBackStack()
            })
        }
        composable("bookingSummary") {
            BookingSummaryScreen(viewModel = viewModel, onCancel = {
                viewModel.clearBooking()
                navController.navigate("roomList") {
                    popUpTo("roomList") { inclusive = true }
                }
            })
        }
    }
}
