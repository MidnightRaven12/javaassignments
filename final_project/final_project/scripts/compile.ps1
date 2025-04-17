$JAVAFX_PATH = "C:\Users\Owner\OneDrive\Desktop\temporary\javafx-sdk-24" 
$SRC_DIR = "src"
$OUTPUT_DIR = "bin"
$MAIN_CLASS = "WhackAMoleGame"
$JAR_FILE = "final_project2.jar"  
$JAR_OUTPUT_DIR = "C:\Users\Owner\OneDrive\Desktop\Homework\oakland\java\assignment4\final_project\final_project"
Set-Location ..

if (-not (Test-Path -Path $OUTPUT_DIR)) {
    New-Item -Path $OUTPUT_DIR -ItemType Directory
}

Write-Host "Compiling Java files..."

$javacCmd = "javac --module-path $JAVAFX_PATH\lib --add-modules javafx.controls,javafx.fxml,javafx.media -d $OUTPUT_DIR $SRC_DIR\WhackAMoleGame.java $SRC_DIR\Hole.java"
Invoke-Expression $javacCmd

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed"
    exit 1
}

Write-Host "Packaging into JAR file..."

# Create the JAR file
$jarCmd = "jar --create --file=$JAR_OUTPUT_DIR\$JAR_FILE --main-class=$MAIN_CLASS -C $OUTPUT_DIR ."
Invoke-Expression $jarCmd

if ($LASTEXITCODE -ne 0) {
    Write-Host "JAR creation failed"
    exit 1
}

Write-Host "Running application..."

# Run the JAR file
$runCmd = "java --module-path $JAVAFX_PATH\lib --add-modules javafx.controls,javafx.fxml,javafx.media -jar $JAR_OUTPUT_DIR\$JAR_FILE"
Invoke-Expression $runCmd

Write-Host "Done"

Set-Location scripts
