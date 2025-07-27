package com.gymkmp.managementapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersScreen() {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with search
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Members",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            
            FloatingActionButton(
                onClick = { /* Add new member */ },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Member")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search members...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Members list
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleMembers.filter { 
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.email.contains(searchQuery, ignoreCase = true)
            }) { member ->
                MemberCard(member = member)
            }
        }
    }
}

@Composable
fun MemberCard(member: MemberData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = member.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = member.email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = member.phone,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                AssistChip(
                    onClick = { },
                    label = { Text(member.membershipStatus) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = when (member.membershipStatus) {
                            "Active" -> MaterialTheme.colorScheme.primaryContainer
                            "Expired" -> MaterialTheme.colorScheme.errorContainer
                            "Suspended" -> MaterialTheme.colorScheme.tertiaryContainer
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoChip(
                    icon = Icons.Default.CalendarToday,
                    text = "Joined ${member.joinDate}"
                )
                InfoChip(
                    icon = Icons.Default.FitnessCenter,
                    text = "${member.totalVisits} visits"
                )
                InfoChip(
                    icon = Icons.Default.Schedule,
                    text = "Expires ${member.expiryDate}"
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* View details */ }) {
                    Text("View Details")
                }
                TextButton(onClick = { /* Edit member */ }) {
                    Text("Edit")
                }
                TextButton(
                    onClick = { /* More actions */ },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
            }
        }
    }
}

@Composable
fun InfoChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class MemberData(
    val name: String,
    val email: String,
    val phone: String,
    val membershipStatus: String,
    val joinDate: String,
    val expiryDate: String,
    val totalVisits: Int
)

private val sampleMembers = listOf(
    MemberData(
        name = "John Doe",
        email = "john.doe@email.com",
        phone = "+1 (555) 123-4567",
        membershipStatus = "Active",
        joinDate = "Jan 2024",
        expiryDate = "Jan 2025",
        totalVisits = 45
    ),
    MemberData(
        name = "Jane Smith",
        email = "jane.smith@email.com",
        phone = "+1 (555) 234-5678",
        membershipStatus = "Active",
        joinDate = "Feb 2024",
        expiryDate = "Feb 2025",
        totalVisits = 32
    ),
    MemberData(
        name = "Mike Johnson",
        email = "mike.johnson@email.com",
        phone = "+1 (555) 345-6789",
        membershipStatus = "Expired",
        joinDate = "Dec 2023",
        expiryDate = "Dec 2024",
        totalVisits = 67
    ),
    MemberData(
        name = "Sarah Wilson",
        email = "sarah.wilson@email.com",
        phone = "+1 (555) 456-7890",
        membershipStatus = "Active",
        joinDate = "Mar 2024",
        expiryDate = "Mar 2025",
        totalVisits = 28
    ),
    MemberData(
        name = "Alex Rodriguez",
        email = "alex.rodriguez@email.com",
        phone = "+1 (555) 567-8901",
        membershipStatus = "Suspended",
        joinDate = "Nov 2023",
        expiryDate = "Nov 2024",
        totalVisits = 15
    )
)