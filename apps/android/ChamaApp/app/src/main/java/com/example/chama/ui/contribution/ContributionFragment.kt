package com.example.chama.ui.contribution

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chama.viewmodel.ContributionViewModel
import java.text.NumberFormat
import java.util.Locale

class ContributionFragment : Fragment() {

    private val viewModel: ContributionViewModel by viewModels()

    // Group info — set these before navigating to this fragment
    var groupId: String = "1"
    var groupName: String = "Kilimani Savings"

    // Selected payment method
    private var selectedMethod = "mpesa"

    // Views we need to reference
    private lateinit var etAmount: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var tvPhoneLabel: TextView
    private lateinit var tvEmailLabel: TextView
    private lateinit var tvSummaryAmount: TextView
    private lateinit var tvSummaryFee: TextView
    private lateinit var tvSummaryTotal: TextView
    private lateinit var btnPay: Button
    private lateinit var tvHelper: TextView
    private lateinit var btnMpesa: LinearLayout
    private lateinit var btnAirtel: LinearLayout
    private lateinit var btnPaypal: LinearLayout

    // Colors
    private val colorPrimary  = Color.parseColor("#1A237E")
    private val colorLight    = Color.parseColor("#E3F2FD")
    private val colorDivider  = Color.parseColor("#E0E0E0")
    private val colorSecondary = Color.parseColor("#666666")
    private val colorTertiary  = Color.parseColor("#888888")
    private val colorSuccess   = Color.parseColor("#4CAF50")
    private val colorError     = Color.parseColor("#F44336")
    private val colorWhite     = Color.WHITE

