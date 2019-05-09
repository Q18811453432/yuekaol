package weektest.baway.com.yuekaolx.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import weektest.baway.com.yuekaolx.R;
import weektest.baway.com.yuekaolx.base.Mybase;
import weektest.baway.com.yuekaolx.bean.JsonBean;
import weektest.baway.com.yuekaolx.https.Https;
import weektest.baway.com.yuekaolx.wbview;

public class Tabfragment extends Fragment {

    private Banner banner;
    private PullToRefreshListView listView;
    private PullToRefreshScrollView scrollView;
    private  Mybase adapter;
    private List<JsonBean.results> ls=new ArrayList<>();
    private String path="http://172.17.8.100/movieApi/movie/v1/findHotMovieList?page=1&count=3";
    private int pager=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.tabfragment, container, false);
        banner=inflate.findViewById(R.id.bn);
        listView=inflate.findViewById(R.id.listview);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        List<String> list=new ArrayList<>();
        list.add("http://img1.imgtn.bdimg.com/it/u=2917799186,1224386513&fm=26&gp=0.jpg");
        list.add("http://pic170.nipic.com/file/20180630/24206353_151645934000_2.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3140522669,3854181466&fm=214&gp=0.jpg");
        list.add("http://pic172.nipic.com/file/20180713/24206353_153551986000_2.jpg");
        list.add("http://http://gss0.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/lvpics/h=800/sign=6932d6f59f504fc2bd5fbd05d5dce7f0/314e251f95cad1c8e19aa6da7b3e6709c93d512d.jpg");
        list.add("http://img1.qunarzz.com/travel/d6/1601/44/9459d7829a35e8f7.jpg_r_650x487x95_a39a086b.jpg");
        list.add("http://pic41.nipic.com/20140519/18505720_091514561148_2.jpg");
        banner.setImages(list);
    banner.setImageLoader(new ImageLoader() {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }).start();
         data();
         init();
        return inflate;
    }
    public void init(){
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout starts = listView.getLoadingLayoutProxy(true, false);
        starts.setPullLabel("下拉刷新");
        starts.setReleaseLabel("松开刷新");
        starts.setRefreshingLabel("正在刷新");
        ILoadingLayout ends = listView.getLoadingLayoutProxy(false, true);
        ends.setPullLabel("上拉刷新");
        ends.setReleaseLabel("松开加载");
        ends.setRefreshingLabel("正在加载");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),wbview.class);
                startActivity(intent);

            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                pager=1;
                data();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                   pager++;
                   data();
            }
        });
       adapter=new Mybase(ls,getContext());
        listView.setAdapter(adapter);
    }
public void data(){
    Https instance = Https.getInstance();
    instance.getAsynTask(path);
    instance.getcallback(new CallBack() {
        @Override
        public void getjson(String json) {
            Gson gson=new Gson();
            JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
            List<JsonBean.results> result = jsonBean.getResult();
               if (pager==1){
                   ls.clear();
               }
               ls.addAll(result);
               adapter.notifyDataSetChanged();
               listView.onRefreshComplete();
        }
    });
}


}
