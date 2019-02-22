package soexample.umeng.com.weidushop.mvp.view;

import soexample.umeng.com.weidushop.bean.HomeBean;
import soexample.umeng.com.weidushop.bean.ShowBean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public interface shopView {
    void success(ShowBean bean);
    void failed(Exception e);
    void bannersuccess(HomeBean bean);
    void bannerfailed(Exception e);
}
