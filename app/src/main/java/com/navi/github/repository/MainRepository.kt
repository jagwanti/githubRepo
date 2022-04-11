package com.navi.github.repository

import android.util.Log
import com.navi.github.model.Repo
import com.navi.github.model.api.GitHubAPI
import retrofit2.Response
import javax.inject.Inject

class MainRepository  @Inject constructor(val gitHubAPI: GitHubAPI){
    suspend fun getPullRequestData(owner:String,repo:String): Response<List<Repo>> {
        return try {
            return gitHubAPI.getPullRequestData(owner,repo)
        }catch (e:Exception){
            Log.e("MainRepository","gitHubAPI $e")
            Response.error(-1,null)
        }
    }
}