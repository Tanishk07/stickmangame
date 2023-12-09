package org.example;

import javafx.animation.Timeline;
import javafx.scene.layout.Region;

public abstract class CharacterAnimationBase extends Region implements CharacterAnimation {
    // Common fields and methods can be placed here

    @Override
    public abstract Timeline createImageAnimation();
}