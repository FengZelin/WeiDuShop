package soexample.umeng.com.weidushop.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.weidushop.R;

public class CircleFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.activity_circle_fragment,null);

        return view;
    }
    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        CircleFragment fragment = new CircleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}