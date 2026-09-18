package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.EpubFileItem
import com.example.data.GlossaryItem
import com.example.data.QuizQuestion
import com.example.data.TutorialRepository
import com.example.data.TutorialScene
import com.example.tts.TutorialNarrator
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class MainTab {
  VIDEO_LESSON,
  EPUB_EXPLORER,
  PIPELINE_WORKFLOW,
  QUIZ_CHALLENGE,
  GLOSSARY
}

data class QuizState(
  val userAnswers: Map<Int, Int> = emptyMap(), // questionId -> selectedOptionIndex
  val isSubmitted: Boolean = false,
  val score: Int = 0
)

data class LiveCssState(
  val fontSize: Float = 24f,
  val colorHex: Long = 0xFF1E3A8AL,
  val textAlign: String = "center", // "left", "center", "right"
  val isBold: Boolean = true,
  val showBorder: Boolean = false,
  val backgroundColorHex: Long = 0x00000000L
)

class TutorialViewModel(application: Application) : AndroidViewModel(application) {

  private val narrator = TutorialNarrator(application)
  val isSpeaking = narrator.isSpeaking
  val isTamilSupported = narrator.isTamilSupported
  val speechRate = narrator.speechRate

  val scenes: List<TutorialScene> = TutorialRepository.scenes
  val quizQuestions: List<QuizQuestion> = TutorialRepository.quizQuestions
  val sampleEpubFiles: List<EpubFileItem> = TutorialRepository.sampleEpubFiles
  val glossaryItems: List<GlossaryItem> = TutorialRepository.glossaryItems

  private val _currentSceneIndex = MutableStateFlow(0)
  val currentSceneIndex: StateFlow<Int> = _currentSceneIndex.asStateFlow()

  val currentScene: StateFlow<TutorialScene> = MutableStateFlow(scenes[0])

  private val _isPlaying = MutableStateFlow(false)
  val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

  private val _sceneProgressSeconds = MutableStateFlow(0f)
  val sceneProgressSeconds: StateFlow<Float> = _sceneProgressSeconds.asStateFlow()

  private val _autoPlay = MutableStateFlow(true)
  val autoPlay: StateFlow<Boolean> = _autoPlay.asStateFlow()

  private val _playbackSpeed = MutableStateFlow(1.0f)
  val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

  private val _activeTab = MutableStateFlow(MainTab.VIDEO_LESSON)
  val activeTab: StateFlow<MainTab> = _activeTab.asStateFlow()

  // Scene 3 Interactive Device Resizer (1.0 = Desktop, 0.7 = Tablet, 0.4 = Mobile)
  private val _deviceScale = MutableStateFlow(1.0f)
  val deviceScale: StateFlow<Float> = _deviceScale.asStateFlow()

  // Scene 5 Live CSS styling simulator
  private val _liveCssState = MutableStateFlow(LiveCssState())
  val liveCssState: StateFlow<LiveCssState> = _liveCssState.asStateFlow()

  // Scene 8/14 EPUB Explorer selected file
  private val _selectedEpubFile = MutableStateFlow(sampleEpubFiles[0])
  val selectedEpubFile: StateFlow<EpubFileItem> = _selectedEpubFile.asStateFlow()

  // Scene 14 OPF tabs: 0=Metadata, 1=Manifest, 2=Spine
  private val _selectedOpfTab = MutableStateFlow(0)
  val selectedOpfTab: StateFlow<Int> = _selectedOpfTab.asStateFlow()

  // Scene 15 Navigation TOC jumped chapter
  private val _jumpedChapter = MutableStateFlow(1)
  val jumpedChapter: StateFlow<Int> = _jumpedChapter.asStateFlow()

  // Scene 17 Workflow selected step
  private val _selectedWorkflowStep = MutableStateFlow(0)
  val selectedWorkflowStep: StateFlow<Int> = _selectedWorkflowStep.asStateFlow()

  // Quiz State
  private val _quizState = MutableStateFlow(QuizState())
  val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

  // Glossary Search query
  private val _glossaryQuery = MutableStateFlow("")
  val glossaryQuery: StateFlow<String> = _glossaryQuery.asStateFlow()

  private var timerJob: Job? = null

  init {
    updateCurrentScene()
  }

