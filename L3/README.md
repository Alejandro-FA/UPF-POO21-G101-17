# Report Lab 3
Alejandro Fernández and Marc Aguilar

Made with Natural Earth. Free vector and raster map data @ naturalearthdata.com.

## Continent
The main difference in the class `Continent` regarding Lab 2 is that the attribute `countries` now is a list of `Country` and not of `PolygonalRegions`. Consequently, the method `getTotalArea` of this class now is overriden in both classes `Country` and `PolygonalRegions`. 

## City
This class only has the attribute `numhab` since the position and the name of the city is already inherited from both classes `Point` and `GeoPoint`. Appart from the constructor, we have implemented an auxiliary method to get the number of habitants called `getNumHab()`. 

## GeoPoint
This class only has the attribute `name` which is an `String`. Appart from the constructor, it only has the method `draw()` which draws the city as a point and its name in the map, this is done by getting the coordinates from the superclass `Point` and the name from the class itself. 
