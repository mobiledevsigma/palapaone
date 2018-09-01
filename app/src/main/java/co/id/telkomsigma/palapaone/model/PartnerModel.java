package co.id.telkomsigma.palapaone.model;

public class PartnerModel {
    private String partner_id;
    private String partner_name;
    private String partner_logo;
    private String partner_desc;
    private String partner_url;
    private String event_id;
    private String partner_address;
    private String partner_phone;

    public PartnerModel(String partner_id, String partner_name, String partner_logo, String partner_desc, String partner_url, String event_id, String partner_address, String partner_phone) {
        this.partner_id = partner_id;
        this.partner_name = partner_name;
        this.partner_logo = partner_logo;
        this.partner_desc = partner_desc;
        this.partner_url = partner_url;
        this.event_id = event_id;
        this.partner_address = partner_address;
        this.partner_phone = partner_phone;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_logo() {
        return partner_logo;
    }

    public void setPartner_logo(String partner_logo) {
        this.partner_logo = partner_logo;
    }

    public String getPartner_desc() {
        return partner_desc;
    }

    public void setPartner_desc(String partner_desc) {
        this.partner_desc = partner_desc;
    }

    public String getPartner_url() {
        return partner_url;
    }

    public void setPartner_url(String partner_url) {
        this.partner_url = partner_url;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getPartner_address() {
        return partner_address;
    }

    public void setPartner_address(String partner_address) {
        this.partner_address = partner_address;
    }

    public String getPartner_phone() {
        return partner_phone;
    }

    public void setPartner_phone(String partner_phone) {
        this.partner_phone = partner_phone;
    }
}
