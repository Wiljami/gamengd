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
    public PlayerDisplay(UIController uiController, Player player) {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));
        uiController.addUIListener(this);
        name = new Text("");
        hitPoints = new Text("0");
        attack = new Text("0");
        defence = new Text("0");
        receivePlayerData(player);

        Text nameText = new Text("Name:");
        nameText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Text hitPointsText = new Text("Hit points:");
        hitPointsText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Text attackText = new Text("Attack:");
        attackText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Text defenceText = new Text("Defence:");
        defenceText.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        add(nameText, 0, 0);
        add(hitPointsText, 0, 1);
        add(attackText, 0, 2);
        add(defenceText, 0, 3);
        add(name, 1, 0);
        add(hitPoints, 1, 1);
        add(attack, 1, 2);
        add(defence, 1, 3);
    }

    @Override
    public void receivePlayerData(Player player) {
        name.setText(player.getName());
        hitPoints.setText(Integer.toString(player.getHitPoints()));
        attack.setText(Integer.toString(player.getAttack()));
        defence.setText(Integer.toString(player.getDefense()));
    }
}
