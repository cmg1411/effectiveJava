package Chapter12.Day89;

import java.io.Serializable;

public final class MySingleton implements Serializable { // Serializable
    private static final MySingleton INSTANCE = new MySingleton();

    private MySingleton() {
    }

    public static MySingleton getINSTANCE() {
        return INSTANCE;
    }
}