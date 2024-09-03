package koh.db.hub.metadata;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MetaEnv {
    Boolean activation = false;
    Integer volumeLimit = 4 * 1024;
    Integer containerLimit = 1;
    Integer memoryLimit = 512;
    Integer databaseLimit = 1;
    List<Credential> credentials = new ArrayList<>();

    @Data
    public static class Credential {
        Long rid = null;
        String type = null;
        String content = null;
    }
}
