package com.gymkmp.managementapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Welcome back, Admin",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Key Metrics
                Text(
                    text = "Key Metrics",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(dashboardMetrics) { metric ->
                        MetricCard(metric = metric)
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(quickActions) { action ->
                        QuickActionCard(action = action)
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Recent Activity",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        recentActivities.forEach { activity ->
                            ActivityItem(activity = activity)
                            if (activity != recentActivities.last()) {
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                            }
                        }
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Alerts & Notifications",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    alerts.forEach { alert ->
                        AlertCard(alert = alert)
                    }
                }
            }
        }
    }
}

@Composable
fun MetricCard(metric: DashboardMetric) {
    Card(
        modifier = Modifier.width(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (metric.type) {
                MetricType.POSITIVE -> MaterialTheme.colorScheme.primaryContainer
                MetricType.WARNING -> MaterialTheme.colorScheme.tertiaryContainer
                MetricType.NEUTRAL -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                metric.icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = metric.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = metric.label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (metric.change != null) {
                Text(
                    text = metric.change,
                    style = MaterialTheme.typography.bodySmall,
                    color = when (metric.type) {
                        MetricType.POSITIVE -> MaterialTheme.colorScheme.primary
                        MetricType.WARNING -> MaterialTheme.colorScheme.tertiary
                        MetricType.NEUTRAL -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}

@Composable
fun QuickActionCard(action: QuickAction) {
    Card(
        onClick = { /* Handle action */ },
        modifier = Modifier.width(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                action.icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = action.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ActivityItem(activity: RecentActivity) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            activity.icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = activity.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = activity.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = activity.time,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun AlertCard(alert: Alert) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when (alert.priority) {
                AlertPriority.HIGH -> MaterialTheme.colorScheme.errorContainer
                AlertPriority.MEDIUM -> MaterialTheme.colorScheme.tertiaryContainer
                AlertPriority.LOW -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                alert.icon,
                contentDescription = null,
                tint = when (alert.priority) {
                    AlertPriority.HIGH -> MaterialTheme.colorScheme.error
                    AlertPriority.MEDIUM -> MaterialTheme.colorScheme.tertiary
                    AlertPriority.LOW -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = alert.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = alert.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class DashboardMetric(
    val label: String,
    val value: String,
    val change: String? = null,
    val type: MetricType,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

enum class MetricType {
    POSITIVE, WARNING, NEUTRAL
}

data class QuickAction(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

data class RecentActivity(
    val title: String,
    val description: String,
    val time: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

data class Alert(
    val title: String,
    val message: String,
    val priority: AlertPriority,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

enum class AlertPriority {
    HIGH, MEDIUM, LOW
}

private val dashboardMetrics = listOf(
    DashboardMetric("Active Members", "234", "+12 this week", MetricType.POSITIVE, Icons.Default.People),
    DashboardMetric("Today's Classes", "8", "2 upcoming", MetricType.NEUTRAL, Icons.Default.FitnessCenter),
    DashboardMetric("Equipment Issues", "3", "needs attention", MetricType.WARNING, Icons.Default.Build),
    DashboardMetric("Revenue (Month)", "$12,450", "+8.5%", MetricType.POSITIVE, Icons.Default.TrendingUp)
)

private val quickActions = listOf(
    QuickAction("Add Member", Icons.Default.PersonAdd),
    QuickAction("Create Class", Icons.Default.Add),
    QuickAction("Equipment Check", Icons.Default.Assignment),
    QuickAction("Send Notice", Icons.Default.Notifications)
)

private val recentActivities = listOf(
    RecentActivity("New member registered", "John Smith joined Premium plan", "2 hours ago", Icons.Default.PersonAdd),
    RecentActivity("Class completed", "Morning Yoga with 15 attendees", "4 hours ago", Icons.Default.CheckCircle),
    RecentActivity("Equipment maintenance", "Treadmill #3 serviced", "1 day ago", Icons.Default.Build),
    RecentActivity("Payment received", "Monthly subscription from Jane Doe", "2 days ago", Icons.Default.Payment)
)

private val alerts = listOf(
    Alert("Equipment Maintenance Due", "3 machines need scheduled maintenance", AlertPriority.HIGH, Icons.Default.Warning),
    Alert("Class Capacity Alert", "HIIT class tomorrow is fully booked", AlertPriority.MEDIUM, Icons.Default.People),
    Alert("Membership Expiring", "15 memberships expire next week", AlertPriority.LOW, Icons.Default.Schedule)
)