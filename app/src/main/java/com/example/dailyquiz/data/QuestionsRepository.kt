package com.example.dailyquiz.data

object QuestionsRepository {
    fun getSampleQuestions(): List<Question> = listOf(
        Question(
            id = 1,
            text = "Какая столица Франции?",
            answers = listOf("Берлин", "Мадрид", "Париж", "Рим"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 2,
            text = "Какой язык программирования используется в Android?",
            answers = listOf("Java", "Kotlin", "Dart", "Swift"),
            correctAnswerIndex = 1
        )
    )
}