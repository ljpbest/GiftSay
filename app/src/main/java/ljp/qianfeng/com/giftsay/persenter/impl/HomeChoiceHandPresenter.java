package ljp.qianfeng.com.giftsay.persenter.impl;

import ljp.qianfeng.com.giftsay.bean.RootBean;
import ljp.qianfeng.com.giftsay.model.impl.HomeChoiceHandModel;
import ljp.qianfeng.com.giftsay.persenter.FagmentHomePresenter;
import ljp.qianfeng.com.giftsay.ui.view.IFragmentHomeView;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class HomeChoiceHandPresenter implements FagmentHomePresenter,FagmentHomePresenter.CallBack {
    private IFragmentHomeView iFragmentHomeView;
    private HomeChoiceHandModel homeChoiceHandModel;
    public HomeChoiceHandPresenter(){
        homeChoiceHandModel=new HomeChoiceHandModel();
    }
    @Override
    public void sucess(RootBean navigation) {
        iFragmentHomeView.refreshNavigation(navigation);
    }

    @Override
    public void queryData(String path) {
        homeChoiceHandModel.queryNetData(path,this);
    }

    @Override
    public void setFragmentHomeData(IFragmentHomeView iFragmentHomeView) {
        this.iFragmentHomeView=iFragmentHomeView;
    }
}
