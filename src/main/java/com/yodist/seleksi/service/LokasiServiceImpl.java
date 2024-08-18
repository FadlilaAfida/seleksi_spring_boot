package com.yodist.seleksi.service;

import com.yodist.seleksi.model.Lokasi;
import com.yodist.seleksi.repository.LokasiRepository;
import com.yodist.seleksi.service.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class LokasiServiceImpl implements LokasiService {

    private final LokasiRepository lokasiRepository;

    @Autowired
    public LokasiServiceImpl(LokasiRepository lokasiRepository) {
        this.lokasiRepository = lokasiRepository;
    }

    @Override
    public Lokasi saveLokasi(Lokasi lokasi) {
        lokasi.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return lokasiRepository.save(lokasi);
    }

    @Override
    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    @Override
    public Optional<Lokasi> getLokasiById(Integer id) {
        return lokasiRepository.findById(id);
    }

    @Override
    public Lokasi updateLokasi(Lokasi lokasi) {
        Lokasi existingLokasi = lokasiRepository.findById(lokasi.getId()).get();
        existingLokasi.setNamaLokasi(lokasi.getNamaLokasi());
        existingLokasi.setNegara(lokasi.getNegara());
        existingLokasi.setProvinsi(lokasi.getProvinsi());
        existingLokasi.setKota(lokasi.getKota());
        return lokasiRepository.save(existingLokasi);
    }

    @Override
    public void deleteLokasi(Integer id) {
        if (lokasiRepository.existsById(id)) {
            lokasiRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lokasi with id " + id + " does not exist");
        }
    }
}
