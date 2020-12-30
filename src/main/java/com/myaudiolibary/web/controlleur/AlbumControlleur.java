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
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


//@RestController
@Controller
@RequestMapping("/albums")
public class AlbumControlleur {
    @Autowired
    private AlbumService albumService;



   // @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
   // @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createAlbum")
    public RedirectView createAlbum(@ModelAttribute("album") Album album)//Request body map les request json, pour transformer du json en java
    {
        albumService.creeAlbum(album);
        //return "../artists/"+album.getArtist().getId();
        return new RedirectView("/artists/"+album.getArtist().getId());
    }


    @RequestMapping(method = RequestMethod.GET, value="/{id}/{artid}/delete")//pas produces car on renvoit rien
    public RedirectView suppAlbum(@PathVariable("id") Long id, @PathVariable("artid") int artid)
    {
        //delete les albums de l'artistes en prems

        albumService.deleteAlbum(id);
        return new RedirectView("/artists/"+artid);
    }
}
