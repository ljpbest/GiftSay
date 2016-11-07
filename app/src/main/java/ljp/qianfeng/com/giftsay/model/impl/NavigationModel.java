package ljp.qianfeng.com.giftsay.model.impl;

import com.google.gson.Gson;

import ljp.qianfeng.com.giftsay.bean.Navigation;
import ljp.qianfeng.com.giftsay.log.LogUtils;
import ljp.qianfeng.com.giftsay.model.FagmentHomeModule;
import ljp.qianfeng.com.giftsay.persenter.FagmentHomePresenter;
import ljp.qianfeng.com.giftsay.tool.IHttpCallBack;
import ljp.qianfeng.com.giftsay.tool.OKHttpTool;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class NavigationModel implements FagmentHomeModule {
    @Override
    public void queryNetData(String path, final FagmentHomePresenter.CallBack callBack) {
        OKHttpTool.init(path).get().callBack(new IHttpCallBack() {
            @Override
            public void success(String success) {
                LogUtils.log(NavigationModel.class,success);
                Gson gson=new Gson();
                Navigation navigation = gson.fromJson(success, Navigation.class);
                callBack.sucess(navigation);
            }

            @Override
            public void failure(String fail) {
                callBack.sucess(null);
            }
        });
    }
}
