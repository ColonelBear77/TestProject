package com.testProject.onlineLibrary.domain;

import javax.persistence.*;

@Entity
public class Genre {
    @Id
    @SequenceGenerator(name = "genre_id_generator", sequenceName = "genre_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_generator")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
