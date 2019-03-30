package com.shojishunsuke.pronounceflachcard.Model

import java.io.Serializable


data class QuestionWord(
    var woord: String = "",
    var recognizedWord: String = "",
    var quetionNumber: Int = 0,
    var isPronounce: Boolean = true,
    var isTrue: Boolean = false
) : Serializable {
}