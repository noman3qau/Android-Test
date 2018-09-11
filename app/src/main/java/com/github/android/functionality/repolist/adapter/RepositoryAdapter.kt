package com.github.android.functionality.repolist.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.android.R
import com.github.android.databinding.RepositoryListItemBinding
import com.github.android.functionality.repolist.listener.RepositoryClickListener
import com.github.android.functionality.repolist.model.Repository


class RepositoryAdapter(var projectClickCallBack: RepositoryClickListener, var projectListItems: List<Repository>) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {

        val binding: RepositoryListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.getContext()), R.layout.repository_list_item, parent, false)

        binding.clickListener = projectClickCallBack

        return RepositoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {

        holder?.binding?.repository = this.projectListItems?.get(position)
        holder?.binding?.executePendingBindings();

    }


    override fun getItemCount(): Int {
        return projectListItems!!.size
    }

    /**
     * Project listview adapter view holder
     */
    class RepositoryViewHolder(bindingg: RepositoryListItemBinding) : RecyclerView.ViewHolder(bindingg.root) {
        var binding: RepositoryListItemBinding

        init {
            binding = bindingg
        }
    }


}