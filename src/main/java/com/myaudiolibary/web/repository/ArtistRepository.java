package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.model.Artist;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;


public interface ArtistRepository extends JpaRepository<Artist, Long> {


    ArrayList<Artist> findByNameContainsIgnoreCase(String name);


    boolean existsByName(String name);

   // Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
