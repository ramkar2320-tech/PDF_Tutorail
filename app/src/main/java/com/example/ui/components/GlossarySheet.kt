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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GlossaryItem
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CssBlue
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EpubPurple
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.OpfGreen
import com.example.ui.theme.PdfRed
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@Composable
fun GlossarySheet(
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  val query by viewModel.glossaryQuery.collectAsState()
  val allItems = viewModel.glossaryItems
  val filteredItems = if (query.isBlank()) {
    allItems
  } else {
    allItems.filter {
      it.term.contains(query, ignoreCase = true) ||
        it.fullForm.contains(query, ignoreCase = true) ||
        it.definitionTa.contains(query, ignoreCase = true) ||
        it.definitionEn.contains(query, ignoreCase = true)
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Navy900)
      .padding(14.dp)
  ) {
    // Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text("DIGITAL PUBLISHING GLOSSARY", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("கலைச்சொல் அகராதி — ஆங்கிலம் & தமிழ் விளக்கம்", color = TextSecondaryDark, fontSize = 11.sp)
      }
      Icon(Icons.Default.Book, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(24.dp))
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Search bar
    OutlinedTextField(
      value = query,
      onValueChange = { viewModel.setGlossaryQuery(it) },
      placeholder = { Text("Search term (e.g. OPF, Spine, MIME)...", color = TextSecondaryDark, fontSize = 12.sp) },
      leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CyanAccent) },
      trailingIcon = {
        if (query.isNotEmpty()) {
          IconButton(onClick = { viewModel.setGlossaryQuery("") }) {
            Icon(Icons.Default.Clear, contentDescription = null, tint = TextSecondaryDark)
          }
        }
      },
      singleLine = true,
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = CyanAccent,
        unfocusedBorderColor = BorderSubtle,
        focusedContainerColor = Navy800,
        unfocusedContainerColor = Navy800,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
      ),
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))

    // Results count
    Text(
      "${filteredItems.size} கலைச்சொற்கள் கிடைக்கின்றன",
      color = AmberAccent,
      fontSize = 10.sp,
      fontWeight = FontWeight.Medium
    )

    Spacer(modifier = Modifier.height(6.dp))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      items(filteredItems) { item ->
        GlossaryCard(item)
      }
    }
  }
}

@Composable
private fun GlossaryCard(item: GlossaryItem) {
  val badgeColor = when (item.iconType) {
    "PDF" -> PdfRed
    "HTML" -> HtmlOrange
    "CSS" -> CssBlue
    "EPUB" -> EpubPurple
    "OPF" -> OpfGreen
    else -> CyanAccent
  }

  Surface(
    shape = RoundedCornerShape(10.dp),
    color = Navy800,
    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            color = badgeColor.copy(alpha = 0.2f),
            shape = RoundedCornerShape(6.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, badgeColor)
          ) {
            Text(
              item.term,
              color = badgeColor,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(item.fullForm, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
      Text(item.definitionTa, color = TextPrimaryDark, fontSize = 11.sp, lineHeight = 16.sp)
      Spacer(modifier = Modifier.height(2.dp))
      Text(item.definitionEn, color = TextSecondaryDark, fontSize = 10.sp)
    }
  }
}
