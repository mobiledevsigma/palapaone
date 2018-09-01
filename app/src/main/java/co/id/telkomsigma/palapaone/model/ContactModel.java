package co.id.telkomsigma.palapaone.model;

public class ContactModel {
    private String contact_id;
    private String contact_number;
    private String contact_name;
    private String event_id;
    private String contact_type;
    private String contact_image;
    private String contact_layout;
    private String contact_longitude;
    private String contact_latitude;

    public ContactModel(String contact_id, String contact_number, String contact_name, String event_id, String contact_type, String contact_image, String contact_layout, String contact_longitude, String contact_latitude) {
        this.contact_id = contact_id;
        this.contact_number = contact_number;
        this.contact_name = contact_name;
        this.event_id = event_id;
        this.contact_type = contact_type;
        this.contact_image = contact_image;
        this.contact_layout = contact_layout;
        this.contact_longitude = contact_longitude;
        this.contact_latitude = contact_latitude;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public String getContact_image() {
        return contact_image;
    }

    public void setContact_image(String contact_image) {
        this.contact_image = contact_image;
    }

    public String getContact_layout() {
        return contact_layout;
    }

    public void setContact_layout(String contact_layout) {
        this.contact_layout = contact_layout;
    }

    public String getContact_longitude() {
        return contact_longitude;
    }

    public void setContact_longitude(String contact_longitude) {
        this.contact_longitude = contact_longitude;
    }

    public String getContact_latitude() {
        return contact_latitude;
    }

    public void setContact_latitude(String contact_latitude) {
        this.contact_latitude = contact_latitude;
    }
}
