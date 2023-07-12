package com.softwartemind;

import java.math.BigInteger;

public class Subscriber {
    private int subscriberId;
    private String name;
    private String surname;
    private BigInteger imsi;
    private int phoneNumber;
    private String provider;

    public Subscriber(int subscriberId, String name, String surname, BigInteger imsi, int phoneNumber, String operator) {
        this.subscriberId = subscriberId;
        this.name = name;
        this.surname = surname;
        this.imsi = imsi;
        this.phoneNumber= phoneNumber;
        this.provider = operator;
    }

    public Subscriber() {

    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
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

    public BigInteger getImsi() {
        return imsi;
    }

    public void setImsi(BigInteger imsi) {
        this.imsi = imsi;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberId=" + subscriberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", imsi=" + imsi +
                ", phoneNumber=" + phoneNumber +
                ", provider='" + provider + '\'' +
                '}';
    }
}
