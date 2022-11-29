package com.cinema.classic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cinema.classic.adapter.ViewpagerAdapter
import com.cinema.classic.databinding.FragmentCollectionObjectBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewpagerFragment : Fragment() {
    private lateinit var demoCollectionAdapter: ViewpagerAdapter

    private lateinit var binding: FragmentCollectionObjectBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionObjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionAdapter = ViewpagerAdapter(this)
        binding.pager.adapter = demoCollectionAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0) {
                tab.text = "PLOT"
            } else {
                tab.text = "BOOKMARK"
            }
        }.attach()
    }
}