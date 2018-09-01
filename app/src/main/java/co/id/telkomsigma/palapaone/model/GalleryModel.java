package co.id.telkomsigma.palapaone.model;

public class GalleryModel {
    private String gallery_id;
    private String gallery_file;
    private String gallery_testimoni;
    private String gallery_caption;
    private String gallery_status;
    private String gallery_by;
    private String gallery_date;
    private String event_id;

    public GalleryModel(String gallery_id, String gallery_file, String gallery_testimoni, String gallery_caption, String gallery_status, String gallery_by, String gallery_date, String event_id) {
        this.gallery_id = gallery_id;
        this.gallery_file = gallery_file;
        this.gallery_testimoni = gallery_testimoni;
        this.gallery_caption = gallery_caption;
        this.gallery_status = gallery_status;
        this.gallery_by = gallery_by;
        this.gallery_date = gallery_date;
        this.event_id = event_id;
    }

    public String getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(String gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getGallery_file() {
        return gallery_file;
    }

    public void setGallery_file(String gallery_file) {
        this.gallery_file = gallery_file;
    }

    public String getGallery_testimoni() {
        return gallery_testimoni;
    }

    public void setGallery_testimoni(String gallery_testimoni) {
        this.gallery_testimoni = gallery_testimoni;
    }

    public String getGallery_caption() {
        return gallery_caption;
    }

    public void setGallery_caption(String gallery_caption) {
        this.gallery_caption = gallery_caption;
    }

    public String getGallery_status() {
        return gallery_status;
    }

    public void setGallery_status(String gallery_status) {
        this.gallery_status = gallery_status;
    }

    public String getGallery_by() {
        return gallery_by;
    }

    public void setGallery_by(String gallery_by) {
        this.gallery_by = gallery_by;
    }

    public String getGallery_date() {
        return gallery_date;
    }

    public void setGallery_date(String gallery_date) {
        this.gallery_date = gallery_date;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
