package soexample.umeng.com.weidushop.mvp.presenter.inter;

import java.util.Map;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public interface IPresenter {
    void startRequqst(String url, Map<String, String> parmas, Class clazz);
}
