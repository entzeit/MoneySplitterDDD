package main.kotlin.moneysplitter.presentation.view

//Presentation DTO
data class TransactionView(
    val fromName: String,
    val toName: String,
    val amount: String
)