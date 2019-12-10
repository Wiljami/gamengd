package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.GameMechanic;
import fi.tuni.tiko.gamengd.util.json.JSONLoader;
import fi.tuni.tiko.gamengd.util.Util;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.scripts.pathfinding.AStar;
import fi.tuni.tiko.gamengd.util.json.JacksonMonster;

import java.io.File;
import java.util.HashMap;

public class Monster extends Unit {
    private static HashMap<String, Monster> monsterProtoTypes;
    private static final String MONSTERFOLDER = "monsters/";

    private String id;
    private transient AStar pathfind;

    public static void setup() {
        monsterProtoTypes = new HashMap<>();
        File[] monsterFiles = Util.walkFolder(MONSTERFOLDER);
        for (File f : monsterFiles) {
            Monster monster = JSONLoader.loadMonster(f);
            if (monsterProtoTypes.containsKey(monster.getId())) {
                System.out.println("Monster.setup()::Duplicate MonsterID: + " + monster.getId());
            }
            monsterProtoTypes.put(monster.getId(), monster);
        }
    }

    public Monster(JacksonMonster jm) {
        super(new Sprite(ImageLoader.loadImage(jm.getGraphic())));
        setId(jm.getId());
        setName(jm.getName());
        setAttack(jm.getAttack());
        setDefense(jm.getDefense());
        setHitPoints(jm.getHitPoints());
    }

    public Monster(Monster protoMonster) {
        super(new Sprite(protoMonster.getSprite().getImage()));
        setId(protoMonster.getId());
        setName(protoMonster.getName());
        setAttack(protoMonster.getAttack());
        setDefense(protoMonster.getDefense());
        setHitPoints(protoMonster.getHitPoints());
    }

    public static Monster spawn(String id, int x, int y, Level level) {
        Monster protoMonster = monsterProtoTypes.get(id);
        Monster monster = new Monster(protoMonster);
        monster.setLevel(level);
        monster.setXY(x, y);
        return monster;
    }

    public static Monster spawn(String id, Tile tile, Level level) {
        return spawn(id, tile.getX(), tile.getY(), level);
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        if (checkForPlayerAdjacency()) {
            System.out.println("PUNCH PLAYER");
            System.out.println(GameMechanic.randomRoll());
        } else if (pathfind != null) {
            movePathFinding();
        } else {
            chasePlayer();
        }
        super.doTurn(turnInfo);
    }

    private boolean checkForPlayerAdjacency () {
        Tile[] neighbours = level.getTileAt(getX(), getY()).getNeighbours();
        for (Tile t : neighbours) {
            if (t == level.getPlayer().getTile()) return true;
        }
        return false;
    }

    private void movePathFinding() {
        Tile nextStep = pathfind.getStep();
        if (nextStep == null) {
            pathfind = null;
            randomMove();
        } else {
            move(nextStep);
        }
    }

    private void chasePlayer() {
        Tile endTile = level.getPlayer().getTile();
        createNewPath(endTile);
    }

    public void createNewPath(Tile endTile) {
        Tile startTile = level.getTileAt(getX(), getY());
        if (pathfind == null) pathfind = new AStar(level, startTile, endTile);
    }

    @Override
    void move(Tile tile) {
        if (!tile.hasUnit()) {
            super.move(tile);
        } else {
            if (randomMoveCheck()) randomMove();
        }
    }

    private boolean randomMoveCheck() {
        Tile[] neighbours = level.getTileAt(getX(), getY()).getNeighbours();
        boolean turnDoable = false;
        for(Tile t : neighbours) {
            if (t.isEnterable()) {
                turnDoable = true;
            }
        }
        return  turnDoable;
    }

    private void randomMove() {
        int x = (int)(Math.random()*3) - 1;
        int y = (int)(Math.random()*3) - 1;
        while (!level.getTileAt(getX()+x, getY()+y).isPassable()) {
            x = (int)(Math.random()*3) - 1;
            y = (int)(Math.random()*3) - 1;
        }
        move(x, y);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
