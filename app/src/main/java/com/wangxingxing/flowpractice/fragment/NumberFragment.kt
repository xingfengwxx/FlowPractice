package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.flowpractice.databinding.FragmentNumberBinding
import com.wangxingxing.flowpractice.viewmodel.NumberViewModel
import kotlinx.coroutines.flow.collect

/**
 * author : 王星星
 * date : 2021/7/22 15:35
 * email : 1099420259@qq.com
 * description : StateFlow使用
 */
class NumberFragment : Fragment() {

    private val mBinding: FragmentNumberBinding by lazy {
        FragmentNumberBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<NumberViewModel>()

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
            btnPlus.setOnClickListener {
                viewModel.increment()
            }
            btnMinus.setOnClickListener {
                viewModel.decrement()
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.number.collect { value ->
                mBinding.tvNumber.text = "$value"
            }
        }
    }
}