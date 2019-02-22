package soexample.umeng.com.weidushop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.weidushop.R;
import soexample.umeng.com.weidushop.adapter.shop.XiangqingAdapter;
import soexample.umeng.com.weidushop.bean.ShopXiangBean;
import soexample.umeng.com.weidushop.mvp.presenter.XPresenter;
import soexample.umeng.com.weidushop.mvp.view.XView;

public class XiangqingActivity extends AppCompatActivity implements XView {

    @BindView(R.id.btn_shop)
    TextView btnShop;
    @BindView(R.id.btn_xiangqing)
    TextView btnXiangqing;
    @BindView(R.id.btn_pinlun)
    TextView btnPinlun;
    @BindView(R.id.ly_shang)
    LinearLayout lyShang;
    @BindView(R.id.product_show)
    RecyclerView productShow;
    @BindView(R.id.wb_show)
    WebView wbShow;
    XPresenter presenter;
    private XiangqingAdapter adapter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        bind = ButterKnife.bind(this);
        Intent intent = getIntent();
        String s = intent.getStringExtra("cid");
        int cid = Integer.valueOf(s);
        presenter = new XPresenter();
        presenter.attch(this);
        presenter.getXiangqing(cid);
    }

    @Override
    public void successful(ShopXiangBean data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        productShow.setLayoutManager(layoutManager);
        adapter = new soexample.umeng.com.weidushop.adapter.shop.XiangqingAdapter(this,data.getResult());


        adapter.setXiangqingClickListener(new XiangqingAdapter.OnXiangqingClickListener() {
            @Override
            public void onXiangqingClick(String pic) {
                Intent intent = new Intent(XiangqingActivity.this, PhotoViewActivity.class);
                String s = String.valueOf(pic);
                intent.putExtra("pic", s);
                startActivity(intent);
            }
        });
        productShow.setAdapter(adapter);
        WebSettings settings = wbShow.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS
        String js = "<script type=\"text/javascript\">" +
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}" +
                "</script>";
        wbShow.loadData(data.getResult().getDetails() + js, "text/html; charset=UTF-8", null);

    }

    @Override
    public void failed(Exception e) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.delete();
        }
        bind.unbind();
    }
}
