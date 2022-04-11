package com.navi.github.model.api

import com.navi.github.model.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
        @GET(" repos/{owner}/{repo}/pulls?state=closed")
        suspend fun getPullRequestData(@Path("owner") owner:String,@Path("repo") repo:String): Response<List<Repo>>
}