package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.UserRecord;
import org.jooq.Condition;

import java.util.List;
import java.util.Optional;

import static koh.db.hub.vps_management.tables.User.USER;

public class UserRepository {

    public Optional<UserRecord> createUser(String email, String password) {
        UserRecord userRecord = new UserRecord().setEmail(email).setPassword(password);
        userRecord.insert();
        return Optional.of(userRecord);
    }

    public Optional<UserRecord> getUserByEmailAndPassword(String email, String password) {
        Condition c = USER.EMAIL.eq(email).and(USER.PASSWORD.eq(password));
        return DataHub.useContext().fetch(USER, List.of(c)).stream().findAny();
    }
    
    
    public Optional<UserRecord> getUserByEmail(String email) {
        Condition c = USER.EMAIL.eq(email);
        return DataHub.useContext().fetch(USER, List.of(c)).stream().findAny();
    }
}
