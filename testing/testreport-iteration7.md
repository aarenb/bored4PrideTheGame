# Testreport iteration 7

## User testing 
**Date:** May 25th 2023

**Version:** Alpha 1.1

**Type:** Explorative

**Testing environment:** Mac, using their own computer and running the .jar file

**Notes from test user:**
- The screen is black when game starts
- Sometimes after a bit the main menu graphics become visible, but the selection icon doesn't move
- The main menu does work, as if you move it up 1 after starting the game and the game quits as it should and if you just press space bar instantly when starting game the music changes as it enters game mode 

------

**Date:** May 25th 2023

**Version:** Alpha 1.4

**Type:** Explorative

**Testing environment:** Mac, using their own computer and running the .jar file

**Notes from test user:**
- The game works as it should when not in full screen
- Very fun to play!
- When put back into full screen, it goes back to black screen like before.

## Manual testing with test cases
**Date:** 29 May 2023

**Version:** Alpha 1.5

**Testing environment:** Windows 11, app run through .exe file

| Test | Result |
| --- | --- |
| TC1 | Pass |
| TC2.1 | Pass |
| TC2.4 | Pass |
| TC3.1.2 | Pass |
| TC3.2 | Pass |
| TC4.2.1 | Pass |
| TC4.3.1 | Pass |
| TC5.7 | Pass |
| TC4.4.1 | Pass |
| TC4.5 | Pass |
| TC5.1.2 | Pass |
| TC5.2 | Pass |
| TC5.3 | Pass |
| TC5.4 | Pass |
| TC5.5 | Pass |
| TC5.6 | Pass |
| TC5.8 | Pass |
| TC6.1.1 | Pass |
| TC6.2.1 | Fail - bug |
| TC7.1.1 | Pass |
| TC7.2.1 | Pass |
| TC7.3.1 | Pass |
| TC7.4 | Pass |
| TC8.1 | Pass |
| TC8.2.1 | Pass |
| TC8.3.1 | Pass |
| TC8.4.2 | Fail - bug |
| TC9.1 | Pass |
| TC9.2 | Pass |
| TC9.4 | Fail - bug |
| TC9.5 | Pass |
| TC10 | Fail - bug |
| COVERAGE & SUCCESS | 88% OK |

### Comment

Bug in TC6.2.1 with "oof" not always playing is still there

Found bug in TC8.4.2 - the follow bot doesn't always turn towards the player after being hit, pattern found: bug happens if there's collision for the follow bot in the direction the player is moving in while hitting follow bot.

Bug in TC9.4 with follow bot not always dropping loot is still there

Bug in TC10 where game briefly freezes after picking up sussy bit is still there, but it's not very critical to fix, just a tiny bit annoying