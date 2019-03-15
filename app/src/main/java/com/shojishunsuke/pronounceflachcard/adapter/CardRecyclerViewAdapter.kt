package com.shojishunsuke.pronounceflachcard.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import io.realm.RealmResults

class CardRecyclerViewAdapter(private val context: Context?, val realmResults: RealmResults<WordObject>) :
    RecyclerView.Adapter<CardRecyclerViewAdapter.RecyclerViewHolder>() {

    var wordString: String = ""
    var meaningString: String = ""
    val realm = Realm.getDefaultInstance()


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        var wordCard = realmResults.get(position)
        wordString = wordCard!!.word
        meaningString = wordCard.meaning

        holder.wordTextView.setText(wordString)
        holder.wordTextView.setTag(wordCard)

        holder.meaningTextView.setText(meaningString)
        holder.meaningTextView.setTag(wordCard)

        holder.editButton.setTag(wordCard)


        holder.editButton.setOnClickListener {


            var items = arrayOf("編集", "削除")
            AlertDialog.Builder(context)
                .setItems(items, DialogInterface.OnClickListener { dialogInterface, i ->
                    when (i) {
                        0 -> {



                        }
                        1 -> {
                            AlertDialog.Builder(context)
                                .setTitle(realmResults.get(position)?.word)
                                .setMessage("本当に削除しますか？")
                                .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->

                                    deleteWord(position)

                                }
                                ).setNegativeButton("CANCEL", null)
                                .show()
                        }

                    }
                }

                ).show()

        }

    }


    override fun getItemCount(): Int {

        return realmResults.count()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card, parent, false)

        val viewHolder = RecyclerViewHolder(mView)

        viewHolder.wordTextView.setText(wordString)
        viewHolder.meaningTextView.setText(meaningString)
        viewHolder.editButton.setImageResource(R.drawable.outline_more_vert_black_24)

       realm.addChangeListener {
           notifyDataSetChanged()
       }


        return RecyclerViewHolder(mView)
    }


    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val wordTextView = view.findViewById<TextView>(R.id.wordTextView)
        val meaningTextView = view.findViewById<TextView>(R.id.meaningTextView)
        val editButton = view.findViewById<ImageView>(R.id.editButton)

    }

    fun deleteWord(position: Int) {

        realm.executeTransaction {
            realmResults.get(position)!!.deleteFromRealm()

        }

        notifyItemRemoved(position)


    }
}