package com.navi.github.repository

import android.util.Log
import com.navi.github.model.Resource
import com.navi.github.model.api.GitHubAPI
import javax.inject.Inject

class MainRepository  @Inject constructor(val gitHubAPI: GitHubAPI){
    suspend fun getPullRequestData(): Resource<> {
        return try {
            return gitHubAPI.getPullRequestData()
        }catch (e:Exception){
            Log.e("MainRepository","gitHubAPI $e")
            Resource.Error("An $e occurred")
        }
    }
}