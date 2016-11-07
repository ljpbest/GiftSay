package ljp.qianfeng.com.giftsay.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.HomeListBean;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class HomeFragmentListAdapter extends BaseAdapter {

    Context context;
    List<HomeListBean.DataBean.ItemsBean> list;
    LayoutInflater layoutInflater;
    public HomeFragmentListAdapter(Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setList(List<HomeListBean.DataBean.ItemsBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list==null?null:list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeListBean.DataBean.ItemsBean bean=list.get(i);
        HomeViewHolder homeViewHolder=null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.fragment_home_homelist_item,null,false);
            homeViewHolder=new HomeViewHolder(view);
        }else {
            homeViewHolder= (HomeViewHolder) view.getTag();
        }
        Picasso.with(context).load(bean.getCover_image_url()).into(homeViewHolder.bigImage);
        homeViewHolder.texttitle.setText(bean.getTitle());
        homeViewHolder.checklike.setText(bean.getLikes_count()+"");
        if(bean.isLiked()){
            homeViewHolder.checklike.setChecked(true);
        }
        return view;
    }
    class HomeViewHolder{
        ImageView bigImage;
        TextView texttitle;
        CheckBox checklike;
        HomeViewHolder(View view){
            view.setTag(this);
            bigImage= (ImageView) view.findViewById(R.id.fragment_home_homelist_item_image);
            texttitle=(TextView)view.findViewById(R.id.fragment_home_homelist_item_texttitle);
            checklike=(CheckBox)view.findViewById(R.id.fragment_home_homelist_item_textcollect);
        }
    }
}
