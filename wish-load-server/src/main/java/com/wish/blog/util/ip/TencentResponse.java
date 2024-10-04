package com.wish.blog.util.ip;

public class TencentResponse {
    private String message;
    private String request_id;
    private Result result;
    private int status;

    public static class Result {
        private AdInfo ad_info;
        private String ip;
        private Location location;

        public static class AdInfo {
            private int adcode;
            private String city;
            private String district;
            private String nation;
            private int nationCode;
            private String province;

            public int getAdcode() {
                return adcode;
            }

            public void setAdcode(int adcode) {
                this.adcode = adcode;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public int getNationCode() {
                return nationCode;
            }

            public void setNationCode(int nationCode) {
                this.nationCode = nationCode;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            // Getters and setters
        }

        public static class Location {
            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            // Getters and setters
        }

        // Getters and setters


        public AdInfo getAd_info() {
            return ad_info;
        }

        public void setAd_info(AdInfo ad_info) {
            this.ad_info = ad_info;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Getters and setters
}