package com.github.android.network

import com.github.android.functionality.repolist.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequest {

    /**
     * API top android trending repos
     */
    @GET(EndPoints.REPO_LIST)
    fun getRepoList(@Query("language") language: String): Observable<List<Repository>>


}