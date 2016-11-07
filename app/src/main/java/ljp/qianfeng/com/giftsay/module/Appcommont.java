package ljp.qianfeng.com.giftsay.module;

import dagger.Component;
import dagger.Module;
import ljp.qianfeng.com.giftsay.ui.MainActivity;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
@Component(modules={AppModule.class})
public interface Appcommont {
    void inject(MainActivity activity);
}
