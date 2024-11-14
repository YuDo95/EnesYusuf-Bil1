package dk.kea.enesyusufbil.service;

import dk.kea.enesyusufbil.model.User;
import dk.kea.enesyusufbil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    // Henter en liste over alle bruger-objekter fra databasen.
    // Metoden anvender `getAllUsers` fra `UserRepository`.
    public List<User> getAllUsers() {return userRepository.getAllUsers();}

    // Opretter en ny bruger i databasen.
    // Metoden kalder `createUser` i `UserRepository` for at gemme brugerens data.
    public void createUser(User user) {userRepository.createUser(user);}

    // Opdaterer en eksisterende bruger i databasen.
    // Anvender `updateUser` fra `UserRepository` for at opdatere brugerens oplysninger.
    public void updateUser(User user) {userRepository.updateUser(user);}

    // Sletter en bruger fra databasen baseret på `userId`.
    // Metoden bruger `deleteUser` fra `UserRepository`.
    public void deleteUser(int userId) {userRepository.deleteUser(userId);}

    // Validerer brugerens loginoplysninger.
    // Anvender `validateUser` fra `UserRepository` til at kontrollere, om email og password er gyldige.
    public boolean login(String email, String password) {return userRepository.validateUser(email, password);}

    /* Henter en specifik bruger baseret på `userId`.
       Denne metode er ikke brugt endnu, men kunne være nyttig til at hente data for en enkelt bruger.
    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    } */

}
