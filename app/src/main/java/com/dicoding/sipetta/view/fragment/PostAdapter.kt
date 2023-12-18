package com.dicoding.sipetta.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.sipetta.R
import com.dicoding.sipetta.data.pref.PostItem

class PostAdapter(private val postList: List<PostItem>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = postList[position]

        holder.tvUsername.text = currentItem.name
        holder.tvDescription.text = currentItem.description
        holder.tvLabel.text = currentItem.label
        holder.tvTextSim.text = currentItem.textSim
        holder.tvSeverity.text = currentItem.severity
    }

    override fun getItemCount() = postList.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvLabel: TextView = itemView.findViewById(R.id.tv_item_label)
        val tvTextSim: TextView = itemView.findViewById(R.id.tv_item_textsim)
        val tvSeverity: TextView = itemView.findViewById(R.id.tv_item_severity)
        val ivReply: ImageView = itemView.findViewById(R.id.iv_item_fav)
        val ivFav: ImageView = itemView.findViewById(R.id.iv_item_reply)
    }
}