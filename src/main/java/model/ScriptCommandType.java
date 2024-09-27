package model;

import java.util.Arrays;
import java.util.Objects;

public enum ScriptCommandType {
    MAVEN(1),
    IMPORT_ANALYZE(2),
    DOCKER(3);

    private final int code;


    public static ScriptCommandType fromValue(final int value) {
        return Arrays.stream(ScriptCommandType.values())
                .filter(type -> Objects.equals(type.getCode(), value))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public int getCode() {
        return code;
    }

    ScriptCommandType(int code) {
        this.code = code;
    }
}
