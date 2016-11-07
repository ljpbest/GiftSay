package ljp.qianfeng.com.giftsay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class HomeViewPageadapter extends FragmentStatePagerAdapter {
    List<String> listtitle;
    List<Fragment> fragmentList;

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public void setListtitle(List<String> listtitle) {
        this.listtitle = listtitle;
    }

    public HomeViewPageadapter(FragmentManager fm, List<String> listtitle, List<Fragment> fragmentList) {
        super(fm);
        this.listtitle = listtitle;
        this.fragmentList = fragmentList;
    }

    public HomeViewPageadapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listtitle==null?null:listtitle.get(position);
    }
}
