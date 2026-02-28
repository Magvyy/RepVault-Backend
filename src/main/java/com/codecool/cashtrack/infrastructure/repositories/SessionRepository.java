package com.codecool.cashtrack.infrastructure.repositories;

import com.codecool.cashtrack.domain.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {


}
