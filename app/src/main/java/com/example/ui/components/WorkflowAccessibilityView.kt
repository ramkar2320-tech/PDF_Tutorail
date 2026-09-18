package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.LinearScale
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TutorialRepository
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CssBlue
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EpubPurple
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.OpfGreen
import com.example.ui.theme.PdfRed
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@Composable
fun WorkflowAccessibilityView(
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  var selectedTab by remember { mutableStateOf(0) } // 0 = PDF to EPUB Workflow, 1 = Accessibility Standards

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Navy900)
      .padding(14.dp)
      .verticalScroll(rememberScrollState())
  ) {
    // Top Title
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text("WORKFLOW & ACCESSIBILITY", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("PDF முதல் Accessible EPUB3 தயாரிக்கும் முழு செயல்முறை", color = TextSecondaryDark, fontSize = 11.sp)
      }
      Icon(Icons.Default.Hub, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(24.dp))
    }

    Spacer(modifier = Modifier.height(12.dp))

    TabRow(
      selectedTabIndex = selectedTab,
      containerColor = Navy800,
      contentColor = CyanAccent,
      indicator = { tabPositions ->
        TabRowDefaults.SecondaryIndicator(
          modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
          color = CyanAccent
        )
      }
    ) {
      Tab(
        selected = selectedTab == 0,
        onClick = { selectedTab = 0 },
        text = { Text("14-Step Pipeline", fontSize = 11.sp) }
      )
      Tab(
        selected = selectedTab == 1,
        onClick = { selectedTab = 1 },
        text = { Text("Accessible EPUB3", fontSize = 11.sp) }
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    if (selectedTab == 0) {
      // 14-Step Pipeline Flow
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = Navy800,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            "PDF TO EPUB3 CONVERSION PIPELINE",
            color = CyanAccent,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
          )
          Text(
            "ஒரு PDF-ஐ EPUB ஆக மாற்றும்போது PDF-ஐ அப்படியே EPUB-க்குள் போடுவதில்லை. கீழ்கண்ட படிநிலைகளை முறையாக பின்பற்ற வேண்டும்:",
            color = TextPrimaryDark,
            fontSize = 11.sp,
            modifier = Modifier.padding(vertical = 4.dp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          // 5 Major Phases
          PipelinePhaseCard(
            phaseNumber = "PHASE 1",
            title = "PDF Analysis & Order Detection",
            titleTa = "PDF பகுப்பாய்வு & வாசிக்கும் வரிசை",
            steps = listOf(
              "1. PDF Layout Analysis (பக்கங்கள், பத்திகள், விளிம்புகள் ஆய்வு)",
              "2. Text & Image Extraction (உரை & படங்களை பிரித்தெடுத்தல்)",
              "3. Reading Order Detection (Multi-column வாசிக்கும் வரிசை)"
            ),
            color = PdfRed
          )

          Spacer(modifier = Modifier.height(8.dp))

          PipelinePhaseCard(
            phaseNumber = "PHASE 2",
            title = "Semantic Structuring",
            titleTa = "அர்த்தமுள்ள கட்டமைப்பு",
            steps = listOf(
              "4. Heading Detection (h1 முதல் h6 தலைப்புகள் கண்டறிதல்)",
              "5. Paragraph & List Detection (பத்திகள் மற்றும் பட்டியல்கள்)",
              "6. Table & Figure Structuring (அட்டவணைகள் மற்றும் படங்கள்)"
            ),
            color = HtmlOrange
          )

          Spacer(modifier = Modifier.height(8.dp))

          PipelinePhaseCard(
            phaseNumber = "PHASE 3",
            title = "XHTML & CSS Production",
            titleTa = "XHTML உருவாக்கம் & CSS வடிவமைப்பு",
            steps = listOf(
              "7. Semantic XHTML Generation (XML விதிகளுடன் அத்தியாயங்கள்)",
              "8. CSS Styling Rules (எழுத்துரு அளவு, இடைவெளி, layout)",
              "9. Image Alt-Text & Accessibility (படங்களுக்கான மாற்று உரை)"
            ),
            color = CssBlue
          )

          Spacer(modifier = Modifier.height(8.dp))

          PipelinePhaseCard(
            phaseNumber = "PHASE 4",
            title = "Packaging & Navigation",
            titleTa = "தொகுப்பு மற்றும் பொருளடக்கம்",
            steps = listOf(
              "10. Metadata Authoring (தலைப்பு, ஆசிரியர், ISBN, மொழி)",
              "11. nav.xhtml (HTML5 பொருளடக்கம் Table of Contents)",
              "12. package.opf (Metadata, Manifest, Spine reading order)",
              "13. EPUB3 Packaging (uncompressed mimetype உடன் ZIP)"
            ),
            color = OpfGreen
          )

          Spacer(modifier = Modifier.height(8.dp))

          PipelinePhaseCard(
            phaseNumber = "PHASE 5",
            title = "Quality & Validation",
            titleTa = "சரிபார்த்தல் மற்றும் இறுதி ஒப்புதல்",
            steps = listOf(
              "14. EPUBCheck Validation (பிழையற்ற சர்வதேச தரச் சான்றிதழ்)"
            ),
            color = AmberAccent
          )
        }
      }
    } else {
      // Accessibility Standards View
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = Navy800,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Accessibility, contentDescription = null, tint = Color(0xFF34D399), modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("ACCESSIBLE EPUB3 STANDARDS", color = Color(0xFF34D399), fontWeight = FontWeight.Bold, fontSize = 13.sp)
          }

          Text(
            "அனைத்து வாசகர்களும், குறிப்பாக பார்வைத்திறன் சவால்கள் உள்ளவர்களும் Screen Reader மூலம் வாசிக்கத்தக்க முறையில் அமைத்தல்:",
            color = TextPrimaryDark,
            fontSize = 11.sp,
            modifier = Modifier.padding(vertical = 4.dp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          AccessibilityStandardItem(
            tag = "1. Semantic HTML5",
            tamilDesc = "<div> மற்றும் <span>-ஐ மட்டுமே பயன்படுத்தாமல், <main>, <section>, <header>, <h1>-<h6> போன்ற அர்த்தமுள்ள semantic tag-களை பயன்படுத்த வேண்டும்.",
            code = "<section epub:type=\"chapter\" role=\"doc-chapter\">\n  <h1>பாடம் 1</h1>\n</section>"
          )

          Spacer(modifier = Modifier.height(8.dp))

          AccessibilityStandardItem(
            tag = "2. Image Alternative Text (Alt Text)",
            tamilDesc = "தகவல் தரும் ஒவ்வொரு படத்திற்கும் அர்த்தமுள்ள சுருக்கமான alt text வழங்கப்பட வேண்டும். அலங்கார படங்களுக்கு alt=\"\" என குறிப்பிடலாம்.",
            code = "<img src=\"../images/figure01.png\"\n     alt=\"EPUB தொகுப்பின் மூன்று தூண்கள் விளக்கப்படம்\" />"
          )

          Spacer(modifier = Modifier.height(8.dp))

          AccessibilityStandardItem(
            tag = "3. Logical Reading Order",
            tamilDesc = "PDF-ல் உள்ள பல பத்திகள் (multi-columns) கலங்காமல், வாசகர் படிக்க வேண்டிய சரியான வரிசையில் அடுத்தடுத்து XHTML-ல் அமைய வேண்டும்.",
            code = "<spine>\n  <itemref idref=\"ch01\"/>\n  <itemref idref=\"ch02\"/>\n</spine>"
          )

          Spacer(modifier = Modifier.height(8.dp))

          AccessibilityStandardItem(
            tag = "4. Language Tagging",
            tamilDesc = "Text-to-speech குரல் இயந்திரம் தமிழ் மொழியை சரியாக உச்சரிக்க xml:lang=\"ta\" குறியீடு அவசியம்.",
            code = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ta\">"
          )

          Spacer(modifier = Modifier.height(8.dp))

          AccessibilityStandardItem(
            tag = "5. Accessibility Metadata",
            tamilDesc = "package.opf-ல் Schema.org accessMode, accessibilityFeature போன்ற தகவல்களை பதிவு செய்தல்.",
            code = "<meta property=\"schema:accessMode\">textual</meta>\n<meta property=\"schema:accessMode\">visual</meta>"
          )
        }
      }
    }
  }
}

