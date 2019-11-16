package fi.tuni.tiko.gamengd;

public class Entity {
    private Sprite sprite;
    public Entity (Sprite sprite) {
        setSprite(sprite);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}