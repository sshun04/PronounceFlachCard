package com.shojishunsuke.pronounceflachcard

//TemplateMethodを使ってみたかった
abstract class AbstractTest {
    abstract fun setWord()
    abstract fun checkIsTrue()
    abstract fun moveToNextFragment()

  fun test(){
      setWord()
      checkIsTrue()
      moveToNextFragment()
  }

}