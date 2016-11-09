package ljp.qianfeng.com.giftsay.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ljp.qianfeng.com.giftsay.R;
import ljp.qianfeng.com.giftsay.bean.ChoiceContentBean;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class HomeChoiceExpandAdapter extends BaseExpandableListAdapter {
    Map<String,List<ChoiceContentBean.DataBean.ItemsBean>> map;
    Context context;
    LayoutInflater layoutInflater;
    List<String> keylist;
    public HomeChoiceExpandAdapter(Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
        map=new HashMap<>();
        keylist=new ArrayList<>();
    }

    public void setMap(Map<String, List<ChoiceContentBean.DataBean.ItemsBean>> map) {
        this.map.putAll(map);
    }

    public void setKeylist(List<String> keylist) {
        this.keylist.addAll(keylist);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {

        return keylist==null?0:keylist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(keylist!=null) {
            String key = keylist.get(i);
            List<ChoiceContentBean.DataBean.ItemsBean> mlist = map.get(key);
            return mlist == null ? 0 : mlist.size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return keylist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        String key=keylist.get(i);
        List<ChoiceContentBean.DataBean.ItemsBean> mlist=map.get(key);
        return mlist.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder=null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.fragment_home_expandlist_groupitem,null,false);
            groupViewHolder=new GroupViewHolder(view);
        }else {
            groupViewHolder= (GroupViewHolder) view.getTag();
        }
        groupViewHolder.textViewtime.setText(keylist.get(i));
        return view;
    }
    class GroupViewHolder{
        TextView textViewtime;
        GroupViewHolder(View view){
            view.setTag(this);
            textViewtime= (TextView) view.findViewById(R.id.home_choice_expandgroup_texttime);
        }
    }
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String key=keylist.get(i);
        List<ChoiceContentBean.DataBean.ItemsBean> llist=map.get(key);
        ChoiceContentBean.DataBean.ItemsBean bean=llist.get(i1);
        ChildViewHolder childViewHolder=null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.fragment_home_expandlist_childitem,null,false);
            childViewHolder=new ChildViewHolder(view);
        }else {
            childViewHolder= (ChildViewHolder) view.getTag();
        }
        Picasso.with(context).load(bean.getCover_image_url()).into(childViewHolder.bigImage);
        childViewHolder.texttitle.setText(bean.getTitle());
        childViewHolder.checklike.setText(bean.getLikes_count()+"");
        if(bean.isLiked()){
            childViewHolder.checklike.setChecked(true);
        }
        return view;
    }
    class ChildViewHolder{
        ImageView bigImage;
        TextView texttitle;
        CheckBox checklike;
        ChildViewHolder(View view){
            view.setTag(this);
            bigImage= (ImageView) view.findViewById(R.id.fragment_home_expandlist_childite_image);
            texttitle=(TextView)view.findViewById(R.id.fragment_home_expandlist_childitem_texttitle);
            checklike=(CheckBox)view.findViewById(R.id.fragment_home_expandlist_childite_textcollect);
        }
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
