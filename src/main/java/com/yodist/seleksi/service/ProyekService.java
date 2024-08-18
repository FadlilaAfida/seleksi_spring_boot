package com.yodist.seleksi.service;

import com.yodist.seleksi.model.Proyek;

import java.util.List;
import java.util.Optional;

public interface ProyekService {
    Proyek saveProyek(Proyek proyek);
    List<Proyek> getAllProyek();
    Optional<Proyek> getProyekById(Integer id);
    Proyek updateProyek(Proyek proyek);
    void deleteProyek(Integer id);
}