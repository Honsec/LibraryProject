package genius.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 引导界面
     *
     * @param view
     */
    public void onClickGuide(View view) {
        startActivity(new Intent(getApplicationContext(), GuideActivity.class));
    }

    /**
     * tab主界面
     *
     * @param view
     */
    public void onClickTabMain(View view) {
        startActivity(new Intent(getApplicationContext(), TabMainActivity.class));
    }

    /**
     * 可滑动tab界面
     *
     * @param view
     */
    public void onClickSlideTab(View view) {
        startActivity(new Intent(getApplicationContext(), MoreTabActivity.class));
    }


    /**
     * 可滑动tab界面
     *
     * @param view
     */
    public void onClickSpringTab(View view) {
        startActivity(new Intent(getApplicationContext(), SpringActivity.class));
    }



    /**
     * 可滑动tab界面
     *
     * @param view
     */
    public void onClickSlideBar(View view) {
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
    }

}
