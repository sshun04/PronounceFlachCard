package com.shojishunsuke.pronounceflachcard.Model

import java.io.Serializable


data class QuestionWord(open var woord : String = "",
                        open var recognizedWord :String= "",
                        open var quetionNumber :Int = 0,
                        open var isPronounce : Boolean = true,
                        open var isTrue:Boolean = false):Serializable{
}