package ljp.qianfeng.com.giftsay.model.impl;

import com.google.gson.Gson;

import ljp.qianfeng.com.giftsay.bean.ChoiceHandBean;
import ljp.qianfeng.com.giftsay.bean.ChoiceListBean;
import ljp.qianfeng.com.giftsay.log.LogUtils;
import ljp.qianfeng.com.giftsay.model.FagmentHomeModule;
import ljp.qianfeng.com.giftsay.persenter.FagmentHomePresenter;
import ljp.qianfeng.com.giftsay.tool.IHttpCallBack;
import ljp.qianfeng.com.giftsay.tool.OKHttpTool;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class HomeChoiceListModel implements FagmentHomeModule {
    @Override
    public void queryNetData(String path, final FagmentHomePresenter.CallBack callBack) {
        OKHttpTool.init(path).get().callBack(new IHttpCallBack() {
            @Override
            public void success(String success) {
                LogUtils.log(HomeChoiceListModel.class,success);
                Gson gson=new Gson();
                ChoiceListBean choiceListBean=gson.fromJson(success,ChoiceListBean.class);
                callBack.sucess(choiceListBean);
            }

            @Override
            public void failure(String fail) {
                callBack.sucess(null);
            }
        });
    }
}
