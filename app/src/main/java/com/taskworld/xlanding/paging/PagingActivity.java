package com.taskworld.xlanding.paging;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.taskworld.xlanding.R;


public class PagingActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    private static final int NUM_PAGES = 5;


    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ParallaxTransformer());
        mPager.setOnPageChangeListener(new XPageChangedListener());
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private Fragment[] mFragmentList;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentList = new Fragment[NUM_PAGES];
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragment fm = ScreenSlidePageFragment.create(position);

            int colorId = getResources().getColor(R.color.main_theme_color);
            Drawable icon = getResources().getDrawable(R.drawable.d1);
            if (position == 1) {
                colorId = getResources().getColor(R.color.indigo);
                icon = getResources().getDrawable(R.drawable.d2);
            } else if (position == 2) {
                colorId = getResources().getColor(R.color.carnationPink);
                icon = getResources().getDrawable(R.drawable.d3);
            } else if (position == 3) {
                colorId = getResources().getColor(R.color.straw);
                icon = getResources().getDrawable(R.drawable.d4);
            } else if (position == 4) {
                colorId = getResources().getColor(R.color.macaroniAndCheese);
                icon = getResources().getDrawable(R.drawable.d5);
            }

            fm.setBackgroundColor(colorId);
            fm.setmIconDrawable(icon);

            mFragmentList[position] = fm;
            return fm;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public Fragment retrieveFragementAtIndex(int index) {
            return mFragmentList[index];
        }
    }

    private class XPageChangedListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.v("DUMB", "onPageScrolled # currentPage: " + mPager.getCurrentItem() + ", position: " + position + ", positionOffset: " + positionOffset);//+", positionOffsetPixels: "+positionOffsetPixels);
            ScreenSlidePageFragment fragment = (ScreenSlidePageFragment) mPagerAdapter.retrieveFragementAtIndex(position);
            fragment.setSwipeOffset(positionOffset);

            int currentPage = mPager.getCurrentItem();
            if (currentPage == 0 && position == 0 && positionOffset == 0) {     //swipe left on the first page
                Log.v(TAG, "swipe left on the first page");
            } else if (currentPage == NUM_PAGES - 1 && position == NUM_PAGES - 1 && positionOffset == 0) {  //swipe right on the last page
                Log.v(TAG, "swipe right on the last page");
            } else if (currentPage > position) {        //swipe left
                Log.v(TAG, "swipe left");
            } else if (currentPage == position) {       //swipe right
                Log.v(TAG, "swipe right");
            }


        }

        @Override
        public void onPageSelected(int position) {
            ScreenSlidePageFragment fragment = (ScreenSlidePageFragment) mPagerAdapter.retrieveFragementAtIndex(position);
//            fragment.testRunAnimation();
        }
    }

    public class ParallaxTransformer implements ViewPager.PageTransformer {


        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();


            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(1);

            } else if (position <= 1) { // [-1,1]
                RelativeLayout revContent = (RelativeLayout) view.findViewById(R.id.linContent);
                revContent.setTranslationX(-position * (pageWidth / 2));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(1);
            }
        }
    }


}
