package com.example.psb_click.repository;

import com.example.psb_click.entity.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {

    @Query("select s from ServicesEntity s where s.service_id = ?1")
    Optional<ServicesEntity> findByService_id(Integer id);
}
