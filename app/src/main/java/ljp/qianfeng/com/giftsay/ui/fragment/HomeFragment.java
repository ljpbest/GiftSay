package ljp.qianfeng.com.giftsay.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.Navigation;
import ljp.qianfeng.com.giftsay.bean.RootBean;
import ljp.qianfeng.com.giftsay.constant.HttpUrlPath;
import ljp.qianfeng.com.giftsay.log.LogUtils;
import ljp.qianfeng.com.giftsay.persenter.FagmentHomePresenter;
import ljp.qianfeng.com.giftsay.persenter.impl.NvigationPresenter;
import ljp.qianfeng.com.giftsay.ui.MainActivity;
import ljp.qianfeng.com.giftsay.ui.adapter.HomeViewPageadapter;
import ljp.qianfeng.com.giftsay.ui.view.IFragmentHomeView;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
public class HomeFragment extends Fragment implements IFragmentHomeView{
    private TabLayout tablayout;
    private ViewPager viewpager;
    private FagmentHomePresenter fagmentHomePresenter;
    private List<String> title;
    private List<Fragment> fragmentList;
    private MainActivity context;
    private HomeViewPageadapter homeViewPageadapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fagmentHomePresenter=new NvigationPresenter();
        fagmentHomePresenter.setFragmentHomeData(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_home,null);
        tablayout = (TabLayout)view.findViewById(R.id.home_tablayout_menu);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager = (ViewPager)view.findViewById(R.id.home_viewpager_showmain);
        homeViewPageadapter=new HomeViewPageadapter(context.getSupportFragmentManager(),title,fragmentList);
        viewpager.setAdapter(homeViewPageadapter);
        tablayout.setupWithViewPager(viewpager);
        fagmentHomePresenter.queryData(HttpUrlPath.NAVIGATION_PATH);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context= (MainActivity) context;
    }

    @Override
    public void refreshNavigation(RootBean navigation) {
        Navigation navigation1= (Navigation) navigation;
        List<Navigation.DataBean.ChannelsBean> beanlist=navigation1.getData().getChannels();
        LogUtils.log(HomeFragment.class,beanlist.size()+"");

        title=new ArrayList<>();
        fragmentList=new ArrayList<>();
        for(int i=0,len=beanlist.size();i<len;i++){
            title.add(beanlist.get(i).getName());
            if(i==0){
                fragmentList.add(CompetitiveFragment.newInstance(beanlist.get(i).getId()));
                continue;
            }
            fragmentList.add(HomeListFragment.newInstance(beanlist.get(i).getId()));
        }
        homeViewPageadapter.setListtitle(title);
        homeViewPageadapter.setFragmentList(fragmentList);
        homeViewPageadapter.notifyDataSetChanged();
    }
}
