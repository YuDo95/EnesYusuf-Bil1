package dk.kea.enesyusufbil.repository;

import dk.kea.enesyusufbil.model.Lejeaftale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class LejeaftaleRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor, der injicerer JdbcTemplate til brug i repository-klassen
    @Autowired
    public LejeaftaleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter alle lejeaftaler fra databasen
    public List<Lejeaftale> getAllLejeaftaler() {
        String sql = "SELECT * FROM lejeaftale";
        return jdbcTemplate.query(sql, this::mapRowToLejeaftale);
    }

    // Opretter en ny lejeaftale i databasen med de givne værdier
    public void createLejeaftale(Lejeaftale lejeaftale) {
        String sql = "INSERT INTO lejeaftale " +
                "(lejeaftale_bil_vognnummer, " +
                "lejeaftale_user_id, " +
                "lejeaftale_startdato, " +
                "lejeaftale_slutdato, " +
                "lejeaftale_pris, " +
                "lejeaftale_aftalt_km, " +
                "lejeaftale_created_at, " +
                "lejeaftale_updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

        // Udfører SQL-sætningen for at indsætte data i databasen
        jdbcTemplate.update(sql,
                lejeaftale.getBilVognnummer(),
                lejeaftale.getUserId(),
                lejeaftale.getStartdato(),
                lejeaftale.getSlutdato(),
                lejeaftale.getPris(),
                lejeaftale.getAftaltKm());
    }

    // Opdaterer en eksisterende lejeaftale i databasen
    public void updateLejeaftale(Lejeaftale lejeaftale) {
        String sql = "UPDATE lejeaftale SET " +
                "lejeaftale_startdato = ?, " +
                "lejeaftale_slutdato = ?, " +
                "lejeaftale_pris = ?, " +
                "lejeaftale_aftalt_km = ?, " +
                "lejeaftale_slut_km = ?, " +
                "lejeaftale_overkoerte_km = ?, " +
                "lejeaftale_updated_at = CURRENT_TIMESTAMP " +
                "WHERE lejeaftale_id = ?";

        // Udfører SQL-sætningen for at opdatere data i databasen
        jdbcTemplate.update(sql,
                lejeaftale.getStartdato(),
                lejeaftale.getSlutdato(),
                lejeaftale.getPris(),
                lejeaftale.getAftaltKm(),
                lejeaftale.getSlutKm(),
                lejeaftale.isOverkoerteKm(),
                lejeaftale.getLejeaftaleId());
    }

    // Sletter en lejeaftale fra databasen baseret på dens ID
    public void deleteLejeaftale(int id) {
        String sql = "DELETE FROM lejeaftale WHERE lejeaftale_id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Mapper hver række fra ResultSet til et Lejeaftale-objekt
    private Lejeaftale mapRowToLejeaftale(ResultSet rs, int rowNum) throws SQLException {
        Lejeaftale lejeaftale = new Lejeaftale();
        lejeaftale.setLejeaftaleId(rs.getInt("lejeaftale_id"));
        lejeaftale.setBilVognnummer(rs.getInt("lejeaftale_bil_vognnummer"));
        lejeaftale.setUserId(rs.getInt("lejeaftale_user_id"));
        lejeaftale.setStartdato(rs.getObject("lejeaftale_startdato", LocalDate.class));
        lejeaftale.setSlutdato(rs.getObject("lejeaftale_slutdato", LocalDate.class));
        lejeaftale.setPris(rs.getDouble("lejeaftale_pris"));
        lejeaftale.setAftaltKm(rs.getInt("lejeaftale_aftalt_km"));
        lejeaftale.setSlutKm(rs.getObject("lejeaftale_slut_km", Integer.class));
        lejeaftale.setOverkoerteKm(rs.getBoolean("lejeaftale_overkoerte_km"));
        lejeaftale.setCreatedAt(rs.getObject("lejeaftale_created_at", LocalDate.class));
        lejeaftale.setUpdatedAt(rs.getObject("lejeaftale_updated_at", LocalDate.class));

        return lejeaftale;
    }

    // Kommentar: Denne metode bruges ikke endnu, men kan hente en lejeaftale ud fra dens ID
    /*
    public Lejeaftale getLejeaftaleById(int id) {
        String sql = "SELECT * FROM lejeaftale WHERE lejeaftale_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToLejeaftale);
    }
    */
}
