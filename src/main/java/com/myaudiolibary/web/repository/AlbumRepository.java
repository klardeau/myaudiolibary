package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    void deleteByArtistId(Long id);

    void deleteById(Long id);
    Album save(Album album);

    boolean existsByTitle(String title);
}
