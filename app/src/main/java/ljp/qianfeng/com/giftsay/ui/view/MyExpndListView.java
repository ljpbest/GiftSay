package ljp.qianfeng.com.giftsay.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListView;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class MyExpndListView extends ExpandableListView {
    private float startY;
    private float endY;
    private boolean isup;
    public boolean isup(){
        return isup;
    }
    public MyExpndListView(Context context) {
        this(context,null);
    }

    public MyExpndListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyExpndListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isup=false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                endY = ev.getY();
                if(endY-startY>0){
                    isup=true;
                }else {
                    isup=false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
