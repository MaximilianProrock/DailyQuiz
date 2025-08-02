package com.example.dailyquiz.data

import java.util.Date

data class QuizResult(
    val id: Int = 0,
    val date: Date = Date(),
    val score: Int,
    val total: Int
)