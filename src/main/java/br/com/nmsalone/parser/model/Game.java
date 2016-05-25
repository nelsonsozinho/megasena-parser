package br.com.nmsalone.parser.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Game model
 */
public class Game implements Serializable {

    private Long id;
    private Date dateDraw;
    private Integer winnersCount;
    private String city;
    private Double estimatedPrize;
    private Integer n1;
    private Integer n2;
    private Integer n3;
    private Integer n4;
    private Integer n5;
    private Integer n6;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDraw() {
        return dateDraw;
    }

    public void setDateDraw(Date dateDraw) {
        this.dateDraw = dateDraw;
    }

    public Integer getWinnersCount() {
        return winnersCount;
    }

    public void setWinnersCount(Integer winnersCount) {
        this.winnersCount = winnersCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getEstimatedPrize() {
        return estimatedPrize;
    }

    public void setEstimatedPrize(Double estimatedPrize) {
        this.estimatedPrize = estimatedPrize;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
    }

    public Integer getN3() {
        return n3;
    }

    public void setN3(Integer n3) {
        this.n3 = n3;
    }

    public Integer getN4() {
        return n4;
    }

    public void setN4(Integer n4) {
        this.n4 = n4;
    }

    public Integer getN5() {
        return n5;
    }

    public void setN5(Integer n5) {
        this.n5 = n5;
    }

    public Integer getN6() {
        return n6;
    }

    public void setN6(Integer n6) {
        this.n6 = n6;
    }
}
