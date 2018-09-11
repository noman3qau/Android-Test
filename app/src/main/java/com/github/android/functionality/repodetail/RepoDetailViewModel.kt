package com.github.android.functionality.repodetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.github.android.functionality.repolist.model.Repository


class RepoDetailViewModel(application: Application) : AndroidViewModel(application) {

    var obsRepository = ObservableField<Repository>()


    init {


    }


}