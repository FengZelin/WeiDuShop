package soexample.umeng.com.weidushop.mvp.view;

import soexample.umeng.com.weidushop.bean.Bean;

/**
 * date:2019/2/18
 * author:冯泽林(asus)
 * function:
 */
public interface IView {
    void onSuccess(Bean bean);
    void onFailed(String e);
}
