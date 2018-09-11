package com.github.android.functionality.repolist.listener

import com.github.android.functionality.repolist.model.Repository

interface RepositoryClickListener {
    fun onRepositoryClick(repository: Repository)

}