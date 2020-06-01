package donat.com.user

import scala.util.Random

class NameGenerator {

  val familyName: List[String] = List("A", "B", "C")

  val firstName: List[String] = List("1", "2", "3")

  def giveName: String = {
    familyName(Random.nextInt(familyName.length)) + " " + firstName(Random.nextInt(firstName.length))
  }

}
