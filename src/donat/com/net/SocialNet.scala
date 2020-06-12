package donat.com.net

import donat.com.film.{Category, Film, FilmGenerator}
import donat.com.user.{NameGenerator, User}

import scala.annotation.tailrec
import scala.util.Random

case class SocialNet(size: Int, edges: Int = 3, filmNumber: Int = 20) {

  val films: List[Film] = {
    @tailrec
    def createFilms(filmsToFill: List[Film]): List[Film] = {
      if (filmsToFill.size == filmNumber) filmsToFill
      else createFilms(filmsToFill :+ FilmGenerator.giveFilm)
    }
    createFilms(List[Film]())
  }

  val users: List[User] = {
    @tailrec
    def createUsers(userToFill: List[User]): List[User] = {
      if (userToFill.length == size) userToFill
      else {
        val age: Int = Random.nextInt(50) + 15
        val name: String = NameGenerator.giveName
        val catLength: Int = Random.nextInt(Category.values.size)
        createUsers(userToFill :+ User(name, age, Category.values.toList(catLength)))
      }
    }

    createUsers(List[User]())
  }

  def printUsers(): Unit = {
    users.foreach(println)
    println(s"Number of edges: $numberOfEdges")
  }

  def printFilms(): Unit = {
    films.foreach(println)
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
