package com.example;

import java.util.List;

/**
 * Created by DongJunJie on 2016-11-8.
 */

public class Json {


    /**
     * app_id : 1569
     * app_open_type : 0
     * desc :
     * downloadurl : /iwonka/android/appmall/151222092853bzNn.apk
     * is_adver : 0
     * is_show_name : 1
     * name : 首发影院
     * package_name : com.golive.cinema
     * pic : /image/yiui/160804155802zNvl.jpg
     * position : 41
     * private_info :
     * type : 1
     * video_section : {"action":"","class_name":"com.golive.cinema.MainActivity",
     * "params":[{"key":"startTypeKey","type":"0","value":"2"},{"key":"startContentKey",
     * "type":"0","value":"37720"}],"server_id":"GOLIVE","server_name":"全球播","start_type":"0",
     * "startpara_type":"2","uri":""}
     * weburl : https:www.taobao.com
     * apk_start_section : {"package_name":"com.xx.xxxx","class_name":"xxxxxxxxxx",
     * "action":"xxxxxxxxxx","uri":"xxxxxxxxxx","flag":1,"params":[{"type":"0","key":"startflag",
     * "value":"1"},{"type":"0","key":"startflag2","value":"1"}]}
     */

    private List<AdversBean> advers;

    public List<AdversBean> getAdvers() {
        return advers;
    }

    public void setAdvers(List<AdversBean> advers) {
        this.advers = advers;
    }

    public static class AdversBean {
        private String app_id;
        private String app_open_type;
        private String desc;
        private String downloadurl;
        private String is_adver;
        private String is_show_name;
        private String name;
        private String package_name;
        private String pic;
        private String position;
        private String private_info;
        private String type;
        /**
         * action :
         * class_name : com.golive.cinema.MainActivity
         * params : [{"key":"startTypeKey","type":"0","value":"2"},{"key":"startContentKey",
         * "type":"0","value":"37720"}]
         * server_id : GOLIVE
         * server_name : 全球播
         * start_type : 0
         * startpara_type : 2
         * uri :
         */

        private VideoSectionBean video_section;
        private String weburl;
        /**
         * package_name : com.xx.xxxx
         * class_name : xxxxxxxxxx
         * action : xxxxxxxxxx
         * uri : xxxxxxxxxx
         * flag : 1
         * params : [{"type":"0","key":"startflag","value":"1"},{"type":"0","key":"startflag2",
         * "value":"1"}]
         */

        private ApkStartSectionBean apk_start_section;

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getApp_open_type() {
            return app_open_type;
        }

        public void setApp_open_type(String app_open_type) {
            this.app_open_type = app_open_type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDownloadurl() {
            return downloadurl;
        }

        public void setDownloadurl(String downloadurl) {
            this.downloadurl = downloadurl;
        }

        public String getIs_adver() {
            return is_adver;
        }

        public void setIs_adver(String is_adver) {
            this.is_adver = is_adver;
        }

        public String getIs_show_name() {
            return is_show_name;
        }

        public void setIs_show_name(String is_show_name) {
            this.is_show_name = is_show_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPrivate_info() {
            return private_info;
        }

        public void setPrivate_info(String private_info) {
            this.private_info = private_info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public VideoSectionBean getVideo_section() {
            return video_section;
        }

        public void setVideo_section(VideoSectionBean video_section) {
            this.video_section = video_section;
        }

        public String getWeburl() {
            return weburl;
        }

        public void setWeburl(String weburl) {
            this.weburl = weburl;
        }

        public ApkStartSectionBean getApk_start_section() {
            return apk_start_section;
        }

        public void setApk_start_section(ApkStartSectionBean apk_start_section) {
            this.apk_start_section = apk_start_section;
        }

        public static class VideoSectionBean {
            private String action;
            private String class_name;
            private String server_id;
            private String server_name;
            private String start_type;
            private String startpara_type;
            private String uri;
            /**
             * key : startTypeKey
             * type : 0
             * value : 2
             */

            private List<ParamsBean> params;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getServer_id() {
                return server_id;
            }

            public void setServer_id(String server_id) {
                this.server_id = server_id;
            }

            public String getServer_name() {
                return server_name;
            }

            public void setServer_name(String server_name) {
                this.server_name = server_name;
            }

            public String getStart_type() {
                return start_type;
            }

            public void setStart_type(String start_type) {
                this.start_type = start_type;
            }

            public String getStartpara_type() {
                return startpara_type;
            }

            public void setStartpara_type(String startpara_type) {
                this.startpara_type = startpara_type;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public List<ParamsBean> getParams() {
                return params;
            }

            public void setParams(List<ParamsBean> params) {
                this.params = params;
            }

            public static class ParamsBean {
                private String key;
                private String type;
                private String value;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class ApkStartSectionBean {
            private String package_name;
            private String class_name;
            private String action;
            private String uri;
            private int flag;
            /**
             * type : 0
             * key : startflag
             * value : 1
             */

            private List<ParamsBean> params;

            public String getPackage_name() {
                return package_name;
            }

            public void setPackage_name(String package_name) {
                this.package_name = package_name;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public List<ParamsBean> getParams() {
                return params;
            }

            public void setParams(List<ParamsBean> params) {
                this.params = params;
            }

            public static class ParamsBean {
                private String type;
                private String key;
                private String value;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
