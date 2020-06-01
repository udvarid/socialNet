package donat.com.user

import scala.util.Random

object NameGenerator {

  private val familyName: List[String] = List("Kovács", "Nemes", "Udvari", "Vas", "Kis", "Nagy", "Szent", "Simovits",
    "Kott", "Gál", "Társas", "Buzády")

  private val firstName: List[String] = List("Donát", "Bianka", "Gyula", "Gabi", "Domi", "Julcsi", "Jesszi",
    "Barni", "Krisztina", "Hanna", "Anna", "Csongi", "Zoltán")

  def giveName: String = {
    familyName(Random.nextInt(familyName.length)) + " " + firstName(Random.nextInt(firstName.length))
  }

}
