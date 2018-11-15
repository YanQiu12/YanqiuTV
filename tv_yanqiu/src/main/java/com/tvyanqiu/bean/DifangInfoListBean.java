package com.tvyanqiu.bean;

import java.util.List;

public class DifangInfoListBean {

    private List<DifangtvinfoBean> difangtvinfo;

    public List<DifangtvinfoBean> getDifangtvinfoList() {
        return difangtvinfo;
    }

    public void setDifangtvinfoList(List<DifangtvinfoBean> difangtvinfo) {
        this.difangtvinfo = difangtvinfo;
    }

    public static class DifangtvinfoBean {
        /**
         * tv_is_open : true
         * tv_logo_url : icon_tv_cctv1.png
         * tv_name : CCTV
         * difang_json_url : tvinfo/cctvtv_info.json
         */

        private boolean tv_is_open;
        private String tv_logo_url;
        private String tv_name;
        private String difang_json_url;

        public boolean isTv_is_open() {
            return tv_is_open;
        }

        public void setTv_is_open(boolean tv_is_open) {
            this.tv_is_open = tv_is_open;
        }

        public String getTv_logo_url() {
            return tv_logo_url;
        }

        public void setTv_logo_url(String tv_logo_url) {
            this.tv_logo_url = tv_logo_url;
        }

        public String getTv_name() {
            return tv_name;
        }

        public void setTv_name(String tv_name) {
            this.tv_name = tv_name;
        }

        public String getDifang_json_url() {
            return difang_json_url;
        }

        public void setDifang_json_url(String difang_json_url) {
            this.difang_json_url = difang_json_url;
        }
    }
}
