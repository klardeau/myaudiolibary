package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.model.Album;
import com.myaudiolibary.web.model.Artist;
import com.myaudiolibary.web.repository.ArtistRepository;
import com.myaudiolibary.web.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@RestController
@Controller
@RequestMapping("/artists")
public class ArtistControlleur {

    @Autowired
    private ArtistService artistService;


    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String avoirArtist(@PathVariable(value="id")Long id, final ModelMap model){
        Artist art = artistService.getArtist(id);
        //model.put("artist",art);
        Album album= new Album();
        album.setArtist(art);//on ajoute l'artiste à l'album
        model.addAttribute("album",album);//pour créer des albums
        model.addAttribute("artist",art);
        return "detailArtist";
    }

    @RequestMapping(params = {"name"},method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String avoirArtistByName(final ModelMap model, @RequestParam(value="name") String name){
        ArrayList<Artist> pageArt = artistService.getArtistByName(name);


        model.addAttribute("artistes", pageArt);


        return "listeRecherche";
    }
    //public Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){


    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String getTousArtist(@RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection, final ModelMap model){
        Page<Artist> lstArt = artistService.getAllArtist(page, size, sortProperty, sortDirection);
        PageRequest pageRequest = PageRequest.of(page, size,
                Sort.Direction.fromString(sortDirection), sortProperty);

        model.addAttribute("artistes", lstArt);
        //pageEmploye.has
//        pageEmploye.getTotalElements();
        //employes.totalElements =>
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page) * size + lstArt.getNumberOfElements());
        model.put("nbTotPage", lstArt.getTotalPages()); //donne le nb total de page afin de limité la barre de changement de page
        model.put("nbStr", lstArt.stream().count());//nb d'élément près découpé !
        model.put("nbArtiste", lstArt.getTotalElements());//nb Artiste
        return "listeArtists";
    }

    @RequestMapping(value = "/ajoutArtist")
    public String addArtPage(final ModelMap model){
        Artist art= new Artist();
        model.addAttribute("art",art);
        return "ajoutArtist";
    }

    @PostMapping("/creeArtist")
    public String creeArtist(@ModelAttribute("art") Artist art)//Request body map les request json, pour transformer du json en java
    {
        Artist arti = artistService.createArtist(art);
        return "redirect:/";
        //return "../artists?page=0&amp;size=10&amp;sortProperty=name&amp;sortDirection=ASC";
    }

    /*@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist creeArtist(@RequestBody Artist art)//Request body map les request json, pour transformer du json en java
    {
        Artist arti = artistService.createArtist(art);
        return arti;
    }*/
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json", value="/{id}")//produces=MediaType.APPLICATION_JSON_VALUE
    public Artist modifArtist(@PathVariable Long id, @RequestBody Artist artist)
    {
        Artist art = artistService.updateArtist(id,artist);
        return artist;
    }

    //@RequestMapping(method = RequestMethod.DELETE, value="/{id}")//pas produces car on renvoit rien
    //    @ResponseStatus(HttpStatus.NO_CONTENT)//204 sa ses bien passé je te renvoie rien mais c'est normal
    //le delete artist par id
    @RequestMapping(method = RequestMethod.GET, value="/{id}/delete")//pas produces car on renvoit rien
    //@ResponseStatus(HttpStatus.NO_CONTENT)//204 sa ses bien passé je te renvoie rien mais c'est normal///// FAIRE ATTENTION il empeche de renvoyer quelque chose
    public RedirectView suppArtist(@PathVariable Long id)
    {
        artistService.deleteArtist(id);


        return new RedirectView("/artists?page=0&size=10&sortProperty=name&sortDirection=ASC");//REDIRIGE TOI STP
    }





}
