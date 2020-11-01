package com.hamburgueria.hamburgueriaapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LancheRepository extends JpaRepository<Lanche, Long> {
}
