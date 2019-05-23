package com.shojishunsuke.pronounceflachcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel

class DrawerRecyclerViewAdapter(private val fragmentActivity: FragmentActivity,private val context: Context, private val titleList: MutableList<String>) :
    RecyclerView.Adapter<DrawerRecyclerViewAdapter.TitleViewHolder>(), OnDataChangedListener {

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.titleTextView.text = titleList[position]


        val sharedViewModel = ViewModelProviders.of(fragmentActivity).get(SharedViewModel::class.java )

        holder.titleTextView.setOnClickListener {

            sharedViewModel.select(titleList[position])
            sharedViewModel.saveLastTitle()

        }

        holder.titleTextView.tag = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {



        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_drawer_list, parent, false)




        return TitleViewHolder(mView)
    }

    override fun getItemCount(): Int = titleList.count()

    override fun onDataChanged() {
        notifyDataSetChanged()
    }


    class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
    }
}