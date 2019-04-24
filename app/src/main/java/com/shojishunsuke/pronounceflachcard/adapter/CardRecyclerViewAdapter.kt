package com.shojishunsuke.pronounceflachcard.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapePathModel
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R
import io.realm.Realm
import io.realm.RealmResults

class CardRecyclerViewAdapter(private val context: Context?, val realmResults: RealmResults<WordObject>) :
    RecyclerView.Adapter<CardRecyclerViewAdapter.RecyclerViewHolder>() {

    val realm = Realm.getDefaultInstance()

    var wordString: String = ""
    var meaningString: String = ""

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val wordCard = realmResults[position]
        wordString = wordCard!!.word
        meaningString = wordCard.meaning

        holder.wordTextView.setText(wordString)
        holder.wordTextView.setTag(wordCard)

        holder.meaningTextView.setText(meaningString)
        holder.meaningTextView.setTag(wordCard)

        holder.optionButton.setImageResource(R.drawable.outline_more_vert_black_24)

        holder.optionButton.setTag(wordCard)


        holder.optionButton.setOnClickListener {


            lateinit var wordEditText: EditText
            lateinit var meaningEditText: EditText


            val popupMenu = PopupMenu(context!!, holder.optionButton,Gravity.END)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editPop -> {


                        val detailDialog = AlertDialog.Builder(context)
                            .setPositiveButton("保存", DialogInterface.OnClickListener { _, _ ->

                                realm.executeTransaction {


                                    wordCard.word = wordEditText.text.toString()
                                    wordCard.meaning = meaningEditText.text.toString()

                                    it.copyToRealm(wordCard)


                                }

                            })
                            .setNegativeButton(
                                "キャンセル",
                                null
                            )
                            .create()

                        val parentView = detailDialog.getLayoutInflater().inflate(R.layout.fragment_word_edit, null)

                        wordEditText = parentView.findViewById(R.id.wordEditText)
                        wordEditText.setText(realmResults.get(position)?.word)

                        meaningEditText = parentView.findViewById(R.id.meaningEditText)
                        meaningEditText.setText(realmResults.get(position)?.meaning)

                        detailDialog.setView(parentView)
                        detailDialog.show()
                    }
                    R.id.deletePop -> {


                        AlertDialog.Builder(context)
                            .setTitle(realmResults.get(position)?.word)
                            .setMessage("本当に削除しますか？")
                            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->

                                deleteWord(position)

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
            detailWord.setText(realmResults[position]?.word)

            val detailMeaning = parentView.findViewById<TextView>(R.id.meaningEditText)
            detailMeaning.setText(realmResults[position]?.meaning)

            detailDialog.setView(parentView)
            detailDialog.show()
        }


    }


    override fun getItemCount(): Int = realmResults.count()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card, parent, false)


        realm.addChangeListener {
            notifyDataSetChanged()
        }

        return RecyclerViewHolder(mView)
    }


    private fun deleteWord(position: Int) {

        realm.executeTransaction {

            realmResults[position]!!.deleteFromRealm()

        }

        notifyItemRemoved(position)

    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val wordTextView = view.findViewById<TextView>(R.id.wordTextView)
        val meaningTextView = view.findViewById<TextView>(R.id.meaningTextView)
        val optionButton = view.findViewById<ImageView>(R.id.editButton)
        val wordBox = view.findViewById<ConstraintLayout>(R.id.wordBox)
        val card   = view.findViewById<CardView>(R.id.cardView)

    }



}