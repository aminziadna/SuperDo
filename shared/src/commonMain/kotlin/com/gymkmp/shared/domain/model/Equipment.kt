package com.gymkmp.shared.domain.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@Serializable
data class Equipment(
    val id: String,
    val name: String,
    val category: EquipmentCategory,
    val brand: String? = null,
    val model: String? = null,
    val serialNumber: String? = null,
    val purchaseDate: LocalDate? = null,
    val purchasePrice: Double? = null,
    val status: EquipmentStatus,
    val location: String,
    val maintenanceSchedule: MaintenanceSchedule? = null,
    val lastMaintenanceDate: LocalDate? = null,
    val nextMaintenanceDate: LocalDate? = null,
    val notes: String? = null,
    val imageUrl: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

@Serializable
enum class EquipmentCategory {
    CARDIO,
    STRENGTH,
    FREE_WEIGHTS,
    FUNCTIONAL,
    ACCESSORIES,
    OTHER
}

@Serializable
enum class EquipmentStatus {
    AVAILABLE,
    IN_USE,
    OUT_OF_ORDER,
    MAINTENANCE,
    RETIRED
}

@Serializable
data class MaintenanceSchedule(
    val intervalInDays: Int,
    val description: String,
    val assignedTechnician: String? = null
)

@Serializable
data class MaintenanceRecord(
    val id: String,
    val equipmentId: String,
    val date: LocalDate,
    val type: MaintenanceType,
    val description: String,
    val cost: Double? = null,
    val technicianName: String,
    val partsReplaced: List<String> = emptyList(),
    val createdAt: LocalDateTime
)

@Serializable
enum class MaintenanceType {
    ROUTINE,
    REPAIR,
    INSPECTION,
    CLEANING,
    CALIBRATION
}