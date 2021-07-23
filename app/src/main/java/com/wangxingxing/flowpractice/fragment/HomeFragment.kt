package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wangxingxing.flowpractice.R
import com.wangxingxing.flowpractice.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val mBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.apply {
            btnFlowAndDownload.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_downloadFragment)
            }

            btnFlowAndRoom.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_userFragment)
            }

            btnFlowAndRetrofit.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
            }

            btnStateFlow.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_numberFragment)
            }

            btnSharedFlow.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_sharedFlowFragment)
            }
        }
    }

}