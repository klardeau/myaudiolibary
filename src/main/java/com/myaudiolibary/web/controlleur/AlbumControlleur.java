package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.model.Album;
import com.myaudiolibary.web.model.Artist;
import com.myaudiolibary.web.repository.AlbumRepository;
import com.myaudiolibary.web.repository.ArtistRepository;
import com.myaudiolibary.web.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


//@RestController
@Controller
@RequestMapping("/albums")
public class AlbumControlleur {
    @Autowired
    private AlbumService albumService;



    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album)//Request body map les request json, pour transformer du json en java
    {
        albumService.creeAlbum(album);
        return album;
    }


    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")//pas produces car on renvoit rien
    @ResponseStatus(HttpStatus.NO_CONTENT)//204 sa ses bien passé je te renvoie rien mais c'est normal
    public void suppAlbum(@PathVariable Long id)
    {
        //delete les albums de l'artistes en prems

        albumService.deleteAlbum(id);
    }
}
