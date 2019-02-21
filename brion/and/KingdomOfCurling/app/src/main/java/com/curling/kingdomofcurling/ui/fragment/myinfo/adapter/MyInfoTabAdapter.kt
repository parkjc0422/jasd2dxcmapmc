package com.curling.kingdomofcurling.ui.fragment.myinfo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.curling.kingdomofcurling.ui.fragment.myinfo.MdnEditFragment
import com.curling.kingdomofcurling.ui.fragment.myinfo.PasswordEditFragment

class MyInfoTabAdapter(val fragmentManager: FragmentManager) : FragmentPagerAdapter (fragmentManager){
    override fun getItem(position: Int): Fragment {
        if(position == 0) return MdnEditFragment.instance
        else if( position == 1) return PasswordEditFragment.instance
        else return MdnEditFragment.instance
    }

    override fun getCount(): Int = 2


    override fun getPageTitle(position: Int): CharSequence? {
        if(position == 0) return "회원정보 변경"
        else if(position == 1) return "비밀번호 변경"
        return super.getPageTitle(position)
    }

}