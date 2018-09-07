package co.id.telkomsigma.palapaone.model;

public class InboxModel {

    private String inboxID;
    private String inboxTitle;
    private String inboxText;
    private String inboxType;
    private String inboxAttachment;
    private String inboxCategory;
    private String eventID;
    private String createDate;

    public InboxModel(String inboxID, String inboxTitle, String inboxText, String inboxType, String inboxAttachment, String inboxCategory, String eventID, String createDate) {
        this.inboxID = inboxID;
        this.inboxTitle = inboxTitle;
        this.inboxText = inboxText;
        this.inboxType = inboxType;
        this.inboxAttachment = inboxAttachment;
        this.inboxCategory = inboxCategory;
        this.eventID = eventID;
        this.createDate = createDate;
    }

    public String getInboxID() {
        return inboxID;
    }

    public void setInboxID(String inboxID) {
        this.inboxID = inboxID;
    }

    public String getInboxTitle() {
        return inboxTitle;
    }

    public void setInboxTitle(String inboxTitle) {
        this.inboxTitle = inboxTitle;
    }

    public String getInboxText() {
        return inboxText;
    }

    public void setInboxText(String inboxText) {
        this.inboxText = inboxText;
    }

    public String getInboxType() {
        return inboxType;
    }

    public void setInboxType(String inboxType) {
        this.inboxType = inboxType;
    }

    public String getInboxAttachment() {
        return inboxAttachment;
    }

    public void setInboxAttachment(String inboxAttachment) {
        this.inboxAttachment = inboxAttachment;
    }

    public String getInboxCategory() {
        return inboxCategory;
    }

    public void setInboxCategory(String inboxCategory) {
        this.inboxCategory = inboxCategory;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}