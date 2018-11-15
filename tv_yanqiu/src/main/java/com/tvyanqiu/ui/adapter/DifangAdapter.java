package com.tvyanqiu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tvyanqiu.R;
import com.tvyanqiu.bean.DifangInfoListBean;
import com.tvyanqiu.ui.activity.DifangActivity;
import com.tvyanqiu.config.Constant;

import java.util.ArrayList;

public class DifangAdapter extends BaseAdapter {

    Context context;
    ArrayList<DifangInfoListBean.DifangtvinfoBean> okDifanginfoList;

    public DifangAdapter(Context context, ArrayList<DifangInfoListBean.DifangtvinfoBean> okTvinfoList) {
        this.context = context;
        this.okDifanginfoList = okTvinfoList;
    }

    @Override
    public int getCount() {
        return okDifanginfoList == null ? 0 : okDifanginfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return okDifanginfoList == null ? null : okDifanginfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return okDifanginfoList == null ? 0 : position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_tvinfo, null);
            holder = new ViewHolder();
            holder.tvTvname = convertView.findViewById(R.id.tv_tvname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTvname.setTag(R.id.imageloader_uri,okDifanginfoList.get(position).getTv_logo_url());
        Glide
                .with(context)
                .load(Constant.BASE_URL+Constant.IMAGES_URL+okDifanginfoList.get(position).getTv_logo_url())
                .placeholder(R.mipmap.icon_tv_error)
                .crossFade()
                .into(holder.tvTvname);
        holder.tvTvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DifangActivity.class);
                String difangJsonUrl = okDifanginfoList.get(position).getDifang_json_url();
                intent.putExtra("difangJsonUrl",difangJsonUrl);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView tvTvname;
    }

    public void setData(ArrayList<DifangInfoListBean.DifangtvinfoBean> okDifanginfoList) {
        if(this.okDifanginfoList!=null){
            this.okDifanginfoList.clear();
        }
        this.okDifanginfoList = okDifanginfoList;
        notifyDataSetChanged();
    }
}
