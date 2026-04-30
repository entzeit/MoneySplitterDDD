package main.kotlin.presentation.view

//Presentation DTO
data class TransactionView(
    val fromName: String,
    val toName: String,
    val amount: String
)