package soexample.umeng.com.weidushop.mvp.presenter;

import java.util.Map;

import soexample.umeng.com.weidushop.bean.RegisterBean;
import soexample.umeng.com.weidushop.mvp.model.IModeImpl;
import soexample.umeng.com.weidushop.mvp.presenter.inter.RegisterIPresenter;
import soexample.umeng.com.weidushop.utils.ICallBack;
import soexample.umeng.com.weidushop.mvp.view.RegisterView;


/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class RegisterIPresenterImpl implements RegisterIPresenter {
    private IModeImpl model;
    private RegisterView iv;

    public RegisterIPresenterImpl(RegisterView iv) {
        model = new IModeImpl();
        this.iv = iv;
    }
    public void ReisterAdd(String url,Map<String,String> parmas,Class clazz){
        model.requestData(url, parmas, clazz, new ICallBack() {
            @Override
            public void OnFailed(String e) {
                iv.getAddFailed(e);
            }

            @Override
            public void OnSuccess(Object o) {

                RegisterBean bean= (RegisterBean) o;
                if(bean!=null){
                    iv.getAddSuccess(bean);
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
