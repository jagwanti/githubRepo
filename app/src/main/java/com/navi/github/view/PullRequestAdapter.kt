package com.navi.github.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.navi.github.R
import com.navi.github.model.Repo
import com.navi.github.parseDateString

class PullRequestAdapter(val data:ArrayList<Repo>,val userNm:String): RecyclerView.Adapter<PullRequestAdapter.PullReqViewHolder>() {
    private lateinit var mContext:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullReqViewHolder {
        mContext = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.repo_item_row, parent, false)
        return PullReqViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullReqViewHolder, position: Int) {
        val repo = data[position]
        holder.title.text = repo.title
        Glide.with(mContext).load(repo.user.avatar_url).into(holder.userImg)
        holder.userName.text = mContext.resources.getString(R.string.owned_by,userNm)
        holder.createdAt.text =  mContext.resources.getString(R.string.created_time,parseDateString(repo.created_at))
        holder.closedAt.text =  mContext.resources.getString(R.string.closed_time,parseDateString(repo.closed_at))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(it: List<Repo>?) {
        data.clear()
        data.addAll( it as ArrayList<Repo>)
    }

    class PullReqViewHolder(view:View):RecyclerView.ViewHolder(view){
        val userImg:ImageView = view.findViewById(R.id.user_iv)
        val title:TextView = view.findViewById(R.id.title_tv)
        val userName:TextView = view.findViewById(R.id.user_tv)
        val createdAt:TextView = view.findViewById(R.id.created_tv)
        val closedAt:TextView = view.findViewById(R.id.closed_tv)
    }

}