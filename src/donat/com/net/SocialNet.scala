package donat.com.net

import donat.com.film.{Category, Film, FilmGenerator, FilmRating}
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

  def printUsersWithFilms(): Unit = {
    users.foreach(user => {
      println(s"User ${user.name} have been watched the following films. Favourite filmtype is ${user.favoriteFilmType}")
      user.filmSeen.foreach(println)
      println("--------------")
    })
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

  def everyBodyToMovie(): Unit = {
    users.foreach(u => {
      while (u.filmSeen.size < 10) {
        u.seeAFilm(films)
      }
    }
    )
  }

  def giveFilmParis(): List[(FilmRating, FilmRating)] = {
    for {
      u <- users
      fl1 <- u.filmSeen
      fl2 <- u.filmSeen
    } yield (fl1, fl2)
  }

  def filterFilms(filmPairs: List[(FilmRating, FilmRating)], choosedFilm: Film, givenRating: Int): List[(FilmRating, FilmRating)] = {
    filmPairs.filter(p => !p._1.equals(p._2) &&
      p._1.film.equals(choosedFilm) &&
      p._1.rating.equals(givenRating) &&
      p._2.rating >= 4)
  }

  def giveAdvise(): Unit = {
    val choosedFilm: Film = films(Random.nextInt(films.size))
    val givenRating: Int = Random.nextInt(5) + 1
    println(s"The choosed filme is '${choosedFilm.name}' with rating of $givenRating")
    println("-----------")
    val filteredFilmPairs: List[(FilmRating, FilmRating)] = filterFilms(giveFilmParis(), choosedFilm, givenRating)
    val advise: (Film, Int) = filteredFilmPairs
      .groupBy(f => f._2)
      .map(f => (f._1.film, f._2.size))
      .toList
      .minBy(-_._2)

    println(s"My advise is ${advise._1.name}, which is a ${advise._1.category}. There are ${advise._2} other user(s) who rated this, with minimum 4 rating, after watched" +
      s" the ${choosedFilm.name} with the same rating you gave.")
    println("This is a great movie for you!")

  }

  private def connectUsers(userOne: User, userTwo: User): Unit = {
    userOne.addFriend(userTwo)
    userTwo.addFriend(userOne)
  }

}
