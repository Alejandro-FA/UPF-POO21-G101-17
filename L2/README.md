# Report Lab 2
Alejandro Fernández and Marc Aguilar

27/10/2021

## Point
We have reused the code of Lab 1, so there's no much to explain here.

## PolygonalRegion
The first decision we took in this class was to use `List<Point>` for the type of the attribute `points` instead of `LinkedList<Point>` in order to allow other types of lists too (such as `ArrayList`). Thanks to this we have also been able to think about and practice **upcasts** in Java. If we had used a `LinkedList` in the constructor of `PolygonalRegion` inside the main method of `MyWindow`, the code would have workd fine as well.

Then, in the `getArea()` method we have used the mathematical formula suggested for computing areas of _convex_ polygons. We have written a sperate case for the last element of the sum to avoid using an unnecessary `if else` condition inside the for loop to distinguish the last case.

Finally, we have decided to use the `drawPolygon` method of the `Graphics` in our `draw()` method because we thought that it might be more optimized for drawing polygons than just drawing lines. The conversion from a list of Points to 2 arrays of coordinates is pretty straightforward.

Both these methods assume that the **order of the points** that define the polygon are specified **clock-wise** or **anti clock-wise**. Otherwise the results are not correct. This isn't optimal by far, but implementing a procedure to order the points appropiately is time-consuming and quite complex. Furthermore, we would probably need to add aditional methods to the existing classes (such as a way to compute the distance between 2 points). After commenting this issue with other groups, it seems that the problem is out of the scope of the lab, and it is better to leave it as is.

## Continent
The class continent only has the attribute `countries`, which is a list of `PolygonalRegion`. Each of this regions will represent a conuntry. In order to draw it we created the method `draw`, which iterates over all the countries of the `Continent` instance and calls the `draw` method of each country (`PolygonalRegion`)

## World
The process followed to create the class `World` is similar to the one explained before. This class has an attribute caled `conts` which is a list of `Continent`. The `draw` method works as the one of `Continent`, but instead of iterating over countries we iterate over the continents. 

## MyMap
We have added the attribute `world` as specified in the guidelines and modified the constructor accordingly.

## MyWindow
We have added a large section that just consists on the creation of the regions of each continent of the world. **We thought that doing this step using a separate .txt file and read system call** would be better, but it was out of the scope of the lab and writing the coordinates directly in the code works just fine.

## Creation of the map
In order to draw a map similar to the globe, we have downloaded an image of the actual globe. Then, we have decided to include North and South America, Africa and EuroAsia as continents, each of which will only have two countries. 

After that we have taken some measures of each country (which dont't fit the real one), and we have computed the scaled coordinates in order to fit them into the 1000x1000 pixels window. 