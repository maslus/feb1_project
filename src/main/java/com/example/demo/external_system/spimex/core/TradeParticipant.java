package com.example.demo.external_system.spimex.core;

import java.time.LocalDateTime;

public class TradeParticipant {
    private Long ID;
    private String Name;
    private String INN;
    private Residence Residence;
    private LocalDateTime StoreDate;
    private LocalDateTime BlockDate;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public Residence getResidence() {
        return Residence;
    }

    public void setResidence(Residence residence) {
        Residence = residence;
    }

    public LocalDateTime getStoreDate() {
        return StoreDate;
    }

    public void setStoreDate(LocalDateTime storeDate) {
        StoreDate = storeDate;
    }

    public LocalDateTime getBlockDate() {
        return BlockDate;
    }

    public void setBlockDate(LocalDateTime blockDate) {
        BlockDate = blockDate;
    }

}
