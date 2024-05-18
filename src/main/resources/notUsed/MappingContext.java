package notUsed;

import java.util.HashMap;
import java.util.Map;

public class MappingContext {
    private Map<Class<?>, Object> knownInstances = new HashMap<>();

    public <T> T getInstance(Class<T> clazz) {
        return clazz.cast(knownInstances.get(clazz));
    }

    public <T> void setInstance(Class<T> clazz, T instance) {
        knownInstances.put(clazz, instance);
    }
}
