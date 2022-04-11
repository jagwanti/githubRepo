package com.navi.github.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.navi.github.R
import com.navi.github.databinding.RepoFragmentBinding
import com.navi.github.model.Repo
import com.navi.github.model.Status
import com.navi.github.viewmodal.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoFragment : Fragment() {

    companion object {
        fun newInstance() = RepoFragment()
    }

    private lateinit var mainBinding:RepoFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var dataList = ArrayList<Repo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mainBinding = DataBindingUtil.inflate(inflater,R.layout.repo_fragment,container,false)
        return mainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.getGithubPullRequests()
        mainBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PullRequestAdapter(dataList,viewModel.userName)
            addItemDecoration(DividerItemDecoration(requireContext(),HORIZONTAL))
        }
        viewModel.repoPullReqResponse.observe(viewLifecycleOwner, {
            (mainBinding.recyclerView.adapter as PullRequestAdapter).setData(it)
            mainBinding.recyclerView.adapter?.notifyDataSetChanged()
        })
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                mainBinding.progressBar.visibility = View.VISIBLE
            } else {
                mainBinding.progressBar.visibility = View.GONE
            }
        })
    }
}