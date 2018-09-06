package co.id.telkomsigma.palapaone.model;

public class MateriModel {

    private String materi_id;
    private String materi_title;
    private String materi_author;
    private String materi_desc;
    private String materi_file;
    private String materi_speakID;

    public MateriModel(String materi_id, String materi_title, String materi_author, String materi_desc, String materi_file, String materi_speakID) {
        this.materi_id = materi_id;
        this.materi_title = materi_title;
        this.materi_author = materi_author;
        this.materi_desc = materi_desc;
        this.materi_file = materi_file;
        this.materi_speakID = materi_speakID;
    }

    public String getMateri_id() {
        return materi_id;
    }

    public void setMateri_id(String materi_id) {
        this.materi_id = materi_id;
    }

    public String getMateri_title() {
        return materi_title;
    }

    public void setMateri_title(String materi_title) {
        this.materi_title = materi_title;
    }

    public String getMateri_author() {
        return materi_author;
    }

    public void setMateri_author(String materi_author) {
        this.materi_author = materi_author;
    }

    public String getMateri_desc() {
        return materi_desc;
    }

    public void setMateri_desc(String materi_desc) {
        this.materi_desc = materi_desc;
    }

    public String getMateri_file() {
        return materi_file;
    }

    public void setMateri_file(String materi_file) {
        this.materi_file = materi_file;
    }

    public String getMateri_speakID() {
        return materi_speakID;
    }

    public void setMateri_speakID(String materi_speakID) {
        this.materi_speakID = materi_speakID;
    }
}
