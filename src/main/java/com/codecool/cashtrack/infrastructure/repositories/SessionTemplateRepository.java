package com.codecool.cashtrack.infrastructure.repositories;

import com.codecool.cashtrack.domain.entities.SessionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionTemplateRepository extends JpaRepository<SessionTemplate, Long> {


}
