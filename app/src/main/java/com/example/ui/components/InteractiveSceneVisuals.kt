package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuizQuestion
import com.example.data.TutorialRepository
import com.example.data.TutorialScene
import com.example.ui.LiveCssState
import com.example.ui.QuizState
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CssBlue
import com.example.ui.theme.CssBlueContainer
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EpubPurple
import com.example.ui.theme.EpubPurpleContainer
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.HtmlOrangeContainer
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoLight
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.OpfGreen
import com.example.ui.theme.OpfGreenContainer
import com.example.ui.theme.PdfRed
import com.example.ui.theme.PdfRedContainer
import com.example.ui.theme.SurfaceCardDark
import com.example.ui.theme.SurfaceHighlight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@Composable
fun DynamicSceneVisual(
  scene: TutorialScene,
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Navy900)
      .padding(12.dp),
    contentAlignment = Alignment.Center
  ) {
    when (scene.id) {
      1 -> Scene1IntroVisual()
      2 -> Scene2PdfVisual()
      3 -> Scene3ReflowableVisual(viewModel)
      4 -> Scene4HtmlVisual()
      5 -> Scene5CssVisual(viewModel)
      6 -> Scene6EpubVisual()
      7 -> Scene7PackageVisual()
      8 -> Scene8PackageTreeVisual(viewModel)
      9 -> Scene9MimetypeVisual()
      10 -> Scene10ContainerXmlVisual()
      11 -> Scene11XhtmlVisual()
      12 -> Scene12CssStyleVisual()
      13 -> Scene13ImagesVisual()
      14 -> Scene14OpfVisual(viewModel)
      15 -> Scene15NavVisual(viewModel)
      16 -> Scene16SummaryVisual()
      17 -> Scene17WorkflowVisual(viewModel)
      18 -> Scene18AccessibilityVisual()
      19 -> Scene19RecapVisual()
      20 -> Scene20QuizVisual(viewModel)
      21 -> Scene21FinalVisual()
      else -> Scene1IntroVisual()
    }
  }
}

// SCENE 1: Introduction Flow
@Composable
fun Scene1IntroVisual() {
  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.96f,
    targetValue = 1.04f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "scale"
  )

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text(
      text = "DIGITAL PUBLISHING TRANSFORMATION",
      color = CyanAccent,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.5.sp
    )
    Spacer(modifier = Modifier.height(8.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
    ) {
      FlowBadge(text = "PDF", color = PdfRed, icon = Icons.Default.PictureAsPdf)
      FlowArrow()
      FlowBadge(text = "HTML", color = HtmlOrange, icon = Icons.Default.Code)
      FlowArrow()
      FlowBadge(text = "CSS", color = CssBlue, icon = Icons.Default.FormatPaint)
      FlowArrow()
      FlowBadge(text = "EPUB3", color = EpubPurple, icon = Icons.Default.MenuBook, modifier = Modifier.scale(pulseScale))
    }

    Spacer(modifier = Modifier.height(14.dp))

    Surface(
      shape = RoundedCornerShape(12.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Brush.linearGradient(listOf(PdfRed, EpubPurple))),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text("PDF முதல் Accessible EPUB3 வரை", color = TextPrimaryDark, fontWeight = FontWeight.Bold, fontSize = 14.sp)
          Text("Beginner Friendly Digital Publishing Tutorial", color = CyanAccent, fontSize = 11.sp)
          Text("Structured • Reflowable • Accessible", color = TextSecondaryDark, fontSize = 10.sp)
        }
      }
    }
  }
}

// SCENE 2: What is PDF?
@Composable
fun Scene2PdfVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Computer screen mockup
    Surface(
      shape = RoundedCornerShape(10.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.5.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column {
        // Window titlebar
        Row(
          modifier = Modifier.fillMaxWidth().background(Navy700).padding(horizontal = 8.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFEF4444)))
          Spacer(modifier = Modifier.width(4.dp))
          Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFF59E0B)))
          Spacer(modifier = Modifier.width(4.dp))
          Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF10B981)))
          Spacer(modifier = Modifier.width(10.dp))
          Text("sample_document.pdf — Adobe Acrobat Reader", color = TextSecondaryDark, fontSize = 10.sp)
        }

        // Mockup PDF Page
        Surface(
          color = Color.White,
          modifier = Modifier.fillMaxWidth().padding(12.dp),
          shape = RoundedCornerShape(4.dp)
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("PDF Page 1 (Fixed Layout)", color = Color(0xFF1E293B), fontWeight = FontWeight.Bold, fontSize = 12.sp)
              Surface(color = Color(0xFFFEE2E2), shape = RoundedCornerShape(4.dp)) {
                Text("Page-Oriented", color = Color(0xFF991B1B), fontSize = 9.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontWeight = FontWeight.Bold)
              }
            }
            Spacer(modifier = Modifier.height(6.dp))

            // Highlighted components in PDF
            PdfLayerItem(name = "Heading", color = Color(0xFF2563EB))
            PdfLayerItem(name = "Text Content (Fixed X, Y positions)", color = Color(0xFF475569))
            PdfLayerItem(name = "Embedded Image (Rasterized)", color = Color(0xFF059669))
            PdfLayerItem(name = "Table Data (Lines & Boundaries)", color = Color(0xFFD97706))
            PdfLayerItem(name = "Page Layout (Margin: 1in, A4 Fixed)", color = Color(0xFFDC2626))
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = PdfRedContainer.copy(alpha = 0.4f),
      border = androidx.compose.foundation.BorderStroke(1.dp, PdfRed.copy(alpha = 0.5f))
    ) {
      Text(
        text = "PDF = Portable Document Format (Fixed Coordinates)",
        color = Color(0xFFFCA5A5),
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
      )
    }
  }
}

