package pl.edu.wszib.http2.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.http2.service.model.Produkt;
import pl.edu.wszib.http2.service.model.Profile;

import java.util.List;

@Repository
public interface ProduktDao extends JpaRepository<Produkt, Integer> {

    List<Produkt> findAllByCenaLessThan(Float cena);
    List<Produkt> findAllByCenaGreaterThanEqual(Float cena);
    List<Produkt> findAllByCenaBetween(Float min, Float max);
}
