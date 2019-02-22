package soexample.umeng.com.weidushop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.weidushop.R;
import soexample.umeng.com.weidushop.activity.XiangqingActivity;
import soexample.umeng.com.weidushop.adapter.homeadapter.MshopAdapter;
import soexample.umeng.com.weidushop.adapter.homeadapter.PshopAdapter;
import soexample.umeng.com.weidushop.adapter.homeadapter.RshopAdapter;
import soexample.umeng.com.weidushop.app.Apis;
import soexample.umeng.com.weidushop.bean.HomeBean;
import soexample.umeng.com.weidushop.bean.ShowBean;
import soexample.umeng.com.weidushop.mvp.presenter.Presenter;
import soexample.umeng.com.weidushop.mvp.view.shopView;

public class HomeFragment extends Fragment implements shopView  {
    public static final String BUNDLE_TITLE = "title";
    @BindView(R.id.imageview_fenlei)
    ImageView imageviewFenlei;
    @BindView(R.id.imageview_sousuo)
    ImageView imageviewSousuo;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.imageview_resoushangpin)
    ImageView imageviewResoushangpin;
    @BindView(R.id.recyclerview_rexiaoshangpin)
    RecyclerView recyclerviewRexiaoshangpin;
    @BindView(R.id.imageview_molishishang)
    ImageView imageviewMolishishang;
    @BindView(R.id.recyclerview_molishishang)
    RecyclerView recyclerviewMolishishang;
    @BindView(R.id.imageview_pinzhishenghuo)
    ImageView imageviewPinzhishenghuo;
    @BindView(R.id.recyclerview_pinzhishenghuo)
    RecyclerView recyclerviewPinzhishenghuo;
    Unbinder unbinder;
    private Presenter presenter;
    private List<ShowBean.ResultBean.RxxpBean.CommodityListBean> rlist=new ArrayList<>();
    private List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> mlist=new ArrayList<>();
    private List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> plist=new ArrayList<>();
    private RshopAdapter rshopAdapter;
    private MshopAdapter mshopAdapter;
    private PshopAdapter pshopAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_home_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        presenter = new Presenter();
        presenter.attch(this);
        inivView();
        initPager();
        initIntent();
        return view;
    }

    private void initPager() {

    }

    private void initIntent() {

        presenter.getbanner(Apis.BANNER_URL);
    }

    private void inivView() {
        presenter.get(Apis.SHOW_URL);
//        商品热销
        rshopAdapter = new RshopAdapter(getActivity(), rlist);
        recyclerviewRexiaoshangpin.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerviewRexiaoshangpin.setAdapter(rshopAdapter);
        rshopAdapter.setXiangqingClickListener(new RshopAdapter.OnXiangqingClickListener() {
            @Override
            public void onXiangqingClick(int id) {
                Intent it=new Intent(getActivity(),XiangqingActivity.class);
                String s = String.valueOf(id);
                it.putExtra("cid",s);
                startActivity(it);
            }
        });
//         魔力时尚
        mshopAdapter = new MshopAdapter(getActivity(), mlist);
        recyclerviewMolishishang.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerviewMolishishang.setAdapter(mshopAdapter);
        mshopAdapter.setXiangqingClickListener(new MshopAdapter.OnXiangqingClickListener() {
            @Override
            public void onXiangqingClick(int id) {
                Intent it=new Intent(getActivity(),XiangqingActivity.class);
                String s = String.valueOf(id);
                it.putExtra("cid",s);
                startActivity(it);
            }
        });
//        品质生活
        pshopAdapter = new PshopAdapter(getActivity(), plist);
        recyclerviewPinzhishenghuo.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerviewPinzhishenghuo.setAdapter(pshopAdapter);
        pshopAdapter.setXiangqingClickListener(new PshopAdapter.OnXiangqingClickListener() {
            @Override
            public void onXiangqingClick(int id) {
                Intent it=new Intent(getActivity(),XiangqingActivity.class);
                String s = String.valueOf(id);
                it.putExtra("cid",s);
                startActivity(it);
            }
        });
    }
    @Override
    public void success(ShowBean bean) {
        List<ShowBean.ResultBean.RxxpBean.CommodityListBean> commodityList = bean.getResult().getRxxp().get(0).getCommodityList();
        List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> mcommodityList = bean.getResult().getMlss().get(0).getCommodityList();
        List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> pcommodityList = bean.getResult().getPzsh().get(0).getCommodityList();
        if(commodityList!=null){
            rlist.clear();
            rlist.addAll(commodityList);
            rshopAdapter.notifyDataSetChanged();
        }
        if(mcommodityList!=null){
            mlist.clear();
            mlist.addAll(mcommodityList);
            mshopAdapter.notifyDataSetChanged();
        }
        if(pcommodityList!=null){
            plist.clear();
            plist.addAll(pcommodityList);
            pshopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void bannersuccess(HomeBean bean) {
        final List<HomeBean.ResultBean> mResult = bean.getResult();

        final List<String> Imagelist = new ArrayList();
        List<String> Titlelist = new ArrayList();
        for (int i = 0; i < mResult.size(); i++) {
            Imagelist.add(mResult.get(i).getImageUrl());
            Titlelist.add(mResult.get(i).getTitle());
        }

        banner.setData(mResult, null);
        //设置图片圆角角度
        XBanner.XBannerAdapter mXBannerAdapter = new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RoundedCorners roundedCorners = new RoundedCorners(10);

                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 150);
                Glide.with(getActivity()).load(mResult.get(position).getImageUrl()).apply(options).into((ImageView) view);
            }


        };
        banner.loadImage(mXBannerAdapter);
        banner.setPageTransformer(Transformer.Default);
        banner.setPageChangeDuration(1000);
    }

    @Override
    public void bannerfailed(Exception e) {

    }

    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


}
