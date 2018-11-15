package com.tvyanqiu.bean;

import java.util.List;

public class TvInfoListBean {

    private List<TvinfoBean> tvinfo;

    public List<TvinfoBean> getTvinfoList() {
        return tvinfo;
    }

    public void setTvinfoList(List<TvinfoBean> tvinfo) {
        this.tvinfo = tvinfo;
    }

    public static class TvinfoBean {
        /**
         * tv_is_open : true
         * tv_name : CCTV-1
         * tv_logo_url : icon_tv_cctv1.png
         * tv_url : http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8
         */

        private boolean tv_is_open;
        private String tv_name;
        private String tv_logo_url;
        private String tv_url;

        public boolean isTv_is_open() {
            return tv_is_open;
        }

        public void setTv_is_open(boolean tv_is_open) {
            this.tv_is_open = tv_is_open;
        }

        public String getTv_name() {
            return tv_name;
        }

        public void setTv_name(String tv_name) {
            this.tv_name = tv_name;
        }

        public String getTv_logo_url() {
            return tv_logo_url;
        }

        public void setTv_logo_url(String tv_logo_url) {
            this.tv_logo_url = tv_logo_url;
        }

        public String getTv_url() {
            return tv_url;
        }

        public void setTv_url(String tv_url) {
            this.tv_url = tv_url;
        }
    }
}
