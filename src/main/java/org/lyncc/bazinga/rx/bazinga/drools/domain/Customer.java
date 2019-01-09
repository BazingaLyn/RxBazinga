package org.lyncc.bazinga.rx.bazinga.drools.domain;

import com.alibaba.fastjson.JSON;

public class Customer {

    private String name;

    private String surname;

    private String contry;

    public Customer(String name, String surname, String contry) {
        this.name = name;
        this.surname = surname;
        this.contry = contry;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
