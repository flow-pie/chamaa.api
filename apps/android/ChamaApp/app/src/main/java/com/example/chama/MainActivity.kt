package com.example.chama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chama.network.TokenManager
import com.example.chama.ui.contribution.ContributionScreen
import com.example.chama.ui.theme.ChamaAppTheme

// ── Brand Colors ──────────────────────────────────────────────────────────
private val Primary      = Color(0xFF1A237E)
private val PrimaryLight = Color(0xFF3949AB)
private val BgStart      = Color(0xFFE3F2FD)
private val BgEnd        = Color(0xFFF5F5F5)
private val TextGray     = Color(0xFF888888)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.init(this)
        enableEdgeToEdge()
        setContent {
            ChamaAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController    = navController,
                    startDestination = "welcome"
                ) {
                    // Screen 1 — Welcome
                    composable("welcome") {
                        WelcomeScreen(
                            onGetStarted = { navController.navigate("contribution") },
                            onSignIn     = { navController.navigate("contribution") }
                        )
                    }

                    // Screen 2 — Contribution
                    composable("contribution") {
                        ContributionScreen(
                            groupId   = "1",
                            groupName = "Kilimani Savings",
                            onBack    = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

// ── Welcome Screen ────────────────────────────────────────────────────────
@Composable
fun WelcomeScreen(
    onGetStarted : () -> Unit = {},
    onSignIn     : () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(BgStart, Color.White, BgEnd))
            )
    ) {
        // Top-right decoration circle
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = (-80).dp)
                .clip(CircleShape)
                .background(Primary.copy(alpha = 0.15f))
        )

        // Bottom-left decoration circle
        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-60).dp, y = 60.dp)
                .clip(CircleShape)
                .background(Primary.copy(alpha = 0.10f))
        )

        // Main content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {

            // Logo
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(listOf(Primary, PrimaryLight))
                    )
            ) {
                Icon(
                    imageVector        = Icons.Default.Person,
                    contentDescription = "Logo",
                    tint               = Color.White,
                    modifier           = Modifier.size(70.dp)
                )
            }

            Spacer(Modifier.height(40.dp))

            // Subtitle
            Text(
                text      = "Welcome to Chama App",
                fontSize  = 16.sp,
                color     = TextGray,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            // App name
            Text(
                text       = "Chama App",
                fontSize   = 36.sp,
                fontWeight = FontWeight.Bold,
                color      = Primary,
                textAlign  = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            // Tagline
            Text(
                text      = "Together We Save, Together We Grow",
                fontSize  = 14.sp,
                color     = TextGray,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(60.dp))

            // Get Started button
            Button(
                onClick  = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape  = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                )
            ) {
                Text(
                    text       = "Get Started",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White
                )
            }

            Spacer(Modifier.height(20.dp))

            // Sign In link
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text     = "Already have an account? ",
                    fontSize = 14.sp,
                    color    = TextGray
                )
                TextButton(
                    onClick        = onSignIn,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text       = "Sign In",
                        fontSize   = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color      = Primary
                    )
                }
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    ChamaAppTheme {
        WelcomeScreen()
    }
}