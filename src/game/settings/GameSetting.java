package game.settings;

public class GameSetting {
    private boolean debugMode;

    public GameSetting(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}
