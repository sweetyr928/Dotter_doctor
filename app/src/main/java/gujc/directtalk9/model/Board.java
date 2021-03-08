package gujc.directtalk9.model;

import java.util.Date;

public class Board {
    public String name;
    public String title;
    public String id;
    public boolean match;
    private Date timestamp;
    public String doctor;
    public String hospital;
    public String doctorid;
    public String board1;
    public String board2;
    public String board3;

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getBoard1() {
        return board1;
    }

    public void setBoard1(String board1) {
        this.board1 = board1;
    }

    public String getBoard2() {
        return board2;
    }

    public void setBoard2(String board2) {
        this.board2 = board2;
    }

    public String getBoard3() {
        return board3;
    }

    public void setBoard3(String board3) {
        this.board3 = board3;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Board(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
