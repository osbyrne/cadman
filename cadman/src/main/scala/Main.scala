object Main {

  case class Centimeter(value: Double) {
    require(value > 0, "Centimeter value must be positive")
  }

  case class Point(x: Double, y: Double, z: Double) {
    def +(other: Point): Point = Point(x + other.x, y + other.y, z + other.z)
    def *(scalar: Double): Point = Point(x * scalar, y * scalar, z * scalar)
  }

  sealed trait Shape {
    def tessellate(): List[(Point,Point,Point)]
  }

  case class Cube(position: Point, side: Centimeter) extends Shape {
    override def tessellate(): List[(Point,Point,Point)] = {

      val halfSide = side.value / 2.0
      val p1 = position + Point(-halfSide, -halfSide, -halfSide)
      val p2 = position + Point(halfSide, -halfSide, -halfSide)
      val p3 = position + Point(halfSide, halfSide, -halfSide)
      val p4 = position + Point(-halfSide, halfSide, -halfSide)
      val p5 = position + Point(-halfSide, -halfSide, halfSide)
      val p6 = position + Point(halfSide, -halfSide, halfSide)
      val p7 = position + Point(halfSide, halfSide, halfSide)
      val p8 = position + Point(-halfSide, halfSide, halfSide)

      val triangles: List[(Point,Point,Point)] = List(
        (p1, p2, p3), (p1, p3, p4), // bottom face
        (p5, p6, p7), (p5, p7, p8), // top face
        (p1, p5, p8), (p1, p8, p4), // left face
        (p2, p6, p7), (p2, p7, p3), // right face
        (p1, p2, p6), (p1, p6, p5), // front face
        (p4, p3, p7), (p4, p7, p8)  // back face
      )
      return triangles
    }
  }

  case class Rectangle(position: Point, heigt: Centimeter, length: Centimeter, width: Centimeter) extends Shape {
    override def tessellate(): List[(Point,Point,Point)] = {
      val halfHeigt = heigt.value / 2.0
      val halfLength = length.value / 2.0
      val halfWidth = width.value / 2.0
      val p1 = position + Point(-halfLength, -halfWidth, -halfHeigt)
      val p2 = position + Point(halfLength, -halfWidth, -halfHeigt)
      val p3 = position + Point(halfLength, halfWidth, -halfHeigt)
      val p4 = position + Point(-halfLength, halfWidth, -halfHeigt)
      val p5 = position + Point(-halfLength, -halfWidth, halfHeigt)
      val p6 = position + Point(halfLength, -halfWidth, halfHeigt)
      val p7 = position + Point(halfLength, halfWidth, halfHeigt)
      val p8 = position + Point(-halfLength, halfWidth, halfHeigt)

      val triangles: List[(Point,Point,Point)] = List(
        (p1, p2, p3), (p1, p3, p4), // bottom face
        (p5, p6, p7), (p5, p7, p8), // top face
        (p1, p5, p8), (p1, p8, p4), // left face
        (p2, p6, p7), (p2, p7, p3), // right face
        (p1, p2, p6), (p1, p6, p5), // front face
        (p4, p3, p7), (p4, p7, p8)  // back face
      )
      return triangles
    }
  }

  case class Cylinder(position: Point, heigt: Centimeter, radius: Centimeter) extends Shape {
    override def tessellate(): List[(Point,Point,Point)] = {
      val halfHeigt = heigt.value / 2.0
      val radiusValue = radius.value
      val numberOfSides = 20
      val angleIncrement = 360.0 / numberOfSides
      var triangles: List[(Point,Point,Point)] = List()

      for(i <- 0 until numberOfSides){
        val angle1 = i * angleIncrement
        val angle2 = (i + 1) * angleIncrement

        val x1 = radiusValue * scala.math.cos(scala.math.toRadians(angle1))
        val y1 = radiusValue * scala.math.sin(scala.math.toRadians(angle1))
        val x2 = radiusValue * scala.math.cos(scala.math.toRadians(angle2))
        val y2 = radiusValue * scala.math.sin(scala.math.toRadians(angle2))


        val p1 = position + Point(x1, y1, -halfHeigt)
        val p2 = position + Point(x2, y2, -halfHeigt)
        val p3 = position + Point(x2, y2, halfHeigt)
        val p4 = position + Point(x1, y1, halfHeigt)

        triangles = triangles :+ (p1, p2, p3)
        triangles = triangles :+ (p1, p3, p4)
      }
      return triangles
    }
  }

  case class Sphere(position: Point, radius: Centimeter) extends Shape {
    override def tessellate(): List[(Point,Point,Point)] = {
      val radiusValue = radius.value
      val numberOfSlices = 20
      val numberOfSegments = 20

      val phiIncrement = 180.0 / numberOfSlices
      val thetaIncrement = 360.0 / numberOfSegments
      var triangles: List[(Point,Point,Point)] = List()

      for(i <- 0 until numberOfSlices){
        for(j <- 0 until numberOfSegments){
          val phi1 = i * phiIncrement - 90
          val phi2 = (i + 1) * phiIncrement - 90
          val theta1 = j * thetaIncrement
          val theta2 = (j+1) * thetaIncrement

          val x1 = radiusValue * scala.math.cos(scala.math.toRadians(phi1)) * scala.math.cos(scala.math.toRadians(theta1))
          val y1 = radiusValue * scala.math.cos(scala.math.toRadians(phi1)) * scala.math.sin(scala.math.toRadians(theta1))
          val z1 = radiusValue * scala.math.sin(scala.math.toRadians(phi1))

          val x2 = radiusValue * scala.math.cos(scala.math.toRadians(phi1)) * scala.math.cos(scala.math.toRadians(theta2))
          val y2 = radiusValue * scala.math.cos(scala.math.toRadians(phi1)) * scala.math.sin(scala.math.toRadians(theta2))
          val z2 = radiusValue * scala.math.sin(scala.math.toRadians(phi1))

          val x3 = radiusValue * scala.math.cos(scala.math.toRadians(phi2)) * scala.math.cos(scala.math.toRadians(theta2))
          val y3 = radiusValue * scala.math.cos(scala.math.toRadians(phi2)) * scala.math.sin(scala.math.toRadians(theta2))
          val z3 = radiusValue * scala.math.sin(scala.math.toRadians(phi2))

          val x4 = radiusValue * scala.math.cos(scala.math.toRadians(phi2)) * scala.math.cos(scala.math.toRadians(theta1))
          val y4 = radiusValue * scala.math.cos(scala.math.toRadians(phi2)) * scala.math.sin(scala.math.toRadians(theta1))
          val z4 = radiusValue * scala.math.sin(scala.math.toRadians(phi2))


          val p1 = position + Point(x1, y1, z1)
          val p2 = position + Point(x2, y2, z2)
          val p3 = position + Point(x3, y3, z3)
          val p4 = position + Point(x4, y4, z4)


          triangles = triangles :+ (p1, p2, p3)
          triangles = triangles :+ (p1, p3, p4)
        }
      }
      return triangles
    }
  }

  def STLgenerator(title: String, triangles: List[(Point,Point,Point)]): String = {
      val stlString = new StringBuilder()
      stlString.append(s"solid ${title}\n")

      for ((v1, v2, v3) <- triangles) {
        stlString.append(s"  facet normal 0 0 0\n")
        stlString.append(s"    outer loop\n")
        stlString.append(s"      vertex ${v1.x} ${v1.y} ${v1.z}\n")
        stlString.append(s"      vertex ${v2.x} ${v2.y} ${v2.z}\n")
        stlString.append(s"      vertex ${v3.x} ${v3.y} ${v3.z}\n")
        stlString.append(s"    endloop" + "\n")
        stlString.append(s"  endfacet" + "\n")
      }

      stlString.append(s"endsolid ${title}\n")

      return stlString.toString()
  }
}