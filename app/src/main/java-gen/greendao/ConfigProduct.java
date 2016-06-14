package greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table CONFIG_PRODUCT.
 */
public class ConfigProduct {

    private Long product_id;
    private String base_image;
    private String images;
    private Integer position;

    public ConfigProduct() {
    }

    public ConfigProduct(Long product_id) {
        this.product_id = product_id;
    }

    public ConfigProduct(Long product_id, String base_image, String images, Integer position) {
        this.product_id = product_id;
        this.base_image = base_image;
        this.images = images;
        this.position = position;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getBase_image() {
        return base_image;
    }

    public void setBase_image(String base_image) {
        this.base_image = base_image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
