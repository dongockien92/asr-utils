package com.me.asrutils.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Action {
    DISABLE(0), BLOCK(1), AUDIT(2), WARN(6);

    @Getter
    private final int mode;

    public static Action parse(int mode) {
        for (Action action : Action.values()) {
            if (action.getMode() == mode) {
                return action;
            }
        }
        throw new IllegalArgumentException("Action mode is invalid");
    }
}
