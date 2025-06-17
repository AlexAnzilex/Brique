# Dev Notes – Tests and Behaviors to Implement

This file tracks test cases, design notes, and refactoring tasks for the Brique project.

---

## 🏁️ Cell / Board

- [x] Dimension of the board is correct
- [ ] Invalid moves are rejected (occupied cells and out-of-bounds).
- [ ] Board updates correctly after each move.

---

## 🕹️ Player

- [x] Two players can't have the same name, but if they do, they are the same player
- [ ] Black always starts the game.
- [ ] Turn alternates correctly between players.
- [ ] Pie rule available to White only on turn 2.
- [ ] Tracks current player and move history.

---

## 🧩 Escorts
 
- [ ] Placing a stone should trigger escort rule.
- [ ] Escort rule should remove enemy stones from the target cell and place one over it.
- [ ] Squares with 0 or 1 escort are unaffected.
- [ ] A placed stone should not trigger chain reactions incorrectly.

---

## 🥧 Pie rule

- [ ] Pie rule is correctly implemented (White can swap on turn 2).
- [ ] Pie rule is disabled after turn 2.

---

## ⭐ Victory Conditions

- [ ] Player wins by connecting two opposite edges of their color.
- [ ] Victory must be detected immediately after the move.
- [ ] Game ends when a player wins.
- [ ] Draws are not possible.

---


## 🔁 Refactoring 

- [ ] Improve class names and method readability.
