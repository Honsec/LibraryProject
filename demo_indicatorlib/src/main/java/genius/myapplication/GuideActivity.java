package genius.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.china.serra.indicator.indicator.Indicator;
import com.china.serra.indicator.indicator.IndicatorViewPager;

/**
 * Created by Hongsec on 2016-07-12.
 */
public class GuideActivity extends FragmentActivity {

        private IndicatorViewPager indicatorViewPager;

    private LayoutInflater inflate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewPager viewPager =(ViewPager) findViewById(R.id.guide_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.guide_indicator);
        indicatorViewPager = new IndicatorViewPager(indicator,viewPager);
        inflate = LayoutInflater.from(getApplicationContext());

    }


    private IndicatorViewPager.IndicatorPagerAdapter pagerAdapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        private  int[] images= {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};

        @Override
        public int getCount() {
            return images.length;
        }

        //indicator view
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if(convertView == null){
                convertView = inflate.inflate(R.layout.tab_guide,container,false);
            }

            return convertView;
        }


        //content view
        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if(convertView == null){
                convertView = new View(getApplicationContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            convertView.setBackgroundResource(images[position]);
            return convertView;
        }


    };


}
