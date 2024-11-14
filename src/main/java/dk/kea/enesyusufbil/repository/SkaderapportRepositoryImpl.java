package dk.kea.enesyusufbil.repository;

import dk.kea.enesyusufbil.model.Skaderapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SkaderapportRepositoryImpl implements SkaderapportRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SkaderapportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter alle skaderapporter fra databasen ved hjælp af et SQL SELECT-statement.
    // `jdbcTemplate.query` bruger `mapRowToSkaderapport` til at konvertere hver række til et `Skaderapport`-objekt.
    @Override
    public List<Skaderapport> findAll() {
        String sql = "SELECT * FROM skaderapport";
        return jdbcTemplate.query(sql, this::mapRowToSkaderapport);
    }

    // Henter en specifik skaderapport baseret på dens ID.
    // SQL SELECT-statementet bruger en `WHERE`-klausul til at finde en skaderapport med et bestemt `skaderapport_id`.
    @Override
    public Skaderapport findById(int id) {
        String sql = "SELECT * FROM skaderapport WHERE skaderapport_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToSkaderapport);
    }

    // Opretter en ny skaderapport i databasen med et SQL INSERT-statement.
    // `?`-symbolerne bruges som pladsholdere for at indsætte værdier fra `Skaderapport`-objektet.
    @Override
    public void save(Skaderapport skaderapport) {
        String sql = "INSERT INTO skaderapport (skaderapport_vognnummer, skaderapport_user_id, skaderapport_xtra_km, skaderapport_total_skade_pris) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                skaderapport.getVognnummer(),
                skaderapport.getUserId(),
                skaderapport.getXtraKm(),
                skaderapport.getTotalSkadePris());
    }

    // Opdaterer en eksisterende skaderapport i databasen baseret på dens ID.
    // `UPDATE`-statementet bruger `SET` til at ændre kolonneværdier og `WHERE` for at sikre, at kun én række opdateres.
    @Override
    public void update(Skaderapport skaderapport) {
        String sql = "UPDATE skaderapport SET skaderapport_vognnummer = ?, skaderapport_user_id = ?, " +
                "skaderapport_xtra_km = ?, skaderapport_total_skade_pris = ? " +
                "WHERE skaderapport_id = ?";
        jdbcTemplate.update(sql,
                skaderapport.getVognnummer(),
                skaderapport.getUserId(),
                skaderapport.getXtraKm(),
                skaderapport.getTotalSkadePris(),
                skaderapport.getId());
    }

    // Sletter en skaderapport fra databasen baseret på dens ID.
    // SQL DELETE-statementet bruger `WHERE` for at finde og slette skaderapporten med det specifikke `skaderapport_id`.
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM skaderapport WHERE skaderapport_id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Mapper en række fra databasen til et `Skaderapport`-objekt.
    // `ResultSet` giver adgang til data i hver kolonne, som bliver tildelt de tilsvarende felter i `Skaderapport`.
    private Skaderapport mapRowToSkaderapport(ResultSet rs, int rowNum) throws SQLException {
        Skaderapport skaderapport = new Skaderapport();
        skaderapport.setId(rs.getInt("skaderapport_id"));
        skaderapport.setVognnummer(rs.getInt("skaderapport_vognnummer"));
        skaderapport.setUserId(rs.getInt("skaderapport_user_id"));
        skaderapport.setXtraKm(rs.getInt("skaderapport_xtra_km"));
        skaderapport.setTotalSkadePris(rs.getDouble("skaderapport_total_skade_pris"));
        skaderapport.setCreatedAt(LocalDate.from(rs.getTimestamp("skaderapport_created_at").toLocalDateTime()));
        skaderapport.setUpdatedAt(LocalDate.from(rs.getTimestamp("skaderapport_updated_at").toLocalDateTime()));
        return skaderapport;
    }
}
