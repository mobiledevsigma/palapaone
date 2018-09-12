package co.id.telkomsigma.palapaone.model;

public class RundownModel {

    private String rundown_id;
    private String rundown_name;
    private String rundown_start;
    private String rundown_end;
    private String rundown_place;
    private String rundown_layout;
    private String rundown_desc;

    public RundownModel(String rundown_id, String rundown_name, String rundown_start, String rundown_end,
                        String rundown_place, String rundown_layout, String rundown_desc) {
        this.rundown_id = rundown_id;
        this.rundown_name = rundown_name;
        this.rundown_start = rundown_start;
        this.rundown_end = rundown_end;
        this.rundown_place = rundown_place;
        this.rundown_layout = rundown_layout;
        this.rundown_desc = rundown_desc;
    }

    public String getRundown_id() {
        return rundown_id;
    }

    public void setRundown_id(String rundown_id) {
        this.rundown_id = rundown_id;
    }

    public String getRundown_name() {
        return rundown_name;
    }

    public void setRundown_name(String rundown_name) {
        this.rundown_name = rundown_name;
    }

    public String getRundown_start() {
        return rundown_start;
    }

    public void setRundown_start(String rundown_start) {
        this.rundown_start = rundown_start;
    }

    public String getRundown_end() {
        return rundown_end;
    }

    public void setRundown_end(String rundown_end) {
        this.rundown_end = rundown_end;
    }

    public String getRundown_place() {
        return rundown_place;
    }

    public void setRundown_place(String rundown_place) {
        this.rundown_place = rundown_place;
    }

    public String getRundown_layout() {
        return rundown_layout;
    }

    public void setRundown_layout(String rundown_layout) {
        this.rundown_layout = rundown_layout;
    }

    public String getRundown_desc() {
        return rundown_desc;
    }

    public void setRundown_desc(String rundown_desc) {
        this.rundown_desc = rundown_desc;
    }
}
