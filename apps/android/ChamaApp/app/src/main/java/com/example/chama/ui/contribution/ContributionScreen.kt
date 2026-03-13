package com.example.chama.ui.contribution

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chama.viewmodel.ContributionViewModel
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.BorderStroke

// ─────────────────────────────────────────────
// Colors matching the ChamaApp design spec
// ─────────────────────────────────────────────
private val Primary       = Color(0xFF1A237E)
private val PrimaryLight  = Color(0xFF3949AB)
private val BgStart       = Color(0xFFE3F2FD)
private val BgEnd         = Color(0xFFF5F5F5)
private val TextSecondary = Color(0xFF666666)
private val TextTertiary  = Color(0xFF888888)
private val Divider       = Color(0xFFE0E0E0)
private val SuccessGreen  = Color(0xFF4CAF50)
private val ErrorRed      = Color(0xFFF44336)
private val CardBg        = Color(0xFFF3F4F9)

// ─────────────────────────────────────────────
// Entry point composable
// ─────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContributionScreen(
    groupId: String   = "1",
    groupName: String = "Kilimani Savings",
    onBack: () -> Unit = {},
    viewModel: ContributionViewModel = viewModel()
) {
    val state by viewModel.state.observeAsState(ContributionViewModel.ContributionState.Idle)

    // Form state
    var amount         by remember { mutableStateOf("5000") }
    var phoneNumber    by remember { mutableStateOf("") }
    var email          by remember { mutableStateOf("") }
    var selectedMethod by remember { mutableStateOf("mpesa") }

    // Error state
    var amountError  by remember { mutableStateOf("") }
    var phoneError   by remember { mutableStateOf("") }
    var emailError   by remember { mutableStateOf("") }

    // Dialogs
    var showLoading  by remember { mutableStateOf(false) }
    var showSuccess  by remember { mutableStateOf(false) }
    var showFailure  by remember { mutableStateOf(false) }
    var failureMsg   by remember { mutableStateOf("") }
    var successRef   by remember { mutableStateOf("") }

    // React to ViewModel state
    LaunchedEffect(state) {
        when (state) {
            is ContributionViewModel.ContributionState.Loading -> {
                showLoading = true
                showSuccess = false
                showFailure = false
            }
            is ContributionViewModel.ContributionState.Success -> {
                val s = state as ContributionViewModel.ContributionState.Success
                successRef  = s.reference ?: ""
                showLoading = false
                showSuccess = true
            }
            is ContributionViewModel.ContributionState.Failure -> {
                val f = state as ContributionViewModel.ContributionState.Failure
                failureMsg  = f.message
                showLoading = false
                showFailure = true
            }
            else -> {
                showLoading = false
            }
        }
    }

    // ── Dialogs ──────────────────────────────────────────────────────────
    if (showLoading) {
        LoadingDialog(selectedMethod = selectedMethod)
    }

    if (showSuccess) {
        SuccessDialog(
            amount    = amount.toDoubleOrNull() ?: 0.0,
            groupName = groupName,
            reference = successRef,
            onDone    = {
                showSuccess = false
                viewModel.resetState()
                onBack()
            }
        )
    }

    if (showFailure) {
        FailureDialog(
            message  = failureMsg,
            onRetry  = {
                showFailure = false
                viewModel.resetState()
            },
            onCancel = {
                showFailure = false
                viewModel.resetState()
                onBack()
            }
        )
    }

    // ── Main screen ──────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(BgStart, Color.White, BgEnd)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            // ── Top bar ──────────────────────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Primary
                    )
                }
                Text(
                    text       = "Make Contribution",
                    fontSize   = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = Primary
                )
            }

            // ── Group name + subtitle ─────────────────────────────
            Text(
                text       = groupName,
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = Primary,
                modifier   = Modifier.padding(top = 8.dp)
            )
            Text(
                text     = "Monthly Contribution",
                fontSize = 14.sp,
                color    = TextSecondary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // ── Form card ─────────────────────────────────────────
            Card(
                modifier  = Modifier.fillMaxWidth(),
                shape     = RoundedCornerShape(20.dp),
                colors    = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    // Amount
                    FieldLabel("Amount (KSh)")
                    OutlinedTextField(
                        value         = amount,
                        onValueChange = {
                            amount = it
                            amountError = ""
                        },
                        modifier      = Modifier.fillMaxWidth(),
                        placeholder   = { Text("Enter amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError       = amountError.isNotEmpty(),
                        supportingText = if (amountError.isNotEmpty()) {
                            { Text(amountError, color = ErrorRed) }
                        } else null,
                        shape         = RoundedCornerShape(14.dp),
                        colors        = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor   = Primary,
                            unfocusedBorderColor = Divider
                        )
                    )

                    Spacer(Modifier.height(20.dp))

                    // Payment method
                    FieldLabel("Payment Method")
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PaymentMethodButton(
                            emoji    = "📱",
                            label    = "M-Pesa",
                            selected = selectedMethod == "mpesa",
                            modifier = Modifier.weight(1f),
                            onClick  = { selectedMethod = "mpesa" }
                        )
                        PaymentMethodButton(
                            emoji    = "📶",
                            label    = "Airtel",
                            selected = selectedMethod == "airtel",
                            modifier = Modifier.weight(1f),
                            onClick  = { selectedMethod = "airtel" }
                        )
                        PaymentMethodButton(
                            emoji    = "💳",
                            label    = "PayPal",
                            selected = selectedMethod == "paypal",
                            modifier = Modifier.weight(1f),
                            onClick  = { selectedMethod = "paypal" }
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    // Phone field (M-Pesa / Airtel)
                    if (selectedMethod == "mpesa" || selectedMethod == "airtel") {
                        val phoneLabel = if (selectedMethod == "mpesa")
                            "Phone Number (STK Push)" else "Airtel Phone Number"

                        FieldLabel(phoneLabel)
                        OutlinedTextField(
                            value         = phoneNumber,
                            onValueChange = {
                                phoneNumber = it
                                phoneError  = ""
                            },
                            modifier      = Modifier.fillMaxWidth(),
                            placeholder   = { Text("254 7XX XXX XXX") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            isError       = phoneError.isNotEmpty(),
                            supportingText = if (phoneError.isNotEmpty()) {
                                { Text(phoneError, color = ErrorRed) }
                            } else null,
                            shape  = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor   = Primary,
                                unfocusedBorderColor = Divider
                            )
                        )
                    }

                    // Email field (PayPal)
                    if (selectedMethod == "paypal") {
                        FieldLabel("PayPal Email")
                        OutlinedTextField(
                            value         = email,
                            onValueChange = {
                                email      = it
                                emailError = ""
                            },
                            modifier      = Modifier.fillMaxWidth(),
                            placeholder   = { Text("your@email.com") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            isError       = emailError.isNotEmpty(),
                            supportingText = if (emailError.isNotEmpty()) {
                                { Text(emailError, color = ErrorRed) }
                            } else null,
                            shape  = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor   = Primary,
                                unfocusedBorderColor = Divider
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Summary card ──────────────────────────────────────
            val amountValue = amount.toDoubleOrNull() ?: 0.0
            val fee   = if (selectedMethod == "paypal" && amountValue > 0) amountValue * 0.029 + 30.0 else 0.0
            val total = amountValue + fee

            Card(
                modifier  = Modifier.fillMaxWidth(),
                shape     = RoundedCornerShape(20.dp),
                colors    = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    SummaryRow("Contribution Amount", formatKsh(amountValue))
                    SummaryRow("Processing Fee", formatKsh(fee))
                    Divider(
                        color    = Divider,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    SummaryRow("Total", formatKsh(total), bold = true)
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── Pay button ────────────────────────────────────────
            val btnLabel = when (selectedMethod) {
                "mpesa"  -> "Pay with M-Pesa"
                "airtel" -> "Pay with Airtel Money"
                "paypal" -> "Pay with PayPal"
                else     -> "Pay Now"
            }

            Button(
                onClick = {
                    // Validate
                    var valid = true
                    if (amountValue <= 0) {
                        amountError = "Please enter a valid amount"
                        valid = false
                    }
                    if ((selectedMethod == "mpesa" || selectedMethod == "airtel") && phoneNumber.isBlank()) {
                        phoneError = "Please enter your phone number"
                        valid = false
                    }
                    if (selectedMethod == "paypal" &&
                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    ) {
                        emailError = "Please enter a valid email"
                        valid = false
                    }
                    if (!valid) return@Button

                    // Submit
                    when (selectedMethod) {
                        "mpesa", "airtel" -> viewModel.contributeViaMobileMoney(
                            groupId     = groupId,
                            amount      = amountValue,
                            phoneNumber = phoneNumber,
                            method      = selectedMethod
                        )
                        "paypal" -> viewModel.contributeViaPaypal(
                            groupId = groupId,
                            amount  = amountValue,
                            email   = email
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape  = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text(
                    text       = btnLabel,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White
                )
            }

            // Helper text
            val helperText = when (selectedMethod) {
                "mpesa"  -> "You will receive an STK push on your phone"
                "airtel" -> "An Airtel Money prompt will be sent to your phone"
                "paypal" -> "You will be redirected to PayPal to complete payment"
                else     -> ""
            }

            Text(
                text      = helperText,
                fontSize  = 12.sp,
                color     = TextTertiary,
                textAlign = TextAlign.Center,
                modifier  = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 32.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────
// Reusable small composables
// ─────────────────────────────────────────────

@Composable
private fun FieldLabel(text: String) {
    Text(
        text       = text,
        fontSize   = 13.sp,
        fontWeight = FontWeight.Medium,
        color      = TextSecondary,
        modifier   = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
private fun PaymentMethodButton(
    emoji: String,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bgColor     = if (selected) Color(0xFFE8EAF6) else Color.White
    val borderColor = if (selected) Primary else Divider
    val textColor   = if (selected) Primary else TextSecondary

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 14.dp, horizontal = 8.dp)
    ) {
        Text(text = emoji, fontSize = 20.sp)
        Spacer(Modifier.height(4.dp))
        Text(
            text       = label,
            fontSize   = 11.sp,
            color      = textColor,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            textAlign  = TextAlign.Center
        )
    }
}

@Composable
private fun SummaryRow(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text       = label,
            fontSize   = 14.sp,
            color      = if (bold) Primary else TextSecondary,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            modifier   = Modifier.weight(1f)
        )
        Text(
            text       = value,
            fontSize   = 14.sp,
            color      = Primary,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.SemiBold
        )
    }
}

// ─────────────────────────────────────────────
// Dialog composables
// ─────────────────────────────────────────────

@Composable
private fun LoadingDialog(selectedMethod: String) {
    val message = when (selectedMethod) {
        "mpesa"  -> "Please enter your M-Pesa PIN on your phone."
        "airtel" -> "Please enter your Airtel Money PIN on your phone."
        else     -> "Connecting to PayPal..."
    }

    Dialog(onDismissRequest = {}) {
        Card(
            shape  = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                CircularProgressIndicator(color = Primary, modifier = Modifier.size(52.dp))
                Spacer(Modifier.height(20.dp))
                Text("Processing Payment", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Primary)
                Spacer(Modifier.height(8.dp))
                Text(message, fontSize = 13.sp, color = TextSecondary, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
private fun SuccessDialog(
    amount: Double,
    groupName: String,
    reference: String,
    onDone: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(
            shape  = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(36.dp))
                        .background(SuccessGreen)
                ) {
                    Text("✓", fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(20.dp))
                Text("Contribution Successful!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Primary)
                Spacer(Modifier.height(8.dp))
                Text(
                    text      = "${formatKsh(amount)} has been contributed to $groupName",
                    fontSize  = 14.sp,
                    color     = TextSecondary,
                    textAlign = TextAlign.Center
                )
                if (reference.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text("Ref: $reference", fontSize = 12.sp, color = TextTertiary)
                }
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick  = onDone,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape    = RoundedCornerShape(16.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("Done", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun FailureDialog(
    message: String,
    onRetry: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(
            shape  = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(36.dp))
                        .background(ErrorRed)
                ) {
                    Text("✕", fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(20.dp))
                Text("Payment Failed", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
                Spacer(Modifier.height(8.dp))
                Text(message, fontSize = 14.sp, color = TextSecondary, textAlign = TextAlign.Center)
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick  = onRetry,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape    = RoundedCornerShape(16.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("Try Again", fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(Modifier.height(10.dp))
                OutlinedButton(
                    onClick  = onCancel,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape    = RoundedCornerShape(16.dp),
                    border   = BorderStroke(1.5.dp, Primary)
                ) {
                    Text("Cancel", fontWeight = FontWeight.SemiBold, color = Primary)
                }
            }
        }
    }
}

// ─────────────────────────────────────────────
// Utility
// ─────────────────────────────────────────────
private fun formatKsh(amount: Double): String {
    val fmt = NumberFormat.getNumberInstance(Locale.US)
    fmt.maximumFractionDigits = 0
    return "KSh ${fmt.format(amount)}"
}