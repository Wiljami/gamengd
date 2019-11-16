package fi.tuni.tiko.gamengd;

public class Entity {
    Sprite sprite;
    public Entity (Sprite sprite) {
        setSprite(sprite);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}