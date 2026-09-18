package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Layers
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
import androidx.compose.runtime.collectAsState
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
import com.example.data.EpubFileItem
import com.example.data.TutorialRepository
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CssBlue
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EpubPurple
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoLight
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.OpfGreen
import com.example.ui.theme.PdfRed
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@Composable
fun EpubPackageExplorer(
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  val selectedFile by viewModel.selectedEpubFile.collectAsState()
  val files = viewModel.sampleEpubFiles
  var activeViewTab by remember { mutableStateOf(0) } // 0 = File Inspector, 1 = Architecture Diagram

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
        Text("EPUB3 PACKAGE LAB", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("MyBook.epub உள் கட்டமைப்பு ஆய்வுக்கூடம்", color = TextSecondaryDark, fontSize = 11.sp)
      }
      Surface(color = IndigoDark, shape = RoundedCornerShape(8.dp)) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Default.Layers, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("ZIP Structure", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Switch Tabs
    TabRow(
      selectedTabIndex = activeViewTab,
      containerColor = Navy800,
      contentColor = CyanAccent,
      indicator = { tabPositions ->
        TabRowDefaults.SecondaryIndicator(
          modifier = Modifier.tabIndicatorOffset(tabPositions[activeViewTab]),
          color = CyanAccent
        )
      }
    ) {
      Tab(
        selected = activeViewTab == 0,
        onClick = { activeViewTab = 0 },
        text = { Text("கோப்புகள் (Files & Code)", fontSize = 11.sp) }
      )
      Tab(
        selected = activeViewTab == 1,
        onClick = { activeViewTab = 1 },
        text = { Text("முழுமையான வரைபடம் (Architecture)", fontSize = 11.sp) }
      )
    }

    Spacer(modifier = Modifier.height(12.dp))

    if (activeViewTab == 0) {
      // File Tree List
      Text("SELECT FILE TO INSPECT:", color = AmberAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold)
      Spacer(modifier = Modifier.height(6.dp))

      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        files.forEach { file ->
          val isSelected = file.path == selectedFile.path
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = if (isSelected) IndigoDark else Navy800,
            border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CyanAccent else BorderSubtle),
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.selectEpubFile(file) }
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              // Indent according to level
              if (file.level > 0) {
                Spacer(modifier = Modifier.width((file.level * 14).dp))
                Text("└── ", color = TextSecondaryDark, fontFamily = FontFamily.Monospace, fontSize = 10.sp)
              }
              Icon(
                when (file.extension) {
                  "OPF" -> Icons.Default.Description
                  "XML" -> Icons.Default.Code
                  "XHTML" -> Icons.Default.InsertDriveFile
                  "CSS" -> Icons.Default.Code
                  "MIME" -> Icons.Default.InsertDriveFile
                  else -> Icons.Default.InsertDriveFile
                },
                contentDescription = null,
                tint = when (file.extension) {
                  "OPF" -> OpfGreen
                  "XHTML" -> HtmlOrange
                  "CSS" -> CssBlue
                  "MIME" -> AmberAccent
                  else -> CyanAccent
                },
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                file.path,
                color = if (isSelected) Color.White else TextPrimaryDark,
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
              )
              Spacer(modifier = Modifier.weight(1f))
              Badge(
                containerColor = when (file.extension) {
                  "OPF" -> Color(0xFF065F46)
                  "XHTML" -> Color(0xFF7C2D12)
                  "CSS" -> Color(0xFF075985)
                  "MIME" -> Color(0xFF78350F)
                  else -> Color(0xFF1E293B)
                }
              ) {
                Text(file.extension, color = Color.White, fontSize = 8.sp, modifier = Modifier.padding(2.dp))
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Inspection Box for Selected File
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Navy800,
        border = androidx.compose.foundation.BorderStroke(1.5.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.Info, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(selectedFile.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Text(selectedFile.path, color = TextSecondaryDark, fontFamily = FontFamily.Monospace, fontSize = 10.sp)
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(selectedFile.purposeTa, color = TextPrimaryDark, fontSize = 11.sp, lineHeight = 16.sp)
          Spacer(modifier = Modifier.height(2.dp))
          Text(selectedFile.purposeEn, color = CyanAccent, fontSize = 10.sp)

          Spacer(modifier = Modifier.height(8.dp))
          Text("SOURCE CODE PREVIEW:", color = AmberAccent, fontSize = 9.sp, fontWeight = FontWeight.Bold)

          Surface(
            color = Color(0xFF0A0F1D),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = selectedFile.codeSnippet,
              color = Color(0xFFE2E8F0),
              fontFamily = FontFamily.Monospace,
              fontSize = 10.sp,
              lineHeight = 14.sp,
              modifier = Modifier.padding(8.dp)
            )
          }
        }
      }
    } else {
      // Complete Package Architecture Visual
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Navy800,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text("MyBook.epub COMPLETE ARCHITECTURE", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 12.sp)
          Spacer(modifier = Modifier.height(8.dp))

          ArchitectureBlock("1. mimetype", "application/epub+zip (Uncompressed format tag)", AmberAccent)
          ArchitectureBlock("2. META-INF/container.xml", "Bootstrap pointer locating EPUB/package.opf", CyanAccent)
          ArchitectureBlock("3. EPUB/package.opf", "Triad: Metadata, File Manifest, and Spine reading order", OpfGreen)
          ArchitectureBlock("4. EPUB/nav.xhtml", "Accessible HTML5 Table of Contents (TOC)", IndigoLight)
          ArchitectureBlock("5. EPUB/xhtml/*.xhtml", "Structured semantic book chapters with text & sections", HtmlOrange)
          ArchitectureBlock("6. EPUB/css/*.css", "Reading styles, typography, margins, night/day rules", CssBlue)
          ArchitectureBlock("7. EPUB/images/*", "Cover, diagrams, charts with descriptive alt text", Color(0xFF10B981))
          ArchitectureBlock("8. EPUB/fonts/*", "Embedded fonts for authentic typography (optional)", Color(0xFFEC4899))
        }
      }
    }
  }
}

@Composable
private fun ArchitectureBlock(title: String, description: String, color: Color) {
  Surface(
    shape = RoundedCornerShape(6.dp),
    color = Navy900,
    border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
  ) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
      Box(modifier = Modifier.size(8.dp).background(color, RoundedCornerShape(2.dp)))
      Spacer(modifier = Modifier.width(8.dp))
      Column {
        Text(title, color = color, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        Text(description, color = TextPrimaryDark, fontSize = 9.sp)
      }
    }
  }
}
