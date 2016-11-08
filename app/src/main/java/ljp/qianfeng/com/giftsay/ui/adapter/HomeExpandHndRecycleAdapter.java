package ljp.qianfeng.com.giftsay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.ChoiceListBean;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class HomeExpandHndRecycleAdapter extends RecyclerView.Adapter<HomeExpandHndRecycleAdapter.MyViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ChoiceListBean.DataBean.SecondaryBannersBean> list;

    public HomeExpandHndRecycleAdapter(Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setList(List<ChoiceListBean.DataBean.SecondaryBannersBean> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.imageview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
             Picasso.with(context).load(list.get(position).getImage_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image_item_image);
        }
    }


}
