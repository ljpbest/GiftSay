package ljp.qianfeng.com.giftsay.ui.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import ljp.qianfeng.com.giftsay.ui.adapter.HomeMenuGriddpter;
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
    private LinearLayout linerlayout;
    private GridView homegrid;
    private CheckBox checkbox;
    private Animation   animation1;
    private Animation   animation2;
    private HomeMenuGriddpter homeMenuGriddpter;
    private LinearLayout linerout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fagmentHomePresenter=new NvigationPresenter();
        fagmentHomePresenter.setFragmentHomeData(this);
        initanimation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_home,null);
        tablayout = (TabLayout)view.findViewById(R.id.home_tablayout_menu);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager = (ViewPager)view.findViewById(R.id.home_viewpager_showmain);
        linerlayout = (LinearLayout)view.findViewById(R.id.home_viewpager_show);
        linerout = (LinearLayout)view.findViewById(R.id.home_tablayout_linearout_out);
        homegrid = (GridView)view.findViewById(R.id.home_viewpager_showgridview);
        homeMenuGriddpter = new HomeMenuGriddpter(context);
        homegrid.setAdapter(homeMenuGriddpter);
        checkbox = (CheckBox)view.findViewById(R.id.home_tablayout_radiobuttom);
        homeViewPageadapter=new HomeViewPageadapter(context.getSupportFragmentManager(),title,fragmentList);
        viewpager.setAdapter(homeViewPageadapter);
        tablayout.setupWithViewPager(viewpager);
        fagmentHomePresenter.queryData(HttpUrlPath.NAVIGATION_PATH);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ch=(CheckBox)view;
                if (ch.isChecked()){
                    linerlayout.setVisibility(View.VISIBLE);
                    linerlayout.startAnimation(animation1);
                }else {
                    linerlayout.startAnimation(animation2);
                    linerlayout.setVisibility(View.GONE);
                    linerout.setFocusable(true);
                    linerout.setFocusableInTouchMode(true);
                }
            }
        });
        return view;
    }
    private void initanimation(){
        animation1 = new ScaleAnimation(1f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0f);
        animation1.setFillAfter(true);
        animation1.setDuration(500);
        animation2 = new ScaleAnimation(1f,1f,1f,0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0f);
        animation2.setFillAfter(true);
        animation2.setDuration(500);
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
        homeMenuGriddpter.setCandidates(navigation1.getData().getCandidates());
        homeMenuGriddpter.notifyDataSetChanged();
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
