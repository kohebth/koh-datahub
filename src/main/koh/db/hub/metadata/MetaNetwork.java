package koh.db.hub.metadata;

import lombok.Data;

import java.util.List;

@Data
public class MetaNetwork {
    List<String> ports;
    List<String> exposes;
}
