import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

object Main {
  def main(args: Array[String]): Unit = {
    val edgeLength = 6.0 // Edge length in cm
    val stlContent = generateSTLCube(edgeLength)
    writeToFile("cube.stl", stlContent)
    println(s"STL file for a cube with edge length $edgeLength cm has been written to cube.stl")
  }

  def generateSTLCube(edgeLength: Double): String = {
    val vertices = Seq(
      (0.0, 0.0, 0.0), (edgeLength, 0.0, 0.0), (edgeLength, edgeLength, 0.0), (0.0, edgeLength, 0.0),
      (0.0, 0.0, edgeLength), (edgeLength, 0.0, edgeLength), (edgeLength, edgeLength, edgeLength), (0.0, edgeLength, edgeLength)
    )

    val faces = Seq(
      (0, 1, 2, 3), (4, 5, 6, 7), (0, 1, 5, 4),
      (2, 3, 7, 6), (0, 3, 7, 4), (1, 2, 6, 5)
    )

    def vertexString(v: (Double, Double, Double)): String = s"vertex ${v._1} ${v._2} ${v._3}"

    def facetString(v1: (Double, Double, Double), v2: (Double, Double, Double), v3: (Double, Double, Double)): String = {
      s"""
      |facet normal 0 0 0
      |  outer loop
      |    ${vertexString(v1)}
      |    ${vertexString(v2)}
      |    ${vertexString(v3)}
      |  endloop
      |endfacet
      """.stripMargin
    }

    val facets = faces.flatMap { case (v1, v2, v3, v4) =>
      Seq(
        facetString(vertices(v1), vertices(v2), vertices(v3)),
        facetString(vertices(v1), vertices(v3), vertices(v4))
      )
    }

    s"""
    |solid cube
    |${facets.mkString("\n")}
    |endsolid cube
    """.stripMargin
  }

  def writeToFile(filename: String, content: String): Unit = {
    val path = Paths.get(filename)
    Files.write(path, content.getBytes(StandardCharsets.UTF_8))
  }
}