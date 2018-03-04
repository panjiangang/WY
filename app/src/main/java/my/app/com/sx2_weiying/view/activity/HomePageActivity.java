package my.app.com.sx2_weiying.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.app.com.sx2_weiying.R;

public class HomePageActivity extends Activity {

    @BindView(R.id.main_img)
    ImageView img;
    //倒计时
    private int flag = 3;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        //进行缩放
        ScaleAnimation sa = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        sa.setDuration(2000);
        sa.setRepeatCount(2);
        sa.setRepeatMode(Animation.REVERSE);
        img.startAnimation(sa);

        sharedPreferences = getSharedPreferences("homepage_flag", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int name = sharedPreferences.getInt("name", 0);

        if (name % 2 == 0) {
            img.setBackgroundResource(R.drawable.homepage_img_one);
        } else if (name % 2 == 1) {
            img.setBackgroundResource(R.drawable.homepage_img_two);
        }

        editor.putInt("name", sharedPreferences.getInt("name", 0) + 1);

        editor.commit();

        //定时器
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                flag--;
                if (flag == 0) {
                    //进行跳转
                    Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.schedule(task, 1, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}


