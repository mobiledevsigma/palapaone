package co.id.telkomsigma.palapaone.model;

public class AgendaModel {

    private String agenda_id;
    private String agenda_name;
    private String agenda_date;
    private String event_id;
    private String day_x;

    public AgendaModel(String agenda_id, String agenda_name, String agenda_date, String event_id, String day_x) {
        this.agenda_id = agenda_id;
        this.agenda_name = agenda_name;
        this.agenda_date = agenda_date;
        this.event_id = event_id;
        this.day_x = day_x;
    }

    public String getAgenda_id() {
        return agenda_id;
    }

    public void setAgenda_id(String agenda_id) {
        this.agenda_id = agenda_id;
    }

    public String getAgenda_name() {
        return agenda_name;
    }

    public void setAgenda_name(String agenda_name) {
        this.agenda_name = agenda_name;
    }

    public String getAgenda_date() {
        return agenda_date;
    }

    public void setAgenda_date(String agenda_date) {
        this.agenda_date = agenda_date;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getDay_x() {
        return day_x;
    }

    public void setDay_x(String day_x) {
        this.day_x = day_x;
    }
}
