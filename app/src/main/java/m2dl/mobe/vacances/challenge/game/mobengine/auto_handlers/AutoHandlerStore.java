package m2dl.mobe.vacances.challenge.game.mobengine.auto_handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoHandlerStore {

    private static final List<AutoHandler> autoHandlers = new ArrayList<>();

    public static List<AutoHandler> getAutoHandlers() {
        return autoHandlers;
    }

    public static void start() {
        for (AutoHandler autoHandler : autoHandlers) {
            autoHandler.start();
        }
    }

    public static void stop() {
        for (AutoHandler autoHandler : autoHandlers) {
            autoHandler.stop();
        }
    }

    public static void addAutoHandlers(AutoHandler... autoHandlers) {
        AutoHandlerStore.autoHandlers.addAll(Arrays.asList(autoHandlers));
    }
}
