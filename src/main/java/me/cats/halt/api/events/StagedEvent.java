package me.cats.halt.api.events;

public class StagedEvent {

    /**
     * the stage of the event
     */
    public Stage stage;

    public StagedEvent() {
    }

    public StagedEvent(Stage stage) {
        this.stage = stage;
    }

    public enum Stage {
        EARLY,
        LATE
    }
}
