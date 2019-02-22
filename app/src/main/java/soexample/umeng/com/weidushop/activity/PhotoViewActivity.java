package soexample.umeng.com.weidushop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.weidushop.R;

/**
 * date:2019/2/17
 * author:冯泽林(asus)
 * function:
 */
public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.vp_photo)
    ViewPager vpPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String s = intent.getStringExtra("pic");
        String s1 = String.valueOf(s);
        ArrayList<String> bannerlist1=new ArrayList<>();

        vpPhoto.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return false;
            }
        });
    }

}
