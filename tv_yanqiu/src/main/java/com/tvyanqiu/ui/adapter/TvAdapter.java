package com.tvyanqiu.ui.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tvyanqiu.R;
import com.tvyanqiu.bean.TvInfoListBean;
import com.tvyanqiu.config.Constant;
import com.tvyanqiu.ui.dialog.TVDialogFragment;

import java.util.ArrayList;

public class TvAdapter extends BaseAdapter {

    Context context;
    FragmentManager fragmentManager;
    ArrayList<TvInfoListBean.TvinfoBean> okTvinfoList;

    public TvAdapter(Context context, FragmentManager fragmentManager, ArrayList<TvInfoListBean.TvinfoBean> okTvinfoList) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.okTvinfoList = okTvinfoList;
    }

    @Override
    public int getCount() {
        return okTvinfoList == null ? 0 : okTvinfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return okTvinfoList == null ? null : okTvinfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return okTvinfoList == null ? 0 : position;
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
        holder.tvTvname.setTag(R.id.imageloader_uri,okTvinfoList.get(position).getTv_logo_url());
        Glide
                .with(context)
                .load(Constant.BASE_URL+Constant.IMAGES_URL+okTvinfoList.get(position).getTv_logo_url())
                .placeholder(R.mipmap.icon_tv_error)
                .crossFade()
                .into(holder.tvTvname);
        holder.tvTvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TVDialogFragment webDialogFragment = new TVDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TVUrl", okTvinfoList.get(position).getTv_url());
                webDialogFragment.setArguments(bundle);
                webDialogFragment.show(fragmentManager, "webDialogFragment");
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView tvTvname;
    }

    public void setData(ArrayList<TvInfoListBean.TvinfoBean> okTvinfoList) {
        if(this.okTvinfoList!=null){
            this.okTvinfoList.clear();
        }
        this.okTvinfoList = okTvinfoList;
        notifyDataSetChanged();
    }
}
