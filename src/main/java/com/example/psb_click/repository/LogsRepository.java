package com.example.psb_click.repository;

import com.example.psb_click.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs,Integer> {

    //todo paging and sorting link => https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.paging-and-sorting
}
