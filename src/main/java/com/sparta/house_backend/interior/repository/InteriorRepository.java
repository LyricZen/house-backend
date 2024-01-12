package com.sparta.house_backend.interior.repository;

import com.sparta.house_backend.interior.entity.Interior;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteriorRepository extends JpaRepository<Interior, Long> {
}
