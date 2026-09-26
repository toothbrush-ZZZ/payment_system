package com.example.payment_system;

public class Account {
    
    private Long id;
    private String name;
    private String type;
    
    public Account() {}
    
    public Account(Long id, String name, String type) {
        
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
