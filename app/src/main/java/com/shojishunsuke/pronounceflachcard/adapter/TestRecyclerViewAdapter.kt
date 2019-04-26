package com.shojishunsuke.pronounceflachcard.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
                    it.rotation = 0f
                    holder.deatilTextView.setText(resultWord.meaning)
                } else {

                    it.rotation = 180f
                    holder.deatilTextView.visibility = View.GONE

                }
            }



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