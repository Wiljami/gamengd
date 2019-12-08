package fi.tuni.tiko.gamengd.controller.crisis;

public class Crisis {
    private double chance;
    private int coolDown;
    private String id;
    private CrisisSource source;

    public Crisis(double chance, int coolDown, String id, CrisisSource source) {
        setChance(chance);
        setCoolDown(coolDown);
        setId(id);
        setSource(source);
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public CrisisSource getSource() {
        return source;
    }

    public void setSource(CrisisSource source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void trigger() {
        source.runCrisis(this);
    }
}
