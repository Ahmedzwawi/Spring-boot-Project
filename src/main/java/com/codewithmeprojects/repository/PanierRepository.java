package com.codewithmeprojects.repository;

import com.codewithmeprojects.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier,Long> {
    public Panier findPanierByUserId(Long Id) ;

}
