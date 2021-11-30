# Report Lab 4
Alejandro Fernández and Marc Aguilar
<br>
<br>

In order to properly organize all the code of this lab we have decided to **divide it in 4 distinct packages**: **`input`**, **`entities`**, **`gui`** and **`main`**, with an additional directory to store the data required (**`assets`**). Down below we explain in more detail the implementation of each of the packages.

## entities

Regarding the `Entity` class and its subclasses there isn't much to talk about, since the implementation is already specified in the instructions of the lab. Nonetheless, we have decided added some additional method in order to do some minor improvements.

### Vector

We have added the method `public double magnitude()`, which computes the magnitude of the vector. This is used in the `RectangularRegion` and `TriangularRegion` classes for an easier computation of the area.

### TriangularRegion and RectangularRegion

When trying to implement the triangular and rectangular regions, which are subclasses of `PolygonalRegion`, we encountered a problem. We wanted to have different attributes to store the points, instead of the list stored in `PolygonalRegion`. The problem came when trying to create a list before calling the `super()` constructor, something not possible in Java. In the end we settled for adding an additional constructor in `PolygonalRegion`:

```java
public PolygonalRegion(Color lineColor, Color fillColor) {
    super(lineColor, fillColor);
    this.points = new LinkedList<Point>();
}
```

Which instead of receiving a list of `Point` it just creates an empty one. Then we have added the method `public void addPoint(Point p)`, which is used to add points to the `PolygonalRegion` one by one.

With all these changes, we have then implemented the constructor of `RectangularRegion` as follows:

```java
 public RectangularRegion(Color lineColor, Color fillColor, Point p1, Point p2) {
     super(lineColor, fillColor);
     this.p1 = p1;
     this.p2 = p2;
     Point p3 = new Point(p1.getX(), p2.getY()); // We deduce the remaining points of the rectangle
     Point p4 = new Point(p2.getX(), p1.getY());

     // Note that the order is important
     this.addPoint(p1);
     this.addPoint(p3);
     this.addPoint(p2);
     this.addPoint(p4);
 }
```

As you can see, we use the constructor without a list of points for the `super` class, and then we add the points of the rectangle one by one.

## gui

In order to implement the possibility of selecting entities we have modified the `DrawPanel` class. Besides the instructions given, we have also implemented a method for changing the color of an Entity when it is selected. Furthermore, we have written the code in such a way that it is also possible to remove an Entity from the selection when clicking on it. One minor detail that is worth mentioning is that the `isPointInside` is only implemented in the class `Region` (and its subclasses). Therefore, a downcast from `Entity` is needed, with the appropiate verification using the `instanceof` operator just in case.

## assets

This folder stores the files with the information of the entities of the program. This comes in handy to avoid clustering the code with multiple instantiations of the `Entity` class. The format of the files is as follows:
* For `EllipsoidalRegion`: `Rline;Gline;Bline;Rfill;Gfill;Bfill;centerX;centerY;radius1;radius2`
* For `CircularRegion`: `Rline;Gline;Bline;Rfill;Gfill;Bfill;centerX;centerY;radius`
* For `PolygonalRegion` and its sublcasses: `Rline;Gline;Bline;Rfill;Gfill;Bfill;points`, where the points are specified with the following format: `point1X,point1Y,point2X,point2Y,...,pointNX,pointNY`

## input

We have decided to create the entities in the file `assets`, as it has been explained above. Therefore, we needed some methods in order to parse those `csv` files and create the entities. These methods are in the folder `main`.

The main class is `EntitiesFile`, which is an abstract class, and has the necessary methods to parse the color (`parseColor`), the fields (`parseFields`), and read the files (`read`). The format of the colors is the same for all entities, but the format of the coordinates is not. Consequently, `parseFields` is an abstract method. 

There are two subclasses of `EntitiesFile`, which are `EllipsesFile` and `PolygonsFile`. Both classes override the method `parseFields` in its own way. In the class `PolygonsFile` there is an extra method called `parsePoints` since it is not as simple as in `EllipsesFile` to determine which subclass of polygon is (it is just a matter of simplification). 


## main

This folder only contains the class `TestGUI` which is the one that contains the `main` method. In the main method we create an empty `LinkedList` of `Entity`, which will store all the entities from `assets`. 

To do so, for each file in assets we create its associated instance, which is an `EllipsesFile` for ellipses and circles, or a `PolygonsFile` for polygons, rectangles and triangles. As we create those instances, we read the files with the method `read` which is inherited from `EntitiesFile` and we add them to the Linked List `entitiesList`. 

At the end, we create the `EntityDrawer` instance called `drawer`, and we add each entity of `entitiesList` with the given method `addDrawable`. 



