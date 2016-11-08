package ljp.qianfeng.com.giftsay.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.ChoiceContentBean;
import ljp.qianfeng.com.giftsay.bean.ChoiceHandBean;
import ljp.qianfeng.com.giftsay.bean.ChoiceListBean;
import ljp.qianfeng.com.giftsay.bean.RootBean;
import ljp.qianfeng.com.giftsay.constant.HttpUrlPath;
import ljp.qianfeng.com.giftsay.log.LogUtils;
import ljp.qianfeng.com.giftsay.persenter.impl.HomeChoiceContentPresenter;
import ljp.qianfeng.com.giftsay.persenter.impl.HomeChoiceHandPresenter;
import ljp.qianfeng.com.giftsay.persenter.impl.HomeChoiceListPresenter;
import ljp.qianfeng.com.giftsay.ui.adapter.HomeChoiceExpandAdapter;
import ljp.qianfeng.com.giftsay.ui.adapter.HomeExpandHandBaseAdapter;
import ljp.qianfeng.com.giftsay.ui.adapter.HomeExpandHandPageAdapter;
import ljp.qianfeng.com.giftsay.ui.adapter.HomeExpandHndRecycleAdapter;
import ljp.qianfeng.com.giftsay.ui.view.IFragmentHomeView;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class CompetitiveFragment extends Fragment implements IFragmentHomeView {
    private final static String KEY="content";
    private Context context;
    private int page;
    private HomeChoiceHandPresenter homeChoiceHandPresenter;
    private HomeChoiceListPresenter homeChoiceListPresenter;
    private HomeChoiceContentPresenter homeChoiceContentPresenter;
    private ExpandableListView expandableListView;
    private HomeChoiceExpandAdapter homeChoiceExpandAdapter;
    private Map<String,List<ChoiceContentBean.DataBean.ItemsBean>> map;
    private List<String> keylist;
    private ViewPager handviewpager;
    private TabLayout hand_tablayout;
    private GridView handgrid;
    private HomeExpandHandPageAdapter homeExpandHandPageAdapter;
    private HomeExpandHandBaseAdapter homeExpandHandBaseAdapter;
    private RecyclerView recycleview;
    private HomeExpandHndRecycleAdapter homeExpandHndRecycleAdapter;

    public static CompetitiveFragment newInstance(int id){
        CompetitiveFragment competitiveFragment=new CompetitiveFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(KEY,id);
        competitiveFragment.setArguments(bundle);
        return competitiveFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page=20;
        homeChoiceHandPresenter=new HomeChoiceHandPresenter();
        homeChoiceHandPresenter.setFragmentHomeData(this);
        homeChoiceListPresenter=new HomeChoiceListPresenter();
        homeChoiceListPresenter.setFragmentHomeData(this);
        homeChoiceContentPresenter=new HomeChoiceContentPresenter();
        homeChoiceContentPresenter.setFragmentHomeData(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int con=arguments.getInt(KEY);
        String path=getPath(con,page);
        View view = inflater.inflate(R.layout.fragment_home_cometitive,null);
        expandableListView = (ExpandableListView)view.findViewById(R.id.home_choice_content_expandableList);
        homeChoiceExpandAdapter = new HomeChoiceExpandAdapter(context);
        expandableListView.setAdapter(homeChoiceExpandAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        View handview=inflater.inflate(R.layout.fragment_home_expandlist_hand,null);
        handviewpager = (ViewPager)handview.findViewById(R.id.fragment_home_expandlist_hand_viewpage);
        hand_tablayout = (TabLayout)handview.findViewById(R.id.fragment_home_expandlist_hand_tablayout);
        recycleview = (RecyclerView)handview.findViewById(R.id.fragment_home_expandlist_hand_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recycleview.setLayoutManager(linearLayoutManager);
        homeExpandHandPageAdapter = new HomeExpandHandPageAdapter(context);
        handviewpager.setAdapter(homeExpandHandPageAdapter);
        homeExpandHndRecycleAdapter = new HomeExpandHndRecycleAdapter(context);
        recycleview.setAdapter(homeExpandHndRecycleAdapter);
        expandableListView.addHeaderView(handview);
        homeChoiceHandPresenter.queryData(HttpUrlPath.HOME_CHOICE_HANDPAGER);
        homeChoiceListPresenter.queryData(HttpUrlPath.HOME_CHOICE_LISTPAGER);
        homeChoiceContentPresenter.queryData(path);
        return view;
    }

    @Override
    public void refreshNavigation(RootBean navigation) {
        if(navigation instanceof ChoiceHandBean){
            ChoiceHandBean choiceHandBean= (ChoiceHandBean) navigation;
            List<ChoiceHandBean.DataBean.BannersBean> mhlist=choiceHandBean.getData().getBanners();
            homeExpandHandPageAdapter.setList(mhlist);
            homeExpandHandPageAdapter.notifyDataSetChanged();

        }else if(navigation instanceof ChoiceListBean){
            ChoiceListBean choiceListBean= (ChoiceListBean) navigation;
            List<ChoiceListBean.DataBean.SecondaryBannersBean> cclist=choiceListBean.getData().getSecondary_banners();
            homeExpandHndRecycleAdapter.setList(cclist);
            homeExpandHndRecycleAdapter.notifyDataSetChanged();
        }else if(navigation instanceof ChoiceContentBean){
            ChoiceContentBean choiceContentBean= (ChoiceContentBean) navigation;
            List<ChoiceContentBean.DataBean.ItemsBean> maplist=choiceContentBean.getData().getItems();
            map=new HashMap<>();
            keylist=new ArrayList<>();
            for(int i=0,len=maplist.size();i<len;i++){
                int time=maplist.get(i).getPublished_at();
                String timestr=gettime(time*1000);
                List<ChoiceContentBean.DataBean.ItemsBean> itemsBeen = map.get(timestr);
                if(itemsBeen==null){
                    List<ChoiceContentBean.DataBean.ItemsBean> slit=new ArrayList<>();
                    keylist.add(timestr);
                    slit.add(maplist.get(i));
                    map.put(timestr,slit);
                }else {
                    itemsBeen.add(maplist.get(i));
                }
            }
            homeChoiceExpandAdapter.setMap(map);
            homeChoiceExpandAdapter.setKeylist(keylist);
            homeChoiceExpandAdapter.notifyDataSetChanged();
            for(int i=0,len=keylist.size();i<len;i++){
                expandableListView.expandGroup(i);
            }
        }
    }
    private String gettime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日 EE");
        return simpleDateFormat.format(new Date(time));
    }
    private String getPath(int id,int page){
        String path= HttpUrlPath.HOME_LIST_HAND+id+HttpUrlPath.HOME_LIST_CENTRAL+page+HttpUrlPath.HOME_LIST_TAIL;
        return path;
    }
}
