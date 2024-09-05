# 2D Cutting Stock Problem

This project implements the 2D cutting stock problem using Java. The objective is to optimally cut a large sheet into smaller pieces with minimal waste. The solution is visualized using a Swing-based UI, with each piece distinguished by unique colors based on its size.

## Features
- **Piece & Sheet Representation**: Define pieces and the main sheet with dimensions.
- **Automatic Piece Color Assignment**: Pieces with the same dimensions are automatically assigned the same color, while different-sized pieces get distinct colors.
- **Sorting by Area**: The algorithm sorts pieces by area, ensuring that larger pieces are placed first.
- **Collision Detection**: Prevents overlap and ensures each piece fits within the boundaries of the sheet.
- **Graphical Visualization**: Uses Java Swing to visualize the arrangement of pieces on the sheet, with each piece clearly marked.

## Problem Description
The 2D cutting stock problem involves cutting a large rectangular sheet into smaller pieces with pre-defined dimensions. The challenge is to minimize waste and optimally place all pieces on the sheet.

In this project:
- The sheet size is **6x7 units**.
- The pieces have varying dimensions, which are inputted into the system.
- The algorithm places each piece on the sheet without overlap and visualizes the result.

## Visualization
The output is displayed in a Swing-based window. Each piece is shown in a distinct color, and a black border outlines each piece. Below is an example output:

![image](https://github.com/user-attachments/assets/d2a160cd-25a4-4389-8765-7cd296c0a5d4)

## How It Works
1. **Piece Class**: Represents each piece with width, height, and a color.
    - The color is automatically assigned based on the piece’s dimensions.
2. **Sheet Class**: Represents the main sheet where the pieces are placed.
    - A 2D array tracks the placement of pieces, preventing overlap.
3. **CuttingStock Class**: Implements the logic for sorting and placing pieces.
    - Pieces are placed in order of size, from largest to smallest, to minimize empty space.
4. **CuttingStockUI Class**: Handles the graphical visualization of the sheet and placed pieces using Java Swing.

## Code Structure
- **Piece.java**: Defines the `Piece` class, which represents a piece to be cut from the sheet.
- **Sheet.java**: Defines the `Sheet` class, which manages the sheet and tracks placed pieces.
- **CuttingStock.java**: Implements the logic to solve the cutting stock problem.
- **CuttingStockUI.java**: Visualizes the solution using Swing.

## Installation and Setup

### Prerequisites
- **Java JDK 8 or later**: Ensure that Java is installed on your system. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
- **Java Swing**: This project uses Swing for the graphical user interface. Swing is part of Java SE, so no additional libraries are required.

### Steps to Run

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/2D-Cutting-Stock.git
    ```
2. Navigate into the project directory:
    ```bash
    cd 2D-Cutting-Stock
    ```
3. Compile the Java files:
    ```bash
    javac CuttingStockUI.java
    ```
4. Run the project:
    ```bash
    java CuttingStockUI
    ```

Once run, a window will open showing the arrangement of the pieces on the sheet with minimal waste.

## Example Usage

To modify the sheet size or the pieces, you can update the `main` method of the `CuttingStockUI.java` file, where the pieces and sheet are initialized:

```java
Sheet sheet = new Sheet(6, 7); // 6x7 units sheet
Piece[] pieces = {
    new Piece(2, 3),
    new Piece(4, 1),
    new Piece(1, 1),
    // Add more pieces as required
};
