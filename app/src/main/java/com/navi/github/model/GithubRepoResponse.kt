package com.navi.github.model


/*[{
	"id": 1,
	"state": "open",
	"title": "Amazing new feature",
	"user": {
		"id": 1,
		"avatar_url": "https://github.com/images/error/octocat_happy.gif"
	},
	"created_at": "2011-01-26T19:01:12Z",
	"closed_at": "2011-01-26T19:01:12Z"
}]*/

data class Repo(var id:Int,var state:String,var title:String,var user:User,var created_at:String?,var closed_at:String?)

data class User(var id:Int,var avatar_url:String)
