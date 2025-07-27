package com.gymkmp.shared.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDateTime

@Serializable
data class GymClass(
    val id: String,
    val name: String,
    val description: String,
    val trainerId: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val maxCapacity: Int,
    val currentBookings: Int = 0,
    val location: String,
    val difficulty: DifficultyLevel,
    val equipment: List<String> = emptyList(),
    val price: Double? = null,
    val isRecurring: Boolean = false,
    val recurrencePattern: RecurrencePattern? = null,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

@Serializable
enum class DifficultyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED,
    ALL_LEVELS
}

@Serializable
data class RecurrencePattern(
    val type: RecurrenceType,
    val interval: Int = 1,
    val daysOfWeek: List<Int> = emptyList(), // 1-7 (Monday-Sunday)
    val endDate: LocalDateTime? = null
)

@Serializable
enum class RecurrenceType {
    DAILY,
    WEEKLY,
    MONTHLY
}

@Serializable
data class ClassBooking(
    val id: String,
    val userId: String,
    val classId: String,
    val bookingTime: LocalDateTime,
    val status: BookingStatus,
    val checkedIn: Boolean = false,
    val checkedInAt: LocalDateTime? = null,
    val createdAt: LocalDateTime
)

@Serializable
enum class BookingStatus {
    CONFIRMED,
    WAITLISTED,
    CANCELLED,
    NO_SHOW
}