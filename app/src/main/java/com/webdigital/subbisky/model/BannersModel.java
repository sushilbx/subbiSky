package com.webdigital.subbisky.model;
import java.util.List;
public class BannersModel {
    private String message;
    private Boolean success;
    private Integer statusCode;
    private List<BannersLists> banners;
    private List<ServicesLists> services;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<BannersLists> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersLists> banners) {
        this.banners = banners;
    }

    public List<ServicesLists> getServices() {
        return services;
    }

    public void setServices(List<ServicesLists> services) {
        this.services = services;
    }

    public class BannersLists {
        private Integer id;
        private String image,status,created_at,updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

    public class ServicesLists {
        private Integer id,commission,shopCount;
        private String parent_id,name,slug,image,status,type,created_at,updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCommission() {
            return commission;
        }

        public void setCommission(Integer commission) {
            this.commission = commission;
        }

        public Integer getShopCount() {
            return shopCount;
        }

        public void setShopCount(Integer shopCount) {
            this.shopCount = shopCount;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
