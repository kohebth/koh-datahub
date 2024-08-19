package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.SessionRecord;
import org.jooq.Condition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static koh.db.hub.vps_management.tables.Session.SESSION;

public class SessionRepository {

    public Optional<SessionRecord> createSession(Long userId, String sessionId) {
        SessionRecord session = SESSION
                .newRecord()
                .setUserId(userId)
                .setSessionId(sessionId)
                .setExpireTime(LocalDateTime.now().plusDays(30));
        if (session.insert() == 1) {
            return Optional.of(session);
        }
        return Optional.empty();
    }

    public Optional<SessionRecord> getSession(Long userId, String sessionId, LocalDateTime expireTime) {
        Condition c = SESSION.SESSION_ID
                .eq(sessionId)
                .and(SESSION.USER_ID.eq(userId))
                .and(SESSION.EXPIRE_TIME.eq(expireTime));
        return DataHub.useContext().fetch(SESSION, List.of(c)).stream().findAny();
    }
}
