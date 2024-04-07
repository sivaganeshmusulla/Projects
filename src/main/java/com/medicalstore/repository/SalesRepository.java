package com.medicalstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.medicalstore.entity.Sales;


/*
 * @Repository
 * @Author Siva Ganesh
 */
@Repository
public interface SalesRepository extends JpaRepository<Sales, Long>{
	List<Sales> findByClientId(Long clientId);


}
