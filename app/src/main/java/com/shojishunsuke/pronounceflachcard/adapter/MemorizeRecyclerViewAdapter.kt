package com.shojishunsuke.pronounceflachcard.adapter

import android.content.Context
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

class MemorizeRecyclerViewAdapter(private val context: Context?, val realmResults: RealmResults<WordObject>) :
    RecyclerView.Adapter<MemorizeRecyclerViewAdapter.MemorizeRecyclerViewHolder>() {

    var wordString: String = ""
    var meaningString: String = ""
    override fun onBindViewHolder(holder: MemorizeRecyclerViewHolder, position: Int) {
        var wordCard = realmResults.get(position)
        wordString = wordCard!!.word
        meaningString = wordCard.meaning

        holder.wordTextView.setText(wordString)
        holder.wordTextView.setTag(wordCard)

        holder.meaningTextView.setText(meaningString)
        holder.meaningTextView.setTag(wordCard)
        when (wordCard.isDone) {
            true -> {
                holder.checkBox.setImageResource(R.drawable.outline_check_box_black_48)
            }
            false -> {
                holder.checkBox.setImageResource(R.drawable.outline_check_box_outline_blank_black_48)
            }
        }
        holder.checkBox.setOnClickListener(View.OnClickListener {
            when (wordCard.isDone) {
                true -> {
                    holder.checkBox.setImageResource(R.drawable.outline_check_box_outline_blank_black_48)
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction {
                            wordCard.isDone = false
                            realm.copyToRealm(wordCard)
                        }
                    }

                }
                false -> {
                    holder.checkBox.setImageResource(R.drawable.outline_check_box_black_48)
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction {
                            wordCard.isDone = true
                            realm.copyToRealm(wordCard)
                        }
                    }
                }
            }
        })


        holder.checkBox.setTag(wordCard)


    }

    override fun getItemCount(): Int {

        return realmResults.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemorizeRecyclerViewHolder {

        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_memorize_card, parent, false)

        val viewHolder = MemorizeRecyclerViewHolder(mView)

        viewHolder.wordTextView.setText(wordString)
        viewHolder.meaningTextView.setText(meaningString)








        viewHolder.wordTextView.setOnClickListener(View.OnClickListener {
            when (viewHolder.wordTextView.visibility) {

                View.GONE -> {
                    viewHolder.wordTextView.visibility = View.VISIBLE
                    viewHolder.meaningTextView.visibility = View.GONE
                }

                View.VISIBLE -> {
                    viewHolder.wordTextView.visibility = View.GONE
                    viewHolder.meaningTextView.visibility = View.VISIBLE

                }

            }
        })

        viewHolder.meaningTextView.setOnClickListener(View.OnClickListener {
            when (viewHolder.meaningTextView.visibility) {

                View.GONE -> {
                    viewHolder.meaningTextView.visibility = View.VISIBLE
                    viewHolder.wordTextView.visibility = View.GONE
                }

                View.VISIBLE -> {
                    viewHolder.meaningTextView.visibility = View.GONE
                    viewHolder.wordTextView.visibility = View.VISIBLE
                }
            }
        })


        return MemorizeRecyclerViewHolder(mView)


    }

    class MemorizeRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val wordTextView = view.findViewById<TextView>(R.id.wordMemoTextView)
        val meaningTextView = view.findViewById<TextView>(R.id.meaningMemoTextView)
        val checkBox = view.findViewById<ImageView>(R.id.checkBox)

    }

}