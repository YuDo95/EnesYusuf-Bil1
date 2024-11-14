package dk.kea.enesyusufbil.repository;

import dk.kea.enesyusufbil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter alle brugere fra databasen.
    // `jdbcTemplate.query` bruger `UserRowMapper` til at konvertere hver række til et `User`-objekt.
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    // Opretter en ny bruger i databasen.
    // `?`-symbolerne er pladsholdere for parameterbinding, som indsætter værdier fra `User`-objektet i SQL INSERT-statementet.
    public void createUser(User user) {
        String sql = "INSERT INTO user " +
                "(user_navn, user_email, user_password, user_telefonnummer, user_rolle, imageURL) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                user.getUser_navn(),
                user.getUser_email(),
                user.getUser_password(),
                user.getUser_telefonnummer(),
                user.getUser_rolle(),
                user.getImageUrl());
    }

    // Opdaterer en eksisterende bruger i databasen.
    // SQL `UPDATE`-statementet bruger `SET` til at ændre værdier og `WHERE` for at finde brugeren med det specifikke `user_id`.
    public void updateUser(User user) {
        String sql = "UPDATE user SET " +
                "user_navn = ?, user_email = ?, user_password = ?, user_telefonnummer = ?, user_rolle = ?, imageURL = ? " +
                "WHERE user_id = ?";

        jdbcTemplate.update(sql,
                user.getUser_navn(),
                user.getUser_email(),
                user.getUser_password(),
                user.getUser_telefonnummer(),
                user.getUser_rolle(),
                user.getImageUrl(),
                user.getUser_id());
    }

    // Sletter en bruger fra databasen baseret på `user_id`.
    // `DELETE`-statementet bruger `WHERE` til at finde og slette brugeren med det angivne `user_id`.
    public void deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    // Validerer en brugers loginoplysninger ved at tælle rækker med det angivne email og password.
    // SQL `COUNT(*)` bruges til at tjekke om en bruger med disse oplysninger eksisterer i databasen.
    public boolean validateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE user_email = ? AND user_password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);
        return count != null && count > 0;
    }

    // RowMapper for `User`-objekter.
    // `mapRow`-metoden bruger `ResultSet` til at hente data fra hver kolonne og sætter det på et `User`-objekt.
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUser_id(rs.getInt("user_id"));
            user.setUser_navn(rs.getString("user_navn"));
            user.setUser_email(rs.getString("user_email"));
            user.setUser_password(rs.getString("user_password"));
            user.setUser_telefonnummer(rs.getInt("user_telefonnummer"));
            user.setUser_rolle(rs.getString("user_rolle"));
            user.setImageUrl(rs.getString("imageURL"));
            return user;
        }
    }

    /* Ikke anvendt (endnu)
    // Henter en specifik bruger baseret på `user_id`.
    // SQL SELECT-statementet bruger `WHERE` til at finde en bruger med det angivne `user_id`.
    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserRowMapper());
    }*/
}
