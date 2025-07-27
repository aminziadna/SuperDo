import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.startKoin
import com.gymkmp.shared.di.sharedModule

fun main() {
    startKoin {
        modules(sharedModule)
    }
    
    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        GymWebApp()
    }
}

@Composable
fun GymWebApp() {
    Div(
        attrs = {
            style {
                padding(20.px)
                fontFamily("Arial, sans-serif")
            }
        }
    ) {
        H1(
            attrs = {
                style {
                    color(Color("#1976D2"))
                    marginBottom(20.px)
                }
            }
        ) {
            Text("🏋️ Gym Management System - Web Dashboard")
        }
        
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Grid)
                    gridTemplateColumns("repeat(auto-fit, minmax(300px, 1fr))")
                    gap(20.px)
                    marginTop(30.px)
                }
            }
        ) {
            DashboardCard(
                title = "Members",
                description = "Manage gym members and memberships",
                icon = "👥"
            )
            
            DashboardCard(
                title = "Classes",
                description = "Schedule and manage fitness classes",
                icon = "🏃‍♀️"
            )
            
            DashboardCard(
                title = "Equipment",
                description = "Track and maintain gym equipment",
                icon = "🏋️‍♂️"
            )
            
            DashboardCard(
                title = "Reports",
                description = "View analytics and generate reports",
                icon = "📊"
            )
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    description: String,
    icon: String
) {
    Div(
        attrs = {
            style {
                backgroundColor(Color.white)
                border(1.px, LineStyle.Solid, Color("#e0e0e0"))
                borderRadius(8.px)
                padding(20.px)
                boxShadow("0 2px 4px rgba(0,0,0,0.1)")
                cursor("pointer")
                transition("transform 0.2s ease")
            }
            onMouseEnter { 
                it.target.style.transform = "translateY(-2px)"
            }
            onMouseLeave { 
                it.target.style.transform = "translateY(0)"
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    fontSize(32.px)
                    marginBottom(10.px)
                }
            }
        ) {
            Text(icon)
        }
        
        H3(
            attrs = {
                style {
                    color(Color("#1976D2"))
                    marginBottom(8.px)
                    fontSize(18.px)
                }
            }
        ) {
            Text(title)
        }
        
        P(
            attrs = {
                style {
                    color(Color("#666"))
                    fontSize(14.px)
                    lineHeight(1.4)
                    margin(0.px)
                }
            }
        ) {
            Text(description)
        }
    }
}

object AppStyleSheet : StyleSheet() {
    init {
        "body" style {
            margin(0.px)
            padding(0.px)
            backgroundColor(Color("#f5f5f5"))
            fontFamily("Arial, sans-serif")
        }
        
        "#root" style {
            minHeight(100.vh)
        }
    }
}