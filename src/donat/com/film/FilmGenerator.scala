package donat.com.film

import scala.util.Random

object FilmGenerator {

  val filmNamePartOne: List[String] = List("The", "Heroes", "Dog", "One", "Maniac", "Star", "Here", "Endless", "Love",
  "Death", "Girl", "Man", "Dogs", "Caviar")

  val filmNamePartTwo: List[String] = List("of", "and", "or", "why", "not", "", "is", "going to", "was", "will",
  "does", "do", "on", "at", "beyound", "below", "top of", "top")

  val filmNamePartThree: List[String] = List("secret", "war", "love", "ending", "cold", "hot", "revange", "happy", "cheated",
    "always")

  def giveFilm: Film = {
    val partOne = filmNamePartOne(Random.nextInt(filmNamePartOne.size))
    val partTwo = filmNamePartTwo(Random.nextInt(filmNamePartTwo.size))
    val partThree = filmNamePartThree(Random.nextInt(filmNamePartThree.size))
    val category = Category.values.toList(Random.nextInt(Category.values.size))

    val partOneAndTwo: String = if (partTwo.equals("")) partOne else partOne + " " + partTwo
    Film(partOneAndTwo + " " + partThree, category)
  }

}
