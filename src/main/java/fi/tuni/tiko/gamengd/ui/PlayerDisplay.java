package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.controller.ui.UIListener;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * PlayerDisplay is an UI element displaying the player stats.
 *
 * It implements UIListener to be able to receive changes that happen in the
 * player stats and it extends GridPane as the base UI element.
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class PlayerDisplay extends GridPane implements UIListener {
    /**
     * Reference to the name Text field
     */
    private Text name;
    /**
     * Reference to the hitPoints Text field
     */
    private Text hitPoints;
    /**
     * Reference to the attack Text field
     */
    private Text attack;
    /**
     * Reference to the defence Text field
     */
    private Text defence;
    /**
     * Reference to the killCount Text field
     */
    private Text killCount;

    /**
     * PlayerDisplay constructor.
     * @param player player whose stats we display.
     */
    public PlayerDisplay(Player player) {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));
        setupLabels();
        setupChangingTexts();

        triggerUIListener(player);
    }

    /**
     * setupChangingTexts adds the changing Texts to the PlayerDisplay.
     */
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
        killCount = new Text("0");
        killCount.setFont(font);

        add(name, 1, 0);
        add(hitPoints, 1, 1);
        add(attack, 1, 2);
        add(defence, 1, 3);
        add(killCount, 1, 4);
    }

    /**
     * setupLabels adds the static texts to the PlayerDisplay.
     */
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
        Text killCountText = new Text("Kill count:");
        killCountText.setFont(font);

        add(nameText, 0, 0);
        add(hitPointsText, 0, 1);
        add(attackText, 0, 2);
        add(defenceText, 0, 3);
        add(killCountText, 0, 4);
    }

    /**
     * triggerUIListener is a method from UIListener interface.
     *
     * Upon being called this method updates the values within the changing
     * Texts in the PlayerDisplay.
     * @param source Player whose stats are displayed
     */
    @Override
    public void triggerUIListener(Player source) {
        name.setText(source.getName());
        String hitPointsText = source.getHitPoints() + "/" + source.getMaxHitPoints();
        hitPoints.setText(hitPointsText);
        attack.setText(Integer.toString(source.getAttack()));
        defence.setText(Integer.toString(source.getDefense()));
        killCount.setText(Integer.toString(source.getKillCount()));
    }
}