    // ---------------------------------------------------------------
    // Build UI programmatically (matching your project style)
    // ---------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Root scroll + container
        val scroll = ScrollView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(colorLight)
        }

        val root = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        scroll.addView(root)

        // ---------- Back button + title row ----------
        val topRow = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val btnBack = Button(requireContext()).apply {
            text = "←"
            textSize = 18f
            setTextColor(colorPrimary)
            setBackgroundColor(Color.TRANSPARENT)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        val tvTitle = TextView(requireContext()).apply {
            text = "Make Contribution"
            textSize = 17f
            setTypeface(null, Typeface.BOLD)
            setTextColor(colorPrimary)
            setPadding(16, 0, 0, 0)
        }

        topRow.addView(btnBack)
        topRow.addView(tvTitle)
        root.addView(topRow)

        // ---------- Group name ----------
        root.addView(TextView(requireContext()).apply {
            text = groupName
            textSize = 24f
            setTypeface(null, Typeface.BOLD)
            setTextColor(colorPrimary)
            setPadding(0, 24, 0, 4)
        })

        root.addView(TextView(requireContext()).apply {
            text = "Monthly Contribution"
            textSize = 14f
            setTextColor(colorSecondary)
            setPadding(0, 0, 0, 24)
        })

        // ---------- Amount field ----------
        root.addView(makeLabel("Amount (KSh)"))
        etAmount = makeEditText("Enter amount", InputType.TYPE_CLASS_NUMBER).also {
            it.setText("5000")
            it.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    updateSummary(s?.toString()?.toDoubleOrNull() ?: 0.0)
                }
            })
        }
        root.addView(etAmount)

        // ---------- Payment method label ----------
        root.addView(makeLabel("Payment Method"))

        // ---------- Payment method row ----------
        val methodRow = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            weightSum = 3f
            setPadding(0, 8, 0, 8)
        }

        btnMpesa  = makeMethodButton("M-Pesa",  selected = true)
        btnAirtel = makeMethodButton("Airtel Money", selected = false)
        btnPaypal = makeMethodButton("PayPal", selected = false)

        btnMpesa.setOnClickListener  { selectMethod("mpesa") }
        btnAirtel.setOnClickListener { selectMethod("airtel") }
        btnPaypal.setOnClickListener { selectMethod("paypal") }

        val p = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            .also { it.setMargins(4, 0, 4, 0) }

        methodRow.addView(btnMpesa,  p)
        methodRow.addView(btnAirtel, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).also { it.setMargins(4, 0, 4, 0) })
        methodRow.addView(btnPaypal, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).also { it.setMargins(4, 0, 4, 0) })

        root.addView(methodRow)

        // ---------- Phone number field ----------
        tvPhoneLabel = makeLabel("Phone Number (STK Push)")
        root.addView(tvPhoneLabel)
        etPhone = makeEditText("254 7XX XXX XXX", InputType.TYPE_CLASS_PHONE)
        root.addView(etPhone)

        // ---------- Email field (PayPal, hidden by default) ----------
        tvEmailLabel = makeLabel("PayPal Email")
        tvEmailLabel.visibility = View.GONE
        root.addView(tvEmailLabel)

        etEmail = makeEditText("your@email.com", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        etEmail.visibility = View.GONE
        root.addView(etEmail)

        // ---------- Summary card ----------
        root.addView(makeDivider())

        root.addView(makeLabel("Summary"))

        val summaryCard = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(colorWhite)
            setPadding(32, 24, 32, 24)
        }

        tvSummaryAmount = makeSummaryRow(summaryCard, "Contribution Amount", "KSh 5,000")
        tvSummaryFee    = makeSummaryRow(summaryCard, "Processing Fee", "KSh 0")
        summaryCard.addView(makeDivider())
        tvSummaryTotal  = makeSummaryRow(summaryCard, "Total", "KSh 5,000", bold = true)

        root.addView(summaryCard)

        // ---------- Pay button ----------
        root.addView(makeDivider())

        btnPay = Button(requireContext()).apply {
            text = "Pay with M-Pesa"
            textSize = 15f
            setTypeface(null, Typeface.BOLD)
            setTextColor(colorWhite)
            setBackgroundColor(colorPrimary)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                160
            ).also { it.setMargins(0, 16, 0, 8) }
            setOnClickListener { submitContribution() }
        }
        root.addView(btnPay)

        // ---------- Helper text ----------
        tvHelper = TextView(requireContext()).apply {
            text = "You will receive an STK push on your phone"
            textSize = 12f
            setTextColor(colorTertiary)
            gravity = Gravity.CENTER
            setPadding(0, 4, 0, 0)
        }
        root.addView(tvHelper)

        return scroll
    }

    // ---------------------------------------------------------------
    // onViewCreated — observe ViewModel
    // ---------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ContributionViewModel.ContributionState.Idle -> { /* nothing */ }

                is ContributionViewModel.ContributionState.Loading -> {
                    showLoadingDialog()
                }

                is ContributionViewModel.ContributionState.Success -> {
                    dismissAllDialogs()
                    showSuccessDialog(state.amount, state.groupName, state.reference)
                }

                is ContributionViewModel.ContributionState.Failure -> {
                    dismissAllDialogs()
                    showFailureDialog(state.message)
                }
            }
        }
    }

    // ---------------------------------------------------------------
    // Payment method selection
    // ---------------------------------------------------------------

    private fun selectMethod(method: String) {
        selectedMethod = method

        // Reset all
        listOf(btnMpesa, btnAirtel, btnPaypal).forEach { btn ->
            btn.setBackgroundColor(colorWhite)
            btn.setPadding(8, 16, 8, 16)
            (btn.getChildAt(1) as? TextView)?.setTextColor(colorSecondary)
        }

        // Highlight selected
        val selectedBtn = when (method) {
            "mpesa"  -> btnMpesa
            "airtel" -> btnAirtel
            "paypal" -> btnPaypal
            else     -> btnMpesa
        }
        selectedBtn.setBackgroundColor(Color.parseColor("#E8EAF6"))
        (selectedBtn.getChildAt(1) as? TextView)?.setTextColor(colorPrimary)

        // Show/hide fields
        when (method) {
            "mpesa" -> {
                tvPhoneLabel.text = "Phone Number (M-Pesa STK Push)"
                tvPhoneLabel.visibility = View.VISIBLE
                etPhone.visibility = View.VISIBLE
                tvEmailLabel.visibility = View.GONE
                etEmail.visibility = View.GONE
                btnPay.text = "Pay with M-Pesa"
                tvHelper.text = "You will receive an STK push on your phone"
            }
            "airtel" -> {
                tvPhoneLabel.text = "Airtel Phone Number"
                tvPhoneLabel.visibility = View.VISIBLE
                etPhone.visibility = View.VISIBLE
                tvEmailLabel.visibility = View.GONE
                etEmail.visibility = View.GONE
                btnPay.text = "Pay with Airtel Money"
                tvHelper.text = "An Airtel Money prompt will be sent to your phone"
            }
            "paypal" -> {
                tvPhoneLabel.visibility = View.GONE
                etPhone.visibility = View.GONE
                tvEmailLabel.visibility = View.VISIBLE
                etEmail.visibility = View.VISIBLE
                btnPay.text = "Pay with PayPal"
                tvHelper.text = "You will be redirected to PayPal to complete payment"
            }
        }

        updateSummary(etAmount.text?.toString()?.toDoubleOrNull() ?: 0.0)
    }

    // ---------------------------------------------------------------
    // Summary calculation
    // ---------------------------------------------------------------

    private fun updateSummary(amount: Double) {
        val fee = if (selectedMethod == "paypal" && amount > 0) amount * 0.029 + 30.0 else 0.0
        val total = amount + fee
        tvSummaryAmount.text = formatKsh(amount)
        tvSummaryFee.text    = formatKsh(fee)
        tvSummaryTotal.text  = formatKsh(total)
    }

    private fun formatKsh(amount: Double): String {
        val fmt = NumberFormat.getNumberInstance(Locale.US)
        fmt.maximumFractionDigits = 0
        return "KSh ${fmt.format(amount)}"
    }

    // ---------------------------------------------------------------
    // Validation + submit
    // ---------------------------------------------------------------

    private fun submitContribution() {
        val amountText = etAmount.text?.toString()?.trim()
        val amount = amountText?.toDoubleOrNull()

        if (amount == null || amount <= 0) {
            etAmount.error = "Please enter a valid amount"
            return
        }

        when (selectedMethod) {
            "mpesa", "airtel" -> {
                val phone = etPhone.text?.toString()?.trim()
                if (phone.isNullOrEmpty()) {
                    etPhone.error = "Please enter your phone number"
                    return
                }
                viewModel.contributeViaMobileMoney(
                    groupId    = groupId,
                    amount     = amount,
                    phoneNumber = phone,
                    method     = selectedMethod
                )
            }
            "paypal" -> {
                val email = etEmail.text?.toString()?.trim()
                if (email.isNullOrEmpty() ||
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                ) {
                    etEmail.error = "Please enter a valid email"
                    return
                }
                viewModel.contributeViaPaypal(
                    groupId = groupId,
                    amount  = amount,
                    email   = email
                )
            }
        }
    }

    // ---------------------------------------------------------------
    // Dialogs
    // ---------------------------------------------------------------

    private var loadingDialog: AlertDialog? = null
    private var resultDialog: AlertDialog?  = null

    private fun showLoadingDialog() {
        if (loadingDialog?.isShowing == true) return

        val msg = when (selectedMethod) {
            "mpesa"  -> "Please enter your M-Pesa PIN on your phone."
            "airtel" -> "Please enter your Airtel Money PIN on your phone."
            "paypal" -> "Connecting to PayPal..."
            else     -> "Processing payment..."
        }

        loadingDialog = AlertDialog.Builder(requireContext())
            .setTitle("Processing Payment")
            .setMessage(msg)
            .setCancelable(false)
            .create()

        loadingDialog?.show()
    }

    private fun showSuccessDialog(amount: Double, groupName: String, reference: String?) {
        val refText = if (!reference.isNullOrEmpty()) "\nRef: $reference" else ""
        resultDialog = AlertDialog.Builder(requireContext())
            .setTitle("✅ Contribution Successful!")
            .setMessage("${formatKsh(amount)} has been contributed to $groupName.$refText")
            .setCancelable(false)
            .setPositiveButton("Done") { dialog, _ ->
                dialog.dismiss()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            .create()

        resultDialog?.show()
    }

    private fun showFailureDialog(errorMessage: String) {
        resultDialog = AlertDialog.Builder(requireContext())
            .setTitle("❌ Payment Failed")
            .setMessage(errorMessage)
            .setCancelable(false)
            .setPositiveButton("Try Again") { dialog, _ ->
                dialog.dismiss()
                viewModel.resetState()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            .create()

        resultDialog?.show()
    }

    private fun dismissAllDialogs() {
        loadingDialog?.dismiss()
        loadingDialog = null
        resultDialog?.dismiss()
        resultDialog = null
    }

    // ---------------------------------------------------------------
    // UI helper builders
    // ---------------------------------------------------------------

    private fun makeLabel(text: String) = TextView(requireContext()).apply {
        this.text = text
        textSize = 13f
        setTypeface(null, Typeface.BOLD)
        setTextColor(colorSecondary)
        setPadding(0, 16, 0, 6)
    }

    private fun makeEditText(hint: String, inputType: Int) =
        EditText(requireContext()).apply {
            this.hint = hint
            this.inputType = inputType
            textSize = 15f
            setTextColor(Color.parseColor("#212121"))
            setHintTextColor(colorTertiary)
            setBackgroundColor(colorWhite)
            setPadding(24, 24, 24, 24)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.setMargins(0, 0, 0, 8) }
        }

    private fun makeMethodButton(label: String, selected: Boolean): LinearLayout {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(8, 16, 8, 16)
            setBackgroundColor(if (selected) Color.parseColor("#E8EAF6") else colorWhite)

            addView(TextView(requireContext()).apply {
                text = when (label) {
                    "M-Pesa"       -> "📱"
                    "Airtel Money" -> "📶"
                    "PayPal"       -> "💳"
                    else -> "💰"
                }
                textSize = 20f
                gravity = Gravity.CENTER
            })

            addView(TextView(requireContext()).apply {
                text = label
                textSize = 11f
                gravity = Gravity.CENTER
                setTextColor(if (selected) colorPrimary else colorSecondary)
                setPadding(0, 4, 0, 0)
            })
        }
    }

    private fun makeDivider() = View(requireContext()).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 2
        ).also { it.setMargins(0, 16, 0, 16) }
        setBackgroundColor(colorDivider)
    }

    // Adds a row to a card and returns the value TextView for later updates
    private fun makeSummaryRow(
        parent: LinearLayout,
        label: String,
        initialValue: String,
        bold: Boolean = false
    ): TextView {
        val row = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(0, 8, 0, 8)
        }

        row.addView(TextView(requireContext()).apply {
            text = label
            textSize = 14f
            setTextColor(if (bold) colorPrimary else colorSecondary)
            if (bold) setTypeface(null, Typeface.BOLD)
            layoutParams = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        })

        val valueView = TextView(requireContext()).apply {
            text = initialValue
            textSize = 14f
            setTextColor(colorPrimary)
            if (bold) setTypeface(null, Typeface.BOLD)
        }

        row.addView(valueView)
        parent.addView(row)
        return valueView
    }
}