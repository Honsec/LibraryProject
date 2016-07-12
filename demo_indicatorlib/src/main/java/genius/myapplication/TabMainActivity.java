package genius.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.china.serra.indicator.indicator.Indicator;
import com.china.serra.indicator.indicator.IndicatorViewPager;
import com.china.serra.indicator.viewpager.SViewPager;

/**
 * Created by Hongsec on 2016-07-12.
 */
public class TabMainActivity extends FragmentActivity{

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabmain);
        SViewPager sViewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);

        indicatorViewPager = new IndicatorViewPager(indicator,sViewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

    }


    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{

        private String[] tabNames = {"Home","Message","Recoverd","Me"};

        private int[] tabIcons = {R.drawable.maintab_1_selector,R.drawable.maintab_2_selector,R.drawable.maintab_3_selector,R.drawable.maintab_4_selector};

        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater  = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabIcons.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if(convertView == null){
                convertView = (TextView) inflater.inflate(R.layout.tab_main,container,false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0,tabIcons[position],0,0);

            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            FirstLayerFragment firstLayerFragment = new FirstLayerFragment();
            Bundle bundle = new Bundle();
            bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME,tabNames[position]);
            bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX,position);
            firstLayerFragment.setArguments(bundle);
            return firstLayerFragment;
        }
    }

}
