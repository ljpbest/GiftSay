package ljp.qianfeng.com.giftsay.persenter;

import ljp.qianfeng.com.giftsay.bean.Navigation;
import ljp.qianfeng.com.giftsay.bean.RootBean;
import ljp.qianfeng.com.giftsay.ui.view.IFragmentHomeView;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public interface FagmentHomePresenter {
    void queryData(String path);
    void setFragmentHomeData(IFragmentHomeView iFragmentHomeView);
    interface CallBack{
        void sucess(RootBean navigation);

    }
}
