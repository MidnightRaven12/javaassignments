# Setting up variables
$JAVAFX_PATH = "C:\Users\Owner\OneDrive\Desktop\temporary\javafx-sdk-24" # Change here. 
$SRC_DIR = "src"
$OUTPUT_DIR = "bin"
$MAIN_CLASS = "WhackAMoleGame" 

Set-Location .. 

if (-not (Test-Path -Path $OUTPUT_DIR)) {
    New-Item -Path $OUTPUT_DIR -ItemType Directory
}

Write-Host "Compiling Java files..."

# Compile Java files
$javacCmd = "javac --module-path $JAVAFX_PATH\lib --add-modules javafx.controls,javafx.fxml,javafx.media -d $OUTPUT_DIR $SRC_DIR\WhackAMoleGame.java $SRC_DIR\Hole.java"
Invoke-Expression $javacCmd

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed"
    exit 1
}



Write-Host "Running application..."

$runCmd = "java --module-path $JAVAFX_PATH\lib --add-modules javafx.controls,javafx.fxml,javafx.media -cp $OUTPUT_DIR $MAIN_CLASS"
Invoke-Expression $runCmd

Write-Host "Done"
