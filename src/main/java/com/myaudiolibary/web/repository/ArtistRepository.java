package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
