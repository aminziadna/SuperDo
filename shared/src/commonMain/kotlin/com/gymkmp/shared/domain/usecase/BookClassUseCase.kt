package com.gymkmp.shared.domain.usecase

import com.gymkmp.shared.domain.model.ClassBooking
import com.gymkmp.shared.domain.model.BookingStatus
import com.gymkmp.shared.domain.repository.GymClassRepository
import com.gymkmp.shared.domain.repository.MembershipRepository
import com.benasher44.uuid.uuid4
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class BookClassUseCase(
    private val gymClassRepository: GymClassRepository,
    private val membershipRepository: MembershipRepository
) {
    suspend operator fun invoke(userId: String, classId: String): Result<ClassBooking> {
        return try {
            // Check if user has active membership
            val membership = membershipRepository.getMembershipByUserId(userId)
            if (membership == null || membership.status != com.gymkmp.shared.domain.model.MembershipStatus.ACTIVE) {
                return Result.failure(Exception("User does not have an active membership"))
            }

            // Check if class exists and is active
            val gymClass = gymClassRepository.getClassById(classId)
            if (gymClass == null || !gymClass.isActive) {
                return Result.failure(Exception("Class not found or not active"))
            }

            // Check if class is in the future
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            if (gymClass.startTime <= now) {
                return Result.failure(Exception("Cannot book past or ongoing classes"))
            }

            // Check available spots
            val availableSpots = gymClassRepository.getAvailableSpots(classId)
            val bookingStatus = if (availableSpots > 0) {
                BookingStatus.CONFIRMED
            } else {
                BookingStatus.WAITLISTED
            }

            // Create booking
            val booking = ClassBooking(
                id = uuid4().toString(),
                userId = userId,
                classId = classId,
                bookingTime = now,
                status = bookingStatus,
                createdAt = now
            )

            gymClassRepository.createBooking(booking)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}