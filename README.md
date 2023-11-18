# Stick Hero Game
**Note: UML Diagrams can be found in the `Stick-Hero` folder in both PNG and UML format. Maven has not been configured yet as it is subject to change.**
## Classes

### 1. `SplashController`

#### Methods

- `playButtonHandler(MouseEvent event)`: Handles the play button click event. Loads the `hello-view.fxml` file, creates a new stage for the hello-view, and displays it.

### 2. `HelloViewController`

#### Properties

- `Label welcomeText`: Displays a welcome message.

#### Methods

- `onHelloButtonClick()`: Handles the button click event in the hello-view. Sets the welcome text to a predefined message.

### 3. `Game`

#### Properties

- `Player player`: Represents the player in the game.
- `Vector<MyPlatform> platforms`: Collection of platforms in the game.
- `Vector<Cherry> cherries`: Collection of cherries in the game.

#### Methods

- `start(Stage stage)`: The entry point of the application. Initializes the splash screen.

## Getting Started

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX SDK](https://openjfx.io/)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/stick-hero-game.git
   ```
2. Open the project in any Java IDE.

3. Configure the project to use the JDK and JavaFX SDK.

4. Run the Game class to start the application.

### Usage

1. Launch the application.
2. Navigate through the splash screen.
3. Click the "Play" button to move to the hello-view.
4. Interact with the hello-view by clicking buttons or performing other actions.