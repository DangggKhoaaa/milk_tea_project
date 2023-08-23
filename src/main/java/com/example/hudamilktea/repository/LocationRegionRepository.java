package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion , Long> {
}
