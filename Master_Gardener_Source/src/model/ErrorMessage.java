// Pygmy Marmoset - an assignment submission webapp for CS courses
// Copyright (c) 2017, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This is free software distributed under the terms of the
// GNU Affero Public License v3 or later.  See LICENSE.txt for details.

/*
Taken directly from David Hovemeyer's Pymgy Marmoset github with the intent to implement filters.
https://github.com/daveho/PygmyMarmoset/blob/master/PygmyMarmosetModelClasses/src/edu/ycp/cs/pygmymarmoset/app/model/ErrorMessage.java
 */

package model;

public class ErrorMessage {
    private String text;

    public ErrorMessage() {

    }

    public ErrorMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}