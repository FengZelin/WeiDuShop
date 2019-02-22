package soexample.umeng.com.weidushop.adapter.homeadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import soexample.umeng.com.weidushop.R;
import soexample.umeng.com.weidushop.bean.ShowBean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class PshopAdapter extends RecyclerView.Adapter<PshopAdapter.ViewHodler> {
    private Context context;
    private List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public PshopAdapter(Context context, List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_pz,null);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, final int position) {

        String masterPic = list.get(position).getMasterPic();
        holder.image.setImageURI(masterPic);
        String name = list.get(position).getCommodityName();
        holder.text_name.setText(name);
        int price = list.get(position).getPrice();
        holder.text_price.setText(" ￥ "+price+" 元 ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(position).getCommodityId();
                if(xiangqingClickListener!=null){
                    xiangqingClickListener.onXiangqingClick(id);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView text_name,text_price;
        public ViewHodler(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            text_name=itemView.findViewById(R.id.text_shopname);
            text_price=itemView.findViewById(R.id.text_shopprice);

        }
    }
    public interface OnXiangqingClickListener {
        void onXiangqingClick(int id);
    }

    private OnXiangqingClickListener xiangqingClickListener;

    public void setXiangqingClickListener(OnXiangqingClickListener listener) {
        this.xiangqingClickListener = listener;
    }
}
