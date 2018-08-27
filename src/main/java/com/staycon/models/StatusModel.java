package com.staycon.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "status_model")
public class StatusModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, max = 255, message = "{status.text.size}")
    @Column(name = "text")
    private String text;

    @Column(name = "addedDate")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private Date addedDate;

    public StatusModel() {
    }

    public StatusModel(String text) {
        this.text = text;
    }

    public StatusModel(String text, Date addedDate) {
        this.text = text;
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return "StatusModel{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }

    @PrePersist
    protected void onCreate() {
        if (addedDate == null) {
            addedDate = new Date();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusModel that = (StatusModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text) &&
                Objects.equals(addedDate, that.addedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, addedDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

}