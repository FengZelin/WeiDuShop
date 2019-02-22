package soexample.umeng.com.weidushop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.weidushop.R;
import soexample.umeng.com.weidushop.fragment.CartFragment;
import soexample.umeng.com.weidushop.fragment.CircleFragment;
import soexample.umeng.com.weidushop.fragment.GoodsListFragment;
import soexample.umeng.com.weidushop.fragment.HomeFragment;
import soexample.umeng.com.weidushop.fragment.MineFragment;

public class HomeActivity extends AppCompatActivity {
    private final int ITEM_ONE = 0;
    private final int ITEM_TWO = 1;
    private final int ITEM_THRE = 2;
    private final int ITEM_FOUR = 3;
    private final int ITEM_FIVE = 4;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);

        alphaIndicator.setViewPager(mViewPager);
    }
    class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{
        List<Fragment> fragments = new ArrayList<>();
        String[] titles={"首页","圈子","购物车","订单","我的"};
        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(HomeFragment.newInstance(titles[0]));
            fragments.add(CircleFragment.newInstance(titles[1]));
            fragments.add(GoodsListFragment.newInstance(titles[2]));
            fragments.add(CartFragment.newInstance(titles[3]));
            fragments.add(MineFragment.newInstance(titles[4]));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            这里是在页面滑动的时候进行的一个动画的效果,根据自己的喜好去设计


        }

        @Override
        public void onPageSelected(int position) {
//            当滑动时我们几个F进行的交换
            if (ITEM_ONE == position) {
                alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
            } else if (ITEM_TWO == position) {
                alphaIndicator.getCurrentItemView().removeShow();
            } else if (ITEM_THRE == position) {
                alphaIndicator.removeAllBadge();
            }else if (ITEM_FOUR == position) {
                alphaIndicator.removeAllBadge();
            }else if (ITEM_FIVE == position) {
                alphaIndicator.removeAllBadge();
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //改变时发生的状态,还在等我去进行一个整体的效果

        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == Activity.RESULT_FIRST_USER) {
            if (requestCode == RESULT_CANCELED) {

            }
        }
    }
}
