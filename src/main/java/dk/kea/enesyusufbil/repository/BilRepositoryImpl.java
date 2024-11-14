package dk.kea.enesyusufbil.repository;

import dk.kea.enesyusufbil.model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BilRepositoryImpl implements BilRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor, der injicerer JdbcTemplate for at muliggøre databaseoperationer
    @Autowired
    public BilRepositoryImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter alle biler fra databasen
    @Override
    public List<Bil> findAll()
    {
        String sql = "SELECT * FROM bil";
        return jdbcTemplate.query(sql, new BilRowMapper());
    }

    // Opretter en ny bil i databasen med de angivne felter
    @Override
    public void create(Bil bil)
    {
        String sql = "INSERT INTO bil " +
                "(bil_stelnummer, " +
                "bil_maerke, " +
                "bil_model," +
                "bil_aargang, " +
                "bil_farve, " +
                "bil_registreringsdato, " +
                "bil_udleveringslokation," +
                "bil_staalpris, bil_regAfgift, " +
                "bil_co2Udledning, bil_km ," +
                "udlejet," +
                "imageURL) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                bil.getStelnummer(),
                bil.getMaerke(),
                bil.getModel(),
                bil.getAargang(),
                bil.getFarve(),
                bil.getRegistreringsdato(),
                bil.getUdleveringslokation(),
                bil.getStaalpris(),
                bil.getRegAfgift(),
                bil.getCo2Udledning(),
                bil.getKm(),
                bil.getUdlejet(),
                bil.getImageUrl()
        );
    }

    // Opdaterer en eksisterende bil i databasen baseret på bilens vognnummer
    @Override
    public void update(Bil bil)
    {
        String sql = "UPDATE bil SET " +
                "bil_stelnummer = ?," +
                " bil_maerke = ?, " +
                "bil_model = ?, " +
                "bil_aargang = ?, " +
                "bil_farve = ?, " +
                "bil_registreringsdato = ?," +
                "bil_udleveringslokation = ?, " +
                "bil_staalpris = ?," +
                "bil_regAfgift = ?, " +
                "bil_co2Udledning = ?," +
                "bil_km = ?, " +
                "imageURL = ?," +
                "udlejet = ? " +
                "WHERE bil_vognnummer = ?";

        jdbcTemplate.update(sql,
                bil.getStelnummer(),
                bil.getMaerke(),
                bil.getModel(),
                bil.getAargang(),
                bil.getFarve(),
                bil.getRegistreringsdato(),
                bil.getUdleveringslokation(),
                bil.getStaalpris(),
                bil.getRegAfgift(),
                bil.getCo2Udledning(),
                bil.getKm(),
                bil.getImageUrl(),
                bil.getUdlejet(),
                bil.getVognnummer());
    }

    // Sletter en bil fra databasen baseret på vognnummeret
    @Override
    public void delete(int vognnummer)
    {
        String sql = "DELETE FROM bil WHERE bil_vognnummer = ?";
        jdbcTemplate.update(sql, vognnummer);
    }

    // RowMapper klasse for at mappe resultatsæt til Bil-objekter
    private static class BilRowMapper implements RowMapper<Bil>
    {
        // Mapper en række fra ResultSet til et Bil-objekt
        public Bil mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bil bil = new Bil();
            bil.setVognnummer(rs.getInt("bil_vognnummer"));
            bil.setStelnummer(rs.getString("bil_stelnummer"));
            bil.setMaerke(rs.getString("bil_maerke"));
            bil.setModel(rs.getString("bil_model"));
            bil.setAargang(rs.getInt("bil_aargang"));
            bil.setFarve(rs.getString("bil_farve"));
            bil.setRegistreringsdato(rs.getString("bil_registreringsdato"));
            bil.setUdleveringslokation(rs.getString("bil_udleveringslokation"));
            bil.setStaalpris(rs.getDouble("bil_staalpris"));
            bil.setRegAfgift(rs.getDouble("bil_regAfgift"));
            bil.setCo2Udledning(rs.getDouble("bil_co2Udledning"));
            bil.setKm(rs.getInt("bil_km"));
            bil.setUdlejet(rs.getBoolean("udlejet"));
            bil.setImageUrl(rs.getString("imageURL"));
            return bil;
        }
    }

    /* Metode der (ikke bruges endnu)
    // Finder en bil baseret på vognnummer
    // Returnerer bil-objektet, hvis fundet; ellers returnerer null
    @Override
    public Bil findById(int vognnummer) {
        String sql = "SELECT * FROM bil WHERE bil_vognnummer = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{vognnummer}, new BilRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // eller smid en brugerdefineret undtagelse
        }
    }*/
}
