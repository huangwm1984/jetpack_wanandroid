package com.android.jetpack_mvvm.ext

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.ui.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

fun ViewPager2.init(fragment: Fragment): ViewPager2 {
    //禁止滑动
    isUserInputEnabled = false
    //预加载五个
    this.offscreenPageLimit = 5
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }
                1 -> {
                    return ProjectFragment()
                }
                2 -> {
                    return AggregationFragment()
                }
                3 -> {
                    return PublicFragment()
                }
                4 -> {
                    return MeFragment()
                }
                else -> {
                    return HomeFragment()
                }
            }
        }
        override fun getItemCount(): Int {
            return 5
        }
    }
    return this
}

fun ViewPager2.init(fragment: Fragment, fragments: ArrayList<Fragment>): ViewPager2 {
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
        override fun getItemCount(): Int {
            return fragments.size
        }
    }
    return this
}

@SuppressLint("WrongConstant")
fun BottomNavigationView.init(bottomNavigationView: BottomNavigationView, viewPager2: ViewPager2): BottomNavigationView{
    bottomNavigationView.labelVisibilityMode = 1
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_main -> viewPager2.setCurrentItem(0, false)
            R.id.menu_project -> viewPager2.setCurrentItem(1, false)
            R.id.menu_system -> viewPager2.setCurrentItem(2, false)
            R.id.menu_public -> viewPager2.setCurrentItem(3, false)
            R.id.menu_me -> viewPager2.setCurrentItem(4, false)
        }
        true
    }
    return this
}