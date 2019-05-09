package weektest.baway.com.yuekaolx.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import weektest.baway.com.yuekaolx.R;
import weektest.baway.com.yuekaolx.base.Mypager;

public class Afragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PopupWindow window;
    private ImageView imageView;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

               View inflate = inflater.inflate(R.layout.afragment, container, false);
        tabLayout = inflate.findViewById(R.id.tablayout);
        imageView = inflate.findViewById(R.id.imgs);
        viewPager = inflate.findViewById(R.id.vps);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add("语文");
        list.add("数学");
        list.add("英语");
        list.add("历史");
        viewPager.setAdapter(new Mypager(getFragmentManager(), list));
        tabLayout.setupWithViewPager(viewPager);
        View view = View.inflate(getContext(), R.layout.afragment, null);

        window = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }
}
