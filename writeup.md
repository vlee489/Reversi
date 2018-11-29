# Writeup

## Notes to Marker!!!
#### Note 1
This program required the following packages/libraries from Maven to be able to be built from source
```
com.google.code.gson:gson:2.8.5
com.google.guava:guava:18.0
```
These libraries are used for the saving and loading functions of the game.

#### Note 2
This write up and the accompanying updated design documentation are written for the CMD version of Reversi as discussed with Iain Martian previously.

## self-evaluation

The main challenge that I had when creating the program was the algorithm for checking what spots are valid for the player when it's their turn. To do this for the player who's turn it is I have a method that checks each empty space on the board for if it has one of the opponent's counters present in the 8 directions around it. If it does it increments a counter and when this is done for each of the 8 directions then if the counter is greater then 0 we know that one of the conditions of that locations being a valid move is meet, then this fires off to another method that then checks if there's one of the players counters after a set of the opponent's counters that can create a valid flip. To do this I check if the direction has a opponent's counter beside the empty spot that's being checked and then keeps incrementing till if finds it's own counter that can create a valid flip. After this then I add the move if it's valid to an arrayList that stores the valid moves for the player.

Flipping counters isn't much harder, with it really being a variation of the method that checks if the spot has valid counters to flip, however instead of returning if there are counters to be flipped in a direction, it will flip counters. This is done by checking if the direction has counters to flip, if it does then we go back to the counter that we're flipping from and flip the opponent's counters until we hit our own counters again. This happens for the 8 directions. 