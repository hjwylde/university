// This file is part of the Multi-player Pacman Game.
//
// Pacman is free software; you can redistribute it and/or modify it under the terms of the GNU
// General Public License as published by the Free Software Foundation; either version 3 of the
// License, or (at your option) any later version.
//
// Pacman is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
// the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
// Public License for more details.
//
// You should have received a copy of the GNU General Public License along with Pacman. If not, see
// <http://www.gnu.org/licenses/>
//
// Copyright 2010, David James Pearce.

package com.hjwylde.uni.swen221.assignment8.pacman.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.hjwylde.uni.swen221.assignment8.pacman.game.Board;

/*
 * Code for Assignment 8, SWEN 221
 * Name: Henry J. Wylde
 * Usercode: wyldehenr
 * ID: 300224283
 */

/**
 * The player class simple relays keyboard events to a character proxy in the game. This is only
 * used in a single player game as, in a multi-player game, the slave does the job of this class.
 * 
 * @author djp
 */
public class Player implements KeyListener {
    
    private final Board game;
    private final int uid;
    
    public Player(int uid, Board game) {
        this.game = game;
        this.uid = uid;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if ((code == KeyEvent.VK_RIGHT) || (code == KeyEvent.VK_KP_RIGHT))
            game.player(uid).moveRight();
        else if ((code == KeyEvent.VK_LEFT) || (code == KeyEvent.VK_KP_LEFT))
            game.player(uid).moveLeft();
        else if (code == KeyEvent.VK_UP)
            game.player(uid).moveUp();
        else if (code == KeyEvent.VK_DOWN)
            game.player(uid).moveDown();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}