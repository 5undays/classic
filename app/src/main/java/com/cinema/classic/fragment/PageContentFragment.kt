package com.cinema.classic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cinema.classic.R
import com.cinema.classic.databinding.FragmentCollectionObjectBinding
import com.cinema.classic.databinding.FragmentDetailPageBinding

private const val ARG_OBJECT = "object"
class PageContentFragment : Fragment() {
private lateinit var binding: FragmentDetailPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.text1.text = getInt(ARG_OBJECT).toString()
        }
    }
}