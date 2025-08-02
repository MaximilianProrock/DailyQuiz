package com.example.dailyquiz.data

object QuizRepository {
    private val results = mutableListOf<QuizResult>()

    fun saveResult(score: Int, total: Int) {
        results.add(QuizResult(score = score, total = total))
    }

    fun getAllResults(): List<QuizResult> = results.toList()
}