@Composable
private fun PdfLayerItem(name: String, color: Color) {
  Row(
    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(color))
    Spacer(modifier = Modifier.width(6.dp))
    Text(name, color = color, fontSize = 10.sp, fontWeight = FontWeight.Medium)
  }
}

// SCENE 3: PDF Problem for Reflowable Books (Interactive Resizer)
@Composable
fun Scene3ReflowableVisual(viewModel: TutorialViewModel) {
  val scale by viewModel.deviceScale.collectAsState()

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      "DEVICE RESIZE SIMULATOR",
      color = CyanAccent,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold
    )
    Text(
      "Fixed PDF shrinks & becomes unreadable vs Reflowable EPUB",
      color = TextSecondaryDark,
      fontSize = 10.sp,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Device selector chips
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      FilterChip(
        selected = scale > 0.85f,
        onClick = { viewModel.setDeviceScale(1.0f) },
        label = { Text("🖥️ Desktop (100%)", fontSize = 10.sp) },
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = IndigoDark)
      )
      FilterChip(
        selected = scale in 0.6f..0.85f,
        onClick = { viewModel.setDeviceScale(0.7f) },
        label = { Text("📱 Tablet (70%)", fontSize = 10.sp) },
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = IndigoDark)
      )
      FilterChip(
        selected = scale < 0.6f,
        onClick = { viewModel.setDeviceScale(0.45f) },
        label = { Text("📲 Mobile (45%)", fontSize = 10.sp) },
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = IndigoDark)
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Split comparison: Fixed PDF vs Reflowable EPUB
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      // Left: Fixed PDF Box
      Surface(
        modifier = Modifier.weight(1f),
        color = Navy800,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, PdfRed.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
          Text("Fixed PDF Page", color = PdfRed, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          Text("Shrinks rigidly", color = TextSecondaryDark, fontSize = 9.sp)
          Spacer(modifier = Modifier.height(6.dp))

          // Simulated page scaling down
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(95.dp)
              .background(Color(0xFF0F172A), RoundedCornerShape(4.dp))
              .padding(4.dp),
            contentAlignment = Alignment.Center
          ) {
            Surface(
              modifier = Modifier
                .width((120 * scale).dp)
                .height((80 * scale).dp),
              color = Color.White,
              shape = RoundedCornerShape(2.dp)
            ) {
              Column(modifier = Modifier.padding(4.dp)) {
                Box(modifier = Modifier.fillMaxWidth().height((6 * scale).dp).background(Color(0xFFDC2626)))
                Spacer(modifier = Modifier.height((3 * scale).dp))
                Box(modifier = Modifier.fillMaxWidth().height((4 * scale).dp).background(Color(0xFF94A3B8)))
                Spacer(modifier = Modifier.height((2 * scale).dp))
                Box(modifier = Modifier.fillMaxWidth().height((4 * scale).dp).background(Color(0xFF94A3B8)))
                if (scale < 0.6f) {
                  Text("⚠️ Too small to read!", color = Color.Red, fontSize = 7.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        }
      }

      // Right: Reflowable EPUB Box
      Surface(
        modifier = Modifier.weight(1f),
        color = Navy800,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, EpubPurple.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
          Text("Reflowable EPUB", color = EpubPurple, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          Text("Wraps fluidly", color = TextSecondaryDark, fontSize = 9.sp)
          Spacer(modifier = Modifier.height(6.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(95.dp)
              .background(Color(0xFF1E1B4B), RoundedCornerShape(4.dp))
              .padding(6.dp),
            contentAlignment = Alignment.TopStart
          ) {
            Column {
              Text("Chapter 1", color = Color(0xFFA78BFA), fontWeight = FontWeight.Bold, fontSize = 10.sp)
              Spacer(modifier = Modifier.height(3.dp))
              Text(
                "Text flows smoothly at 100% readable font size regardless of device screen width.",
                color = Color(0xFFE2E8F0),
                fontSize = 9.sp,
                lineHeight = 12.sp
              )
              Text("✅ Perfect reading experience", color = Color(0xFF34D399), fontSize = 8.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}

// SCENE 4: What is HTML?
@Composable
fun Scene4HtmlVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("HTML = HyperText Markup Language", color = HtmlOrange, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Content + Structure", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(8.dp))

    // Semantic Tags Table
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(10.dp)) {
        Text("Semantic Tags in Digital Publishing", color = TextPrimaryDark, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          CodeTagBadge("<h1>", "Heading (Chapter Title)")
          CodeTagBadge("<p>", "Paragraph block")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          CodeTagBadge("<img>", "Embedded image")
          CodeTagBadge("<ul>", "List of items")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          CodeTagBadge("<table>", "Tabular data")
          CodeTagBadge("<section>", "Chapter container")
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Code & Rendered output preview
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Color(0xFF0F172A),
      border = androidx.compose.foundation.BorderStroke(1.dp, HtmlOrange.copy(alpha = 0.4f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Text("CODE:", color = HtmlOrange, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Text(
          text = "<h1>My Book</h1>\n<p>This is my first paragraph.</p>\n<p>This is my second paragraph.</p>",
          color = Color(0xFFFDBA74),
          fontFamily = FontFamily.Monospace,
          fontSize = 10.sp
        )
      }
    }
  }
}

@Composable
private fun CodeTagBadge(tag: String, meaning: String) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(vertical = 2.dp)
  ) {
    Surface(
      color = HtmlOrangeContainer.copy(alpha = 0.7f),
      shape = RoundedCornerShape(4.dp)
    ) {
      Text(tag, color = HtmlOrange, fontFamily = FontFamily.Monospace, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
    }
    Spacer(modifier = Modifier.width(6.dp))
    Text("= $meaning", color = TextSecondaryDark, fontSize = 9.sp)
  }
}

// SCENE 5: What is CSS? (Interactive Live CSS Playground)
@Composable
fun Scene5CssVisual(viewModel: TutorialViewModel) {
  val cssState by viewModel.liveCssState.collectAsState()

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Analogy Banner
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, CssBlue.copy(alpha = 0.5f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(36.dp).background(CssBlueContainer, CircleShape), contentAlignment = Alignment.Center) {
          Icon(Icons.Default.Home, contentDescription = null, tint = CssBlue)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text("House Analogy (வீட்டு ஒப்புமை)", color = TextPrimaryDark, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          Text("HTML = வீட்டின் Structure (Walls & Frame)", color = HtmlOrange, fontSize = 10.sp)
          Text("CSS = வீட்டின் Design & Decoration (Paint & Lights)", color = CssBlue, fontSize = 10.sp)
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Live Rendered Heading
    Surface(
      color = Color.White,
      shape = RoundedCornerShape(6.dp),
      modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp),
        contentAlignment = when (cssState.textAlign) {
          "left" -> Alignment.CenterStart
          "right" -> Alignment.CenterEnd
          else -> Alignment.Center
        }
      ) {
        Text(
          text = "My Book Chapter 1",
          color = Color(cssState.colorHex),
          fontSize = cssState.fontSize.sp,
          fontWeight = if (cssState.isBold) FontWeight.Bold else FontWeight.Normal,
          textAlign = when (cssState.textAlign) {
            "left" -> TextAlign.Left
            "right" -> TextAlign.Right
            else -> TextAlign.Center
          }
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Interactive CSS Toggles
    Row(
      modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      OutlinedButton(
        onClick = {
          viewModel.updateLiveCss {
            it.copy(fontSize = if (it.fontSize == 24f) 16f else 24f)
          }
        },
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text("Font: ${cssState.fontSize.toInt()}sp", fontSize = 10.sp)
      }

      OutlinedButton(
        onClick = {
          viewModel.updateLiveCss {
            val newColor = when (it.colorHex) {
              0xFF1E3A8AL -> 0xFFDC2626L
              0xFFDC2626L -> 0xFF059669L
              else -> 0xFF1E3A8AL
            }
            it.copy(colorHex = newColor)
          }
        },
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text("Color", fontSize = 10.sp)
      }

      OutlinedButton(
        onClick = {
          viewModel.updateLiveCss {
            val nextAlign = when (it.textAlign) {
              "left" -> "center"
              "center" -> "right"
              else -> "left"
            }
            it.copy(textAlign = nextAlign)
          }
        },
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text("Align: ${cssState.textAlign}", fontSize = 10.sp)
      }

      OutlinedButton(
        onClick = {
          viewModel.updateLiveCss { it.copy(isBold = !it.isBold) }
        },
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text(if (cssState.isBold) "Bold" else "Regular", fontSize = 10.sp)
      }
    }
  }
}

// SCENE 6: What is EPUB?
@Composable
fun Scene6EpubVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("EPUB = Electronic Publication", color = EpubPurple, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("The Open Standard for Digital Books", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(10.dp))

    // Publication Formula Grid
    Surface(
      shape = RoundedCornerShape(10.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, EpubPurple.copy(alpha = 0.4f)),
      modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
    ) {
      Column(modifier = Modifier.padding(12.dp)) {
        Text("EPUB PUBLICATION COMPOSITION", color = CyanAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
          FormulaItem("XHTML", "Structured Content", HtmlOrange)
          Text(" + ", color = Color.White, fontWeight = FontWeight.Bold)
          FormulaItem("CSS", "Presentation", CssBlue)
          Text(" + ", color = Color.White, fontWeight = FontWeight.Bold)
          FormulaItem("Images", "Visual Assets", Color(0xFF10B981))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          FormulaItem("Metadata", "Title, Author, ISBN", AmberAccent)
          Text(" + ", color = Color.White, fontWeight = FontWeight.Bold)
          FormulaItem("Navigation", "Table of Contents", CyanAccent)
          Text(" = ", color = Color.White, fontWeight = FontWeight.Bold)
          FormulaItem("EPUB3", "Complete Book", EpubPurple)
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))
    Text("Reads on: E-readers • Android Phones • Tablets • Desktop Apps", color = TextSecondaryDark, fontSize = 9.sp)
  }
}

@Composable
private fun FormulaItem(title: String, subtitle: String, color: Color) {
  Surface(
    shape = RoundedCornerShape(6.dp),
    color = color.copy(alpha = 0.2f),
    border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
    modifier = Modifier.padding(2.dp)
  ) {
    Column(modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)) {
      Text(title, color = color, fontWeight = FontWeight.Bold, fontSize = 9.sp)
    }
  }
}

// SCENE 7: EPUB is a Package
@Composable
fun Scene7PackageVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("EPUB IS A ZIP PACKAGE", color = CyanAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Rename 'book.epub' to 'book.zip' to explore its contents", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(10.dp))

    // Unpack Animation Visual
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth()
    ) {
      Surface(
        color = EpubPurpleContainer,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, EpubPurple)
      ) {
        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
          Icon(Icons.Default.InsertDriveFile, contentDescription = null, tint = EpubPurple, modifier = Modifier.size(32.dp))
          Text("book.epub", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          Text("Single File", color = Color(0xFFDDD6FE), fontSize = 9.sp)
        }
      }

      Icon(Icons.Default.ArrowForward, contentDescription = null, tint = CyanAccent, modifier = Modifier.padding(horizontal = 8.dp))

      Surface(
        color = Navy800,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, CyanAccent)
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Text("ZIP Archive Internal Structure:", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
          Spacer(modifier = Modifier.height(4.dp))
          Text("├── mimetype", color = TextPrimaryDark, fontFamily = FontFamily.Monospace, fontSize = 10.sp)
          Text("├── META-INF/", color = AmberAccent, fontFamily = FontFamily.Monospace, fontSize = 10.sp)
          Text("└── EPUB/", color = EpubPurple, fontFamily = FontFamily.Monospace, fontSize = 10.sp)
        }
      }
    }
  }
}

// SCENE 8: Complete EPUB Package Tree
@Composable
fun Scene8PackageTreeVisual(viewModel: TutorialViewModel) {
  val selectedFile by viewModel.selectedEpubFile.collectAsState()

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text(
      "COMPLETE EPUB PACKAGE EXPLORER",
      color = CyanAccent,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold
    )
    Text("Tap any file to inspect purpose & structure", color = TextSecondaryDark, fontSize = 9.sp)

    Spacer(modifier = Modifier.height(6.dp))

    // Horizontal list of files in EPUB
    Row(
      modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      TutorialRepository.sampleEpubFiles.forEach { file ->
        val isSelected = selectedFile.path == file.path
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = if (isSelected) IndigoDark else Navy800,
          border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CyanAccent else BorderSubtle),
          modifier = Modifier.clickable { viewModel.selectEpubFile(file) }
        ) {
          Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
              if (file.isDirectory) Icons.Default.Folder else Icons.Default.InsertDriveFile,
              contentDescription = null,
              tint = if (isSelected) Color.White else TextSecondaryDark,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(file.name, color = if (isSelected) Color.White else TextPrimaryDark, fontSize = 10.sp)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    // Selected file detail card
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Color(0xFF0F172A),
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text(selectedFile.path, color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 11.sp)
          Badge(containerColor = IndigoDark) {
            Text(selectedFile.extension, color = Color.White, fontSize = 9.sp)
          }
        }
        Text(selectedFile.purposeTa, color = TextPrimaryDark, fontSize = 10.sp, modifier = Modifier.padding(vertical = 4.dp))
        Text(selectedFile.purposeEn, color = TextSecondaryDark, fontSize = 9.sp)

        Spacer(modifier = Modifier.height(4.dp))
        Text("CODE PREVIEW:", color = AmberAccent, fontSize = 8.sp, fontWeight = FontWeight.Bold)
        Text(
          text = selectedFile.codeSnippet.take(160) + if (selectedFile.codeSnippet.length > 160) "\n..." else "",
          color = Color(0xFFE2E8F0),
          fontFamily = FontFamily.Monospace,
          fontSize = 9.sp,
          maxLines = 4,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}

// SCENE 9: mimetype
@Composable
fun Scene9MimetypeVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("FILE: mimetype", color = AmberAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("First file in the ZIP container at byte offset 38", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(10.dp))

    Surface(
      color = Color(0xFF0F172A),
      shape = RoundedCornerShape(8.dp),
      border = androidx.compose.foundation.BorderStroke(1.5.dp, AmberAccent),
      modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
      Column(modifier = Modifier.padding(12.dp)) {
        Text("EXACT FILE CONTENT:", color = TextSecondaryDark, fontSize = 9.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Surface(color = Color.Black, shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "application/epub+zip",
            color = Color(0xFF34D399),
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("⚠️ Strict Rules:", color = AmberAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Text("• Must NOT be compressed (Stored compression level 0)", color = TextPrimaryDark, fontSize = 9.sp)
        Text("• No trailing spaces or newlines allowed", color = TextPrimaryDark, fontSize = 9.sp)
        Text("• Allows operating systems to instantly identify EPUB", color = TextSecondaryDark, fontSize = 9.sp)
      }
    }
  }
}

// SCENE 10: container.xml
@Composable
fun Scene10ContainerXmlVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("META-INF / container.xml", color = CyanAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("The Bootstrap Pointer for EPUB Readers", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Surface(
        color = Navy800,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, CyanAccent),
        modifier = Modifier.weight(1f)
      ) {
        Column(modifier = Modifier.padding(8.dp)) {
          Text("container.xml", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
          Text("<rootfile full-path=\"EPUB/package.opf\" .../>", color = Color(0xFF93C5FD), fontFamily = FontFamily.Monospace, fontSize = 9.sp)
        }
      }

      Icon(Icons.Default.ArrowForward, contentDescription = null, tint = AmberAccent, modifier = Modifier.padding(horizontal = 6.dp))

      Surface(
        color = Navy800,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, AmberAccent),
        modifier = Modifier.weight(1f)
      ) {
        Column(modifier = Modifier.padding(8.dp)) {
          Text("EPUB/package.opf", color = AmberAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
          Text("Master rules, metadata, and reading order", color = TextSecondaryDark, fontSize = 9.sp)
        }
      }
    }
  }
}

// SCENE 11: XHTML Content
@Composable
fun Scene11XhtmlVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text("XHTML = BOOK CONTENT", color = HtmlOrange, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Semantic XML-compliant Chapter Files", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(6.dp))

    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Color(0xFF0F172A),
      border = androidx.compose.foundation.BorderStroke(1.dp, HtmlOrange.copy(alpha = 0.5f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Text("chapter01.xhtml", color = HtmlOrange, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Text(
          text = "<section epub:type=\"chapter\">\n  <h1>Chapter 1</h1>\n  <p>This is the first paragraph with semantic tagging.</p>\n</section>",
          color = Color(0xFFFDBA74),
          fontFamily = FontFamily.Monospace,
          fontSize = 9.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(6.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
      Badge(containerColor = HtmlOrangeContainer) { Text("Well-formed XML", color = Color.White, fontSize = 9.sp) }
      Badge(containerColor = IndigoDark) { Text("Accessible Headings", color = Color.White, fontSize = 9.sp) }
      Badge(containerColor = OpfGreenContainer) { Text("Reflowable", color = Color.White, fontSize = 9.sp) }
    }
  }
}

// SCENE 12: CSS Styling
@Composable
fun Scene12CssVisual() {
  Scene12CssStyleVisual()
}

@Composable
fun Scene12CssStyleVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text("css/style.css — Digital Typography", color = CssBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Controls presentation, fonts, alignment, and spacing", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(6.dp))

    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Color(0xFF0F172A),
      border = androidx.compose.foundation.BorderStroke(1.dp, CssBlue.copy(alpha = 0.5f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Text("style.css", color = CssBlue, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Text(
          text = "body {\n  font-family: serif;\n  line-height: 1.6;\n  margin: 5%;\n}\nh1 {\n  color: #1E3A8A;\n  text-align: center;\n}",
          color = Color(0xFF93C5FD),
          fontFamily = FontFamily.Monospace,
          fontSize = 9.sp
        )
      }
    }
  }
}

// SCENE 13: Images & Media
@Composable
fun Scene13ImagesVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("images/ — Visual Assets & Alt Text", color = Color(0xFF10B981), fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Cover, figures, and charts referenced inside XHTML", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
    ) {
      ImageFileCard("cover.jpg", "Book Cover", Color(0xFF10B981))
      ImageFileCard("figure01.png", "Package Diagram", CyanAccent)
      ImageFileCard("chart01.png", "Workflow Chart", AmberAccent)
    }

    Spacer(modifier = Modifier.height(8.dp))
    Surface(
      color = Navy800,
      shape = RoundedCornerShape(6.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = "<img src=\"../images/figure01.png\" alt=\"Detailed diagram of EPUB structure\" />",
        color = Color(0xFF6EE7B7),
        fontFamily = FontFamily.Monospace,
        fontSize = 9.sp,
        modifier = Modifier.padding(6.dp)
      )
    }
  }
}

@Composable
private fun ImageFileCard(name: String, label: String, color: Color) {
  Surface(
    shape = RoundedCornerShape(6.dp),
    color = Navy800,
    border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f))
  ) {
    Column(modifier = Modifier.padding(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
      Icon(Icons.Default.Image, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
      Text(name, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
      Text(label, color = TextSecondaryDark, fontSize = 8.sp)
    }
  }
}

// SCENE 14: package.opf (Metadata, Manifest, Spine)
@Composable
fun Scene14OpfVisual(viewModel: TutorialViewModel) {
  val selectedTab by viewModel.selectedOpfTab.collectAsState()

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text("package.opf — The Heart of EPUB", color = OpfGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Metadata + Manifest + Spine", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(6.dp))

    TabRow(
      selectedTabIndex = selectedTab,
      containerColor = Navy800,
      contentColor = OpfGreen,
      indicator = { tabPositions ->
        TabRowDefaults.SecondaryIndicator(
          modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
          color = OpfGreen
        )
      }
    ) {
      Tab(
        selected = selectedTab == 0,
        onClick = { viewModel.setOpfTab(0) },
        text = { Text("1. Metadata", fontSize = 10.sp) }
      )
      Tab(
        selected = selectedTab == 1,
        onClick = { viewModel.setOpfTab(1) },
        text = { Text("2. Manifest", fontSize = 10.sp) }
      )
      Tab(
        selected = selectedTab == 2,
        onClick = { viewModel.setOpfTab(2) },
        text = { Text("3. Spine", fontSize = 10.sp) }
      )
    }

    Spacer(modifier = Modifier.height(6.dp))

    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Color(0xFF0F172A),
      border = androidx.compose.foundation.BorderStroke(1.dp, OpfGreen.copy(alpha = 0.4f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        when (selectedTab) {
          0 -> {
            Text("<metadata>", color = OpfGreen, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            Text("Book title, author, identifier (ISBN), language (ta/en), accessibility metadata.", color = TextPrimaryDark, fontSize = 9.sp)
            Text("<dc:title>Sample Book</dc:title>\n<dc:language>ta</dc:language>", color = Color(0xFF6EE7B7), fontFamily = FontFamily.Monospace, fontSize = 9.sp)
          }
          1 -> {
            Text("<manifest>", color = OpfGreen, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            Text("Complete inventory listing EVERY file with unique ID and media-type.", color = TextPrimaryDark, fontSize = 9.sp)
            Text("<item id=\"c1\" href=\"xhtml/ch01.xhtml\" media-type=\"application/xhtml+xml\"/>", color = Color(0xFF6EE7B7), fontFamily = FontFamily.Monospace, fontSize = 9.sp)
          }
          2 -> {
            Text("<spine> = Reading Order", color = AmberAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            Text("Specifies the linear sequence for flipping through chapters.", color = TextPrimaryDark, fontSize = 9.sp)
            Text("<spine>\n  <itemref idref=\"c1\"/> <!-- Chapter 1 -->\n  <itemref idref=\"c2\"/> <!-- Chapter 2 -->\n</spine>", color = Color(0xFFFDE68A), fontFamily = FontFamily.Monospace, fontSize = 9.sp)
          }
        }
      }
    }
  }
}

// SCENE 15: nav.xhtml
@Composable
fun Scene15NavVisual(viewModel: TutorialViewModel) {
  val jumpedChapter by viewModel.jumpedChapter.collectAsState()

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text("nav.xhtml = Table of Contents", color = CyanAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("Interactive navigation document for e-readers", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(6.dp))

    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Text("TABLE OF CONTENTS (TOC)", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Spacer(modifier = Modifier.height(4.dp))

        TocChapterItem("Chapter 1: What is PDF?", isSelected = jumpedChapter == 1) {
          viewModel.setJumpedChapter(1)
        }
        TocChapterItem("Chapter 2: HTML & CSS Foundations", isSelected = jumpedChapter == 2) {
          viewModel.setJumpedChapter(2)
        }
        TocChapterItem("Chapter 3: EPUB Packaging & OPF", isSelected = jumpedChapter == 3) {
          viewModel.setJumpedChapter(3)
        }
      }
    }

    Spacer(modifier = Modifier.height(6.dp))
    Surface(color = Color(0xFF0F172A), shape = RoundedCornerShape(6.dp), modifier = Modifier.fillMaxWidth()) {
      Text(
        "Reader Jumped to: Chapter $jumpedChapter content loaded successfully!",
        color = Color(0xFF34D399),
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(6.dp)
      )
    }
  }
}

@Composable
private fun TocChapterItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
  Surface(
    color = if (isSelected) IndigoDark else Color.Transparent,
    shape = RoundedCornerShape(4.dp),
    modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(Icons.Default.Navigation, contentDescription = null, tint = if (isSelected) Color.White else TextSecondaryDark, modifier = Modifier.size(12.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text(title, color = if (isSelected) Color.White else TextPrimaryDark, fontSize = 10.sp)
    }
  }
}

// SCENE 16: Complete Summary Map
@Composable
fun Scene16SummaryVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("EPUB COMPLETE PACKAGE SUMMARY", color = CyanAccent, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    Text("All components operating in harmony", color = TextSecondaryDark, fontSize = 10.sp)

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      SummaryStepBadge("1. mimetype", AmberAccent)
      FlowArrow()
      SummaryStepBadge("2. container.xml", CyanAccent)
      FlowArrow()
      SummaryStepBadge("3. package.opf", OpfGreen)
      FlowArrow()
      SummaryStepBadge("4. nav.xhtml", IndigoLight)
      FlowArrow()
      SummaryStepBadge("5. XHTML+CSS", EpubPurple)
    }

    Spacer(modifier = Modifier.height(8.dp))
    Surface(color = Navy800, shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
      Text(
        "Result: A standardized, reflowable, portable digital publication ready for distribution on all global e-reading platforms.",
        color = TextPrimaryDark,
        fontSize = 10.sp,
        modifier = Modifier.padding(8.dp),
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
private fun SummaryStepBadge(text: String, color: Color) {
  Surface(
    color = color.copy(alpha = 0.2f),
    shape = RoundedCornerShape(6.dp),
    border = androidx.compose.foundation.BorderStroke(1.dp, color)
  ) {
    Text(text, color = color, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp))
  }
}

// SCENE 17: PDF to EPUB Complete Workflow (Interactive 14-Step Pipeline)
@Composable
fun Scene17WorkflowVisual(viewModel: TutorialViewModel) {
  val selectedStep by viewModel.selectedWorkflowStep.collectAsState()

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text("PDF TO EPUB3 COMPLETE WORKFLOW", color = CyanAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    Text("14-Step Production Pipeline (Never raw PDF dump)", color = TextSecondaryDark, fontSize = 9.sp)

    Spacer(modifier = Modifier.height(6.dp))

    // Step selector chips
    Row(
      modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      TutorialRepository.workflowSteps.forEachIndexed { index, step ->
        val isSelected = index == selectedStep
        FilterChip(
          selected = isSelected,
          onClick = { viewModel.selectWorkflowStep(index) },
          label = { Text(step, fontSize = 9.sp) },
          colors = FilterChipDefaults.filterChipColors(selectedContainerColor = IndigoDark)
        )
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    // Step detail description
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Text("Active Pipeline Stage: ${TutorialRepository.workflowSteps[selectedStep]}", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        Spacer(modifier = Modifier.height(4.dp))
        val stepDetail = when (selectedStep) {
          0 -> "PDF Analysis: Inspect pages, columns, margins, font tables, and embedded raster images."
          1 -> "Text Extraction: Extract unicode text streams while filtering out running headers and footers."
          2 -> "Reading Order Detection: Crucial for multi-column pages to avoid intermingled text flow."
          3 -> "Heading Classification: Map visual font sizes into <h1> through <h6> semantic hierarchy."
          4 -> "Paragraph & List: Group lines into cohesive paragraphs and reconstruct bulleted/numbered lists."
          5 -> "Table Structuring: Convert visual grid coordinates into valid <table>, <tr>, <th>, <td> tags."
          6 -> "XHTML Generation: Write clean, XML-compliant chapter files with valid closing tags."
          7 -> "CSS Styling: Create responsive reading stylesheet for fonts, spacing, and night/day themes."
          8 -> "Alt-Text: Write descriptive text for images and figures to meet EPUB Accessibility guidelines."
          9 -> "Metadata: Specify title, author, ISBN, modified date, and Tamil language tag (ta)."
          10 -> "Navigation: Generate nav.xhtml with ordered list table of contents for reader navigation."
          11 -> "Master OPF: Register all files in the manifest and specify linear reading order in the spine."
          12 -> "Packaging: Create ZIP archive starting with uncompressed mimetype file at byte 38."
          13 -> "Validation: Run automated EPUBCheck to verify schema compliance and zero errors."
          else -> "Standard step execution."
        }
        Text(stepDetail, color = TextPrimaryDark, fontSize = 10.sp, lineHeight = 14.sp)
      }
    }
  }
}

// SCENE 18: Accessible EPUB3
@Composable
fun Scene18AccessibilityVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.Accessibility, contentDescription = null, tint = Color(0xFF34D399), modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(4.dp))
      Text("ACCESSIBLE EPUB3 STANDARDS", color = Color(0xFF34D399), fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
    Text("Inclusive Digital Publishing for All Learners", color = TextSecondaryDark, fontSize = 9.sp)

    Spacer(modifier = Modifier.height(6.dp))

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      AccessibilityBadge("Semantic HTML", "<section>, <header>, <main> tags for screen readers")
      AccessibilityBadge("Image Alt Text", "Descriptive alternative descriptions for non-sighted readers")
      AccessibilityBadge("Reading Order", "Single logical progression across multi-column texts")
      AccessibilityBadge("Language Tagging", "xml:lang=\"ta\" for Indian Tamil text-to-speech engines")
      AccessibilityBadge("Accessibility Metadata", "Schema.org accessMode and feature declarations in OPF")
    }
  }
}

@Composable
private fun AccessibilityBadge(title: String, desc: String) {
  Surface(
    shape = RoundedCornerShape(6.dp),
    color = Navy800,
    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF059669).copy(alpha = 0.5f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF34D399), modifier = Modifier.size(14.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Column {
        Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Text(desc, color = TextSecondaryDark, fontSize = 8.sp)
      }
    }
  }
}

// SCENE 19: Final Recap Cards
@Composable
fun Scene19RecapVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Text("7 CORE CONCEPTS RECAP", color = CyanAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))

    RecapRow("PDF", "Page-oriented document (Fixed layout)", PdfRed)
    RecapRow("HTML/XHTML", "Content + Semantic Structure", HtmlOrange)
    RecapRow("CSS", "Presentation & Styling rules", CssBlue)
    RecapRow("EPUB", "Digital publication package (ZIP archive)", EpubPurple)
    RecapRow("OPF", "Package Info + Resources (Manifest) + Reading Order (Spine)", OpfGreen)
    RecapRow("NAV", "Interactive Table of Contents (nav.xhtml)", IndigoLight)
    RecapRow("EPUB3", "Reflowable + Accessible modern digital book", AmberAccent)
  }
}

@Composable
private fun RecapRow(term: String, desc: String, color: Color) {
  Surface(
    shape = RoundedCornerShape(4.dp),
    color = Navy800,
    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
  ) {
    Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
      Surface(
        color = color.copy(alpha = 0.2f),
        shape = RoundedCornerShape(4.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color)
      ) {
        Text(term, color = color, fontWeight = FontWeight.Bold, fontSize = 9.sp, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
      }
      Spacer(modifier = Modifier.width(6.dp))
      Text(desc, color = TextPrimaryDark, fontSize = 9.sp)
    }
  }
}

// SCENE 20: Quiz Visual
@Composable
fun Scene20QuizVisual(viewModel: TutorialViewModel) {
  val quizState by viewModel.quizState.collectAsState()
  var currentQuestionIndex by remember { mutableStateOf(0) }
  val questions = viewModel.quizQuestions
  val question = questions[currentQuestionIndex]

  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text("KNOWLEDGE CHECK (${currentQuestionIndex + 1}/${questions.size})", color = AmberAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold)
      if (quizState.isSubmitted) {
        Surface(color = OpfGreenContainer, shape = RoundedCornerShape(4.dp)) {
          Text("Score: ${quizState.score}/${questions.size}", color = Color.White, fontSize = 9.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
        }
      }
    }

    Spacer(modifier = Modifier.height(4.dp))

    // Question Box
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        Text(question.questionTa, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        Text(question.questionEn, color = TextSecondaryDark, fontSize = 9.sp)

        Spacer(modifier = Modifier.height(6.dp))

        // Options
        question.options.forEachIndexed { optIndex, optionText ->
          val isSelected = quizState.userAnswers[question.id] == optIndex
          val isCorrect = optIndex == question.correctIndex
          val optionColor = when {
            quizState.isSubmitted && isCorrect -> Color(0xFF10B981)
            quizState.isSubmitted && isSelected && !isCorrect -> Color(0xFFEF4444)
            isSelected -> CyanAccent
            else -> TextPrimaryDark
          }

          Surface(
            color = if (isSelected) IndigoDark else Color(0xFF0F172A),
            shape = RoundedCornerShape(4.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) optionColor else BorderSubtle),
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp).clickable {
              if (!quizState.isSubmitted) {
                viewModel.answerQuizQuestion(question.id, optIndex)
              }
            }
          ) {
            Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
              Text(optionText, color = optionColor, fontSize = 10.sp)
            }
          }
        }

        // Show explanation if submitted
        if (quizState.isSubmitted) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(question.explanationTa, color = CyanAccent, fontSize = 9.sp)
        }
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      OutlinedButton(
        onClick = { if (currentQuestionIndex > 0) currentQuestionIndex-- },
        enabled = currentQuestionIndex > 0,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp)
      ) {
        Text("Previous", fontSize = 9.sp)
      }

      if (!quizState.isSubmitted) {
        Button(
          onClick = { viewModel.submitQuiz() },
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
          colors = ButtonDefaults.buttonColors(containerColor = AmberAccent)
        ) {
          Text("Check Answers", color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        }
      } else {
        OutlinedButton(
          onClick = { viewModel.resetQuiz() },
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp)
        ) {
          Text("Retry Quiz", fontSize = 9.sp)
        }
      }

      OutlinedButton(
        onClick = { if (currentQuestionIndex < questions.size - 1) currentQuestionIndex++ },
        enabled = currentQuestionIndex < questions.size - 1,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp)
      ) {
        Text("Next Q", fontSize = 9.sp)
      }
    }
  }
}

