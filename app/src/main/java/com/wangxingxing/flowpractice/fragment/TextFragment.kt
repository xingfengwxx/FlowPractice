package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.flowpractice.common.LocalEventBus
import com.wangxingxing.flowpractice.databinding.FragmentTextBinding
import kotlinx.coroutines.flow.collect

/**
 * author : 王星星
 * date : 2021/7/22 20:00
 * email : 1099420259@qq.com
 * description :
 */
class TextFragment : Fragment() {

    private val mBinding: FragmentTextBinding by lazy {
        FragmentTextBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            //从EventBus中获取Event，显示
            //三个TextFragment都可以获取到同一个Event，不互相排斥
            LocalEventBus.events.collect {
                mBinding.tvTime.text = it.timestamp.toString()
            }
        }
    }
}