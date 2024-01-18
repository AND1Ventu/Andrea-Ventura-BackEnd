package org.example;

public class Customer {
    private long id;
    private int tier;
    private String name;

    public Customer(long id, int tier, String name){
        this.id = id;
        this.tier = tier;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
