package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SceneSelectorDrawer(
  viewModel: TutorialViewModel,
  onDismiss: () -> Unit
) {
  val currentSceneIndex by viewModel.currentSceneIndex.collectAsState()
  val scenes = viewModel.scenes
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = Navy900,
    contentColor = Color.White
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text("பாடக் காட்சிகள் (ALL 21 SCENES)", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 15.sp)
          Text("நேரடியாக விரும்பிய பகுதிக்குச் செல்லவும்", color = TextSecondaryDark, fontSize = 11.sp)
        }
        IconButton(onClick = onDismiss) {
          Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondaryDark)
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.height(420.dp)
      ) {
        itemsIndexed(scenes) { index, scene ->
          val isCurrent = index == currentSceneIndex
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = if (isCurrent) IndigoDark else Navy800,
            border = androidx.compose.foundation.BorderStroke(1.dp, if (isCurrent) CyanAccent else BorderSubtle),
            modifier = Modifier
              .fillMaxWidth()
              .clickable {
                viewModel.selectScene(index)
                onDismiss()
              }
          ) {
            Row(
              modifier = Modifier.padding(10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                color = if (isCurrent) CyanAccent else Navy700,
                shape = RoundedCornerShape(6.dp)
              ) {
                Text(
                  scene.id.toString().padStart(2, '0'),
                  color = if (isCurrent) Color.Black else Color.White,
                  fontWeight = FontWeight.Bold,
                  fontSize = 10.sp,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                )
              }

              Spacer(modifier = Modifier.width(10.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  scene.titleTa,
                  color = if (isCurrent) Color.White else TextPrimaryDark,
                  fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                  fontSize = 11.sp
                )
                Text(
                  scene.titleEn,
                  color = if (isCurrent) CyanAccent else TextSecondaryDark,
                  fontSize = 10.sp
                )
              }

              Spacer(modifier = Modifier.width(6.dp))

              Text(
                "${scene.durationSeconds}s",
                color = TextSecondaryDark,
                fontSize = 10.sp
              )
            }
          }
        }
      }
    }
  }
}
