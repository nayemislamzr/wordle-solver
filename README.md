# Wordle Solver

Wordle Solver is a Java-based application that uses a Trie data structure and letter frequency analysis to solve Wordle puzzles efficiently. The solver is designed to crack the correct word in **≤ 5 attempts** and comes with a user-friendly GUI for an interactive experience.

## Features

- **Trie Data Structure:** Efficiently stores and searches the word list to quickly narrow down possible answers.
- **Letter Frequency Analysis:** Uses statistical analysis of letter frequencies to prioritize guesses.
- **Fast and Accurate:** Solves puzzles in 5 or fewer attempts, maximizing your chances of success.
- **Graphical User Interface (GUI):** Enjoy a smooth, interactive experience with a modern GUI.
- **Educational:** A great resource for learning about algorithms, data structures, and their application in problem-solving.

## Getting Started

### Prerequisites

- **Java JDK:** Ensure you have Java 8 or later installed. Download from the [Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html) or use [OpenJDK](https://openjdk.java.net/).
- **Eclipse IDE (or any Java IDE):** The project includes Eclipse-specific configuration files (`.classpath`, `.project`, and `.settings`). However, it can be imported into any Java IDE.

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/nayemislamzr/wordle-solver.git
   cd wordle-solver
   ```

2. **Import the Project:**

   - **Eclipse:**
     - Open Eclipse and select **File > Import... > Existing Projects into Workspace**.
     - Browse to the cloned repository and import the project.
   - **Other IDEs:**
     - Create a new Java project and copy the contents of the `src` directory into your project’s source folder.
     - Adjust the build paths as necessary.

3. **Build the Project:**

   - If using Eclipse, the project should build automatically.
   - Otherwise, compile the Java files from the command line:

     ```bash
     javac -d bin src/**/*.java
     ```

## Usage

After building the project, run the solver application to start solving Wordle puzzles:

1. **Launch the GUI:**

   The GUI provides an interactive interface where you can input your Wordle guesses and receive real-time word suggestions.

   ```bash
   java -cp bin your.package.name.Main
   ```

2. **Follow the On-Screen Instructions:**

   - Enter your Wordle clues (correct positions, present letters, and absent letters).
   - The solver will process your input using the Trie structure and letter frequency analysis, and suggest the next best guess.
   - Continue until the correct word is found, typically in 5 or fewer attempts.

## Project Structure

- **src/** - Contains the source code, including:
  - **Trie Implementation:** Classes for building and traversing the Trie.
  - **Letter Frequency Analysis:** Modules responsible for analyzing and ranking potential words.
  - **GUI Components:** Code for the graphical user interface.
- **bin/** - Compiled binaries (if using command-line compilation).
- **.classpath, .project, .settings/** - Eclipse configuration files.

## Contributing

Contributions are welcome! To contribute:

1. **Fork the Repository** and create a feature branch.
2. **Commit Your Changes** with clear, descriptive messages.
3. **Submit a Pull Request** describing your modifications and improvements.

## License

Consider adding a license to clarify how others can use, modify, and distribute your project. The [MIT License](https://opensource.org/licenses/MIT) is a common choice for open-source projects.

## Acknowledgments

- **Wordle:** Thanks to the creators of Wordle for the inspiration behind this solver.
- **Java and Open Source Communities:** For the libraries, tools, and support that made this project possible.

---

Feel free to modify this template to suit any additional features or changes in your project!
