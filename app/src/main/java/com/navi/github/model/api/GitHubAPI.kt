package com.navi.github.model.api

import com.navi.github.model.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubAPI {
        @GET(" repos/{owner}/{repo}/pulls&state=closed")
        suspend fun getPullRequestData(@Path("owner") owner:String,@Path("repo") repo:String): Resource<>
}