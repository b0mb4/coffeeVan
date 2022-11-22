package com.example.mycoffee.entities;

public class Van {

    private int vanId;

    private long vanVolume;

    public int getVanId() {
        return vanId;
    }

    public Van setVanId(int vanId) {
        this.vanId = vanId;
        return this;
    }

    public long getVanVolume() {
        return vanVolume;
    }

    public Van setVanVolume(long vanVolume) {
        this.vanVolume = vanVolume;
        return this;
    }

    @Override
    public String toString() {
        return "\nVan{" +
                "vanId=" + vanId +
                ", vanVolume=" + vanVolume +
                '}';
    }
}
