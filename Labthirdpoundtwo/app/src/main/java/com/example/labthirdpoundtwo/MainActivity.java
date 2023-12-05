package com.example.labthirdpoundtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends FragmentActivity {

    private static final int NUM_PAGES=2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2=findViewById(R.id.pager);
        pagerAdapter= new ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
    }

    private class ScreenSlidePageAdapter extends FragmentStateAdapter {
        public ScreenSlidePageAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    private class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE=0.85f;
        private static final float MIN_ALPHA=0.5f;
        @Override
        public void transformPage(@NonNull View page, float position) {
            int pageWidth=page.getWidth();
            int pageHeight = page.getHeight();
            if (position<-1){
                page.setAlpha(0f);
            }
            else if (position<=1){
                float scalefactor=Math.max(MIN_SCALE, 1-Math.abs(position));
                float vertMargin=pageHeight*(1-scalefactor)/2;
                float horzMargin=pageWidth*(1-scalefactor)/2;

                if (position<0)
                {
                    page.setTranslationX(horzMargin-vertMargin/2);
                } else {
                    page.setTranslationY(-horzMargin+vertMargin/2);
                }
                page.setScaleX(scalefactor);
                page.setScaleY(scalefactor);
                page.setAlpha(MIN_ALPHA+(scalefactor-MIN_SCALE)/(1-MIN_SCALE)+(1-MIN_SCALE));
            }
            else {
                page.setAlpha(0f);
            }
        }
    }
}