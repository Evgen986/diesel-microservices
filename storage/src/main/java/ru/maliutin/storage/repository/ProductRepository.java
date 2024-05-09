package ru.maliutin.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maliutin.storage.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
