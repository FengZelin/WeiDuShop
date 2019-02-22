package soexample.umeng.com.weidushop.mvp.view;

import soexample.umeng.com.weidushop.bean.HomeBean;
import soexample.umeng.com.weidushop.bean.ShowBean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public interface HomeView {
    void onBannerSuccess(HomeBean bean);
    void onBannerFailed(String e);
    void onShopSuccess(ShowBean bean);
    void onShopFailed(String e);
}
