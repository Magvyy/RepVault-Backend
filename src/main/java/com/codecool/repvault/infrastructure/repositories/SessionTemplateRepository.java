package com.codecool.repvault.infrastructure.repositories;

import com.codecool.repvault.domain.entities.SessionTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionTemplateRepository extends JpaRepository<SessionTemplate, Long> {
    List<SessionTemplate> findByUserId(Long userId, Pageable pageable);
}
