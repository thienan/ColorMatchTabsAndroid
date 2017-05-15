package com.yalantis.colormatchtabs.colortabs

import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.SCROLL_STATE_IDLE
import android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING
import java.lang.ref.WeakReference

/**
 * Created by anna on 11.05.17.
 */
class ColorTabLayoutOnPageChangeListener(colorTabLayout: ColorMatchTabLayout) : ViewPager.OnPageChangeListener {

    private var previousScrollState: Int = 0
    private var scrollState: Int = 0

    private val tabLayoutReference: WeakReference<ColorMatchTabLayout> = WeakReference(colorTabLayout)

    override fun onPageScrollStateChanged(state: Int) {
        previousScrollState = scrollState
        scrollState = state
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val tabLayout = tabLayoutReference.get()
        val updateIndicator = !(scrollState == SCROLL_STATE_SETTLING && previousScrollState == SCROLL_STATE_IDLE)
        tabLayout?.setScrollPosition(position, positionOffset, updateIndicator)
    }

    override fun onPageSelected(position: Int) {
        val tabLayout = tabLayoutReference.get()
        tabLayout?.select(tabLayout.getTabAt(position))
    }

}