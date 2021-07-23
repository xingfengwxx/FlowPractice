package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wangxingxing.flowpractice.databinding.FragmentSharedFlowBinding
import com.wangxingxing.flowpractice.viewmodel.SharedFlowViewModel

/**
 * author : 王星星
 * date : 2021/7/22 19:58
 * email : 1099420259@qq.com
 * description : SharedFlow
 */
class SharedFlowFragment : Fragment() {

    private val mBinding: FragmentSharedFlowBinding by lazy {
        FragmentSharedFlowBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<SharedFlowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.apply {
            btnStart.setOnClickListener {
                viewModel.startRefresh()
            }

            btnStop.setOnClickListener {
                viewModel.stopRefresh()
            }
        }
    }

}