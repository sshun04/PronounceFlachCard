package com.shojishunsuke.pronounceflachcard.Model

import java.io.Serializable


data class QuestionWord(
    var word: String = "",
    var meaning :String = "",
    var recognizedWord: String = "",
    var questionNumber: Int = 0,
    var isPronounce: Boolean = true,
    var isTrue: Boolean = false

) : Serializable {
}