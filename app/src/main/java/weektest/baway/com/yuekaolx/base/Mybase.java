package weektest.baway.com.yuekaolx.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.PropertyResourceBundle;

import weektest.baway.com.yuekaolx.R;
import weektest.baway.com.yuekaolx.bean.JsonBean;

public class Mybase extends BaseAdapter {
    private List<JsonBean.results> list;
    private Context context;

    public Mybase(List<JsonBean.results> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                ViewHolder holder;
                if (convertView==null){
                    convertView=View.inflate(context,R.layout.mybase,null);
                    holder=new ViewHolder();
                    holder.t_image=convertView.findViewById(R.id.image);
                    holder.t_text=convertView.findViewById(R.id.text);
                    convertView.setTag(holder);
                }else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.t_text.setText(list.get(position).getSummary());
                Glide.with(context).load(list.get(position).getImageUrl()).into(holder.t_image);
                break;
            case 1:
                ViewHolders holders;
                if (convertView==null){
                    convertView=View.inflate(context,R.layout.mybases,null);
                    holders=new ViewHolders();
                    holders.t_images=convertView.findViewById(R.id.images);
                    holders.t_texts=convertView.findViewById(R.id.texts);
                    convertView.setTag(holders);
                }else {
                    holders = (ViewHolders) convertView.getTag();
                }
                holders.t_texts.setText(list.get(position).getSummary());
                Glide.with(context).load(list.get(position).getImageUrl()).into(holders.t_images);
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    class ViewHolder{
          TextView t_text;
          ImageView t_image;
    }
    class ViewHolders{
        TextView t_texts;
        ImageView t_images;
    }
}
