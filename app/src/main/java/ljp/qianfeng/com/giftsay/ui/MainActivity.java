package ljp.qianfeng.com.giftsay.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.module.DaggerAppcommont;
import ljp.qianfeng.com.giftsay.ui.fragment.CategoryFragment;
import ljp.qianfeng.com.giftsay.ui.fragment.HomeFragment;
import ljp.qianfeng.com.giftsay.ui.fragment.ProfileFragment;
import ljp.qianfeng.com.giftsay.ui.fragment.SelectFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    @Inject
    HomeFragment homeFragment;
    @Inject
    CategoryFragment categoryFragment;
    @Inject
    SelectFragment selectFragment;
    @Inject
    ProfileFragment profileFragment;
    @BindView(R.id.mian_raidogroup)
    RadioGroup radioGroup;
    @BindView(R.id.main_radio_home)
    RadioButton radioButton;

    Fragment mfrgment;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerAppcommont.create().inject(this);
        init();
        radioGroup.setOnCheckedChangeListener(this);

    }
    void init(){
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_main,homeFragment);
        mfrgment=homeFragment;
        transaction.commit();
        radioButton.setChecked(true);
    }
    private void changeFragment(Fragment fragment){
        FragmentTransaction transaction=supportFragmentManager.beginTransaction();
         if(mfrgment!=null){
             transaction.hide(mfrgment);
         }
        if(!fragment.isAdded()){
            transaction.add(R.id.fragment_main,fragment);
        } else {
            transaction.show(fragment);
        }
        mfrgment=fragment;
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.main_radio_category:
                changeFragment(categoryFragment);
                break;
            case R.id.main_radio_home:
                changeFragment(homeFragment);
                break;
            case R.id.main_radio_profile:
                changeFragment(profileFragment);
                break;
            case R.id.main_radio_select:
                changeFragment(selectFragment);
                break;
        }
    }
}
