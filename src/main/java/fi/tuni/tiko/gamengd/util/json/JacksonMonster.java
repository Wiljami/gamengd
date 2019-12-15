package fi.tuni.tiko.gamengd.util.json;

import fi.tuni.tiko.gamengd.entity.Monster;

/**
 * JacksonMonster is json data read to an object.
 *
 * JacksonMonster contains information for a monster. The data is read from
 * a json file and cast to this JacksonMonster using Jackson.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class JacksonMonster {
    /**
     * JacksonMonster id.
     */
    private String id;
    /**
     * JacksonMonster name.
     */
    private String name;
    /**
     * JacksonMonster graphic.
     */
    private String graphic;
    /**
     * JacksonMonster attack.
     */
    private int attack;
    /**
     * JacksonMonster defense.
     */
    private int defense;
    /**
     * JacksonMonster hitPoints.
     */
    private int hitPoints;

    private Monster.Behavior behavior;

    /**
     * JacksonMonster constructor.
     */
    public JacksonMonster() {
    }

    /**
     * setter for id.
     * @param id new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter for id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for attack.
     * @return attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * setter for attack.
     * @param attack new attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * getter for defense.
     * @return defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * setter for defense.
     * @param defense new defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * getter for hitPoints.
     * @return hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * setter for hitPoints.
     * @param hitPoints new hitPoints
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * getter for graphic.
     * @return graphic
     */
    public String getGraphic() {
        return graphic;
    }

    /**
     * setter for graphic.
     * @param graphic new graphic
     */
    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public Monster.Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Monster.Behavior behavior) {
        this.behavior = behavior;
    }
}