// SCENE 21: Final Message & Next Steps
@Composable
fun Scene21FinalVisual() {
  Column(
    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(Icons.Default.School, contentDescription = null, tint = AmberAccent, modifier = Modifier.size(36.dp))
    Spacer(modifier = Modifier.height(4.dp))
    Text("FOUNDATIONS COMPLETE!", color = AmberAccent, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    Text("You now understand PDF, HTML, CSS, and EPUB Packaging", color = TextSecondaryDark, fontSize = 9.sp)

    Spacer(modifier = Modifier.height(8.dp))

    Surface(
      shape = RoundedCornerShape(8.dp),
      color = Navy800,
      border = androidx.compose.foundation.BorderStroke(1.5.dp, CyanAccent),
      modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
      Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("NEXT LESSON PREVIEW:", color = CyanAccent, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Text("Next Lesson → PDF Structure & Reading Order", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text("We will extract text from real PDFs and author package.opf & nav.xhtml practically!", color = TextSecondaryDark, fontSize = 9.sp, textAlign = TextAlign.Center)
      }
    }
  }
}

@Composable
private fun FlowBadge(text: String, color: Color, icon: ImageVector, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(8.dp),
    color = color.copy(alpha = 0.2f),
    border = androidx.compose.foundation.BorderStroke(1.5.dp, color)
  ) {
    Column(
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
      Spacer(modifier = Modifier.height(2.dp))
      Text(text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp)
    }
  }
}

@Composable
private fun FlowArrow() {
  Icon(
    Icons.Default.ArrowForward,
    contentDescription = null,
    tint = TextMuted,
    modifier = Modifier.padding(horizontal = 2.dp).size(14.dp)
  )
}
