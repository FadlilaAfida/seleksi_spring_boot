package com.yodist.seleksi.service;

import com.yodist.seleksi.model.Lokasi;
import com.yodist.seleksi.model.Proyek;
import com.yodist.seleksi.repository.LokasiRepository;
import com.yodist.seleksi.repository.ProyekRepository;
import com.yodist.seleksi.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProyekServiceImpl implements ProyekService {

    private final ProyekRepository proyekRepository;
    private final LokasiRepository lokasiRepository;

    @Autowired
    public ProyekServiceImpl(ProyekRepository ProyekRepository, LokasiRepository lokasiRepository) {
        this.proyekRepository = ProyekRepository;
        this.lokasiRepository = lokasiRepository;
    }

    @Override
    public Proyek saveProyek(Proyek proyek) {
        proyek.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return proyekRepository.save(proyek);
    }

    @Override
    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    @Override
    public Optional<Proyek> getProyekById(Integer id) {
        return proyekRepository.findById(id);
    }

    @Override
    public Proyek updateProyek(Proyek proyek) {
        Proyek existingProyek = proyekRepository.findById(proyek.getId()).get();
        existingProyek.setNamaProyek(proyek.getNamaProyek());
        existingProyek.setClient(proyek.getClient());
        existingProyek.setTglMulai(proyek.getTglMulai());
        existingProyek.setTglSelesai(proyek.getTglSelesai());
        existingProyek.setPimpinanProyek(proyek.getPimpinanProyek());
        existingProyek.setKeterangan(proyek.getKeterangan());
        proyekRepository.save(existingProyek);

        Optional<Proyek> optionalProyek = proyekRepository.findById(existingProyek.getId());
        if (optionalProyek.isPresent()) {
            Proyek proyek2 = optionalProyek.get();

            List<Integer> lokasiIds = proyek.getLokasiList().stream()
                    .map(Lokasi::getId)
                    .collect(Collectors.toList());

            List<Lokasi> lokasiList = lokasiRepository.findAllById(lokasiIds);
            proyek2.setLokasiList(lokasiList);
            return proyekRepository.save(proyek2);
        } else {
            throw new RuntimeException("Proyek not found with id: " + existingProyek.getId());
        }
    }

    @Override
    public void deleteProyek(Integer id) {
        if (proyekRepository.existsById(id)) {
            proyekRepository.deleteById(id);
        } else {
            throw new RuntimeException("Proyek with id " + id + " does not exist");
        }
    }
}
