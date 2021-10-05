package com.services.practical.task.demo.model;

import com.services.practical.task.demo.model.enums.SelectionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_request")
public class ServiceRequest {

    private long id;
    private String name;
    private String heading;
    private String body;
    private SelectionType selection;
    private int priority;

    public ServiceRequest()
    {

    }

    public ServiceRequest(String name, String heading, String body, SelectionType selection, int priority) {
        this.name = name;
        this.heading = heading;
        this.body = body;
        this.selection = selection;
        this.priority = priority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "heading")
    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "selection")
    public SelectionType getSelection() {
        return selection;
    }

    public void setSelection(SelectionType selection) {
        this.selection = selection;
    }

    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
