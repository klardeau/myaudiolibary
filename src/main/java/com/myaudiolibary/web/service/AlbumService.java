package com.myaudiolibary.web.service;

import com.myaudiolibary.web.model.Album;
import com.myaudiolibary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album creeAlbum(@RequestBody Album album)//Request body map les request json, pour transformer du json en java
    {
        albumRepository.save(album);
        return album;
    }

    public void deleteAlbum(@PathVariable Long id)
    {
        //delete les albums de l'artistes en prems

        albumRepository.deleteById(id);
    }


}
