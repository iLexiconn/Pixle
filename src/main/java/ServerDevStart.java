import com.esotericsoftware.minlog.Log;
import net.ilexiconn.pixle.util.SystemUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class ServerDevStart extends DevStart {
    public static void main(String[] args) throws ReflectiveOperationException {
        new ServerDevStart().start(args);
    }

    @Override
    public void preInit(Map<String, String> properties) {
        int level = Log.LEVEL_INFO;
        try {
            Field field = Log.class.getField("LEVEL_" + properties.get("log"));
            level = field.getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.set(level);
    }

    @Override
    public void applyDefaults(Map<String, String> properties) {
        applyDefault(properties, "port", 25565);
        applyDefault(properties, "gamefolder", SystemUtils.getGameFolder().getAbsolutePath());
        applyDefault(properties, "log", "INFO");
    }

    @Override
    public String getStartupClassName() {
        return "net.ilexiconn.pixle.server.PixleServerStartup";
    }
}
