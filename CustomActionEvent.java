package org.example;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;

public class CustomActionEvent extends ActionEvent {
    private final int userData;

    public CustomActionEvent(Object source, EventTarget target, int userData) {

        super(source, target);
        this.userData = userData;
    }

    public int getUserData() {
        return userData;
    }
}