package ljp.qianfeng.com.giftsay.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ljp.qianfeng.com.giftsay.bean.ChoiceHandBean;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class HomeExpandHandPageAdapter extends PagerAdapter {
    Context context;
    public HomeExpandHandPageAdapter(Context context) {
        this.context = context;
    }
    public void setList(List<ChoiceHandBean.DataBean.BannersBean> list) {
        this.list = list;
    }
    List<ChoiceHandBean.DataBean.BannersBean> list;
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(list.get(position).getImage_url()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
