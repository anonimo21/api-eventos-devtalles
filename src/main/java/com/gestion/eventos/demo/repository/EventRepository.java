package com.gestion.eventos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.eventos.demo.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
