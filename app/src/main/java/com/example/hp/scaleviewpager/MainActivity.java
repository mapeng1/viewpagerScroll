package com.example.hp.scaleviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ViewPager mViewPager2;
    private PagerAdapter mAdapter;

    int[] imgRes = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.i};
    String[] tvs = {"aaaaa","bbbbb","ccccc","ddddd","eeeee","fffff","ggggg","hhhhh","iiiii",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setPageMargin(ScreenUtils.dp2px(100));

        mViewPager.setOffscreenPageLimit(2);//viewpager预加载的页数

        // 将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
        findViewById(R.id.fragment_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
        mViewPager.setAdapter(mAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                ImageView view = new ImageView(MainActivity.this);
//                view.setScaleType(ImageView.ScaleType.FIT_XY);
//                view.setPadding(50,50,50,50);
//                view.setBackground(getResources().getDrawable(R.mipmap.viewpager_img_bg));
//                view.setBackgroundColor(Color.parseColor("#44ff0000"));
                View view = View.inflate(MainActivity.this, R.layout.viewpager_item, null);
                ImageView img = view.findViewById(R.id.viewpager_item_img);
                final int realPosition = getRealPosition(position);
                img.setImageResource(imgRes[realPosition]);
                container.addView(view);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click position= " + realPosition, Toast.LENGTH_SHORT).show();
                    }
                });
                return view;
            }


            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public void startUpdate(ViewGroup container) {
                super.startUpdate(container);
                ViewPager viewPager = (ViewPager) container;
                int position = viewPager.getCurrentItem();
                if (position == 0) {
                    position = getFirstItemPosition();
                } else if (position == getCount() - 1) {
                    position = getLastItemPosition();
                }
                viewPager.setCurrentItem(position, false);

            }

            private int getRealCount() {
                return imgRes.length;
            }

            private int getRealPosition(int position) {
                return position % getRealCount();
            }

            private int getFirstItemPosition() {
                return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount();
            }

            private int getLastItemPosition() {
                return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount() - 1;
            }
        });
        mViewPager.setPageTransformer(true, new ScaleInTransformer());
//        mViewPager.setCurrentItem(0);//就是表示默认显示第一张如果不设置应该默认就是显示第一张


        /**
         * viewpager的第二种类型布局
         */
        mViewPager2 = (ViewPager) findViewById(R.id.id_viewpager2);
//        mViewPager2.setPageMargin(10);
        mViewPager2.setOffscreenPageLimit(3);//viewpager预加载的页数
        mViewPager2.setAdapter(mAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(MainActivity.this, R.layout.viewpager_item2, null);
                ImageView img = view.findViewById(R.id.viewpager_item_img);
                TextView tv = view.findViewById(R.id.viewpager_item_tv);
                final int realPosition = getRealPosition(position);
                img.setImageResource(imgRes[realPosition]);
                tv.setText(tvs[realPosition]);
                container.addView(view);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click position= " + realPosition, Toast.LENGTH_SHORT).show();
                    }
                });
                return view;
            }

            /**
             * 通过重写这个方法可以设置viewpager的页面宽度
             * @param position
             * @return
             */
            @Override
            public float getPageWidth(int position) {
                return (float) 0.5;
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public void startUpdate(ViewGroup container) {
                super.startUpdate(container);
                ViewPager viewPager = (ViewPager) container;
                int position = viewPager.getCurrentItem();
                if (position == 0) {
                    position = getFirstItemPosition();
                } else if (position == getCount() - 1) {
                    position = getLastItemPosition();
                }
                viewPager.setCurrentItem(position, false);

            }

            private int getRealCount() {
                return imgRes.length;
            }

            private int getRealPosition(int position) {
                return position % getRealCount();
            }

            private int getFirstItemPosition() {
                return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount();
            }

            private int getLastItemPosition() {
                return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount() - 1;
            }
        });
    }


}
