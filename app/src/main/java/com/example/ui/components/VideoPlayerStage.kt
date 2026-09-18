package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Badge
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TutorialScene
import com.example.ui.TutorialViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.IndigoDark
import com.example.ui.theme.IndigoPrimary
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy800
import com.example.ui.theme.Navy900
import com.example.ui.theme.SurfaceCardDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark

@Composable
fun VideoPlayerStage(
  scene: TutorialScene,
  viewModel: TutorialViewModel,
  modifier: Modifier = Modifier
) {
  val isPlaying by viewModel.isPlaying.collectAsState()
  val isSpeaking by viewModel.isSpeaking.collectAsState()
  val sceneProgress by viewModel.sceneProgressSeconds.collectAsState()
  val autoPlay by viewModel.autoPlay.collectAsState()
  val playbackSpeed by viewModel.playbackSpeed.collectAsState()

  var showSpeedMenu by remember { mutableStateOf(false) }

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = Navy800,
    border = androidx.compose.foundation.BorderStroke(1.5.dp, BorderSubtle),
    modifier = modifier.fillMaxWidth()
  ) {
    Column {
      // Top Video Info Bar
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Navy900)
          .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            color = IndigoDark,
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = "SCENE ${scene.id.toString().padStart(2, '0')}/21",
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 10.sp,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = scene.category.titleTa,
            color = CyanAccent,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          if (isSpeaking) {
            SoundwaveIndicator()
            Spacer(modifier = Modifier.width(6.dp))
          }
          Surface(
            color = Color(0xFF1E293B),
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = "1080p HD",
              color = TextSecondaryDark,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
          }
        }
      }

      // Visual Content Canvas (Video Viewport)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(230.dp)
          .background(Navy900)
      ) {
        DynamicSceneVisual(
          scene = scene,
          viewModel = viewModel,
          modifier = Modifier.fillMaxSize()
        )
      }

      // Transport Control Bar
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Navy800)
          .padding(horizontal = 12.dp, vertical = 6.dp)
      ) {
        // Scrub Slider
        val totalSec = scene.durationSeconds.toFloat()
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = formatTime(sceneProgress.toInt()),
            color = CyanAccent,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
          )
          Slider(
            value = sceneProgress.coerceIn(0f, totalSec),
            onValueChange = { viewModel.seekTo(it) },
            valueRange = 0f..totalSec,
            colors = SliderDefaults.colors(
              thumbColor = CyanAccent,
              activeTrackColor = CyanAccent,
              inactiveTrackColor = Navy700
            ),
            modifier = Modifier
              .weight(1f)
              .padding(horizontal = 8.dp)
          )
          Text(
            text = formatTime(totalSec.toInt()),
            color = TextSecondaryDark,
            fontSize = 10.sp
          )
        }

        // Playback Buttons & Action Chips
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Left: Transport (Prev, Play/Pause, Next)
          Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
              onClick = { viewModel.previousScene() },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(Icons.Default.SkipPrevious, contentDescription = "Previous Scene", tint = Color.White)
            }

            Box(
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Brush.linearGradient(listOf(IndigoPrimary, CyanAccent)))
                .clickable { viewModel.togglePlayPause() },
              contentAlignment = Alignment.Center
            ) {
              Icon(
                if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
              )
            }

            IconButton(
              onClick = { viewModel.nextScene() },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(Icons.Default.SkipNext, contentDescription = "Next Scene", tint = Color.White)
            }
          }

          // Right: Speed, Voice-Over Audio, AutoAdvance
          Row(verticalAlignment = Alignment.CenterVertically) {
            // Speed Dropdown Trigger
            Box {
              Surface(
                color = Navy700,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.clickable { showSpeedMenu = true }
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(Icons.Default.Speed, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(12.dp))
                  Spacer(modifier = Modifier.width(3.dp))
                  Text("${playbackSpeed}x", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
              }

              DropdownMenu(
                expanded = showSpeedMenu,
                onDismissRequest = { showSpeedMenu = false }
              ) {
                listOf(0.75f, 1.0f, 1.25f, 1.5f).forEach { speed ->
                  DropdownMenuItem(
                    text = { Text("${speed}x") },
                    onClick = {
                      viewModel.setPlaybackSpeed(speed)
                      showSpeedMenu = false
                    }
                  )
                }
              }
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Tamil Voice Toggle
            Surface(
              color = if (isSpeaking) IndigoDark else Navy700,
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.clickable { viewModel.toggleVoiceOver() }
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  Icons.Default.VolumeUp,
                  contentDescription = null,
                  tint = if (isSpeaking) CyanAccent else TextSecondaryDark,
                  modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text("Tamil Voice", color = if (isSpeaking) Color.White else TextSecondaryDark, fontSize = 10.sp)
              }
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Autoplay Toggle
            Surface(
              color = if (autoPlay) Color(0xFF065F46) else Navy700,
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.clickable { viewModel.toggleAutoPlay() }
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  Icons.Default.Repeat,
                  contentDescription = null,
                  tint = if (autoPlay) Color(0xFF34D399) else TextSecondaryDark,
                  modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text("Auto", color = if (autoPlay) Color.White else TextSecondaryDark, fontSize = 10.sp)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun SoundwaveIndicator() {
  val infiniteTransition = rememberInfiniteTransition(label = "wave")
  val height1 by infiniteTransition.animateFloat(
    initialValue = 4f,
    targetValue = 14f,
    animationSpec = infiniteRepeatable(tween(350, easing = LinearEasing), RepeatMode.Reverse),
    label = "h1"
  )
  val height2 by infiniteTransition.animateFloat(
    initialValue = 12f,
    targetValue = 4f,
    animationSpec = infiniteRepeatable(tween(280, easing = LinearEasing), RepeatMode.Reverse),
    label = "h2"
  )
  val height3 by infiniteTransition.animateFloat(
    initialValue = 6f,
    targetValue = 16f,
    animationSpec = infiniteRepeatable(tween(420, easing = LinearEasing), RepeatMode.Reverse),
    label = "h3"
  )

  Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
    Box(modifier = Modifier.width(2.5.dp).height(height1.dp).background(CyanAccent, CircleShape))
    Box(modifier = Modifier.width(2.5.dp).height(height2.dp).background(IndigoPrimary, CircleShape))
    Box(modifier = Modifier.width(2.5.dp).height(height3.dp).background(CyanAccent, CircleShape))
  }
}

private fun formatTime(seconds: Int): String {
  val mins = seconds / 60
  val secs = seconds % 60
  return "${mins}:${secs.toString().padStart(2, '0')}"
}
