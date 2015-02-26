package hueller.jhonny.tortoisesecurity.model;

import java.util.Date;

/**
 * Created by jhonny on 25/02/15.
 */
public class Course {
    private int id;
    private String type;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String location;
    private short numberOfSeats;
    private short numberOfMeeting;

    public Course() {
    }
    public Course(int id, String type, Date dateOfStart, Date dateOfEnd, String location, short numberOfSeats,short numberOfMeeting) {
        this.id = id;
        this.type = type;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
        this.numberOfMeeting=numberOfMeeting;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getNumberOfMeeting() {
        return numberOfMeeting;
    }

    public void setNumberOfMeeting(short numberOfMeeting) {
        this.numberOfMeeting = numberOfMeeting;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public short getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(short numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
