# Errors that we encountered and how to fix them.

Okay, sorry for the week long extension, but here's how to fix the errors that appear. 

1. The problem with the bad `secret.class` file was that we initially had a interface with the same with `interface secret {}` (and so on) , which was a different class. Hence, after running the `javac *.java` command, it went through to `Game.java` first (before `secret.java`), and then sees `interface secret`, it decides to make a new class. Hence, it creates a bad `secret.class` files.

2. Turns out all you need is a Powershell script in order to run properly. This ten line script allows you to save headaches when it comes to VS Code. Essentially, you have a much better idea on what you are doing (instead of clicking a single button and hoping for the best.)

So, moral of the story? Maybe make sure that you are aware of every class's name? Maybe know Powershell and Bash in order to get out of a tight spot? I don't know. Make your own conclusions. 
