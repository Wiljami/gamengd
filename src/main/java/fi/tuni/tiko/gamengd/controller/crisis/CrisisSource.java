package fi.tuni.tiko.gamengd.controller.crisis;

/**
 * CrisisSource is an interface for objects to become sources of Crisis.
 *
 * CrisisSource is needed to be able to trigger crisises properly. It lets the
 * Crisis know its source it has triggererd and the source can act accordingly.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public interface CrisisSource {
    /**
     * runCrisis is run when the Crisis is triggered.
     * @param crisis Crisis that was triggered.
     */
    void runCrisis(Crisis crisis);
}
