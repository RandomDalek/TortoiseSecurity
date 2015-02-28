package hueller.jhonny.tortoisesecurity.model;

import java.util.Date;

/**
 * Created by jhonny on 25/02/15.
 */
public class News {
    private int id;
    private Date date;
    private String title;
    private String text;

    public News(Date date, String title, String text) {
        this.date = date;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
