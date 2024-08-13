package koh.db.hub.metadata;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class MetaEnv {
    private final Map<String, String> envMap;
}
