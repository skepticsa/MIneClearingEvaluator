# MineClearingEvaluator

To call the program:

```
java MineClearing field_file script_file
```

The main idea of the implementation is that the clearing vessel does not need to be present in the code. It only acts as a coordinates' reference when we execute the firing command.
We chose to store the mines information in an `ArrayList` instead of creating an array representation of the whole field. That works if the field is sparse meaning we have fewer mines compared with the dimension of the field. As an alternative implementation it may be worth considering an array for the cases where the field has lots of mines.
Our implementation is meant to be efficient when it comes to performance. When the ship moves north for example an array may need to add a new line at the top where in our case the memory used to represent the mines is constant as long as no mines are destroyed and it decreases with the destruction of the mines. The "matrix" of the field is only required to print the field, other than that the code can run to its completion without the whole configuration of the field.  Also the implementation easily removes mines that share the same `(x, y)` coordinates.

The program is made of the following classes:

##Mine.java 

The class implements the information about one Mine: x and y are the coordinates while dist is the distance on the z coordinate. 

The constructor converts the character that represents the distance to an integer which the distance in kilometers. 

The `dist2char()` method does the reverse operation, it takes the distance as an integer and converts it to a proper character. If the distance is invalid then the character returned is '*' (asterisk).

The `toString()` method is creating a string with the internal representation of the mine so we can use it for debugging purposes.

##Field.java 

The class implements the field as a collection of Mines stored in an `ArrayList`. 
The constructor takes a list of string as read from as file and uses the info to identify the position of the mines and adds them to the `ArrayList`.
We store the following piece of info so we can easily calculate the score:

```
  private int initial_nof_mines; 
  private int shots_fired;
  private int moves;
```

`min_x` and `min_y` are required to expand the field when moving South or West.
`size_x` and `size_y` allows us to build the string representation of the field to be printed as an output, see the `printedField()` method.

##Script.java

It stores the moving and firing commands that make the script.

##Evaluator.java

It is the class that reads the input files and builds the `Field` and the `Script` classes. It also executes the script and prints the results to the console's output.

#Tests

We created some simple unit tests using jUnit.

#TODO

[ ] Example 3 calculates the score as 1 when the documentation says it needs to be 1!?
[ ] Write more tests including integration tests
[ ] Come up with more scenarios to test normal use cases as well as edge cases and negative ones.
[ ] The field does not "shrink" when the ship moves North or West so some of the steps do not fully match the documentation, they are larger.
[ ] The code that runs the scenarios, the `Evaluator` and `Field` classes, are too coupled to printing the output. Some refactoring may allow us to run and tests complex scenario without having to intercept the console. Decoupling may allow us to read the input from other places than files as well as sending the output of every step to a graphical interface (a different UI).
