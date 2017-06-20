package com.cirtual.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by abhyankar on 18/6/17.
 */
@Entity
@Table(name="chat")
public class ChatMessage {

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String author;
    private Date createDate;



}
