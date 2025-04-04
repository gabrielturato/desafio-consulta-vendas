package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleReportDTO {

    Long id;
    LocalDate date;
    Double amount;
    String name;

    public SaleReportDTO(Long id, LocalDate date, String name, Double amount) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.amount = amount;
    }

    public SaleReportDTO() {
    }

    public SaleReportDTO(Sale sale) {
        this.id = sale.getId();
        this.date = sale.getDate();
        this.name = sale.getSeller().getName();
        this.amount = sale.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
