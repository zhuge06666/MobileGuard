package cn.edu.gdmec.android.mobileguard.m9advancedtools.widgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Administrator on 2017/12/5.
 */

public class AdvancedToolsView extends RelativeLayout {
    private TextView mDesriptionTV;
    private String desc = "";
    private Drawable drawable;
    private ImageView mLeftImgv;

    public AdvancedToolsView(Context context) {
        super(context);
        init(context);
    }

    public AdvancedToolsView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(context);
    }

    public AdvancedToolsView(Context context, AttributeSet attr) {
        super(context, attr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attr, R.styleable.AdvancedToolsView);
        desc = mTypedArray.getString(R.styleable.AdvancedToolsView_desc);
        drawable = mTypedArray.getDrawable(R.styleable.AdvancedToolsView_android_src);
        mTypedArray.recycle();
        init(context);
    }
    private void init(Context context){
        View view =View.inflate(context,R.layout.ui_advancedtools_view,null);
        this.addView(view);
        mDesriptionTV=(TextView)findViewById(R.id.tv_decription);
        mLeftImgv=(ImageView)findViewById(R.id.imgv_left);
        mDesriptionTV.setText(desc);
        if(drawable != null)
            mLeftImgv.setImageDrawable(drawable);
    }
}