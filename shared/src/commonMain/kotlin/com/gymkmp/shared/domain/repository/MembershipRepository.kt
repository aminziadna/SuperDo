package com.gymkmp.shared.domain.repository

import com.gymkmp.shared.domain.model.Membership
import com.gymkmp.shared.domain.model.MembershipPlan
import com.gymkmp.shared.domain.model.MembershipStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface MembershipRepository {
    suspend fun getAllMemberships(): Flow<List<Membership>>
    suspend fun getMembershipById(id: String): Membership?
    suspend fun getMembershipByUserId(userId: String): Membership?
    suspend fun getMembershipsByStatus(status: MembershipStatus): Flow<List<Membership>>
    suspend fun getExpiringMemberships(beforeDate: LocalDate): Flow<List<Membership>>
    suspend fun createMembership(membership: Membership): Result<Membership>
    suspend fun updateMembership(membership: Membership): Result<Membership>
    suspend fun cancelMembership(id: String): Result<Unit>
    suspend fun renewMembership(id: String, newEndDate: LocalDate): Result<Membership>
    
    // Membership Plans
    suspend fun getAllMembershipPlans(): Flow<List<MembershipPlan>>
    suspend fun getMembershipPlanById(id: String): MembershipPlan?
    suspend fun createMembershipPlan(plan: MembershipPlan): Result<MembershipPlan>
    suspend fun updateMembershipPlan(plan: MembershipPlan): Result<MembershipPlan>
    suspend fun deleteMembershipPlan(id: String): Result<Unit>
}