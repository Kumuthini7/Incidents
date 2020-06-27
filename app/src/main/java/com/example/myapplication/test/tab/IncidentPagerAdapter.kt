package com.example.myapplication.test.tab


/**
 * Created by Kumuthini.N on 28-06-2020
 */

class IncidentPagerAdapter(
    fm: androidx.fragment.app.FragmentManager,
    private val fragments: List<androidx.fragment.app.Fragment>,
    private val title: List<String>
) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment = fragments[position]
    override fun getCount(): Int = fragments.size
    override fun getPageTitle(position: Int): CharSequence = title[position]
}
