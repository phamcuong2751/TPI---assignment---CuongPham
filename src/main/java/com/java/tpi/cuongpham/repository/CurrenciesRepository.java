package com.java.tpi.cuongpham.repository;

import com.java.tpi.cuongpham.entity.CurrenciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrenciesRepository extends JpaRepository<CurrenciesEntity, Long> {
    List<CurrenciesEntity> findAllByCcyCodeLike(String ccyCode);
    Optional<CurrenciesEntity> findByCcyCode(String ccyCode);
    void deleteByCcyCode(String ccyCode);
}
