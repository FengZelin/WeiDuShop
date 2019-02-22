package soexample.umeng.com.weidushop.mvp.presenter;

import java.util.Map;

import soexample.umeng.com.weidushop.bean.Bean;
import soexample.umeng.com.weidushop.mvp.model.IModeImpl;
import soexample.umeng.com.weidushop.mvp.presenter.inter.IPresenter;
import soexample.umeng.com.weidushop.utils.ICallBack;
import soexample.umeng.com.weidushop.mvp.view.LoginView;


/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class IPresenterImpl implements IPresenter {
    private IModeImpl model;
    private LoginView iv;

    public IPresenterImpl(LoginView iv) {
        model = new IModeImpl();
        this.iv = iv;
    }
    @Override
    public void startRequqst(String url, Map<String, String> parmas, Class clazz) {

        model.requestData(url, parmas, clazz, new ICallBack() {
            @Override
            public void OnFailed(String e) {
                iv.getDaraFailed(e);
            }

            @Override
            public void OnSuccess(Object o) {
                Bean bean = (Bean) o;
                if (bean != null) {
                    iv.getDataSuccess(bean);
                }
            }

        });
    }
    public void onDetach() {
        if (model != null) {
            model = null;
        }
        if (iv != null) {
            iv = null;
        }
    }
}
