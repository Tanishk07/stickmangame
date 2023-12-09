package org.example;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface CharacterAnimation {
    Timeline createImageAnimation();
    void createRunAnimation(double xEnd, double gap, double stickLength,Timeline backgroundTimeline, EventHandler<ActionEvent> onAnimationComplete,EventHandler<ActionEvent> onEvent,CherryAndBee cb);
}