package ljp.qianfeng.com.giftsay.persenter.impl;

import ljp.qianfeng.com.giftsay.bean.Navigation;
import ljp.qianfeng.com.giftsay.bean.RootBean;
import ljp.qianfeng.com.giftsay.log.LogUtils;
import ljp.qianfeng.com.giftsay.model.FagmentHomeModule;
import ljp.qianfeng.com.giftsay.model.impl.NavigationModel;
import ljp.qianfeng.com.giftsay.persenter.FagmentHomePresenter;
import ljp.qianfeng.com.giftsay.ui.view.IFragmentHomeView;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class NvigationPresenter implements FagmentHomePresenter,FagmentHomePresenter.CallBack {
    private FagmentHomeModule navigtionModel;
    private IFragmentHomeView iFragmentHomeView;
    public NvigationPresenter(){
        navigtionModel=new NavigationModel();
    }
    @Override
    public void sucess(RootBean navigation) {
        iFragmentHomeView.refreshNavigation(navigation);
    }

    @Override
    public void queryData(String path) {
        navigtionModel.queryNetData(path,this);
    }

    @Override
    public void setFragmentHomeData(IFragmentHomeView iFragmentHomeView) {
            this.iFragmentHomeView=iFragmentHomeView;
    }
}
