# Report of Lab 1
Alejandro Fernández and Marc Aguilar

## Introduction
In this practice session, the first goal was defining a class which could allocate geometric points and define their functionality, such as adding some getters and setters for the coordinates and implementing a method to compute the distance between 2 points. 

The second part was creating a class  which contained a matrix. This matrix computes the distance between the points that have been inserted by a specific method. 

Therefore, we can easily visualize the distance between two points by printing the matrix.

## Theoretical background
This practice has focused on object modelling with java. On one hand, we have had our first contact with how should an object be implemented in java using classes. Then, we have established a relation between two classes, in particular a **composition/aggregation relation between the `Point` class and the `DistanceMatrix` class**.

## Alternative solutions discussed
Firstly we implemented the matrix with `ArrayLists`, which worked fine and was a good option. After discussing it with the practice teacher, we were told that `LinkedLists` are more efficient. Therefore, we ended up changing it. 

The other debate we had was when we were creating the auxiliary method `printMatrix`. At first, every time we wanted to print the distance between two points we computed it with the method `getDistance`. Then we realized that it would be more efficient to direclty print the points from the matrix. 

## Conclusion
At this point, our implementation of the `Point` class and the `DistanceMatrix` class worked without problems and our testing classed passed without errors. So we decided to try to implement the optional steps. In order to do so, we had to **change the definitions of multiple methods**, as well as **renaming the `Point` class to `City`** and all the related references accordingly. Furthermore, we also have added a `name` attribute to the `City` class and related methods to operate with this name. It should be emphasized though, that besides changing the names of the methods and the variables the implementations themselves have not changed.

On a related note, when doing the previous steps we have found some errors:
* On one hand, the `Matrix` class specifies that the `createDistanceMatrix` has to be public, but we have implemented in such a way that it is not meant to be used by the user and thus should remain private. Since changing the definition of the `Matrix` class to private gave us an error, we had two possibilities. The first one was to change the `createDistanceMatrix` to public instead. This was not a solution that we like, so **we decided to erase the definition of `createDistanceMatrix` from the `Matrix` interface** instead. Since we have not seen interfaces in class yet, we don't know if this is a good solution, but the name interface suggests that only public methods should be defined.

* It should be noted that we could also have changed the whole implementation of the `createDistanceMatrix` to be public, but **we considered that it makes the most sense to keep it private** and only call it when adding a `City`, since it does not make sense to call it for any other reason. s
* On another hand, the package keyword is unknown for us, so we have decided to delete it from the `DisplayMatrix.java` and the `Matrix.java` files.
* Once we solved the previous steps, we were able to execute the `TestDisplayMatrix.java` and see the GUI, but each time that we added a point an error was printed to the terminal. After giving some thought to the matter, we realized that the problem was caused by the fact that, inside `DisplayMatrix.java`, the `createMatrixDistance` method is called after calling the `addCity` method. But in our implementation, the matrix creation method was called **inside the `addCity` method**, in such a way that each time a city was added the matrix was updated. This works great and, from our point of view, it is better from a 
user perspective. So **we have decided to comment line 147 to avoid calling the `createMatrixDistance` method unnecesseraly**.