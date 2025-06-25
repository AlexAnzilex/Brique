# Dev Notes – Tests and Behaviors to Implement

This file tracks test cases, design notes, and refactoring tasks for the Brique project.

---

## 🏁️ Cell / Board

- [x] Dimension of the board is correct
- [x] Invalid moves are rejected (occupied cells and out-of-bounds).
- [x] Board updates correctly after each move.

---

## 🕹️ Player

- [x] Two players can't have the same name.
- [x] Black always starts the game.
- [x] Turn alternates correctly between players.
- [x] Pie rule available to White only on turn 2.
- [x] Tracks current player.

---

## 🧩 Escorts
 
- [x] Placing a stone should trigger escort rule.
- [x] Escort rule should remove enemy stones from the target cell and place one over it.
- [x] Squares with 0 or 1 escort are unaffected.
- [x] A placed stone should not trigger chain reactions incorrectly.

---

## 🥧 Pie rule

- [x] Pie rule is correctly implemented (White can swap on turn 2).
- [x] Pie rule is disabled after turn 2.

---

## ⭐ Victory Conditions

- [x] Player wins by connecting two opposite edges of their color.
- [x] Victory must be detected immediately after the move.
- [x] Game ends when a player wins.
- [x] Draws are not possible.

---


## 🔁 Refactoring 

- [x] Improve class names and method readability.
