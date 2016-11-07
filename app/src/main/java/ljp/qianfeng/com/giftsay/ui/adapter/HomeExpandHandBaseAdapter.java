package ljp.qianfeng.com.giftsay.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.ChoiceListBean;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class HomeExpandHandBaseAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<ChoiceListBean.DataBean.SecondaryBannersBean> list;

    public HomeExpandHandBaseAdapter(Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setList(List<ChoiceListBean.DataBean.SecondaryBannersBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder=null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.imageview,null,false);
            baseViewHolder =new BaseViewHolder(view);
        }else {
            baseViewHolder= (BaseViewHolder) view.getTag();
        }
        Picasso.with(context).load(list.get(i).getImage_url()).into(baseViewHolder.imageView);
        return view;
    }
    class BaseViewHolder{
        ImageView imageView;
        BaseViewHolder(View view){
            view.setTag(this);
            imageView= (ImageView) view.findViewById(R.id.image_item_image);
        }
    }
}
