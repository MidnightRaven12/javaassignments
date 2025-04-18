#!/bin/bash

# Setting up variables
JAVAFX_PATH="/path/to/javafx-sdk-24"  # Change this to the correct path.
SRC_DIR="src"
OUTPUT_DIR="bin"
MAIN_CLASS="WhackAMoleGame"

cd ..

# Create output directory if it doesn't exist
if [ ! -d "$OUTPUT_DIR" ]; then
    mkdir -p "$OUTPUT_DIR"
fi

echo "Compiling Java files..."

# Compile Java files
javac --module-path "$JAVAFX_PATH/lib" --add-modules javafx.controls,javafx.fxml,javafx.media \
      -d "$OUTPUT_DIR" \
      "$SRC_DIR/WhackAMoleGame.java" \
      "$SRC_DIR/Hole.java"

# Check if the compilation was successful
if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

echo "Running application..."

# Run the application
java --module-path "$JAVAFX_PATH/lib" --add-modules javafx.controls,javafx.fxml,javafx.media \
     -cp "$OUTPUT_DIR" "$MAIN_CLASS"

echo "Done"
