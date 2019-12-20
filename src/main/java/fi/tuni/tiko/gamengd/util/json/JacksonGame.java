package fi.tuni.tiko.gamengd.util.json;

/**
 * JacksonGame is an object created from a JSONFile using Jackson.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class JacksonGame {
    /**
     * gameTitle data.
     */
    private String gameTitle;
    /**
     * levels array holding JacksonLevels.
     */
    private JacksonLevel[] levels;
    /**
     * JacksonPlayer inside the JacksonGame.
     */
    private JacksonPlayer player;

    /**
     * JacksonGame constructor.
     */
    public JacksonGame() {
    }

    /**
     * Getter for gameTitle
     *
     * @return value of gameTitle
     */
    public String getGameTitle() {
        return gameTitle;
    }

    /**
     * Sets gameTitle
     *
     * @param gameTitle new value
     */
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    /**
     * Getter for levels
     *
     * @return value of levels
     */
    public JacksonLevel[] getLevels() {
        return levels;
    }

    /**
     * Sets levels
     *
     * @param levels new value
     */
    public void setLevels(JacksonLevel[] levels) {
        this.levels = levels;
    }

    /**
     * Getter for player
     *
     * @return value of player
     */
    public JacksonPlayer getPlayer() {
        return player;
    }

    /**
     * Sets player
     *
     * @param player new value
     */
    public void setPlayer(JacksonPlayer player) {
        this.player = player;
    }
}
