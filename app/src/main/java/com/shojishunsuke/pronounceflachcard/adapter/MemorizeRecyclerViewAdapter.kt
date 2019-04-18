package com.shojishunsuke.pronounceflachcard.adapter

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.*
import java.util.*

class MemorizeRecyclerViewAdapter(private val context: Context?, val realmResults: RealmResults<WordObject>) :
    RecyclerView.Adapter<MemorizeRecyclerViewAdapter.MemorizeRecyclerViewHolder>(), TextToSpeech.OnInitListener {

    val realm = Realm.getDefaultInstance()

    var wordString: String = ""
    var meaningString: String = ""
    val textToSpeach = TextToSpeech(context,this)

    override fun onBindViewHolder(holder: MemorizeRecyclerViewHolder, position: Int) {
        val wordCard = realmResults[position]
        wordString = wordCard!!.word
        meaningString = wordCard.meaning

        holder.wordTextView.setText(wordString)
        holder.meaningTextView.setText(meaningString)

        holder.checkBox.isChecked = wordCard.isDone

        holder.checkBox.setOnClickListener{
            realm.executeTransaction{
                wordCard.isDone = holder.checkBox.isChecked
                realm.copyToRealm(wordCard)
            }
        }


        holder.pronounceButton.setOnClickListener {
            textToSpeach.speak(wordCard.word,TextToSpeech.QUEUE_FLUSH,null,null)
        }

        holder.wordTextView.setTag(wordCard)
        holder.meaningTextView.setTag(wordCard)
        holder.pronounceButton.setTag(wordCard)
        holder.checkBox.setTag(wordCard)




    }


    override fun getItemCount(): Int {

        return realmResults.count()

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

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS){
            if (textToSpeach.isLanguageAvailable(Locale.ENGLISH)>= TextToSpeech.LANG_AVAILABLE) {
                textToSpeach.setLanguage(Locale.ENGLISH)
                textToSpeach.setSpeechRate(1.0f)
                textToSpeach.setPitch(1.0f)



            }else{
                Toast.makeText(context,"language not supported",Toast.LENGTH_SHORT).show()
            }
        }

    }

    class MemorizeRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val wordTextView = view.findViewById<TextView>(R.id.wordMemoTextView)
        val meaningTextView = view.findViewById<TextView>(R.id.meaningMemoTextView)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        val pronounceButton = view.findViewById<ImageView>(R.id.pronounceButton)

    }



}