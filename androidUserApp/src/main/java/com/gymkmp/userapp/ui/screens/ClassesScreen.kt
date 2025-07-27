package com.gymkmp.userapp.ui.screens

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
fun ClassesScreen(
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Classes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Filter chips
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sampleClasses) { gymClass ->
                ClassCard(
                    gymClass = gymClass,
                    onBookClass = { /* Handle booking */ }
                )
            }
        }
    }
}

@Composable
fun ClassCard(
    gymClass: GymClassData,
    onBookClass: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                        text = gymClass.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "with ${gymClass.trainer}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                AssistChip(
                    onClick = { },
                    label = { Text(gymClass.difficulty) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = when (gymClass.difficulty) {
                            "Beginner" -> MaterialTheme.colorScheme.primaryContainer
                            "Intermediate" -> MaterialTheme.colorScheme.secondaryContainer
                            else -> MaterialTheme.colorScheme.tertiaryContainer
                        }
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Schedule,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = gymClass.time,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = gymClass.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.People,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${gymClass.spotsLeft} spots left",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = gymClass.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (gymClass.price != null) {
                    Text(
                        text = "$${gymClass.price}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Text(
                        text = "Included",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Button(
                    onClick = onBookClass,
                    enabled = gymClass.spotsLeft > 0
                ) {
                    Text(if (gymClass.spotsLeft > 0) "Book Now" else "Full")
                }
            }
        }
    }
}

data class GymClassData(
    val name: String,
    val trainer: String,
    val time: String,
    val location: String,
    val difficulty: String,
    val description: String,
    val spotsLeft: Int,
    val price: Double? = null
)

private val sampleClasses = listOf(
    GymClassData(
        name = "Morning Yoga",
        trainer = "Sarah Wilson",
        time = "8:00 AM - 9:00 AM",
        location = "Studio A",
        difficulty = "Beginner",
        description = "Start your day with gentle stretches and mindfulness",
        spotsLeft = 5
    ),
    GymClassData(
        name = "HIIT Training",
        trainer = "Mike Johnson",
        time = "10:00 AM - 11:00 AM",
        location = "Main Floor",
        difficulty = "Advanced",
        description = "High-intensity interval training for maximum results",
        spotsLeft = 2
    ),
    GymClassData(
        name = "Pilates Core",
        trainer = "Emma Davis",
        time = "2:00 PM - 3:00 PM",
        location = "Studio B",
        difficulty = "Intermediate",
        description = "Strengthen your core with targeted Pilates exercises",
        spotsLeft = 8
    ),
    GymClassData(
        name = "Spin Class",
        trainer = "Alex Rodriguez",
        time = "6:00 PM - 7:00 PM",
        location = "Cycling Studio",
        difficulty = "Intermediate",
        description = "High-energy cycling workout with upbeat music",
        spotsLeft = 0
    )
)