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
import io.realm.RealmResults

class CardRecyclerViewAdapter(private val context: Context?,val realmResults:RealmResults<WordObject>) :
    RecyclerView.Adapter<CardRecyclerViewAdapter.RecyclerViewHolder>() {

    lateinit var viewHolder:RecyclerViewHolder

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {




    }

    override fun getItemCount(): Int {
        return realmResults.size
        //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card, parent, false)

        val viewHolder = RecyclerViewHolder(mView)



        realmResults.forEach{
            viewHolder.cardWord.setText(it.word+"/"+it.meaning)
        }







        return RecyclerViewHolder(mView)
    }




    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardWord = view.findViewById<TextView>(R.id.WordTextView)
        val likeButton = view.findViewById<ImageView>(R.id.likeButton)

    }
}