package it.epicode.w5d1pratica.repository;

import it.epicode.w5d1pratica.bean.Drink;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

}
