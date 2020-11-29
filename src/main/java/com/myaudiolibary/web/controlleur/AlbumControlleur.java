package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.model.Album;
import com.myaudiolibary.web.model.Artist;
import com.myaudiolibary.web.repository.AlbumRepository;
import com.myaudiolibary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@RestController
@RequestMapping("/albums")
public class AlbumControlleur {
    @Autowired
    private static AlbumRepository albumRepository;


    public static AlbumRepository getAlbumRepository(){return albumRepository;};

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album)//Request body map les request json, pour transformer du json en java
    {
        if(album.getTitle().isEmpty()){throw new EntityNotFoundException("Vous n'avez pas mis le titre de l'album !");}
        albumRepository.save(album);
        return album;
    }
}
