package ru.skillbox.currency.exchange.xmlEntity;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

@ToString
public class Valute {
    private String id;
    private Long numCode;
    private String charCode;
    private Long nominal;
    private String name;
    private String value;
    private String vunitRate;

    @XmlElement(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "NumCode")
    public Long getNumCode() {
        return numCode;
    }

    public void setNumCode(Long numCode) {
        this.numCode = numCode;
    }

    @XmlElement(name = "CharCode")
    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlElement(name = "VunitRate")
    public String getVunitRate() {
        return vunitRate;
    }

    public void setVunitRate(String vunitRate) {
        this.vunitRate = vunitRate;
    }
}