  private fun updateCurrentScene() {
    (currentScene as MutableStateFlow).value = scenes[_currentSceneIndex.value]
    _sceneProgressSeconds.value = 0f
  }

  fun setTab(tab: MainTab) {
    _activeTab.value = tab
  }

  fun togglePlayPause() {
    if (_isPlaying.value) {
      pause()
    } else {
      play()
    }
  }

  fun play() {
    _isPlaying.value = true
    startTimer()
    // Speak Tamil Voice-over
    val scene = scenes[_currentSceneIndex.value]
    narrator.speak(scene.voiceoverTamil) {
      // Voice completed
    }
  }

  fun pause() {
    _isPlaying.value = false
    timerJob?.cancel()
    narrator.stop()
  }

  private fun startTimer() {
    timerJob?.cancel()
    timerJob = viewModelScope.launch {
      while (isActive && _isPlaying.value) {
        delay(500)
        val current = _sceneProgressSeconds.value + (0.5f * _playbackSpeed.value)
        val total = scenes[_currentSceneIndex.value].durationSeconds.toFloat()
        if (current >= total) {
          _sceneProgressSeconds.value = total
          if (_autoPlay.value) {
            nextScene()
          } else {
            pause()
          }
        } else {
          _sceneProgressSeconds.value = current
        }
      }
    }
  }

  fun nextScene() {
    if (_currentSceneIndex.value < scenes.size - 1) {
      _currentSceneIndex.value++
      updateCurrentScene()
      if (_isPlaying.value) {
        play()
      }
    } else {
      pause()
    }
  }

  fun previousScene() {
    if (_currentSceneIndex.value > 0) {
      _currentSceneIndex.value--
      updateCurrentScene()
      if (_isPlaying.value) {
        play()
      }
    } else {
      _sceneProgressSeconds.value = 0f
    }
  }

  fun selectScene(index: Int) {
    if (index in scenes.indices) {
      _currentSceneIndex.value = index
      updateCurrentScene()
      if (_isPlaying.value) {
        play()
      }
    }
  }

  fun seekTo(seconds: Float) {
    _sceneProgressSeconds.value = seconds.coerceIn(0f, scenes[_currentSceneIndex.value].durationSeconds.toFloat())
  }

  fun toggleVoiceOver() {
    if (narrator.isSpeaking.value) {
      narrator.stop()
    } else {
      val scene = scenes[_currentSceneIndex.value]
      narrator.speak(scene.voiceoverTamil)
    }
  }

  fun setPlaybackSpeed(speed: Float) {
    _playbackSpeed.value = speed
    narrator.setSpeechRate(speed)
  }

  fun toggleAutoPlay() {
    _autoPlay.value = !_autoPlay.value
  }

  // Interactive controls
  fun setDeviceScale(scale: Float) {
    _deviceScale.value = scale
  }

  fun updateLiveCss(update: (LiveCssState) -> LiveCssState) {
    _liveCssState.value = update(_liveCssState.value)
  }

  fun selectEpubFile(file: EpubFileItem) {
    _selectedEpubFile.value = file
  }

  fun setOpfTab(tabIndex: Int) {
    _selectedOpfTab.value = tabIndex
  }

  fun setJumpedChapter(chapter: Int) {
    _jumpedChapter.value = chapter
  }

  fun selectWorkflowStep(stepIndex: Int) {
    _selectedWorkflowStep.value = stepIndex
  }

  // Quiz controls
  fun answerQuizQuestion(questionId: Int, optionIndex: Int) {
    val currentAnswers = _quizState.value.userAnswers.toMutableMap()
    currentAnswers[questionId] = optionIndex
    _quizState.value = _quizState.value.copy(userAnswers = currentAnswers)
  }

  fun submitQuiz() {
    var correctCount = 0
    quizQuestions.forEach { q ->
      if (_quizState.value.userAnswers[q.id] == q.correctIndex) {
        correctCount++
      }
    }
    _quizState.value = _quizState.value.copy(
      isSubmitted = true,
      score = correctCount
    )
  }

  fun resetQuiz() {
    _quizState.value = QuizState()
  }

  fun setGlossaryQuery(query: String) {
    _glossaryQuery.value = query
  }

  override fun onCleared() {
    super.onCleared()
    timerJob?.cancel()
    narrator.release()
  }
}
