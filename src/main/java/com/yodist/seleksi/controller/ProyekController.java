package com.yodist.seleksi.controller;

import com.yodist.seleksi.model.Lokasi;
import com.yodist.seleksi.model.Proyek;
import com.yodist.seleksi.service.ProyekService;
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
@RequestMapping("/api/proyek")
public class ProyekController {

    private final ProyekService proyekService;

    @Autowired
    public ProyekController(ProyekService proyekService) {
        this.proyekService = proyekService;
    }

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody Proyek proyek) {

        Proyek savedProyek = proyekService.saveProyek(proyek);
        return new ResponseEntity<>(savedProyek, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proyek>> getAllProyek() {
        List<Proyek> proyeksList = proyekService.getAllProyek();
        return new ResponseEntity<>(proyeksList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Integer id) {
        Optional<Proyek> proyek = proyekService.getProyekById(id);
        return proyek.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable("id") Integer proyekId, @RequestBody Proyek proyek) {
        proyek.setId(proyekId);
        Proyek resultProyek = proyekService.updateProyek(proyek);
        return new ResponseEntity<>(resultProyek, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProyek(@PathVariable("id") Integer proyekId) {
        proyekService.deleteProyek(proyekId);
        Map<String, String> body = new HashMap<>();
        body.put("message", "Proyek berhasil dihapus.");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
