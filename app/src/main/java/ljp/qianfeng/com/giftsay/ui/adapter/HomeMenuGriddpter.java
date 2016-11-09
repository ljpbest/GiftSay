package ljp.qianfeng.com.giftsay.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.Navigation;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class HomeMenuGriddpter extends BaseAdapter {
    List<Navigation.DataBean.CandidatesBean> candidates;
    Context context;
    LayoutInflater layoutIflater;

    public HomeMenuGriddpter(Context context) {
        this.context = context;
        layoutIflater=LayoutInflater.from(context);
    }

    public void setCandidates(List<Navigation.DataBean.CandidatesBean> candidates) {
        this.candidates = candidates;
    }

    @Override
    public int getCount() {
        return candidates==null?0:candidates.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextViewHolder textViewHolder=null;
        if(view==null){
            view=layoutIflater.inflate(R.layout.text_item,viewGroup,false);
            textViewHolder=new TextViewHolder(view);
        }else {
            textViewHolder= (TextViewHolder) view.getTag();
        }
        textViewHolder.textview.setText(candidates.get(i).getName());
        return view;
    }
    class TextViewHolder{
        TextView textview;
        TextViewHolder(View view){
            view.setTag(this);
            textview= (TextView) view.findViewById(R.id.text_item_text);
        }
    }
}
