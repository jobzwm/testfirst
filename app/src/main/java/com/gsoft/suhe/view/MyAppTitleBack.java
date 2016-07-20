package com.gsoft.suhe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gsoft.suhe.R;

/**
 * D:自定义 ToolBar
 * 2016/6/27
 */
public class MyAppTitleBack extends FrameLayout {
    private FrameLayout mLeftLay ;
    private FrameLayout mRightLay ;

    private TextView mLeftTextView ;
    private TextView mRightTextView ;
    private TextView mTitleTextView ;
    private ImageView mLeftImageView ;
    private ImageView mRightImageView ;

    private int type ;//显示类型
    private static final int TYPE_BACK = 0 ;//有返回按钮
    private static final int TYPE_NO_BACK= 1 ;//无返回按钮

    private String mTitleText ;
    private String mLeftText ;
    private String mRightText ;
    private boolean mIsLeftVisible;
    private boolean mIsRightVisible;
    private boolean mIsLeftDrawVisible;
    private boolean mIsRightDrawVisible;
    private boolean mIsLeftImageVisible;
    private boolean mIsRightImageVisible;
    private int mLeftTextColor ;
    private int mRightTextColor ;
    private int mTitleTextColor ;
    private Drawable mLeftDrawLeft ;
    private Drawable mRightDrawRight ;
    private Drawable mLeftImageDraw ;
    private Drawable mRightImageDraw ;

    private MyATBLeftClickListener mLeftClickListener ;
    private MyATBRightClickListener mRightClickListener ;

    public interface MyATBLeftClickListener{
        void onLeftViewClick() ;
    }
    public interface MyATBRightClickListener{
        void onRightViewClick() ;
    }

    public MyAppTitleBack(Context context) {
        this(context , null);
    }

