package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.OpfGreen
import com.example.ui.theme.PdfRed
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@Composable
fun QuizScreen(
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  val quizState by viewModel.quizState.collectAsState()
  val questions = viewModel.quizQuestions

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Navy900)
      .padding(14.dp)
      .verticalScroll(rememberScrollState())
  ) {
    // Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text("LEARNING CHALLENGE", color = AmberAccent, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("கற்றல் சவால் — வினாடி-வினா (3 கேள்விகள்)", color = TextSecondaryDark, fontSize = 11.sp)
      }
      Icon(Icons.Default.School, contentDescription = null, tint = AmberAccent, modifier = Modifier.size(24.dp))
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Score Banner if submitted
    if (quizState.isSubmitted) {
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (quizState.score == questions.size) Color(0xFF064E3B) else Navy800,
        border = androidx.compose.foundation.BorderStroke(1.5.dp, if (quizState.score == questions.size) OpfGreen else AmberAccent),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(Icons.Default.MilitaryTech, contentDescription = null, tint = AmberAccent, modifier = Modifier.size(36.dp))
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            if (quizState.score == questions.size) "அருமை! அனைத்து விடைகளும் சரி! 🎉" else "நன்று! உங்கள் மதிப்பெண்: ${quizState.score} / ${questions.size}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
          )
          Text("Score: ${quizState.score} of ${questions.size} correct", color = CyanAccent, fontSize = 11.sp)

          Spacer(modifier = Modifier.height(8.dp))
          OutlinedButton(
            onClick = { viewModel.resetQuiz() },
            shape = RoundedCornerShape(20.dp)
          ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("மீண்டும் முயற்சி செய்க (Retry)", fontSize = 11.sp)
          }
        }
      }
      Spacer(modifier = Modifier.height(14.dp))
    }

    // Questions list
    questions.forEachIndexed { qIndex, question ->
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Navy800,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              "கேள்வி ${qIndex + 1} of ${questions.size}",
              color = AmberAccent,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp
            )
            if (quizState.isSubmitted) {
              val isCorrect = quizState.userAnswers[question.id] == question.correctIndex
              Surface(
                color = if (isCorrect) Color(0xFF065F46) else Color(0xFF7F1D1D),
                shape = RoundedCornerShape(4.dp)
              ) {
                Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                  Icon(if (isCorrect) Icons.Default.Check else Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                  Spacer(modifier = Modifier.width(3.dp))
                  Text(if (isCorrect) "சரி" else "தவறு", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(question.questionTa, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp, lineHeight = 18.sp)
          Text(question.questionEn, color = TextSecondaryDark, fontSize = 11.sp)

          Spacer(modifier = Modifier.height(10.dp))

          // Options
          question.options.forEachIndexed { optIndex, optionText ->
            val isSelected = quizState.userAnswers[question.id] == optIndex
            val isCorrect = optIndex == question.correctIndex

            val backgroundColor = when {
              quizState.isSubmitted && isCorrect -> Color(0xFF064E3B)
              quizState.isSubmitted && isSelected && !isCorrect -> Color(0xFF7F1D1D)
              isSelected -> IndigoDark
              else -> Navy900
            }

            val borderColor = when {
              quizState.isSubmitted && isCorrect -> OpfGreen
              quizState.isSubmitted && isSelected && !isCorrect -> PdfRed
              isSelected -> CyanAccent
              else -> BorderSubtle
            }

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = backgroundColor,
              border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
                .clickable {
                  if (!quizState.isSubmitted) {
                    viewModel.answerQuizQuestion(question.id, optIndex)
                  }
                }
            ) {
              Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  optionText,
                  color = if (isSelected || (quizState.isSubmitted && isCorrect)) Color.White else TextPrimaryDark,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected || (quizState.isSubmitted && isCorrect)) FontWeight.Bold else FontWeight.Normal
                )
              }
            }
          }

          // If submitted, show detailed explanations
          if (quizState.isSubmitted) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
              color = Color(0xFF0A0F1D),
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(8.dp)) {
                Text(question.explanationTa, color = CyanAccent, fontSize = 10.sp, lineHeight = 14.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(question.explanationEn, color = TextSecondaryDark, fontSize = 9.sp)
              }
            }
          }
        }
      }
    }

    if (!quizState.isSubmitted) {
      Button(
        onClick = { viewModel.submitQuiz() },
        colors = ButtonDefaults.buttonColors(containerColor = AmberAccent),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth().height(46.dp)
      ) {
        Text("விடைகளை சரிபார்க்க (Submit Answers)", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
      }
    }
  }
}
