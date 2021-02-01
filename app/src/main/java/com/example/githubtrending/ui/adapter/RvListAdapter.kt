package com.example.githubtrending.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubtrending.data.model.Item
import com.example.githubtrending.databinding.ChildRepoViewBinding

class RvListAdapter : RecyclerView.Adapter<RvListAdapter.ItemViewHolder>() {


    private var dataList: ArrayList<Item>? = ArrayList()

    /**
     * set data in recyclerview
     */
    fun setData(dataList: ArrayList<Item>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<Item>? {
        return dataList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ChildRepoViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = dataList?.get(position)
        data?.let { holder.setData(it) }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    inner class ItemViewHolder(bind: ChildRepoViewBinding) :
        RecyclerView.ViewHolder(bind.root) {

        var viewBind: ChildRepoViewBinding? = null

        init {
            viewBind = bind
        }

        fun setData(data: Item) {

            viewBind?.avatarIv?.let {
                Glide.with(it)
                    .load(data.owner.avatarUrl)
                    .thumbnail()
                    .into(it)
            }
            viewBind?.userNameTv?.text = data.name
            viewBind?.repoUrlTv?.text = data.htmlUrl

            viewBind?.archiveIv?.visibility = if (data.archived) View.VISIBLE else View.INVISIBLE
        }

    }
}