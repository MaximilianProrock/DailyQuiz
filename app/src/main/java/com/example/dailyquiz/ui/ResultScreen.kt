package com.example.dailyquiz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dailyquiz.data.QuizRepository

@Composable
fun ResultScreen(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    onBackToHome: () -> Unit
) {
    val resultText = when (score) {
        totalQuestions -> "Идеально! 🎉"
        totalQuestions - 1 -> "Почти идеально! 👍"
        totalQuestions / 2 -> "Неплохо! 😊"
        else -> "Попробуйте ещё раз! 💪"
    }

    LaunchedEffect(Unit) {
        QuizRepository.saveResult(score, totalQuestions)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = resultText,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Ваш результат: $score/$totalQuestions",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onRestart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Начать заново")
        }

        OutlinedButton(
            onClick = onBackToHome,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("На главную")
        }
    }
}