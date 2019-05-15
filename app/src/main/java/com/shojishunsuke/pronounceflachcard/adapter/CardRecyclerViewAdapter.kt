package com.shojishunsuke.pronounceflachcard.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.CardRecyclerAdapterViewModel

class CardRecyclerViewAdapter(private val context: Context?,private val wordList: List<WordObject>) :
        RecyclerView.Adapter<CardRecyclerViewAdapter.RecyclerViewHolder>(), OnDataChangedListener {



   private val viewModel = CardRecyclerAdapterViewModel(this)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val wordCard = wordList[position]
       val wordString = wordCard!!.word
       val meaningString = wordCard.meaning

        holder.wordTextView.setText(wordString)
        holder.wordTextView.setTag(wordCard)

        holder.meaningTextView.setText(meaningString)
        holder.meaningTextView.setTag(wordCard)

        holder.optionButton.setImageResource(R.drawable.outline_more_vert_black_24)

        holder.optionButton.setTag(wordCard)


        holder.optionButton.setOnClickListener {


            lateinit var wordEditText: EditText
            lateinit var meaningEditText: EditText


            val popupMenu = PopupMenu(context!!, holder.optionButton, Gravity.END)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editPop -> {

//
                        val detailDialog = AlertDialog.Builder(context)
                                .setPositiveButton("保存", DialogInterface.OnClickListener { _, _ ->


                                    viewModel.editWord(wordCard.id, wordEditText.text.toString(), meaningEditText.text.toString())

                                })
                                .setNegativeButton(
                                        "キャンセル",
                                        null
                                )
                                .create()

                        val parentView = detailDialog.getLayoutInflater().inflate(R.layout.fragment_word_edit, null)

                        wordEditText = parentView.findViewById(R.id.wordEditText)
                        wordEditText.setText(wordCard.word)

                        meaningEditText = parentView.findViewById(R.id.meaningEditText)
                        meaningEditText.setText(wordCard.meaning)

                        detailDialog.setView(parentView)
                        detailDialog.show()



                    }
                    R.id.deletePop -> {


                        AlertDialog.Builder(context)
                                .setTitle(wordCard.word)
                                .setMessage("本当に削除しますか？")
                                .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->

                                    viewModel.deleteWord(wordCard.id)
                                }
                                ).setNegativeButton("CANCEL", null)
                                .show()

                    }
                }

                return@setOnMenuItemClickListener true
            }
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.show()


        }

        holder.wordBox.setOnClickListener {


            val detailDialog = AlertDialog.Builder(context)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    })
                    .create()

            val parentView = detailDialog.getLayoutInflater().inflate(R.layout.dialog_fragment_word_detail, null)

            val detailWord = parentView.findViewById<TextView>(R.id.wordEditText)
            detailWord.setText(wordCard.word)

            val detailMeaning = parentView.findViewById<TextView>(R.id.meaningEditText)
            detailMeaning.setText(wordCard.meaning)

            detailDialog.setView(parentView)
            detailDialog.show()
        }


    }


    override fun getItemCount(): Int = wordList.count()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card, parent, false)

        return RecyclerViewHolder(mView)
    }



    override fun onDataChanged() {
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val wordTextView = view.findViewById<TextView>(R.id.wordTextView)
        val meaningTextView = view.findViewById<TextView>(R.id.meaningTextView)
        val optionButton = view.findViewById<ImageView>(R.id.editButton)
        val wordBox = view.findViewById<ConstraintLayout>(R.id.selectablecard)

    }


}