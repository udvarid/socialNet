package donat.com.net

import donat.com.user.{NameGenerator, User}

import scala.annotation.tailrec
import scala.util.Random

case class SocialNet(size: Int, edges: Int = 3) {

  val users: List[User] = {
    @tailrec
    def createUsers(userToFill: List[User]): List[User] = {
      if (userToFill.length == size) userToFill
      else {
        val age: Int = Random.nextInt(50) + 15
        val name: String = NameGenerator.giveName
        createUsers(userToFill :+ User(name, age))
      }
    }

    createUsers(List[User]())
  }

  def printUsers(): Unit = {
    users.foreach(println)
    println(s"Number of edges: $numberOfEdges")
  }

  private def numberOfEdges: Int = {
    users.map(u => u.friends.length).sum / 2
  }

  def makeFriends(): Unit = {
    @tailrec
    def friendMaker(number: Int): Unit = {
      if (number < size * edges) {
        val userOne = users(Random.nextInt(users.length))
        val userTwo = users(Random.nextInt(users.length))
        if (userOne != userTwo && !userOne.friends.contains(userTwo)) connectUsers(userOne, userTwo)
        friendMaker(numberOfEdges)
      }
    }
    friendMaker(numberOfEdges)
  }

  private def connectUsers(userOne: User, userTwo: User): Unit = {
    userOne.addFriend(userTwo)
    userTwo.addFriend(userOne)
  }

}
