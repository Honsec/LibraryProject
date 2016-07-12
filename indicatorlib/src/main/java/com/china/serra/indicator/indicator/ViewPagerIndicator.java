package com.china.serra.indicator.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.china.serra.indicator.R;

import java.util.List;

//<com.miniram.donpush.cn.common.widget.ViewPagerIndicator
//        android:id="@+id/id_indicator"
//        android:layout_width="match_parent"
//        android:layout_height="48dp"
//        android:background="@android:color/white"
//        android:orientation="horizontal"
//        custom:visible_tab_color_default="@android:color/black"
//        custom:visible_tab_color_guider="#404040"
//        custom:visible_tab_color_pres="@android:color/black"
//        custom:visible_tab_count="4" />

//ArrayList<String> titles= new ArrayList<String>();
//        titles.add(baseActivity.mResources.getString(R.string.receivedad_allpush));
//        titles.add(baseActivity.mResources.getString(R.string.receivedad_hotpush));
//        titles.add(baseActivity.mResources.getString(R.string.receivedad_savebox));
//        titles.add(baseActivity.mResources.getString(R.string.receivedad_myment));
//
//
//        mIndicator.setTabItemTitles(titles);

//  mIndicator.setViewPager(frag_receivedad_viewpager,index);


/**
 * @author hongyangAndroid
 *         http://blog.csdn.net/lmj623565791/
 */
public class ViewPagerIndicator extends LinearLayout {


    private int mGuider_color = 0x0000000;
    private Paint mPaint;

    private Path mPath;

    private int mTriangleWidth;

    private int mTriangleHeight;

    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
    /**
     * 三角形底边的最大宽度
     */
    private final int DIMENSION_TRIANGLE_WIDTH_MAX = (int) (getScreenWidth() / 3 * RADIO_TRIANGLE_WIDTH);

    private int mInitTranslationX;

    private int mTranslationX;

    private int mTabVisibleCount;

    private static final int COUNT_DEFAULT_TAB = 4;
    private static final int DEFAULT_COLOR_TEXT_NORMAL = 0x0000000;
    private static final int DEFAULT_COLOR_TEXT_HIGHLIGHT = 0x000000;

    private int mTab_color_def = Color.parseColor("#000000");
    private int mTab_color_pres = Color.parseColor("#000000");

    private List<String> mTitles;
    private int mRectWidth;
    private List<Integer> imageViewId;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取可见Tab的数量
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ViewPagerIndicator);

        for (int i = 0; i < a.getIndexCount(); i++) {


            if (a.getIndex(i) == R.styleable.ViewPagerIndicator_visible_tab_color_default) {
                mTab_color_def = a.getColor(R.styleable.ViewPagerIndicator_visible_tab_color_default, DEFAULT_COLOR_TEXT_NORMAL);
            } else if (a.getIndex(i) == R.styleable.ViewPagerIndicator_visible_tab_color_pres) {
                mTab_color_pres = a.getColor(R.styleable.ViewPagerIndicator_visible_tab_color_pres, DEFAULT_COLOR_TEXT_HIGHLIGHT);
            } else if (a.getIndex(i) == R.styleable.ViewPagerIndicator_visible_tab_color_guider) {
                mGuider_color = a.getColor(R.styleable.ViewPagerIndicator_visible_tab_color_guider, DEFAULT_COLOR_TEXT_HIGHLIGHT);
            } else if (a.getIndex(i) == R.styleable.ViewPagerIndicator_visible_tab_count) {
                mTabVisibleCount = a.getInt(
                        R.styleable.ViewPagerIndicator_visible_tab_count,
                        COUNT_DEFAULT_TAB);

            }

        }

        if (mTabVisibleCount < 0) {
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        }
        a.recycle();


        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mGuider_color);
        mPaint.setStyle(Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();

        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);

        canvas.restore();

        super.dispatchDraw(canvas);

    }

    private int height = 0;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        height = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);

        if (mTabVisibleCount == 4) {
            mTriangleWidth = (int) (w / mTabVisibleCount) * 15 / 16;
        } else {
            mTriangleWidth = (int) (w / mTabVisibleCount) / 2;
        }
//        mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH_MAX);
        mInitTranslationX = w / mTabVisibleCount / 2 - mTriangleWidth / 2;

        initTriangle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int cCount = getChildCount();
        if (cCount == 0)
            return;

        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view
                    .getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);
        }

        setItemClickEvent();

    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {

//        mTriangleHeight = mTriangleWidth / 2;
//        mTriangleHeight = height*2/9;//mTriangleWidth / 2;
        mTriangleHeight = 7;//height*2/9;//mTriangleWidth / 2;

        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.addRect(0, 0, mTriangleWidth, -mTriangleHeight, Path.Direction.CCW);
//        mPath.lineTo(mTriangleWidth, 0);
//        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();

    }

    /**
     * 指示器跟随手指进行滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mTabVisibleCount;
        mTranslationX = (int) (tabWidth * (offset + position));

        // 容器移动，在tab处于移动至最后一个时
        if (position >= (mTabVisibleCount - 2) && offset > 0
                && getChildCount() > mTabVisibleCount) {

            if (mTabVisibleCount != 1) {
                Log.e("TAG",
                        ((position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset))
                                + "");
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset),
                        0);
            }

        }

        invalidate();

    }

    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            for (String title : mTitles) {
                addView(generateTextView(title));
            }

            setItemClickEvent();
        }
    }

    public void setTabItemTitles(List<String> titles, List<Integer> imageViewId) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            this.imageViewId = imageViewId;

            for (int i = 0; i < mTitles.size(); i++) {
                addView(generateTextView(mTitles.get(i), imageViewId.get(i)));
            }

            setItemClickEvent();
        }
    }

    /**
     * 设置可见的Tab数量
     *
     * @param count
     */
    public void setVisibleTabCount(int count) {
        mTabVisibleCount = count;
    }

    /**
     * 根据title创建Tab
     *
     * @param title
     * @return
     */
    private View generateTextView(String title) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(mTab_color_def);
        tv.setSelected(false);
        tv.setLayoutParams(lp);
        return tv;
    }

    /**
     * 根据title创建Tab
     *
     * @param title
     * @return
     */
    private View generateTextView(String title, Integer imageInt) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(mTab_color_def);
        tv.setSelected(false);
        tv.setLayoutParams(lp);
        tv.setCompoundDrawablesWithIntrinsicBounds(0, imageInt, 0, 0);

        return tv;
    }


    private ViewPager mViewPager;

    public interface PageOnchangeListener {
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }

    public PageOnchangeListener mListener;

    public void setOnPageChangeListener(PageOnchangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置关联的ViewPager
     *
     * @param viewPager
     * @param pos
     */
    public void setViewPager(ViewPager viewPager, int pos) {
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (mListener != null) {
                    mListener.onPageSelected(position);
                }

                highLightTextView(position);
                // 极端情况的Bug修复
                if (position <= (mTabVisibleCount - 2))
                    scrollTo(0, 0);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset,
                            positionOffsetPixels);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }

            }
        });
        mViewPager.setCurrentItem(pos);
        highLightTextView(pos);
    }

    /**
     * 重置TAB文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                view.setSelected(false);
//                ((TextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }

        }

    }

    /**
     * 高亮某个Tab的文本
     *
     * @param pos
     */
    private void highLightTextView(int pos) {
        resetTextViewColor();
        View view = getChildAt(pos);
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            view.setSelected(true);
//            ((TextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    /**
     * 设置Tab的点击事件
     */
    private void setItemClickEvent() {
        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });

        }

    }

}
