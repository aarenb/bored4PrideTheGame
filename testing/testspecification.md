# Testspecification

## **Test case 1: Move player character.**

Issues: #1, #2

**Test steps:**

- Start the app
- Start new game
- Press key W/A/S/D or up arrow/left arrow/down arrow/right arrow, once at a time

**Expected:**

- W/up arrow key = player character moves up
- A/left arrow key = player character moves left
- S/down arrow key = player character moves down
- D/right arrow key = player character moves right
- As the player character is moving, walking animation is visible

## **Test case 2.1: Game map**

Issues: #3, #19; #12, #16, #14, #31

**Test steps:**

- Start the app
- Start new game
- Walk around in the game

**Expected:**

- Game map tiles are visible
- The camera follows the player character as it moves, player character stays in center of game window at all times

## **Test case 2.2: Solid tiles**

Issues: #3

**Test steps:**

- Start the app
- Start new game
- Walk up to and continue going towards brick wall tile as close as possible, try this from as many angles as possible

**Expected:**

- Player stops moving forwards when walking against and touching the brick wall tile

## **Test case 2.3: More solid tiles**

Issues: #3, #12, #16

**Test steps:**

- Start the app
- Start new game
- Walk up to and continue going towards the tile as close as possible, try this from as many angles as possible

Solid tiles to test:

- Brick wall
- Tree
- Flags (not the pole)
- Fence
- Among us statues
- Quiplash character statues
- Jackbox screen

## **Test case 2.4: Even more solid tiles**

Issues: #3, #12, #16, #14

**Test steps:**

* Start the app
* Start new game
* Walk up to and continue going towards the tile as close as possible, try this from as many angles as possible

Solid tiles to test:

* Brick wall
* Tree
* Flags (not the pole)
* Fence
* Among us statues
* Quiplash character statues
* Jackbox screen
* Minecraft grass block
* Minecraft items

## **Test case 3.1: Talk to NPC**

Issues: #4, #43, #40

**Test steps:**

- Start the app
- Start new game
- Walk up to the NPC and press the enter key
- Press enter key again

**Expected:**

- Dialogue window is shown on screen when enter key is first pressed
- Dialogue window dissapears second time enter key is pressed

## **Test case 3.1.1: Updated talk to NPC**

Issues: #4, #43, #40, #13, #17

**Test steps:**

- Start the app
- Start new game
- Walk up to the NPC and press the space bar
- Press space bar again

**Expected:**

- Dialogue window is shown on screen when space bar is first pressed
- Dialogue window disappears second time space bar is pressed

## **Test case 3.1.2: More updated talk to NPC**

