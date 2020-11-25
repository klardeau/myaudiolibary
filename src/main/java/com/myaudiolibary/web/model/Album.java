package com.myaudiolibary.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AlbumId")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ArtistId")
    private Artist artist;

    @Column(name="Title")
    private String title;

    public Album(Long id, String title, Artist artist){
        this.id=id;
        this.title=title;
        this.artist=artist;

    }

    public Album(Long id, String title){
        this.id=id;
        this.title=title;

    }

    public Album(){}

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
