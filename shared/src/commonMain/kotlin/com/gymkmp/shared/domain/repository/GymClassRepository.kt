package com.gymkmp.shared.domain.repository

import com.gymkmp.shared.domain.model.GymClass
import com.gymkmp.shared.domain.model.ClassBooking
import com.gymkmp.shared.domain.model.BookingStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface GymClassRepository {
    // Gym Classes
    suspend fun getAllClasses(): Flow<List<GymClass>>
    suspend fun getClassById(id: String): GymClass?
    suspend fun getClassesByTrainer(trainerId: String): Flow<List<GymClass>>
    suspend fun getClassesByDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<GymClass>>
    suspend fun getUpcomingClasses(): Flow<List<GymClass>>
    suspend fun createClass(gymClass: GymClass): Result<GymClass>
    suspend fun updateClass(gymClass: GymClass): Result<GymClass>
    suspend fun deleteClass(id: String): Result<Unit>
    suspend fun searchClasses(query: String): Flow<List<GymClass>>
    
    // Class Bookings
    suspend fun getAllBookings(): Flow<List<ClassBooking>>
    suspend fun getBookingById(id: String): ClassBooking?
    suspend fun getBookingsByUser(userId: String): Flow<List<ClassBooking>>
    suspend fun getBookingsByClass(classId: String): Flow<List<ClassBooking>>
    suspend fun getBookingsByStatus(status: BookingStatus): Flow<List<ClassBooking>>
    suspend fun createBooking(booking: ClassBooking): Result<ClassBooking>
    suspend fun updateBooking(booking: ClassBooking): Result<ClassBooking>
    suspend fun cancelBooking(id: String): Result<Unit>
    suspend fun checkInUser(bookingId: String): Result<ClassBooking>
    suspend fun getAvailableSpots(classId: String): Int
}