Issues: [#4](#), [#43](#), [#40](#), [#13](#), [#17, #15, #35](#)

**Test steps:**

* Start the app
* Start new game
* Walk up to the NPC and press the space bar
* Press space bar again (repeat several times, until the dialogue window disappears from the screen)

**Expected:**

* Dialogue window is shown on screen when space bar is first pressed
* Text inside of the dialogue window is updated when the space bar is pressed
* Eventually, the dialogue window disappears when the space bar is pressed (and there's no more dialogue left)

## **Test case 3.2: NPC collision**

Issues: #4, #33, #17, #18, #15

**Test steps:**

- Start the app
- Start new game
- Walk up to the NPC, try this from as many angles as possible

**Expected:**

- If player stands in the way of NPC's walking, he stops
- If player tries to walk into NPC, player stops

## **Test case 4.1: Title screen**

Issues: #5, #18, #22

**Test steps:**

- Start the app
- Press W/S or up arrow/down arrow key, once at a time

**Expected:**

- The title screen menu is visible
- BoredChaps icon is shown in top left corner of game window (if not in full screen)
- W/up arrow key = selection icon moves up, if on top selection already: selection icon moves to bottom
- S/down arrow key = selection icon moves down, if on bottom selection already: selection icon moves to top

## **Test case 4.2: New game**

Issues: #5

**Test steps:**

- Start the app
- Click enter key

**Expected:**

- Game mode is entered
- Player character and map is shown on screen

## **Test case 4.2.1: Updated new game**

Issues: #5, #54

**Test steps:**

- Start the app
- Click space bar

**Expected:**

- Game mode is entered
- Player character and map is shown on screen

## **Test case 4.3: Quit**

Issues: #5

**Test steps:**

- Start the app
- Move the selection icon to “Quit”
- Click enter key

**Expected:**

- The app is closed

## **Test case 4.3.1: Updated quit**

Issues: #5, #54

**Test steps:**

- Start the app
- Move the selection icon to “Quit”
- Click space bar

**Expected:**

- The app is closed

## **Test case 4.4: Load game**

Issues: #5

**Test steps:**

- Start the app
- Move the selection icon to “Load game”
- Click space bar

**Expected:**

- "Load game feature coming soon" text appears on screen

## **Test case 4.4.1: Updated load game**

Issues: [#5, #7](#)

**Test steps:**

* Test case 5.7
* Move the selection icon to “Load game”
* Click space bar

**Expected:**

* The game opens at the same place where it was in test case 5.7

## **Test case 4.4.2: More updated load game**

Issues: [#5, #7](#)

**Test steps:**

* Test case 5.7.1
* Move the selection icon to “Load game”
* Click enter key

**Expected:**

* The game opens at the same place where it was in test case 5.7

## **Test case 4.5: Controls**

Issues: #5, #32

**Test steps:**

- Start the app
- Move the selection icon to “Controls”
- Click space bar

**Expected:**

- Controls info screen is shown

## **Test case 4.5.1: Updated controls**

Issues: [#5](#), [#32](#)

**Test steps:**

* Start the app
* Move the selection icon to “Controls”
* Click enter key

**Expected:**

* Controls info screen is shown

## **Test case 5.1: Pause**

Issues: #6

**Test steps:**

- Start the app
- Start new game
- Click escape key

**Expected:**

- The game window goes a bit darker
- “PAUSED” text is displayed
- All entities on the screen stops moving

## **Test case 5.1.1: Updated pause**

Issues: #6

**Test steps:**

- Test case 5.1

**Expected:**

- The game window goes a bit darker
- “PAUSED” text is displayed
- "Settings" text is displayed, with selection icon
- "Title screen" text is displayed
- All entities on the screen stops moving

## **Test case 5.1.2: More updated pause**

Issues: #6

**Test steps:**

- Test case 5.1

**Expected:**

- The game window goes a bit darker
- “PAUSED” text is displayed
- "Settings" text is displayed, with selection icon
- "Save & quit" text is displayed
- "Quit" text is displayed
- All entities on the screen stops moving

## **Test case 5.2: Unpause**

Issues: #6

**Test steps:**

- Test case 5.1
- Click escape key

**Expected:**

- The pause menu screen disappears
- Entities start moving again

## **Test case 5.3: Settings**

Issues: #6, #24

**Test steps:**

- Test case 5.1.1
- Make sure selection icon is on "settings"
- Click space bar

**Expected:**

- Settings menu appears on screen

## **Test case 5.3.1: Updated settings**

Issues: [#6](#), [#24](#)

**Test steps:**

* Test case 5.1.1
* Make sure selection icon is on "settings"
* Click enter key

**Expected:**

* Settings menu appears on screen

## **Test case 5.4: Full screen**

Issues: #24, #28

**Test steps:**

- Test case 5.3
- Click space bar
- Restart app

**Expected:**

- When clicking space bar, an x appears/disappears from full screen checkbox
- "Restart the game to implement change" text appears on screen

After restart:

- If checkbox is checked: Game is in full screenw
- If checkbox is not checked: Game isn't in full screen

## **Test case 5.4.1: Updated full screen**

Issues: [#24](#), [#28](#)

**Test steps:**

* Test case 5.3.1
* Click enter key
* Restart app

**Expected:**

* When clicking enter key, an x appears/disappears from full screen checkbox
* "Restart the game to implement change" text appears on screen

After restart:

* If checkbox is checked: Game is in full screenw
* If checkbox is not checked: Game isn't in full screen

## **Test case 5.5: Music volume**

Issues: #24, #23

**Test steps:**

- Test case 5.3
- Move selection icon to "music"
- Press A/D key or left/right arrow key

**Expected:**

- A/left arrow = bar next to music goes down, and so does music volume
- D/right arrow = bar next to music goes up, and so does music volume
- If bar is empty, there is no music playing

## **Test case 5.5.1: Updated music volume**

Issues: [#24](#), [#23](#)

**Test steps:**

* Test case 5.3.1
* Move selection icon to "music"
* Press A/D key or left/right arrow key

**Expected:**

* A/left arrow = bar next to music goes down, and so does music volume
* D/right arrow = bar next to music goes up, and so does music volume
* If bar is empty, there is no music playing

## **Test case 5.6: Sound effects volume**

Issues: #24, #20

**Test steps:**

- Test case 5.3
- Move selection icon to "sound effects"
- Press A/D key or left/right arrow key

**Expected:**

- A/left arrow = bar next to sound effects goes down, and so does sound effects volume
- D/right arrow = bar next to sound effects goes up, and so does sound effects volume
- If bar is empty, you can't hear any sound effects

## **Test case 5.6.1: Updated sound effects volume**

Issues: [#24](#), [#20](#)

**Test steps:**

* Test case 5.3.1
* Move selection icon to "sound effects"
* Press A/D key or left/right arrow key

**Expected:**

* A/left arrow = bar next to sound effects goes down, and so does sound effects volume
* D/right arrow = bar next to sound effects goes up, and so does sound effects volume
* If bar is empty, you can't hear any sound effects

## **Test case 5.7: Save & quit**

Issues: #6, #5, #7

**Test steps:**

* Walk around a little bit in game/pick up items/just generally play
* Test case 5.1.1
* Make sure selection icon is on "Save & quit"
* Click space bar

**Expected:**

* Title screen is displayed
* Background music changes to the one of the title screen

## **Test case 5.7.1: Updated save & quit**

Issues: [#6](#), [#5](#), [#7](#)

**Test steps:**

* Walk around a little bit in game/pick up items/just generally play
* Test case 5.1.2
* Make sure selection icon is on "Save & quit"
* Click enter key

**Expected:**

* Title screen is displayed
* Background music changes to the one of the title screen

## **Test case 5.8: Quit**

Issues: [#6](#), [#5](#)

**Test steps:**

* Test case 5.1.1
* Make sure selection icon is on "Quit"
* Click space bar

**Expected:**

* Title screen is displayed
* Background music changes to the one of the title screen

## **Test case 5.8.1: Updated quit**

Issues: [#6](#), [#5](#)

**Test steps:**

* Test case 5.1.2
* Make sure selection icon is on "Quit"
* Click enter key

**Expected:**

* Title screen is displayed
* Background music changes to the one of the title screen

## **Test case 6.1: Player health bar**

Issues: #21

**Test steps:**

- Start the app
- Start new game

**Expected:**

- 3 full hearts are displayed in top left corner

## **Test case 6.1.1: Updated player health bar**

Issues: #21, #49

**Test steps:**

- Start the app
- Start new game

**Expected:**

- 5 full hearts are displayed in top left corner

## **Test case 6.2: Player take damage**

Issues: #21, #11, #39

**Test steps:**

- Start the app
- Start new game
- Walk into a follow bot/have follow bot walk into player character, try from as many angles as possible

**Expected:**

- Everytime player character and follow bot touches, half a heart dissapears from player health bar
- After damage has been taken, health bar gets a bit see through
- While health bar is see through, player can’t take any damage

## **Test case 6.2.1: Updated player take damage**

Issues: #21, #11, #39

**Test steps:**

- Start the app
- Start new game
- Walk into a follow bot/have follow bot walk into player character, try from as many angles as possible

**Expected:**

- Everytime player character and follow bot touches, half a heart disappears from player health bar
- "Oof" sound effect plays
- After damage has been taken, health bar gets a bit see through
- While health bar is see through, player can’t take any damage

## **Test case 7.1: Game over**

Issues: #25

**Test steps:**

- Start the app
- Start new game
- Make player take damage until dead (Test case 6.2)
- Press W/S or up arrow/down arrow key, once at a time

**Expected:**

- Game over screen is displayed
- W/up arrow key = selection icon moves up, if on top selection already: selection icon moves to bottom
- S/down arrow key = selection icon moves down, if on bottom selection already: selection icon moves to top

## **Test case 7.1.1: Updated game over**

Issues: #25, #20, #23

**Test steps:**

- Start the app
- Start new game
- Make player take damage until dead (Test case 6.2)
- Press W/S or up arrow/down arrow key, once at a time

**Expected:**

- Game over screen is displayed
- Background music stops playing
- Sad tune sound effect plays
- W/up arrow key = selection icon moves up, if on top selection already: selection icon moves to bottom
- S/down arrow key = selection icon moves down, if on bottom selection already: selection icon moves to top

## **Test case 7.2: Try again**

Issues: #25

**Test steps:**

- Test case 7.1
- Make sure selection icon is on “Try again”
- Press enter key

**Expected:**

- The game starts again, from the beginning. Everything is reset

## **Test case 7.2.1: Updated try again**

Issues: #25, #54, #46, #23

**Test steps:**

- Test case 7.1
- Make sure selection icon is on “Try again”
- Press space bar

**Expected:**

- The game starts again, from the beginning. Everything is reset
- Background music starts from the beginning, if it was rick roll it is now back to default

## **Test case 7.3: Back to title screen**

Issues: #25, #5

**Test steps:**

- Test case 7.1
- Make sure selection icon is on “Title screen”
- Press enter key

**Expected:**

- Title screen is displayed

## **Test case 7.3.1: Updated back to title screen**

Issues: #25, #5, #54, #23

**Test steps:**

- Test case 7.1
- Make sure selection icon is on “Title screen”
- Press space bar

**Expected:**

- Title screen is displayed
- Background music changes to the one of the title screen

## **Test case 7.4: Win**

Issues: #52

**Test steps:**

- Test case 9.3, repeat until all follow bots are dead

**Expected:**

- Win screen appears

## **Test case 8.1: Pick up sword**

Issues: #8

**Test steps:**

- Start the app
- Start new game
- Walk up to the sword item and touch it

**Expected:**

- Sword item disapears from ground
- Text "You picked up a mod sword!" appears on screen

## **Test case 8.2: Attack with picked up sword**

Issues: #8

**Test steps:**

- Test case 8.1
- Press enter key while walking in any direction

**Expected:**

- Player character does a sword animation

## **Test case 8.2.1: Updated attack with picked up sword**

Issues: #8, #54

**Test steps:**

- Test case 8.1
- Press space bar while not touching an NPC

**Expected:**

- Player character does a sword animation

## **Test case 8.3: Attack without picked up sword**

Issues: #8

**Test steps:**

- Start the app
- Start new game
- Press enter key while walking in any direction

**Expected:**

- Nothing happens, except for the player character walking

## **Test case 8.3.1: Updated attack without picked up sword**

Issues: #8, #54

**Test steps:**

- Start the app
- Start new game
- Press space bar while not touching an NPC

**Expected:**

- Nothing happens, except for the player character walking

## **Test case 8.4: Attack follow bot**

Issues: #8, #11

**Test steps:**

- Test case 8.1
- Walk up to a follow bot
- Test case 8.2, while near the follow bot (so that it touches it with sword), try this from as many angles as possible

**Expected:**

- Follow bot takes damage
- Follow bot’s health bar goes down
- Follow bot becomes a bit see through for a while, and during this cannot take any damage

## **Test case 8.4.1: Updated attack follow bot**

Issues: #8, #11

**Test steps:**

- Test case 8.1
- Walk up to a follow bot
- Test case 8.2.1, while near the follow bot (so that it touches it with sword), try this from as many angles as possible

**Expected:**

- Follow bot takes damage
- Follow bot’s health bar goes down
- Follow bot becomes a bit see through for a while, and during this cannot take any damage
- Follow bot turns towards player

## **Test case 8.4.2: More updated attack follow bot**

Issues: [#8](#), [#11, #55](#)

**Test steps:**

* Test case 8.1
* Walk up to a follow bot
* Test case 8.2.1, while near the follow bot (so that it touches it with sword), try this from as many angles as possible

**Expected:**

* Follow bot is pushed back a little bit (knockback)
* Follow bot’s health bar goes down
* Follow bot becomes a bit see through for a while, and during this cannot take any damage
* Follow bot turns towards player

## **Test case 9.1: Bit counter**

Issues: #45

**Test steps:**

- Start the app
- Start new game

**Expected:**

- A bit counter is displayed in top right corner
- The bit counter is at 0

## **Test case 9.2: Pick up bit**

Issues: #45

**Test steps:**

- Start the app
- Start new game
- Walk towards, and touch a bit

**Expected:**

- Bit disappears from ground
- Text "You picked up a bit!" is displayed on screen
- The bit counter is at 1

## **Test case 9.3: Kill follow bot**

Issues: #11, #45

**Test steps:**

- Test case 8.4
- Continue hitting follow bot until follow both health is at zero

**Expected:**

- Follow bot starts blinking
- Follow bot then disappears from screen
- A bit appears on the ground where the follow bot previously was

## **Test case 9.4: Updated kill follow bot**

Issues: #11, #45, #48

**Test steps:**

- Test case 9.3

**Expected:**

- Follow bot starts blinking
- Follow bot then disappears from screen
- A bit or heart appears on the ground where the follow bot previously was

## **Test case 9.5: Pick up heart**

Issues: #48

**Test steps:**

- Test case 9.4, repeat until the follow bot drops a heart
- Walk towards, and touch the heart

**Expected:**

- Heart disappears from the ground
- Text "You picked up a heart!" is displayed on screen
- If player health is not full: player health increases by one heart (or half a heart if only half a heart is missing)
- If player health is already full: Player health stays the same

## **Test case 10: Rick roll**

Issues: #46, #23

**Test steps:**

- Start the app
- Start new game
- Find the rainbow bit in the forest
- Walk towards, and touch rainbow bit

**Expected:**

- Rainbow bit disappears from ground
- Text "You picked up a sussy bit!" is displayed on screen
- Regular background music stops playing, and instead changes to "Never gonna give you up" until a new game is started (it also won't play in the title screen)

## **Test case 11: Check Interact Follow Bot**

Issues: #11

- Automatic test checkInteractFollowBot() in PlayerTest.java
- Run with ./gradlew test