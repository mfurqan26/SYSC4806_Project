package com.amazin;

import jakarta.persistence.*;

import java.io.ByteArrayInputStream;

@Entity
public class Book {
    @Id
    private int id;
    private String name;
    private String description;
    private String publisher;
    // private ByteArrayInputStream cover;

    public int getId() {
        return id;
    }
}
