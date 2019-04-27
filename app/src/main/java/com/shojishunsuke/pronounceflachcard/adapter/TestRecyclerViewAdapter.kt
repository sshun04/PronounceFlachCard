package com.shojishunsuke.pronounceflachcard.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.AnimationUtils
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R

class TestRecyclerViewAdapter(private val context: Context?, val resultWords: ArrayList<QuestionWord>) :
    RecyclerView.Adapter<TestRecyclerViewAdapter.TestRecyclerViewHolder>() {

    override fun onBindViewHolder(holder: TestRecyclerViewHolder, position: Int) {
        val resultWord = resultWords.get(position)
        holder.resultTextView.setText(resultWord.word)

        if (resultWord.isTrue) {
            holder.checkeTextView.setText("✔︎")
            holder.checkeTextView.setTextColor(Color.parseColor("#4CAF50"))
        } else {
            holder.checkeTextView.setText("✖️")
            holder.checkeTextView.setTextColor(Color.parseColor("#f44336"))
        }



            holder.expandButton.setOnClickListener {

                if (holder.deatilTextView.visibility == View.GONE) {
                    holder.deatilTextView.visibility = View.VISIBLE
                    startRotate(it,0f,-180f)
                    holder.deatilTextView.setText(resultWord.meaning)
                } else {


                    startRotate(it,-180f,0f)
                    holder.deatilTextView.visibility = View.GONE

                }
            }



    }
    private fun startRotate(view: View,startRotate:Float,endRotate:Float){
        val rotateAnimation = RotateAnimation(startRotate,endRotate,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotateAnimation.duration = 300
        rotateAnimation.fillAfter = true
        view.startAnimation(rotateAnimation)

    }

    override fun getItemCount(): Int = resultWords.count()


    override fun onViewRecycled(holder: TestRecyclerViewHolder) {
        holder.deatilTextView.visibility = View.GONE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestRecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_test_result_word, parent, false)


        return TestRecyclerViewHolder(mView)

    }


    class TestRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val resultTextView = view.findViewById<TextView>(R.id.wordTextView5)
        val checkeTextView = view.findViewById<TextView>(R.id.checkView)
        val deatilTextView = view.findViewById<TextView>(R.id.detailTextView)
        val expandButton = view.findViewById<ImageView>(R.id.expandArrow)

    }
}