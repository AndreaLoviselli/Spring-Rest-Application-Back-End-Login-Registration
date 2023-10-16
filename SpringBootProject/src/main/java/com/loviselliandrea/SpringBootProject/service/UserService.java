package com.loviselliandrea.SpringBootProject.service;

import com.loviselliandrea.SpringBootProject.dao.UserRepositoryDAO;
import com.loviselliandrea.SpringBootProject.model.Authority;
import com.loviselliandrea.SpringBootProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepositoryDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(@Qualifier("dbUserDAO") UserRepositoryDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> getUserById(int id){
        return userDAO.findById(id);
    }
    public Iterable<User> getUsers(){
        return userDAO.findAll();
    }

    public List<User> findByUsernameContains(String partialUsername){
        return userDAO.findByUsernameContains(partialUsername);
    }

    public List<User> findByUsernameContainsAndEmailContains(String partialUsername, String partialMail){
        return userDAO.findByUsernameContainsAndEmailContains(partialUsername, partialMail);
    }

    public String deleteUser(int id){
        User user = userDAO.findById(id).orElse(null);
        if (user != null){
            userDAO.delete(user);
            return "Utente cancellato correttamente";
        }else {
            return "Errore, utente non trovato";
        }
    }

    public String updateUser(int id, User user){
        user.setId(id);
        User userResult = userDAO.save(user);
        if (userResult != null){
            return "Utente aggiornato correttamente";
        } else {
            return "Errore nel cancellamento dell'utente";
        }
    }

    /**
     * Questo metodo controlla innanzitutto se l'utente esiste già tramite findByUsername.
     * Successivamente effettua tre controlli:
     * 1) è l'utente diverso != null ?
     * 2) sono corretti i campi dell'utente? -> checkUserFields() ?
     * 3) sono corrette le autorizzazioni associate? -> checkUserAuthorities() ?
     * Se i controlli sono superati viene criptata la pw tramite passwordEncoder e salvato l'utente tramite userDAO.
     * @param user su cui effettuare i controlli e da salvare se corretto.
     * @return String descrittiva dell'operazione
     */
    public String registerUser(User user){

        User userExists = userDAO.findByUsername(user.getUsername());
        if(userExists != null) {
            return "Username già in uso";
        }else if (user != null && checkUserFields(user)  && checkUserAuthorities(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.save(user);
            return "Registrazione effettuata con successo";
        }
        return "Errore nella registrazione dell'utente";
    }

    public boolean checkUserFields(User user){
       return !areFieldsBlank(user) && !areFieldsEmpty(user);
    }

    public boolean areFieldsEmpty(User user){
        return user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty();
    }
    public boolean areFieldsBlank(User user){
        return user.getUsername().isBlank() || user.getEmail().isBlank() || user.getPassword().isBlank();
    }

    public boolean checkUserAuthorities(User user){
        return !AreAuthoritiesNullOrEmpty(user) && isRoleCorrect(user);
    }

    public boolean AreAuthoritiesNullOrEmpty(User user){
        return user.getAuthorities() == null || user.getAuthorities().isEmpty();
    }

    /**
     * Questo metodo controlla che le autorizzazioni associate all'utente siano corrette. Al suo interno sono generate
     * due autorizzazioni temporanee. Vengono poi confrontate tramite .equals() alle autorizzazioni dell'utente. Se
     * esse ogni ciclo for-each corrispondono ad almeno una delle due, allora risultano corrette. Nel caso in cui anche
     * una sia scorretta, la var. booleana flagAreRolesCorrect, cambia valore in false.
     * @param user di cui controllare le autorizzazioni.
     * @return false se le autorizzazioni sono irregolari, true se regolari.
     */
    public boolean isRoleCorrect(User user) {
        Authority authorityUserTmp = new Authority(user.getUsername(), "ROLE_USER");
        Authority authorityAdminTmp = new Authority(user.getUsername(), "ROLE_ADMIN");

        boolean flagAreRolesCorrect = true;

        for (Authority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(authorityUserTmp.getAuthority()) ||
                    authority.getAuthority().equals(authorityAdminTmp.getAuthority())) {
            }else{
                flagAreRolesCorrect = false;
            }
        }
        return flagAreRolesCorrect;
    }

    public String login(String username, String password){

        User associatedUser = userDAO.findByUsername(username);

        if (associatedUser == null){
            return "Utente inesistente";
        }

        if ( passwordEncoder.matches(password ,associatedUser.getPassword())) {
            return "Login effettuato con successo";
        }else return "credenziali sbagliate";
    }
}
