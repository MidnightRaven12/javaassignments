Set-Location ..
$javaFiles = Get-ChildItem -Recurse -Filter *.java
javac $($javaFiles)
$classFiles = Get-ChildItem -Path .\  -Recurse -Filter *.class  
$relativePaths = $classFiles | ForEach-Object { $_.FullName.Substring($pwd.Path.Length + 1) } # Gives the relative path.
jar cfm assignment11.jar MANIFEST.MF $($relativePaths)
foreach ($class in $relativePaths) {
    Write-Host "Class Included: $($class)"
}
Set-Location notes #technically, you could change this.

# Run with -ep Bypass. Example: ./script.ps1 -ep Bypass.  