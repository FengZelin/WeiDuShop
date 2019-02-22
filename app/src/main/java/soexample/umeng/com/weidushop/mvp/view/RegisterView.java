package soexample.umeng.com.weidushop.mvp.view;

import soexample.umeng.com.weidushop.bean.RegisterBean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public interface RegisterView {
    void getAddSuccess(RegisterBean bean);
    void getAddFailed(String e);

}
