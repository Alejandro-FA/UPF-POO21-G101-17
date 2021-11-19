# Report Lab 3
Alejandro Fernández and Marc Aguilar
<br>
<br>

First of all, we would like to say that we have dedicated a lot of effort and time to this particular lab. Since we finished the compulsory part of the exerercise in relatively little time, we decided to **include modifications** in order to make it more appealing and complete. After discussing some alternatives, **we agreed that drawing a real representation of the world** instead of a couple of simple polygons would be a fun project.
The development of this idea led to a lot of additional code, so **we ended dividing the program in two distinct packages**: **`input`** and **`main`**, with an additional directory to store the data required (**`assets`**). Down below we explain in more detail the implementation of each of the packages.

## Input
The **first step** was finding a set of coordinates of all the country borders of the world suitable for our application. After some time we found an open source distributions of such coordinates in `.geojson` format at [Datahub.io](https://datahub.io/collections/geojson), which in turn uses data from [Natural Earth](https://www.naturalearthdata.com/downloads/10m-cultural-vectors/). This file is stored in `assets/countries.geojson`. In order to give the corresponding credit to both organizations we have added a `License.txt` file. 

The **second step** of the project revolve around converting the data present in `countries.geojson` into an appropiate format for our programm. This was by far one of the most time-consuming tasks of this process. Before writing any code, **we took a look at the format of the data of the `.geojson`** in order to better understand what should we do. First of all we saw that, besides the data we wanted to obtain (which were the name of the country and the corresponding coordinates of the points of its border), there was some extra information that we didn't need. The other important information that we got from this analysis was that **some countries were formed by multiple polygons instead of a single one** (due to islands and other regions outside de mainland).

After this observation, we considered that the easiest route would be using a `.json` parser. The problem was that the standard libraries don't include one. There are some alternatives made by third-parties, but we wanted to avoid unnecessary dependencies. So in the end, **we established that we would parse the `countries.geojson` file ourselves**. In order to do so we created multiple classes with different purposes, which are detailed below.

### ContinentsDict
The first obstacle we found was that our program includes a distincion between continents, information which the `.geojson` file does not contain. So we ended dividing the countries by continent manually. This was a lengthy monotonous task, so we decided to automatize the process. To do so we created a file called `continents-dict.csv`. This file is basically a list of every country and its corresponding continent. Its format has been chosen in order to easily build a dictionary data structure with it. The class contains two methods:
* **`read()`**: As its names suggests, it reads the `continents-dict.csv` file and returns a `Map<String, String>`. There's an alternative signature in which it is possible to specify the path of the file to read.
* **`main()`**: This method was the one we used to build the `continents-dict.csv` file after the manual division of countries into continents. Since it shouldn't be used anymore, it is marked as `@Deprecated`.

### BorderFiles
This class is the one responsible of parsing the `.geojson` input file into some other file/files easier to read. We first thought of translating the data into a custom format inside a `.txt` file. But it wasn't optimal, since it implied that the process of reading our parsed data into the program would not be straightforward. This coupled with the benefits of having a reusable, universal format led us to use `.csv` files. Another advantage of the `.csv` format is that it can be modified easily by just opening the file with any text editor. In particular, each row of each `.csv` file that contains the border coordinates has the following structure:
```
longitude(degrees);latitude(degrees)
```
**The methods inside this class basically do a lot of String manipulations,** and at the end they write the result to a `.csv` file. Since, as mentioned before, each country may have multiple regions, we decided that the best idea was to create a directory for each country, which in turn are store in a directory of the corresponding continent. Note here that we are using the aforementioned `ContinentsDict` class for assigning each country to the corresponding continent.

Note that given the `countries.geojson` file (and the `continents-dict.csv` file used by the `ContinentsDict` class), the `BorderFiles` class is able to generate all the files with the corresponding directory structure.

### CheckFormat
Class with a main method that ensures that the files created with the `BorderFiles` class have the appropiate format.

### CityFiles
Since each country needs a capital city, and we did not want to look the coordinates of all the capitals of the 255 countries of the world, we implemented this class in order to write a "Dummy" capital city to each country. Note that the cities of each country are written in a file called `cities.csv` which is stored in the directory of the corresponding country. In particular, each row of each `cities.csv` file has the following structure:
```
city name;inhabitants;latitude(degrees);longitude(degrees)
```

### CreateFiles
Main method to create the border files and write the dummy capital city (if necessary).

## Main

### MyWindow
We have implemented two execution modes, a test one (`-t`) and a  complex one (`-c`). By default, the complex one is the one executed since it does not take a lot of time to execute. The **test** mode only draws some countries of the whole world (which are specified in the `continents-dict-test.csv` file), while the **complex** mode draws **all** the countries of the world. We have created this two execution modes for an easier debugging of the program. 

### MyMap
Besides the methods and attributes specified by the example design, we have also added a `webMercatorProj` method. As the name suggests, it computes the Web Mercator Projection to translate spherical coordinates (latitude and longitude, specified in degrees) into a 2D grid. This projection is the one commonly used in most web applications that display maps, such as Google Maps.

### Continent
The main difference in the class `Continent` regarding Lab 2 is that the attribute `countries` now is a list of `Country` and not of `PolygonalRegions`. Consequently, the method `getTotalArea` of this class now is overriden in both classes `Country` and `PolygonalRegions`. 

### City
This class only has the attribute `numhab` since the position and the name of the city is already inherited from both classes `Point` and `GeoPoint` respectively. Appart from the constructor, we have implemented an auxiliary method to get the number of habitants called `getNumHab()`. 

### GeoPoint
This class only has the attribute `name` which is an `String`. Appart from the constructor, it only has the method `draw()` which draws the city as a point and its name as a text label in the map. This is done by getting the coordinates from the superclass `Point` and the name from the class itself. 

### Country
Since we finally have drawn the whole world, and some countries have more than one region (such as islands, for example), we added the attribute regions which is list of `PolygonalRegion` to the class. To do so, the class `Country` now has a composition relation with the `PolygonalRegion` one, instead of inheritance. 

### PolygonalRegion
In this class the only change regarding the last session is that the method `getArea()` now also works properly with **convex** polygons. 


<br> 
<br> 
Made with Natural Earth. Free vector and raster map data @ naturalearthdata.com.