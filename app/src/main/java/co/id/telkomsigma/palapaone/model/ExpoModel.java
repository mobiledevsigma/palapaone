package co.id.telkomsigma.palapaone.model;

public class ExpoModel {
    private String expo_id;
    private String expo_name;
    private String expo_desc;
    private String expo_prod;
    private String expo_map;
    private String expo_loca;

    public ExpoModel(String expo_id, String expo_name, String expo_desc, String expo_prod, String expo_map, String expo_loca) {
        this.expo_id = expo_id;
        this.expo_name = expo_name;
        this.expo_desc = expo_desc;
        this.expo_prod = expo_prod;
        this.expo_map = expo_map;
        this.expo_loca = expo_loca;
    }

    public String getExpo_id() {
        return expo_id;
    }

    public void setExpo_id(String expo_id) {
        this.expo_id = expo_id;
    }

    public String getExpo_name() {
        return expo_name;
    }

    public void setExpo_name(String expo_name) {
        this.expo_name = expo_name;
    }

    public String getExpo_desc() {
        return expo_desc;
    }

    public void setExpo_desc(String expo_desc) {
        this.expo_desc = expo_desc;
    }

    public String getExpo_prod() {
        return expo_prod;
    }

    public void setExpo_prod(String expo_prod) {
        this.expo_prod = expo_prod;
    }

    public String getExpo_map() {
        return expo_map;
    }

    public void setExpo_map(String expo_map) {
        this.expo_map = expo_map;
    }

    public String getExpo_loca() {
        return expo_loca;
    }

    public void setExpo_loca(String expo_loca) {
        this.expo_loca = expo_loca;
    }
}
