package com.gymkmp.shared.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@Serializable
data class Membership(
    val id: String,
    val userId: String,
    val planId: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val status: MembershipStatus,
    val paymentStatus: PaymentStatus,
    val autoRenew: Boolean = false,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

@Serializable
enum class MembershipStatus {
    ACTIVE,
    EXPIRED,
    SUSPENDED,
    CANCELLED,
    PENDING
}

@Serializable
enum class PaymentStatus {
    PAID,
    PENDING,
    OVERDUE,
    FAILED
}

@Serializable
data class MembershipPlan(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val currency: String = "USD",
    val durationInDays: Int,
    val features: List<String>,
    val maxClassBookings: Int? = null,
    val personalTrainerSessions: Int? = null,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)