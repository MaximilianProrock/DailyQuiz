package com.example.dailyquiz.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dailyquiz.data.Question

@Composable
fun QuizScreen(
    questions: List<Question>,
    onBack: () -> Unit,
    onComplete: (score: Int, total: Int) -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }

    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
        }

        Text(
            text = "Вопрос ${currentQuestionIndex + 1} из ${questions.size}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = currentQuestion.text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        currentQuestion.answers.forEachIndexed { index, answer ->
            val isSelected = selectedAnswer == index
            val isCorrect = index == currentQuestion.correctAnswerIndex

            OutlinedButton(
                onClick = { selectedAnswer = index },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = when {
                        isSelected && isCorrect -> Color.Green.copy(alpha = 0.2f)
                        isSelected -> Color.Red.copy(alpha = 0.2f)
                        else -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Text(
                    text = answer,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if (selectedAnswer == currentQuestion.correctAnswerIndex) {
                    score++
                }

                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    selectedAnswer = -1
                } else {
                    onComplete(score, questions.size)
                }
            },
            enabled = selectedAnswer != -1,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (currentQuestionIndex == questions.size - 1) "Завершить" else "Далее")
        }
    }
}