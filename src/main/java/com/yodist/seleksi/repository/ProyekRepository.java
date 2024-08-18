package com.yodist.seleksi.repository;

import com.yodist.seleksi.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Integer> {
}