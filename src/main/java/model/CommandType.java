package model;

import java.util.Arrays;
import java.util.Objects;

public enum CommandType {
    MAVEN(1),
    IMPORT_ANALYZE(2),
    DOCKER(3);

    private final int code;


    public static CommandType fromValue(final int value) {
        return Arrays.stream(CommandType.values())
                .filter(type -> Objects.equals(type.getCode(), value))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public int getCode() {
        return code;
    }

    CommandType(int code) {
        this.code = code;
    }
}
