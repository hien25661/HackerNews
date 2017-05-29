package com.hackernew.helper.event;

import com.hackernew.model.Story;

/**
 * Created by Hien on 5/30/2017.
 */

public class ChooseStoryEvent {
    private Story story;

    public ChooseStoryEvent(Story story) {
        this.story = story;
    }

    public Story getStory() {
        return story;
    }
}
