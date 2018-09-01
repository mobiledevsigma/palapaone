package co.id.telkomsigma.palapaone.model;

public class SpeakerModel {

    private String speaker_id;
    private String speaker_name;
    private String speaker_photo;
    private String speaker_email;
    private String speaker_phone;
    private String speaker_quote;
    private String speaker_nationality;
    private String speaker_event;
    private String speaker_topic;
    private String speaker_job;
    private String speaker_desc;
    private String speaker_about;

    public SpeakerModel(String speaker_id, String speaker_name, String speaker_photo, String speaker_email, String speaker_phone,
                        String speaker_quote, String speaker_nationality, String speaker_event, String speaker_topic, String speaker_job,
                        String desc, String about) {
        this.speaker_id = speaker_id;
        this.speaker_name = speaker_name;
        this.speaker_photo = speaker_photo;
        this.speaker_email = speaker_email;
        this.speaker_phone = speaker_phone;
        this.speaker_quote = speaker_quote;
        this.speaker_nationality = speaker_nationality;
        this.speaker_event = speaker_event;
        this.speaker_topic = speaker_topic;
        this.speaker_job = speaker_job;
        this.speaker_desc = desc;
        this.speaker_about = about;
    }

    public String getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(String speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getSpeaker_name() {
        return speaker_name;
    }

    public void setSpeaker_name(String speaker_name) {
        this.speaker_name = speaker_name;
    }

    public String getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(String speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

    public String getSpeaker_email() {
        return speaker_email;
    }

    public void setSpeaker_email(String speaker_email) {
        this.speaker_email = speaker_email;
    }

    public String getSpeaker_phone() {
        return speaker_phone;
    }

    public void setSpeaker_phone(String speaker_phone) {
        this.speaker_phone = speaker_phone;
    }

    public String getSpeaker_quote() {
        return speaker_quote;
    }

    public void setSpeaker_quote(String speaker_quote) {
        this.speaker_quote = speaker_quote;
    }

    public String getSpeaker_nationality() {
        return speaker_nationality;
    }

    public void setSpeaker_nationality(String speaker_nationality) {
        this.speaker_nationality = speaker_nationality;
    }

    public String getSpeaker_event() {
        return speaker_event;
    }

    public void setSpeaker_event(String speaker_event) {
        this.speaker_event = speaker_event;
    }

    public String getSpeaker_topic() {
        return speaker_topic;
    }

    public void setSpeaker_topic(String speaker_topic) {
        this.speaker_topic = speaker_topic;
    }

    public String getSpeaker_job() {
        return speaker_job;
    }

    public void setSpeaker_job(String speaker_job) {
        this.speaker_job = speaker_job;
    }

    public String getSpeaker_desc() {
        return speaker_desc;
    }

    public void setSpeaker_desc(String speaker_desc) {
        this.speaker_desc = speaker_desc;
    }

    public String getSpeaker_about() {
        return speaker_about;
    }

    public void setSpeaker_about(String speaker_about) {
        this.speaker_about = speaker_about;
    }
}
