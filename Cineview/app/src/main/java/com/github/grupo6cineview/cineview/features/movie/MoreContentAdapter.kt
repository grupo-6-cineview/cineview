package com.github.grupo6cineview.cineview.features.movie

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MoreContentAdapter(
    fragment: Fragment,
    private val fragmentList: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}