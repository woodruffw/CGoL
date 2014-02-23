CGoL
======

An implementation of Conway's Game of Life in Java, with optional user input.

![alt tag] (https://raw.github.com/woodrufw/CGoL/master/examples/cgol.gif?raw=true)


## What is Conway's Game of Life?
Conway's game of life is a cellular automaton created by John Horton Conway in 1970.

Instead of having players, it relies upon the initial state of the plane, as well as four simple rules, to determine the appropriate action at each interval.

The rules are as follows:

1. If a living cell (a single point on the plane) has less than two neighbors, it dies.

2. If a living cell has either two or three neighbors, it survives the iteration.

3. If a living cell has more than three neighbors, it dies.

4. If a dead cell has exactly three neighbors, it comes to life.

## Features:
+ Real-time user input. Allows the user to modify the state of the game, even after beginning.
+ Optional file input. Allows the user to quickly load seeds (like a glider gun) without manually entering the values.

### File format:
If you want to use the file input feature, just create a text-file with the following format:

```
001001
101101
111011
110011
```
...and so forth. Each '1' represents an alive cell, and each '0' represents a dead cell. 
The file can be arbitrarily large, although you should consider the limitations of your system (and the JVM) before creating 10000x10000 grids.

To load the file, just click the 'Load Grid From File...' menu item under the 'File' menu.

## Installation
CGoL builds on all systems with Java.
From your terminal:

```
git clone http://github.com/woodrufw/CGoL
cd CGoL
make
```

The resulting class files will be left in "bin".

To run the game:

```
cd bin
java StartGUI
```