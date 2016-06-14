package digimatic.shangcommerce.model;

/**
 * Created by USER on 04/13/2016.
 */
public class Banner {

    private String url;
    private boolean isImage;
    private String url_video;

    public Banner(String url, boolean isImage, String url_video) {
        this.url = url;
        this.isImage = isImage;
        this.url_video = url_video;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }
}
