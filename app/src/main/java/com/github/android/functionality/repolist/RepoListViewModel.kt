package com.github.android.functionality.repolist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.ObservableField
import com.github.android.functionality.repolist.model.Repository
import com.github.android.network.ApiRequest
import com.github.android.network.NetworkApi
import com.github.android.network.applyIOSchedulers
import com.github.android.utils.Constants
import com.github.android.utils.Constants.REPOSITORY_CATEGORY
import com.github.android.utils.Tools
import io.reactivex.disposables.Disposable


class RepoListViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var disposable: Disposable

    var isLoading = ObservableField<Boolean>()

    var resultError = MutableLiveData<String>()

    var repoList = MutableLiveData<List<Repository>>()


    init {


    }


    /**
     * API call to get top trending android repositories
     */
    fun requestGetAndroidTrendingRepositiesList(context: Context) {

        if (Tools.isNetworkAvailable(context)) {

            isLoading.set(true)
            disposable = NetworkApi.networkRequest(ApiRequest::class.java).getRepoList(REPOSITORY_CATEGORY)
                    .compose(applyIOSchedulers())
                    .subscribe(
                            { result -> // API response
                                isLoading.set(false)

                                if (result?.size!! > 0) {

                                    repoList.postValue(result)

                                } else {

                                    resultError.postValue(Constants.ErrorMapper.ERROR_EMPTY)

                                }

                            },
                            { error -> // API Error
                                isLoading.set(false)

                                resultError.postValue(Constants.ErrorMapper.ERROR_SERVER)

                            }
                    )

        } else {

            resultError.postValue(Constants.ErrorMapper.ERROR_NETWORK)

        }

    }


}