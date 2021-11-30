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

## assets

## input

## main





