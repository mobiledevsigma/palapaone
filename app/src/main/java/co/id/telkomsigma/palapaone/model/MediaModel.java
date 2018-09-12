package co.id.telkomsigma.palapaone.model;

public class MediaModel {
    private String media_id;
    private String media_title;
    private String media_file;
    private String media_date;
    private String media_author;
    private String media_image;

    public MediaModel(String media_id, String media_title, String media_file, String media_date, String media_author, String media_image) {
        this.media_id = media_id;
        this.media_title = media_title;
        this.media_file = media_file;
        this.media_date = media_date;
        this.media_author = media_author;
        this.media_image = media_image;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getMedia_title() {
        return media_title;
    }

    public void setMedia_title(String media_title) {
        this.media_title = media_title;
    }

    public String getMedia_file() {
        return media_file;
    }

    public void setMedia_file(String media_file) {
        this.media_file = media_file;
    }

    public String getMedia_date() {
        return media_date;
    }

    public void setMedia_date(String media_date) {
        this.media_date = media_date;
    }

    public String getMedia_author() {
        return media_author;
    }

    public void setMedia_author(String media_author) {
        this.media_author = media_author;
    }

    public String getMedia_image() {
        return media_image;
    }

    public void setMedia_image(String media_image) {
        this.media_image = media_image;
    }
}
