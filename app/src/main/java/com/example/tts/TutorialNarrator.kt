package com.example.tts

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class TutorialNarrator(context: Context) : TextToSpeech.OnInitListener {

  private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)

  private val _isSpeaking = MutableStateFlow(false)
  val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

  private val _isInitialized = MutableStateFlow(false)
  val isInitialized: StateFlow<Boolean> = _isInitialized.asStateFlow()

  private val _isTamilSupported = MutableStateFlow(false)
  val isTamilSupported: StateFlow<Boolean> = _isTamilSupported.asStateFlow()

  private val _speechRate = MutableStateFlow(1.0f)
  val speechRate: StateFlow<Float> = _speechRate.asStateFlow()

  private var onCompletionCallback: (() -> Unit)? = null

  override fun onInit(status: Int) {
    if (status == TextToSpeech.SUCCESS) {
      val ttsEngine = tts ?: return
      _isInitialized.value = true

      // Try Tamil India locale first
      val tamilLocale = Locale("ta", "IN")
      val result = ttsEngine.isLanguageAvailable(tamilLocale)
      if (result >= TextToSpeech.LANG_AVAILABLE) {
        ttsEngine.language = tamilLocale
        _isTamilSupported.value = true
      } else {
        // Try generic Tamil
        val genericTamil = Locale("ta")
        val genResult = ttsEngine.isLanguageAvailable(genericTamil)
        if (genResult >= TextToSpeech.LANG_AVAILABLE) {
          ttsEngine.language = genericTamil
          _isTamilSupported.value = true
        } else {
          // Fallback to default
          ttsEngine.language = Locale.getDefault()
          _isTamilSupported.value = false
        }
      }

      ttsEngine.setSpeechRate(_speechRate.value)

      ttsEngine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String?) {
          _isSpeaking.value = true
        }

        override fun onDone(utteranceId: String?) {
          _isSpeaking.value = false
          onCompletionCallback?.invoke()
        }

        @Deprecated("Deprecated in Java")
        override fun onError(utteranceId: String?) {
          _isSpeaking.value = false
        }

        override fun onError(utteranceId: String?, errorCode: Int) {
          _isSpeaking.value = false
        }
      })
    }
  }

  fun speak(text: String, onDone: (() -> Unit)? = null) {
    onCompletionCallback = onDone
    val ttsEngine = tts
    if (ttsEngine != null && _isInitialized.value) {
      stop()
      val params = Bundle()
      params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SceneUtterance_${System.currentTimeMillis()}")
      ttsEngine.setSpeechRate(_speechRate.value)
      val result = ttsEngine.speak(text, TextToSpeech.QUEUE_FLUSH, params, "SceneUtterance")
      if (result == TextToSpeech.SUCCESS) {
        _isSpeaking.value = true
      } else {
        _isSpeaking.value = false
      }
    } else {
      // If TTS not ready, track speaking state for simulated play
      _isSpeaking.value = true
    }
  }

  fun stop() {
    tts?.stop()
    _isSpeaking.value = false
  }

  fun setSpeechRate(rate: Float) {
    _speechRate.value = rate
    tts?.setSpeechRate(rate)
  }

  fun release() {
    stop()
    tts?.shutdown()
    tts = null
  }
}
