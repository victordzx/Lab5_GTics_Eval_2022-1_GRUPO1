package edu.pucp.gtics.lab5_gtics_20221.repository;

import edu.pucp.gtics.lab5_gtics_20221.entity.Distribuidoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DistribuidorasRepository extends JpaRepository<Distribuidoras,Integer> {

}
