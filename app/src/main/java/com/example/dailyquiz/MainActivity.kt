package com.example.dailyquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.dailyquiz.data.QuestionsRepository
import com.example.dailyquiz.data.QuizResult
import com.example.dailyquiz.ui.HistoryScreen
import com.example.dailyquiz.ui.HomeScreen
import com.example.dailyquiz.ui.QuizScreen
import com.example.dailyquiz.ui.ResultDetailsScreen
import com.example.dailyquiz.ui.theme.DailyQuizTheme
import com.example.dailyquiz.ui.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyQuizTheme {
                QuizApp()
            }
        }
    }
}

@Composable
fun QuizApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var quizResult by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var selectedResult by remember { mutableStateOf<QuizResult?>(null) }

    when (currentScreen) {
        Screen.Home -> HomeScreen(
            onStartQuiz = { currentScreen = Screen.Quiz },
            onShowHistory = { currentScreen = Screen.History }
        )
        Screen.Quiz -> QuizScreen(
            questions = QuestionsRepository.getSampleQuestions(),
            onBack = { currentScreen = Screen.Home },
            onComplete = { score, total ->
                quizResult = score to total
                currentScreen = Screen.Result
            }
        )
        Screen.Result -> ResultScreen(
            score = quizResult?.first ?: 0,
            totalQuestions = quizResult?.second ?: 1,
            onRestart = { currentScreen = Screen.Quiz },
            onBackToHome = { currentScreen = Screen.Home }
        )
        Screen.History -> HistoryScreen(
            onBack = { currentScreen = Screen.Home },
            onItemClick = { result ->
                selectedResult = result
                currentScreen = Screen.ResultDetails
            }
        )
        Screen.ResultDetails -> selectedResult?.let { result ->
            ResultDetailsScreen(
                result = result,
                onBack = { currentScreen = Screen.History }
            )
        }
    }
}

sealed class Screen {
    object Home : Screen()
    object Quiz : Screen()
    object Result : Screen()
    object History : Screen()
    object ResultDetails : Screen()
}