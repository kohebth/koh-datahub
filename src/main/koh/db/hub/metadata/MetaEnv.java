package koh.db.hub.metadata;

import lombok.Data;

import java.util.Map;

@Data
public class MetaEnv {
    private final Map<String, String> envMap;
}
