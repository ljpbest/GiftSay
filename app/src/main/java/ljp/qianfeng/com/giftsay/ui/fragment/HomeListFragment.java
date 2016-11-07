package ljp.qianfeng.com.giftsay.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.HomeListBean;
import ljp.qianfeng.com.giftsay.bean.RootBean;
import ljp.qianfeng.com.giftsay.constant.HttpUrlPath;
import ljp.qianfeng.com.giftsay.log.LogUtils;
import ljp.qianfeng.com.giftsay.persenter.impl.HomeListPresenter;
import ljp.qianfeng.com.giftsay.ui.adapter.HomeFragmentListAdapter;
import ljp.qianfeng.com.giftsay.ui.view.IFragmentHomeView;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class HomeListFragment extends Fragment implements IFragmentHomeView{
    private int page;
    private final static String KEY="content";
    private HomeListPresenter homeListPresenter;
    private ListView homelistview;
    private Context context;
    private HomeFragmentListAdapter homeFragmentListAdapter;

    public static HomeListFragment newInstance(int id){
        HomeListFragment homeListFragment=new HomeListFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(KEY,id);
        homeListFragment.setArguments(bundle);
        return homeListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //每次加20刷新下一页
        page=20;
        homeListPresenter=new HomeListPresenter();
        homeListPresenter.setFragmentHomeData(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int con=arguments.getInt(KEY);
        String path=getPath(con,page);
        View view = inflater.inflate(R.layout.fragment_home_homelist,null);
        homelistview = (ListView)view.findViewById(R.id.fragment_home_homelist_listview);
        homeFragmentListAdapter = new HomeFragmentListAdapter(context);
        homelistview.setAdapter(homeFragmentListAdapter);
        homeListPresenter.queryData(path);
        return view;
    }

    private String getPath(int id,int page){
        String path= HttpUrlPath.HOME_LIST_HAND+id+HttpUrlPath.HOME_LIST_CENTRAL+page+HttpUrlPath.HOME_LIST_TAIL;
        return path;
    }

    @Override
    public void refreshNavigation(RootBean navigation) {
        HomeListBean homeListBean= (HomeListBean) navigation;
        List<HomeListBean.DataBean.ItemsBean> list=homeListBean.getData().getItems();
        LogUtils.log(HomeListFragment.class,list.size()+"");
        homeFragmentListAdapter.setList(list);
        homeFragmentListAdapter.notifyDataSetChanged();
    }
}
