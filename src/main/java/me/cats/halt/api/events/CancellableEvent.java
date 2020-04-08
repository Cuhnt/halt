package me.cats.halt.api.events;

public class CancellableEvent {

    /**
     * is the event cancelled?
     */
    public boolean canceled;

    public CancellableEvent() {
    }

    public CancellableEvent(boolean canceled) {
        this.canceled = canceled;
    }
}