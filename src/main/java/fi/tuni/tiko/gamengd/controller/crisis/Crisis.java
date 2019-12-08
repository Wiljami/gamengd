package fi.tuni.tiko.gamengd.controller.crisis;

/**
 * Crisis class is an information package for CrisisController.
 *
 * Crisis object holds information of an event, or in this called crisis.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class Crisis {
    /**
     * Chance in percentage.
     */
    private double chance;
    /**
     * Cooldown in turns. 0 means no cooldown
     */
    private int coolDown;
    /**
     * For identification by the crisisSource
     */
    private String id;
    /**
     * Source of this crisis
     */
    private CrisisSource source;

    /**
     * Crisis constructor
     * @param chance chance
     * @param coolDown cooldown
     * @param id id
     * @param source source
     */
    public Crisis(double chance, int coolDown, String id, CrisisSource source) {
        setChance(chance);
        setCoolDown(coolDown);
        setId(id);
        setSource(source);
    }

    /**
     * chance getter
     * @return chance
     */
    public double getChance() {
        return chance;
    }

    /**
     * chance setter.
     *
     * Clamps the value between 0 and 1.
     * @param chance new chance value
     */
    public void setChance(double chance) {
        if (chance < 0) chance = 0;
        if (chance > 1) chance = 1;
        this.chance = chance;
    }

    /**
     * coolDown getter
     * @return coolDown
     */
    public int getCoolDown() {
        return coolDown;
    }

    /**
     * coolDown setter
     *
     * Sets coolDown to 0 if it's negative.
     * @param coolDown coolDown
     */
    public void setCoolDown(int coolDown) {
        if (coolDown < 0) coolDown = 0;
        this.coolDown = coolDown;
    }

    /**
     * source getter
     * @return source
     */
    public CrisisSource getSource() {
        return source;
    }

    /**
     * source setter
     * @param source CrisiSource
     */
    public void setSource(CrisisSource source) {
        this.source = source;
    }

    /**
     * id getter
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * id setter
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Trigger runs the the source's runCrisis method which it has implemented
     * from CrisisSource interface.
     */
    public void trigger() {
        source.runCrisis(this);
    }
}
