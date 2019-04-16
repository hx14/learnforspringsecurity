package com.example.securitytest.spittr.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hx on 2019-04-15.
 */
public class Spittle implements Serializable {
    private final Long id;
    private final String message;
    private final Date time;
    private Double laititude;
    private Double longitude;
    private String text;
    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double longitude, Double laititude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.longitude = longitude;
        this.laititude = laititude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLaititude() {
        return laititude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id", "time");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }
    //
    public Spitter getSpitter() {
        return getSpitter();
    }
}
