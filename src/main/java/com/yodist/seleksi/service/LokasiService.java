package com.yodist.seleksi.service;

import com.yodist.seleksi.model.Lokasi;

import java.util.List;
import java.util.Optional;

public interface LokasiService {
    Lokasi saveLokasi(Lokasi lokasi);
    List<Lokasi> getAllLokasi();
    Optional<Lokasi> getLokasiById(Integer id);
    Lokasi updateLokasi(Lokasi lokasi);
    void deleteLokasi(Integer id);
}

