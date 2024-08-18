package com.yodist.seleksi.controller;

import com.yodist.seleksi.model.Lokasi;
import com.yodist.seleksi.service.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/lokasi")
public class LokasiController {

    private final LokasiService lokasiService;

    @Autowired
    public LokasiController(LokasiService lokasiService) {
        this.lokasiService = lokasiService;
    }

    @PostMapping
    public ResponseEntity<Lokasi> createLokasi(@RequestBody Lokasi lokasi) {

        Lokasi savedLokasi = lokasiService.saveLokasi(lokasi);
        return new ResponseEntity<>(savedLokasi, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Lokasi>> getAllLokasi() {
        List<Lokasi> lokasiList = lokasiService.getAllLokasi();
        return new ResponseEntity<>(lokasiList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lokasi> getLokasiById(@PathVariable Integer id) {
        Optional<Lokasi> lokasi = lokasiService.getLokasiById(id);
        return lokasi.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable("id") Integer lokasiId, @RequestBody Lokasi lokasi) {
        lokasi.setId(lokasiId);
        Lokasi resultLokasi = lokasiService.updateLokasi(lokasi);
        return new ResponseEntity<>(resultLokasi, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLokasi(@PathVariable("id") Integer lokasiId) {
        lokasiService.deleteLokasi(lokasiId);
        Map<String, String> body = new HashMap<>();
        body.put("message", "Lokasi berhasil dihapus.");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
