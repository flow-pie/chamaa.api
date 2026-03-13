package com.example.chama.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chama.data.ContributionRepository
import com.example.chama.models.ContributionRequest
import kotlinx.coroutines.launch

class ContributionViewModel : ViewModel() {

    private val repository = ContributionRepository()

    // ── UI State ──────────────────────────────────────────────────────────
    sealed class ContributionState {
        object Idle    : ContributionState()
        object Loading : ContributionState()
        data class Success(
            val amount    : Double,
            val groupName : String,
            val reference : String?
        ) : ContributionState()
        data class Failure(val message: String) : ContributionState()
    }

    private val _state = MutableLiveData<ContributionState>(ContributionState.Idle)
    val state: LiveData<ContributionState> = _state

    // ── Actions ───────────────────────────────────────────────────────────

    fun contributeViaMobileMoney(
        groupId     : String,
        amount      : Double,
        phoneNumber : String,
        method      : String   // "mpesa" or "airtel"
    ) {
        if (_state.value is ContributionState.Loading) return
        viewModelScope.launch {
            _state.value = ContributionState.Loading
            repository.makeContribution(
                ContributionRequest(
                    groupId       = groupId,
                    amount        = amount,
                    paymentMethod = method,
                    phoneNumber   = phoneNumber
                )
            )
                .onSuccess { response ->
                    _state.value = ContributionState.Success(
                        amount    = amount,
                        groupName = response.groupName ?: "your group",
                        reference = response.transactionReference
                    )
                }
                .onFailure { error ->
                    _state.value = ContributionState.Failure(
                        message = error.message ?: "Payment failed. Please try again."
                    )
                }
        }
    }

    fun contributeViaPaypal(
        groupId : String,
        amount  : Double,
        email   : String
    ) {
        if (_state.value is ContributionState.Loading) return
        viewModelScope.launch {
            _state.value = ContributionState.Loading
            repository.makeContribution(
                ContributionRequest(
                    groupId       = groupId,
                    amount        = amount,
                    paymentMethod = "paypal",
                    email         = email
                )
            )
                .onSuccess { response ->
                    _state.value = ContributionState.Success(
                        amount    = amount,
                        groupName = response.groupName ?: "your group",
                        reference = response.transactionReference
                    )
                }
                .onFailure { error ->
                    _state.value = ContributionState.Failure(
                        message = error.message ?: "PayPal payment failed. Please try again."
                    )
                }
        }
    }

    fun resetState() {
        _state.value = ContributionState.Idle
    }
}