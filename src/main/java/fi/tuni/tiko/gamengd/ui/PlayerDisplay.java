package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.controller.ui.UIListener;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PlayerDisplay extends GridPane implements UIListener {
    private Text name;
    private Text hitPoints;
    private Text attack;
    private Text defence;
    private Player player;
    public PlayerDisplay(Player player) {
        this.player = player;

        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));
        setupLabels();
        setupChangingTexts();

        triggerUIListener();
    }

    private void setupChangingTexts() {
        Font font = Font.font("Arial", 12);

        name = new Text("");
        name.setFont(font);
        hitPoints = new Text("0");
        hitPoints.setFont(font);
        attack = new Text("0");
        attack.setFont(font);
        defence = new Text("0");
        defence.setFont(font);

        add(name, 1, 0);
        add(hitPoints, 1, 1);
        add(attack, 1, 2);
        add(defence, 1, 3);
    }

    private void setupLabels() {
        Font font = Font.font("Arial", FontWeight.BOLD, 12);

        Text nameText = new Text("Name:");
        nameText.setFont(font);
        Text hitPointsText = new Text("Hit points:");
        hitPointsText.setFont(font);
        Text attackText = new Text("Attack:");
        attackText.setFont(font);
        Text defenceText = new Text("Defence:");
        defenceText.setFont(font);

        add(nameText, 0, 0);
        add(hitPointsText, 0, 1);
        add(attackText, 0, 2);
        add(defenceText, 0, 3);
    }

    @Override
    public void triggerUIListener() {
        name.setText(player.getName());
        hitPoints.setText(Integer.toString(player.getHitPoints()));
        attack.setText(Integer.toString(player.getAttack()));
        defence.setText(Integer.toString(player.getDefense()));
    }
}
