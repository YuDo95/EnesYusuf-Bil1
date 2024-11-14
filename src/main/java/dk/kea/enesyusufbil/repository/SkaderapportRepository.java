package dk.kea.enesyusufbil.repository;

import dk.kea.enesyusufbil.model.Skaderapport;

import java.util.List;

public interface SkaderapportRepository {

    List<Skaderapport> findAll();

    Skaderapport findById(int id);

    void save(Skaderapport skaderapport);

    void update(Skaderapport skaderapport);

    void delete(int id);
}
