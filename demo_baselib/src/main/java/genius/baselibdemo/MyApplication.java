package genius.baselibdemo;

import genius.baselib.base.BaseApplication;
import genius.utils.UtilsLog;

/**
 * Created by Hongsec on 2016-07-21.
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        UtilsLog._IS_LOG = true;

    }
}
