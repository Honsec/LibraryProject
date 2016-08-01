package genius.baselibdemo;

import android.os.Bundle;

import genius.baselib.base.BaseActivity;
import genius.baselib.base.BaseApplication;
import genius.baselib.fragment.BaseFragment;

/**
 * Created by Hongsec on 2016-07-21.
 */
public class MyFragment extends BaseFragment {
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
//        setContentView();
    }


    @Override
    protected BaseActivity getBaseParent() {
        return super.getBaseParent();
    }

    @Override
    protected BaseApplication getBaseApp() {
        return super.getBaseApp();
    }
}
