package ljp.qianfeng.com.giftsay.model;

import ljp.qianfeng.com.giftsay.bean.Navigation;
import ljp.qianfeng.com.giftsay.persenter.FagmentHomePresenter;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public interface FagmentHomeModule {
    public void queryNetData(String path, FagmentHomePresenter.CallBack callBack);
}
