package com.gymkmp.shared.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@Serializable
data class User(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val dateOfBirth: LocalDate,
    val role: UserRole,
    val membershipId: String? = null,
    val profileImageUrl: String? = null,
    val address: Address? = null,
    val emergencyContact: EmergencyContact? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val isActive: Boolean = true
)

@Serializable
enum class UserRole {
    MEMBER,
    TRAINER,
    ADMIN,
    MANAGER
}

@Serializable
data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String
)

@Serializable
data class EmergencyContact(
    val name: String,
    val phoneNumber: String,
    val relationship: String
)