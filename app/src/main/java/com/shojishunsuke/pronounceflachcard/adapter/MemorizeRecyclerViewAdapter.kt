package com.shojishunsuke.pronounceflachcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.MemorizeRecyclerViewAdapterViewModel
import io.realm.RealmResults

class MemorizeRecyclerViewAdapter(private val context: Context?,private val wordList: RealmResults<WordObject>) :
    RecyclerView.Adapter<MemorizeRecyclerViewAdapter.MemorizeRecyclerViewHolder>(),OnDataChangedListener {

    private val viewModel = MemorizeRecyclerViewAdapterViewModel(this,context!!)


    override fun onBindViewHolder(holder: MemorizeRecyclerViewHolder, position: Int) {
        val wordCard = wordList[position]
        val wordString = wordCard!!.word
        val meaningString = wordCard.meaning

        holder.wordTextView.setText(wordString)
        holder.meaningTextView.setText(meaningString)

        holder.checkBox.isChecked = wordCard.isDone

        holder.checkBox.setOnClickListener{

            viewModel.switchCheckedState(wordCard.id,holder.checkBox.isChecked)
        }


        holder.pronounceButton.setOnClickListener {
            viewModel.readOut(wordString)
           }

        holder.wordTextView.setTag(wordCard)
        holder.meaningTextView.setTag(wordCard)
        holder.pronounceButton.setTag(wordCard)
        holder.checkBox.setTag(wordCard)




    }

    override fun onDataChanged() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return wordList.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemorizeRecyclerViewHolder {

        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_memorize_card, parent, false)

        val viewHolder = MemorizeRecyclerViewHolder(mView)


        viewHolder.pronounceButton.setImageResource(R.drawable.outline_volume_up_black_36dp)




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
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        val pronounceButton = view.findViewById<ImageView>(R.id.pronounceButton)

    }



}