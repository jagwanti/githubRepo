package com.navi.github.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.navi.github.R
import com.navi.github.databinding.MainFragmentBinding
import com.navi.github.viewmodal.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainBinding : MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mainBinding = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false)
        return mainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainBinding.btn.setOnClickListener {
            viewModel.userName = mainBinding.userName.text.toString()
            viewModel.repoName = mainBinding.repoName.text.toString()
            val bundle = Bundle()
            bundle.putString("userName",viewModel.userName)
            bundle.putString("repoName",viewModel.repoName)
            (requireActivity() as MainActivity).replaceFragment(RepoFragment::class.java,bundle)
        }
    }

}