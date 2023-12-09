# Stick Hero Game
**Note: UML Diagrams can be found in the `Stick-Hero` folder in both PNG and UML format. Maven has not been configured yet as it is subject to change.**

## Introduction
Stick Hero is fun and intuitive game designed to show how JavaFX can be used to create a game using OOP 's principals as well as various Design Patterns and using good coding practices in general.

## Game Mechanics and Tutorial
1. First, on starting the game the user is greeted with a pleasant start screen with a play button.
2. After pressing the play button you are greeted with a map section screen after which you can begin the game.
3. In the game you spawn at a platform and the objective is to reach the next platform without falling into the gap in between them.
4. When you are on a platform you can hold the mouse button to generate a stick whose length is proportional to the duration of the mouse hold.
5. Whilst traversing the stick you can flip to collect cherries or avoid wither(which is equivalent to -1 cherries) on the bottom side of the stick however if you crash into a platform you die.
6. Once you die and reach the death screen you are presented between two options.
7. This allows you to either revive if you have more than 2 cherries else you can return to the home screen.

**Note: Please do not spam click inputs and wait patiently for the game to respond(there might be some slow responsiveness due to different hardware configurations)**

## The magic behind the Scenes(Code Explanation)
1. The code begins with the `Game` class which has the `public static void main(Strings[] args)` function which calls the tests created using the Junit and then the runs the `launch(args)` to start the application.
2. Now the main menu's fxml file is loaded using the `FXMLLoader` which was created using the Scene Builder.
3. Using the controller class `SplashTestController` we present the user with a big blue play button.
4. On hitting the button the user is asked for a username which is used to store his high score so even if he closes the game and plays it again his high score is saved.
5. After this the map selection screen's fxml is loaded and the `SwitchMaps` class assumes control.
6. The user can select the map and hit the start button to begin the game.
7. Starting the gameplay passes the control over to the `MainGame` class which handles all the core functionality of the game.
8. The `MainGame` initially calls the `createContent` function to generate a `AnchorPane` which houses two randomly generated platforms as well as a randomly generated cherry/wither between them. It also places the player at the starting platform.
9. After this we use the `handlleMouseEvents` to create a stick which has a length proportional to the time you hold the mouse button. The creation of the stick and its growth animation is handled by the `handleMousePressed` and the `handleMouseReleased` functions, the former of which also rotates the stick and begins the animation of the player moving across the stick.
10. The `handleMouseReleased` also uses the `checkCherryColission`, `checkPillarColision` and the `checkWitherCollision` to ensure that cherries and wither are successfully collected and the player dies if he hits the pillar upside-down.
11. The `contentCreate` also checks for mouse clicks during the transition to flip the player using the function `handleMouseClick`.
12. Furthermore, the pause functionality is also implemented using the `anchorPane.setOnKeyTyped(this::handlePauseResume)` to pause anywhere where the user can give command.
13. The functions `loadScores` and `saveScores` use the serializable `Player` class to save the high score of the player at evey new platform so that even if the game is abruptly closed the High Sore of the user is saved with his username.
14. In this endeavour if the player hits a pillar or dies of falling due to other reasons the user is directed to the  `DeathScreen.fxml` via the `gameOver` function, which presents the user with two options `Revive` or `Home`.
15. The Functionality of these buttons is handled by the `DeathScreen` class.
16. The home buttons functionality is handled by the `home` function which basically resets the game and trows the user to the main screen.
17. While, the revive functionality is handled by the `revive` function, which calls the `getCherryScore` to check if the user has enough cherries to revive or else throws an error message in a new window.
18. The `revive` function calls the `setCherryScore` to decrement the number of cherries by two. and then calls the `continueGame` method which calls the `revived` functions which restores the game state to before the player died.
19. If the user does not die then the `gameContinue` function is called which calls the `createContent` function to generate the next scene and keeps going in an infinite loop which ends with the player dying.

## Design Patterns

- **Flyweight** : The `Player` class implements the Flyweight design pattern. It maintains a `Vector` of `Player`'s which stores all the scores and names of the `Player`'s in it. When the `getPlayerScore` function ensures there are only unique objects with unique names in the `Vector`. It takes as input a name and returns a `Player` object with that name which was either in the Vector already or if not present creates a `Player` with that name and adds it to the vector and then returns it.
- **Strategy** : We use the `movable` interface to implement two classes `isMoving` and `isStationary` which help us identify is the player is moving or not.

## Creative Twist

- **Background Music** : There is randomly selected music from a wide variety of songs, playing in the background while you play to add to the ambiance.
- **Wither** : There is another type of consumable in your path which you must avoid as eating it causes you to lose cherries.
- **Map Selection** : The player can pick through a wide variety of distinct and unique maps to enhance the gameplay.
- **Special Sound Effects** : Special events like dying, reviving, consuming cherries or wither produce special sound effects to add to the audiovisual experience of the player.

## Getting Started

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX SDK](https://openjfx.io/)
- JavaFX-Media-18.01
- Junit 4

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/stick-hero-game.git
   ```
2. Open the project in IntelliJ IDEA Ultimate Edition.

3. Configure the project to use the JDK and JavaFX SDK.

4. Run the Game class to start the application.
5. Else you can press this button in the IntelliJ IDEA Software `Game`.

### Usage

1. Launch the application.
2. Navigate through the splash screen.
3. Click the "Play" button to play the game.

**Note: Some functionality of this file might only work in the README.md file in IntelliJ IDEA Ultimate Edition Software.**