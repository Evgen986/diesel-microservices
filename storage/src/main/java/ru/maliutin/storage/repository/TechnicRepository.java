package ru.maliutin.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maliutin.storage.domain.Technic;

import java.util.Optional;
@Repository
public interface TechnicRepository extends JpaRepository<Technic, Long> {

    Optional<Technic> findTechnicByTitle(String title);

}
