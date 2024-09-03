package koh.db.hub.metadata;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MetaEnv {
    Boolean activation = false;
    Integer volumeLimit = 4 * 1024;
    Integer containerLimit = 1;
    Integer memoryLimit = 512;
    Integer databaseLimit = 1;
    Map<Long, Credential> credentials = new HashMap<>();

    @Data
    public static class Credential {
        String type = null;
        Map<String, String> content = new HashMap<>();
    }
}
