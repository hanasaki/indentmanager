/*
  Copyright 2019 Craig A. Stockton
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.softwareonpurpose.indentmanager;

/**
 * Used to manage the level of indentation (2 spaces per level by default), with clients incrementing/decrementing
 * the level
 */
@SuppressWarnings("WeakerAccess")
public class IndentManager {

    private final static int DEFAULT_LEVEL = 2;
    private final int spacesPerLevel;
    private int indentationLevel;

    private IndentManager(int spacesPerLevel) {
        this.spacesPerLevel = spacesPerLevel;
    }

    /**
     * Return a new instance of IndentManager with default number of spaces per level
     *
     * @return Instance of IndentManger
     */
    @SuppressWarnings("WeakerAccess")
    public static IndentManager getInstance() {
        return new IndentManager(DEFAULT_LEVEL);
    }

    /**
     * Return a new instance of IndentManager with specified number of spaces per level.
     * If argument is below zero, number of spaces per level will be set to zero.
     *
     * @param spacesPerLevel Number of spaces to indent per level of indentation
     * @return Instance of IndentManager
     */
    @SuppressWarnings("WeakerAccess")
    public static IndentManager getInstance(int spacesPerLevel) {
        return new IndentManager(spacesPerLevel < 0 ? 0 : spacesPerLevel);
    }

    /**
     * Increment indentation level by one
     */
    @SuppressWarnings("WeakerAccess")
    public void increment() {
        increment(1);
    }

    /**
     * Increment indentation by a specific number of levels
     *
     * @param levels Number of levels to increment the current level
     */
    @SuppressWarnings("WeakerAccess")
    public void increment(int levels) {
        indentationLevel += levels;
    }

    /**
     * Decrement indentation level by one
     */
    @SuppressWarnings("WeakerAccess")
    public void decrement() {
        decrement(1);
    }

    /**
     * Decrement indentation by a specific number of levels
     *
     * @param levels Number of levels to decrement the current level
     */
    @SuppressWarnings("WeakerAccess")
    public void decrement(int levels) {
        if (indentationLevel < levels) {
            indentationLevel = 0;
        } else {
            indentationLevel -= levels;
        }
    }

    /**
     * @return Whether IndentManager is at root level
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isAtRootLevel() {
        return indentationLevel == 0;
    }

    /**
     * Format a line, padded with current indentation
     *
     * @param line The line of text to be formatted
     * @return Formatted String value
     */
    @SuppressWarnings("WeakerAccess")
    public String format(String line) {
        boolean lineIsNullOrEmpty = ((line == null) || (line.length() == 0));
        return lineIsNullOrEmpty ? "" : String.format("%s%s", getIndentation(), line);
    }

    private String getIndentation() {
        return new String(new char[spacesPerLevel * indentationLevel]).replace('\0', ' ');
    }
}
