package com.gymkmp.shared.data.repository

import com.gymkmp.shared.domain.model.User
import com.gymkmp.shared.domain.model.UserRole
import com.gymkmp.shared.domain.model.Address
import com.gymkmp.shared.domain.model.EmergencyContact
import com.gymkmp.shared.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FakeUserRepository : UserRepository {
    private val users = MutableStateFlow(generateSampleUsers())

    override suspend fun getAllUsers(): Flow<List<User>> = users

    override suspend fun getUserById(id: String): User? {
        return users.value.find { it.id == id }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return users.value.find { it.email == email }
    }

    override suspend fun getUsersByRole(role: UserRole): Flow<List<User>> {
        return users.map { userList -> userList.filter { it.role == role } }
    }

    override suspend fun createUser(user: User): Result<User> {
        return try {
            val updatedUsers = users.value + user
            users.value = updatedUsers
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            val updatedUsers = users.value.map { if (it.id == user.id) user else it }
            users.value = updatedUsers
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(id: String): Result<Unit> {
        return try {
            val updatedUsers = users.value.filter { it.id != id }
            users.value = updatedUsers
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchUsers(query: String): Flow<List<User>> {
        return users.map { userList ->
            userList.filter {
                it.firstName.contains(query, ignoreCase = true) ||
                it.lastName.contains(query, ignoreCase = true) ||
                it.email.contains(query, ignoreCase = true)
            }
        }
    }

    override suspend fun activateUser(id: String): Result<Unit> {
        return try {
            val updatedUsers = users.value.map { 
                if (it.id == id) it.copy(isActive = true) else it 
            }
            users.value = updatedUsers
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deactivateUser(id: String): Result<Unit> {
        return try {
            val updatedUsers = users.value.map { 
                if (it.id == id) it.copy(isActive = false) else it 
            }
            users.value = updatedUsers
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun generateSampleUsers(): List<User> {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return listOf(
            User(
                id = "1",
                email = "john.doe@email.com",
                firstName = "John",
                lastName = "Doe",
                phoneNumber = "+1234567890",
                dateOfBirth = LocalDate(1990, 5, 15),
                role = UserRole.MEMBER,
                membershipId = "M001",
                address = Address("123 Main St", "Anytown", "CA", "12345", "USA"),
                emergencyContact = EmergencyContact("Jane Doe", "+1234567891", "Spouse"),
                createdAt = now,
                updatedAt = now
            ),
            User(
                id = "2",
                email = "mike.trainer@gym.com",
                firstName = "Mike",
                lastName = "Johnson",
                phoneNumber = "+1234567892",
                dateOfBirth = LocalDate(1985, 8, 22),
                role = UserRole.TRAINER,
                createdAt = now,
                updatedAt = now
            ),
            User(
                id = "3",
                email = "admin@gym.com",
                firstName = "Sarah",
                lastName = "Wilson",
                phoneNumber = "+1234567893",
                dateOfBirth = LocalDate(1980, 3, 10),
                role = UserRole.ADMIN,
                createdAt = now,
                updatedAt = now
            )
        )
    }
}