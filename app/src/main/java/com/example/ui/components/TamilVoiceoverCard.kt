package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TutorialScene
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CssBlue
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EpubPurple
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.OpfGreen
import com.example.ui.theme.PdfRed
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TamilVoiceoverCard(
  scene: TutorialScene,
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  val isSpeaking by viewModel.isSpeaking.collectAsState()

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = Navy800,
    border = androidx.compose.foundation.BorderStroke(1.5.dp, BorderSubtle),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      // Header with Spoken Tamil Voice-over label & listen button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            Icons.Default.RecordVoiceOver,
            contentDescription = null,
            tint = CyanAccent,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "தமிழ் குரல் விளக்கம் (Tamil Voice-Over)",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
          )
        }

        Button(
          onClick = { viewModel.toggleVoiceOver() },
          colors = ButtonDefaults.buttonColors(
            containerColor = if (isSpeaking) PdfRed else IndigoPrimary
          ),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
          shape = RoundedCornerShape(20.dp)
        ) {
          Icon(
            if (isSpeaking) Icons.Default.Stop else Icons.Default.VolumeUp,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            if (isSpeaking) "நிறுத்து (Stop)" else "கேட்க (Listen)",
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Spoken Script Box with highlighted technical terms
      Surface(
        color = Navy900,
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          val annotatedScript = buildAnnotatedTamilScript(scene.voiceoverTamil)
          Text(
            text = annotatedScript,
            color = TextPrimaryDark,
            fontSize = 13.sp,
            lineHeight = 21.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // On-Screen English Highlights
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(Icons.Default.Lightbulb, contentDescription = null, tint = AmberAccent, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "திரையில் தோன்றும் முக்கிய குறிப்புகள் (On-Screen Concepts):",
          color = AmberAccent,
          fontWeight = FontWeight.Bold,
          fontSize = 11.sp
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Surface(
        color = Navy700,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Text(
            text = scene.onScreenHeading,
            color = CyanAccent,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
          )
          Text(
            text = scene.onScreenSubheading,
            color = TextSecondaryDark,
            fontSize = 11.sp
          )

          Spacer(modifier = Modifier.height(6.dp))

          // Key points bullet list
          scene.keyPoints.forEach { point ->
            Row(
              modifier = Modifier.padding(vertical = 2.dp),
              verticalAlignment = Alignment.Top
            ) {
              Text("• ", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 11.sp)
              Text(point, color = TextPrimaryDark, fontSize = 10.sp, lineHeight = 14.sp)
            }
          }
        }
      }
    }
  }
}

// Function that parses Tamil script and highlights English terms in cyan/indigo
private fun buildAnnotatedTamilScript(script: String) = buildAnnotatedString {
  val technicalTerms = listOf(
    "PDF", "HTML", "CSS", "EPUB", "EPUB3", "XHTML", "OPF", "mimetype", "container.xml",
    "package.opf", "nav.xhtml", "Metadata", "Manifest", "Spine", "Navigation",
    "Portable Document Format", "HyperText Markup Language", "Cascading Style Sheets",
    "Electronic Publication", "Reflowable", "Fixed", "Accessibility"
  )

  val regexPattern = technicalTerms.joinToString("|") { Regex.escape(it) }
  val regex = Regex("(?i)($regexPattern)")

  var lastIndex = 0
  regex.findAll(script).forEach { matchResult ->
    val start = matchResult.range.first
    val end = matchResult.range.last + 1

    if (start > lastIndex) {
      append(script.substring(lastIndex, start))
    }

    withStyle(
      style = SpanStyle(
        color = CyanAccent,
        fontWeight = FontWeight.Bold,
        background = Color(0xFF1E293B)
      )
    ) {
      append(" [${matchResult.value}] ")
    }

    lastIndex = end
  }

  if (lastIndex < script.length) {
    append(script.substring(lastIndex))
  }
}
