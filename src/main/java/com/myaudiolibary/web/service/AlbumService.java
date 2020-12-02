package com.myaudiolibary.web.service;

import com.myaudiolibary.web.model.Album;
import com.myaudiolibary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album creeAlbum(@RequestBody Album album)//Request body map les request json, pour transformer du json en java
    {
        if(albumRepository.existsByTitle(album.getTitle())){//retourn un boolean true ou false si true exception si false il crée
            throw new EntityExistsException("L'album de titre: "+album.getTitle()+" existe deja!");//EntityExistsException=ERROR 409
        }
        if(album.getTitle().isEmpty()){//retourn un boolean true ou false si true exception si false il crée
            throw new EntityNotFoundException("L'album ne possède pas de libéllé titre !");//EntityExistsException=ERROR 409
        }
        albumRepository.save(album);
        return album;
    }

    public void deleteAlbum(@PathVariable Long id)
    {
        //delete les albums de l'artistes en prems

        if(!albumRepository.existsById(id)){
            throw new EntityNotFoundException("L'album d'identifiant: "+id+" n'a pas été trouvé !");
        }
        albumRepository.deleteById(id);
    }


}
