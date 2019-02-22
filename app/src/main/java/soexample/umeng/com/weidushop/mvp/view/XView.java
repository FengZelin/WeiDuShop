package soexample.umeng.com.weidushop.mvp.view;

import soexample.umeng.com.weidushop.bean.ShopXiangBean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public interface XView {
    void  successful(ShopXiangBean data);
    void  failed(Exception e);
}
