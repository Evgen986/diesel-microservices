package ru.maliutin.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maliutin.storage.domain.Product;

/**
 * Репозиторий сущности товара.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
