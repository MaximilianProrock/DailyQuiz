package com.example.dailyquiz.data

data class Question(
    val id: Int,
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)