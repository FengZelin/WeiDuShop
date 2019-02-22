package soexample.umeng.com.weidushop.mvp.view;

import soexample.umeng.com.weidushop.bean.Bean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public interface LoginView {
    void getDataSuccess(Bean bean);
    void getDaraFailed(String e);

}