@Composable
private fun PipelinePhaseCard(
  phaseNumber: String,
  title: String,
  titleTa: String,
  steps: List<String>,
  color: Color
) {
  Surface(
    shape = RoundedCornerShape(8.dp),
    color = Navy900,
    border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(phaseNumber, color = color, fontWeight = FontWeight.Bold, fontSize = 9.sp)
        Text(title, color = color, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      }
      Text(titleTa, color = TextSecondaryDark, fontSize = 10.sp)

      Spacer(modifier = Modifier.height(6.dp))

      steps.forEach { step ->
        Row(
          modifier = Modifier.padding(vertical = 2.dp),
          verticalAlignment = Alignment.Top
        ) {
          Icon(Icons.Default.CheckCircle, contentDescription = null, tint = color, modifier = Modifier.size(12.dp).padding(top = 2.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(step, color = TextPrimaryDark, fontSize = 10.sp, lineHeight = 14.sp)
        }
      }
    }
  }
}

@Composable
private fun AccessibilityStandardItem(tag: String, tamilDesc: String, code: String) {
  Surface(
    shape = RoundedCornerShape(8.dp),
    color = Navy900,
    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Text(tag, color = Color(0xFF34D399), fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(modifier = Modifier.height(2.dp))
      Text(tamilDesc, color = TextPrimaryDark, fontSize = 10.sp, lineHeight = 14.sp)
      Spacer(modifier = Modifier.height(6.dp))
      Surface(
        color = Color(0xFF0A0F1D),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(code, color = Color(0xFF93C5FD), fontFamily = FontFamily.Monospace, fontSize = 9.sp, modifier = Modifier.padding(6.dp))
      }
    }
  }
}
