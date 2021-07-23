package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.flowpractice.R
import com.wangxingxing.flowpractice.databinding.FragmentUserBinding
import com.wangxingxing.flowpractice.ui.UserAdapter
import com.wangxingxing.flowpractice.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect


class UserFragment : Fragment() {

    private val viewModel by viewModels<UserViewModel>()

    private val mBinding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //添加用户
        mBinding.apply {
            btnAddUser.setOnClickListener {
                viewModel.insert(
                    etUserId.text.toString(),
                    etFirstName.text.toString(),
                    etLastName.text.toString()
                )
            }
        }

        context?.let {
            val adapter = UserAdapter(it)
            mBinding.recyclerView.adapter = adapter
            //监听users列表改变，自动刷新RecyclerView
            lifecycleScope.launchWhenCreated {
                viewModel.getAll().collect { value ->
                    adapter.setData(value)
                }
            }
        }
    }

}