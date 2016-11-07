package ljp.qianfeng.com.giftsay.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import ljp.qianfeng.com.giftsay.ui.fragment.CategoryFragment;
import ljp.qianfeng.com.giftsay.ui.fragment.HomeFragment;
import ljp.qianfeng.com.giftsay.ui.fragment.ProfileFragment;
import ljp.qianfeng.com.giftsay.ui.fragment.SelectFragment;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
@Module
public class AppModule {
    @Provides
    public HomeFragment getHomeFragment(){
        return new HomeFragment();
    }
    @Provides
    public SelectFragment getSelectFragment(){
        return new SelectFragment();
    }
    @Provides
    public CategoryFragment getCategoryFragment(){
        return new CategoryFragment();
    }
    @Provides
    public ProfileFragment getProfileFragment(){
        return  new ProfileFragment();
    }

}
