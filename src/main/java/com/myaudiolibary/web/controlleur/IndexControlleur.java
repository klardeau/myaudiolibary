package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexControlleur {
    @Autowired
    private ArtistRepository artisteRepository;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String index(final ModelMap model){
        model.put("nbArtiste", artisteRepository.count());
        return "accueil";
    }
}
