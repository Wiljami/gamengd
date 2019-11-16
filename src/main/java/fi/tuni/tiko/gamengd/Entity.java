package fi.tuni.tiko.gamengd;

public class Entity {
    private Sprite sprite;
    private int x;
    private int y;

    public Entity (Sprite sprite, int x, int y) {
        setSprite(sprite);
        setXY(x, y);
    }

    public Entity (Sprite sprite) {
        this(sprite, 0, 0);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        setXY(x, getY());
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        setXY(getX(), y);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void move(int x, int y) {
        setXY(getX() + x, getY() + y);
    }
}