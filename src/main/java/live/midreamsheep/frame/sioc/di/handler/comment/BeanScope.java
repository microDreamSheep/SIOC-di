package live.midreamsheep.frame.sioc.di.handler.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BeanScope {
    SINGLE("SINGLE"),
    PROTOTYPE("PROTOTYPE");
    private final String scope;

    public static BeanScope getScope(String scope) {
        for (BeanScope value : BeanScope.values()) {
            if (value.getScope().equals(scope)) {
                return value;
            }
        }
        return null;
    }
}
