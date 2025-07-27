package com.gymkmp.shared.domain.repository

import com.gymkmp.shared.domain.model.User
import com.gymkmp.shared.domain.model.UserRole
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllUsers(): Flow<List<User>>
    suspend fun getUserById(id: String): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUsersByRole(role: UserRole): Flow<List<User>>
    suspend fun createUser(user: User): Result<User>
    suspend fun updateUser(user: User): Result<User>
    suspend fun deleteUser(id: String): Result<Unit>
    suspend fun searchUsers(query: String): Flow<List<User>>
    suspend fun activateUser(id: String): Result<Unit>
    suspend fun deactivateUser(id: String): Result<Unit>
}