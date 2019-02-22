package soexample.umeng.com.weidushop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.weidushop.R;
import soexample.umeng.com.weidushop.activity.HomeActivity;
import soexample.umeng.com.weidushop.activity.RegisterActivity;
import soexample.umeng.com.weidushop.app.Apis;
import soexample.umeng.com.weidushop.bean.Bean;
import soexample.umeng.com.weidushop.bean.HeaderBean;
import soexample.umeng.com.weidushop.mvp.presenter.IPresenterImpl;
import soexample.umeng.com.weidushop.mvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.edit_cell)
    EditText editCell;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.image_eye)
    ImageView imageEye;
    @BindView(R.id.Check_Box)
    android.widget.CheckBox CheckBox;
    @BindView(R.id.text_Reg)
    TextView textReg;
    @BindView(R.id.but_login)
    Button butLogin;

    private IPresenterImpl presenter;
    int o = 0;
    private SharedPreferences sp;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        presenter = new IPresenterImpl(this);

        initView();
        initData();
    }

    private void initData() {
        sp = getSharedPreferences("into", MODE_PRIVATE);
        String name = sp.getString("name", "");
        String editpassword = sp.getString("password", "");
        boolean ischecked = sp.getBoolean("ischecked", false);
        if (ischecked) {
            editCell.setText(name);
            editPassword.setText(editpassword);
            CheckBox.setChecked(ischecked);
        }
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

    @OnClick({R.id.text_Reg, R.id.but_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.text_Reg:

                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.but_login:
                String name = editCell.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", name);
                map.put("pwd", password);
                presenter.startRequqst(Apis.LOGIN_URL, map, Bean.class);
                if (CheckBox.isChecked()) {
                    sp = getSharedPreferences("into", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("name", name);
                    edit.putString("password", password);
                    edit.putBoolean("ischecked",true);
                    edit.commit();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        bind.unbind();
    }

    @Override
    public void getDataSuccess(Bean bean) {
        String s = bean.getResult().getSessionId();
        int userId = bean.getResult().getUserId();
        HeaderBean bean1 = new HeaderBean(s, userId);
        EventBus.getDefault().postSticky(bean1);
        if (bean.getStatus().equals("0000")) {
            o = 2;
        }
        if (o == 2) {
            Intent it = new Intent(this, HomeActivity.class);
            startActivity(it);
        }else{
            Toast.makeText(this,"账号密码错误！登录失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getDaraFailed(String e) {

    }
}