    public MyAppTitleBack(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public MyAppTitleBack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.my_app_titleback_lay , this , true) ;
        init(context, attrs, defStyleAttr) ;
    }

    /**
     * 初始化
     * @param context context
     * @param attrs attrs
     * @param defStyleAttr def
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        mLeftLay = (FrameLayout) findViewById(R.id.my_titleBack_left_lay);
        mRightLay = (FrameLayout) findViewById(R.id.my_titleBack_right_lay);
        mLeftTextView = (TextView) findViewById(R.id.my_titleBack_left_text);
        mTitleTextView = (TextView) findViewById(R.id.my_titleBack_title_text);
        mRightTextView = (TextView) findViewById(R.id.my_titleBack_right_text);
        mLeftImageView = (ImageView) findViewById(R.id.my_titleBack_left_img);
        mRightImageView = (ImageView) findViewById(R.id.my_titleBack_right_img);

        TypedArray a = context.obtainStyledAttributes(attrs
                , R.styleable.my_app_titleBack , defStyleAttr , 0) ;

        type = a.getInt(R.styleable.my_app_titleBack_type , TYPE_NO_BACK) ;

        mTitleText = a.getString(R.styleable.my_app_titleBack_titleText) ;
        mLeftText = a.getString(R.styleable.my_app_titleBack_titleText) ;
        mRightText = a.getString(R.styleable.my_app_titleBack_titleText) ;

        mIsLeftVisible = a.getBoolean(R.styleable.my_app_titleBack_leftTextVisible , false) ;
        mIsRightVisible = a.getBoolean(R.styleable.my_app_titleBack_leftTextVisible , false) ;
        mIsLeftDrawVisible = a.getBoolean(R.styleable.my_app_titleBack_leftTextDrawVisible , false) ;
        mIsRightDrawVisible = a.getBoolean(R.styleable.my_app_titleBack_rightTextDrawVisible , false) ;
        mIsLeftImageVisible = a.getBoolean(R.styleable.my_app_titleBack_leftImageVisible , false) ;
        mIsRightImageVisible = a.getBoolean(R.styleable.my_app_titleBack_rightImageVisible , false) ;

        mLeftTextColor = a.getColor(R.styleable.my_app_titleBack_myLeftTextColor , getResources().getColor(R.color.myapp_textColor_default)) ;
        mRightTextColor = a.getColor(R.styleable.my_app_titleBack_myRightTextColor , getResources().getColor(R.color.myapp_textColor_default)) ;
        mTitleTextColor = a.getColor(R.styleable.my_app_titleBack_myTitleTextColor , getResources().getColor(R.color.myapp_textColor_default)) ;

        mLeftDrawLeft = a.getDrawable(R.styleable.my_app_titleBack_leftTextDrawLeft) ;
        mRightDrawRight = a.getDrawable(R.styleable.my_app_titleBack_rightTextDrawRight) ;
        mLeftImageDraw = a.getDrawable(R.styleable.my_app_titleBack_leftImageDraw) ;
        mRightImageDraw = a.getDrawable(R.styleable.my_app_titleBack_rightImageDraw) ;

        /*** left部分 ***/

        if(type == TYPE_BACK){//显示返回按钮
            mLeftImageView.setVisibility(View.VISIBLE) ;
        }else{//非返回--可能有别的图标或文字
            //如果textView 和 imageView 的visible属性都为true
            //那么只显示imageView，也就是imageView的visible会覆盖
            if(mIsLeftImageVisible){
                mLeftImageView.setVisibility(View.VISIBLE) ;
                if(mLeftImageDraw != null){
                    mLeftImageView.setImageDrawable(mLeftImageDraw);
                }
            }else{
                if(mIsLeftVisible){
                    mLeftTextView.setVisibility(View.VISIBLE) ;
                    if(mLeftTextView != null){
                        mLeftTextView.setText(mLeftText);
                    }
                    mLeftTextView.setTextColor(mLeftTextColor);

                    if(mIsLeftDrawVisible && mLeftDrawLeft != null){
                        mLeftDrawLeft.setBounds(0, 0
                                , mLeftDrawLeft.getMinimumWidth(), mLeftDrawLeft.getMinimumHeight());
                        mLeftTextView.setCompoundDrawables(mLeftDrawLeft , null , null , null);
                    }
                }
            }
        }

        /*** title部分 ***/

        if(mTitleText != null){
            mTitleTextView.setText(mTitleText);
        }

        mTitleTextView.setTextColor(mTitleTextColor) ;

        /*** right部分 ***/
        if(mIsRightImageVisible){
            mRightImageView.setVisibility(View.VISIBLE);
            if(mRightImageDraw != null){
                mRightImageView.setImageDrawable(mRightImageDraw) ;
            }
        }else{
            if(mIsRightVisible){
                mRightTextView.setVisibility(View.VISIBLE);
                if(mRightText != null){
                    mRightTextView.setText(mRightText);
                }
                mRightTextView.setTextColor(mRightTextColor) ;

                if(mIsRightDrawVisible && mRightDrawRight != null){
                    mRightDrawRight.setBounds(0, 0
                            , mRightDrawRight.getMinimumWidth(), mRightDrawRight.getMinimumHeight());
                    mRightTextView.setCompoundDrawables(null , null , mRightDrawRight , null) ;
                }
            }
        }

        /***监听**/
        mLeftLay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(type == TYPE_BACK || mIsLeftVisible || mIsLeftImageVisible){//显示了
                    if(mLeftClickListener != null){
                        mLeftClickListener.onLeftViewClick();
                    }
                }
            }
        });
        mRightLay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mIsRightVisible || mIsRightImageVisible){
                    if(mRightClickListener != null){
                        mRightClickListener.onRightViewClick() ;
                    }
                }
            }
        });
    }

    /**
     * left点击监听
     * @param mLeftListener left
     */
    public void setLeftViewClickListener(MyATBLeftClickListener mLeftListener){
        mLeftClickListener = mLeftListener ;
    }

    /**
     * right点击监听
     * @param mRightListener right
     */
    public void setRightViewClickListener(MyATBRightClickListener mRightListener){
        mRightClickListener = mRightListener ;
    }

    /**
     * 设置leftImage的显示隐藏
     * @param mLeftImageVisible visible
     */
    public void setLeftImageViewVisible(boolean mLeftImageVisible){
        mIsLeftImageVisible = mLeftImageVisible ;
        mLeftImageView.setVisibility(mLeftImageVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置leftImageView的背景
     * @param drawable drawable
     */
    public void setLeftImageDrawable(Drawable drawable){
        if(mIsLeftImageVisible){
            mLeftImageDraw = drawable ;
            if(mLeftImageDraw != null){
                mLeftImageView.setImageDrawable(mLeftImageDraw);
            }
        }
    }

    /**
     * 设置leftTextView的drawableLeft
     * @param drawable drawableLeft
     */
    public void setLeftTextViewDrawLeft(Drawable drawable){
        if(mIsLeftVisible && mIsLeftDrawVisible){
            mLeftDrawLeft = drawable ;
            if(mLeftDrawLeft != null){
                mLeftDrawLeft.setBounds(0, 0
                        , mLeftDrawLeft.getMinimumWidth(), mLeftDrawLeft.getMinimumHeight());
                mLeftTextView.setCompoundDrawables(mLeftDrawLeft , null , null , null);
            }
        }
    }

    /**
     * 设置left的显示隐藏
     * @param mLeftVisible visible
     */
    public void setLeftTextViewVisible(boolean mLeftVisible){
        mIsLeftVisible = mLeftVisible ;
        mLeftTextView.setVisibility(mLeftVisible ? View.VISIBLE : View.INVISIBLE) ;
    }

    /**
     * 设置left的内容
     * @param mLeftString leftText
     */
    public void setLeftTextViewText(String mLeftString){
        mLeftTextView.setText(mLeftString == null ? "" : mLeftString) ;
    }

    /**
     * 设置right的显示隐藏
     * @param mRightVisible visible
     */
    public void setRightTextViewVisible(boolean mRightVisible){
        mIsRightVisible = mRightVisible ;
        mRightTextView.setVisibility(mRightVisible ? View.VISIBLE : View.INVISIBLE) ;
    }

    /**
     * 设置right的内容
     * @param mRightString rightText
     */
    public void setRightTextViewText(String mRightString){
        mRightTextView.setText(mRightString == null ? "" : mRightString) ;
    }
    /**
     * 设置rightImage的显示隐藏
     * @param mRightImageVisible visible
     */
    public void setRightImageViewVisible(boolean mRightImageVisible){
        mIsRightImageVisible = mRightImageVisible ;
        mRightImageView.setVisibility(mRightImageVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置rightImageView的背景
     * @param drawable drawable
     */
    public void setRightImageDrawable(Drawable drawable){
        if(mIsRightImageVisible){
            mRightImageDraw = drawable ;
            if(mRightImageDraw != null){
                mRightImageView.setImageDrawable(mRightImageDraw);
            }
        }
    }
    /**
     * 设置title的内容
     * @param mTitleString titleText
     */
    public void setTitleTextViewText(String mTitleString){
        mTitleTextView.setText(mTitleString == null ? "" : mTitleString) ;
    }




}
