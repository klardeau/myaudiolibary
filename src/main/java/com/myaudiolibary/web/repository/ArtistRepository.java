package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.model.Artist;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ArtistRepository extends JpaRepository<Artist, Long> {


    Page<Artist> findByNameContainsIgnoreCase(String name, Pageable pageable);


    boolean existsByName(String name);

   // Page<Artist> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);
}
