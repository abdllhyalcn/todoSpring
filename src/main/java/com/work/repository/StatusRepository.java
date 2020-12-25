package com.work.repository;

import com.work.models.EStatus;
import com.work.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(EStatus name);

    Boolean existsByName(String name);
}
