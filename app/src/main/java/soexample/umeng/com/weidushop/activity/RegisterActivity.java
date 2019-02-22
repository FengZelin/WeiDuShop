package soexample.umeng.com.weidushop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import soexample.umeng.com.weidushop.MainActivity;
import soexample.umeng.com.weidushop.R;
import soexample.umeng.com.weidushop.app.Apis;
import soexample.umeng.com.weidushop.bean.RegisterBean;
import soexample.umeng.com.weidushop.mvp.presenter.RegisterIPresenterImpl;
import soexample.umeng.com.weidushop.mvp.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.edit_cell)
    EditText editCell;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.image_eye)
    ImageView imageEye;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.but_login)
    Button butLogin;
    private RegisterIPresenterImpl presenter;
    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        presenter = new RegisterIPresenterImpl(this);
    }

    private void initView() {
        imageEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //显示
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //隐藏
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });

    }
    @OnClick({R.id.text_login, R.id.but_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.but_login:
                String name = editCell.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("phone",name);
                map.put("pwd",password);
                presenter.ReisterAdd(Apis.REGISTER_URL,map,RegisterBean.class);
                //遍历map中的键
                for (String key : map.keySet()) {
                    System.out.println("Key = " + key);
                }
//遍历map中的值
                for (String value : map.values()) {
                    System.out.println("Value = " + value);
                }
                System.out.print("");
                break;
        }
    }

    @Override
    public void getAddSuccess(RegisterBean bean) {
        System.out.print("==========="+bean.getStatus());
        String status = bean.getStatus();
        if (status.equals("0000")) {
            a = 1;
            Log.i("TAG",a+"");
        }
        if (a == 1) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }else{
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAddFailed(String e) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
