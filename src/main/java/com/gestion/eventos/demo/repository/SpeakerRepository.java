package com.gestion.eventos.demo.repository;

import com.gestion.eventos.demo.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    Optional<Speaker> findByEmail(String email);

    boolean existsByEmail(String email);
}
