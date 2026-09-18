package com.example.ui

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.EpubPackageExplorer
import com.example.ui.components.GlossarySheet
import com.example.ui.components.QuizScreen
import com.example.ui.components.SceneSelectorDrawer
import com.example.ui.components.TamilVoiceoverCard
import com.example.ui.components.VideoPlayerStage
import com.example.ui.components.WorkflowAccessibilityView
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EpubPurple
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.PdfRed
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialMainScreen(
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  val activeTab by viewModel.activeTab.collectAsState()
  val currentScene by viewModel.currentScene.collectAsState()
  var showSceneDrawer by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Navy900,
          titleContentColor = Color.White
        ),
        title = {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = IndigoDark,
              modifier = Modifier.size(36.dp)
            ) {
              Box(
                modifier = Modifier
                  .fillMaxSize()
                  .background(Brush.linearGradient(listOf(PdfRed, EpubPurple))),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.MenuBook,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(20.dp)
                )
              }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "EPUB Tamil Tutorial",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.White
              )
              Text(
                text = "PDF, HTML, CSS & EPUB என்றால் என்ன?",
                fontSize = 11.sp,
                color = CyanAccent
              )
            }
          }
        },
        actions = {
          // Scene drawer trigger
          Surface(
            color = Navy800,
            shape = RoundedCornerShape(20.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.padding(end = 8.dp)
          ) {
            IconButton(
              onClick = { showSceneDrawer = true },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                Icons.Default.List,
                contentDescription = "Scene List",
                tint = CyanAccent,
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }
      )
    },
    bottomBar = {
      NavigationBar(
        containerColor = Navy900,
        contentColor = Color.White,
        tonalElevation = 8.dp
      ) {
        NavigationBarItem(
          selected = activeTab == MainTab.VIDEO_LESSON,
          onClick = { viewModel.setTab(MainTab.VIDEO_LESSON) },
          icon = { Icon(Icons.Default.PlayCircle, contentDescription = "Video Lesson") },
          label = { Text("வீடியோ", fontSize = 10.sp) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = CyanAccent,
            selectedTextColor = CyanAccent,
            unselectedIconColor = TextSecondaryDark,
            unselectedTextColor = TextSecondaryDark,
            indicatorColor = IndigoDark
          )
        )

        NavigationBarItem(
          selected = activeTab == MainTab.EPUB_EXPLORER,
          onClick = { viewModel.setTab(MainTab.EPUB_EXPLORER) },
          icon = { Icon(Icons.Default.Layers, contentDescription = "EPUB Lab") },
          label = { Text("EPUB Lab", fontSize = 10.sp) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = CyanAccent,
            selectedTextColor = CyanAccent,
            unselectedIconColor = TextSecondaryDark,
            unselectedTextColor = TextSecondaryDark,
            indicatorColor = IndigoDark
          )
        )

        NavigationBarItem(
          selected = activeTab == MainTab.PIPELINE_WORKFLOW,
          onClick = { viewModel.setTab(MainTab.PIPELINE_WORKFLOW) },
          icon = { Icon(Icons.Default.Hub, contentDescription = "Workflow") },
          label = { Text("செயல்முறை", fontSize = 10.sp) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = CyanAccent,
            selectedTextColor = CyanAccent,
            unselectedIconColor = TextSecondaryDark,
            unselectedTextColor = TextSecondaryDark,
            indicatorColor = IndigoDark
          )
        )

        NavigationBarItem(
          selected = activeTab == MainTab.QUIZ_CHALLENGE,
          onClick = { viewModel.setTab(MainTab.QUIZ_CHALLENGE) },
          icon = { Icon(Icons.Default.School, contentDescription = "Quiz") },
          label = { Text("வினாடி-வினா", fontSize = 10.sp) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = CyanAccent,
            selectedTextColor = CyanAccent,
            unselectedIconColor = TextSecondaryDark,
            unselectedTextColor = TextSecondaryDark,
            indicatorColor = IndigoDark
          )
        )

        NavigationBarItem(
          selected = activeTab == MainTab.GLOSSARY,
          onClick = { viewModel.setTab(MainTab.GLOSSARY) },
          icon = { Icon(Icons.Default.Book, contentDescription = "Glossary") },
          label = { Text("அகராதி", fontSize = 10.sp) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = CyanAccent,
            selectedTextColor = CyanAccent,
            unselectedIconColor = TextSecondaryDark,
            unselectedTextColor = TextSecondaryDark,
            indicatorColor = IndigoDark
          )
        )
      }
    }
  ) { paddingValues ->
    Box(
      modifier = modifier
        .fillMaxSize()
        .background(Navy900)
        .padding(paddingValues)
    ) {
      when (activeTab) {
        MainTab.VIDEO_LESSON -> {
          Column(
            modifier = Modifier
              .fillMaxSize()
              .verticalScroll(rememberScrollState())
              .padding(horizontal = 14.dp, vertical = 8.dp)
          ) {
            // Video Presentation Player Stage
            VideoPlayerStage(
              scene = currentScene,
              viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Tamil Voice-Over Card with script and technical term highlights
            TamilVoiceoverCard(
              scene = currentScene,
              viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(14.dp))
          }
        }
        MainTab.EPUB_EXPLORER -> {
          EpubPackageExplorer(viewModel = viewModel)
        }
        MainTab.PIPELINE_WORKFLOW -> {
          WorkflowAccessibilityView(viewModel = viewModel)
        }
        MainTab.QUIZ_CHALLENGE -> {
          QuizScreen(viewModel = viewModel)
        }
        MainTab.GLOSSARY -> {
          GlossarySheet(viewModel = viewModel)
        }
      }

      if (showSceneDrawer) {
        SceneSelectorDrawer(
          viewModel = viewModel,
          onDismiss = { showSceneDrawer = false }
        )
      }
    }
  }
}
