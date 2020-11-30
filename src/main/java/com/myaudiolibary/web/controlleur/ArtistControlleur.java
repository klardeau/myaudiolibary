package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.model.Artist;
import com.myaudiolibary.web.repository.ArtistRepository;
import com.myaudiolibary.web.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistControlleur {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Artist avoirArtist(@PathVariable(value="id")Long id){
        Artist art = artistService.getArtist(id);
        return art;
    }


    @RequestMapping(params = {"name"},method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> avoirArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        Page<Artist> lstArt = artistService.getArtistByName(name, page, size, sortProperty, sortDirection);
        return lstArt;
    }
    //public Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){


    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getTousArtist(@RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        Page<Artist> lstArt = artistService.getAllArtist(page, size, sortProperty, sortDirection);
        return lstArt;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist creeArtist(@RequestBody Artist art)//Request body map les request json, pour transformer du json en java
    {
        Artist arti = artistService.createArtist(art);
        return arti;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json", value="/{id}")//produces=MediaType.APPLICATION_JSON_VALUE
    public Artist modifArtist(@PathVariable Long id, @RequestBody Artist artist)
    {
        Artist art = artistService.updateArtist(id,artist);
        return artist;
    }

    //le delete artist par id
    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")//pas produces car on renvoit rien
    @ResponseStatus(HttpStatus.NO_CONTENT)//204 sa ses bien passé je te renvoie rien mais c'est normal
    public void suppArtist(@PathVariable Long id)
    {
        artistService.deleteArtist(id);
    }



}
