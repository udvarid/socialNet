package donat.com.user

import donat.com.film.{Category, Film, FilmRating}

import scala.util.Random

case class User(name: String, age: Int, favoriteFilmType: Category.Value) {

  var friends: List[User] = List()

  var filmSeen: List[FilmRating] = List()

  def addFriend(friend: User): Unit =
    friends = friends :+ friend

  var visitFlag: VisitFlag = new VisitFlag

  var resultFlag: ResultFlag = new ResultFlag

  def visitToInit(): Unit = {
    visitFlag.size = 0
    visitFlag.visited = false
    visitFlag.inQueue = false
  }

  def seeAFilm(films: List[Film]): Unit = {
    var seenAlready: Boolean = true
    while (seenAlready) {
      val aFilm: Film = films(Random.nextInt(films.size))
      if (!filmSeen.exists(f => f.film.equals(aFilm))) {
        var rating = Random.nextInt(5) + 1
        if (aFilm.category.equals(favoriteFilmType)) {
          rating = (rating + 3).min(5)
        }
        filmSeen = filmSeen :+ FilmRating(aFilm, rating)
        seenAlready = false
      }
    }
  }

  def visited(): Unit = {
    visitFlag.visited = true
  }

  def putIntoQueue(): Unit = {
    visitFlag.inQueue = true
  }

  def isVisited: Boolean = visitFlag.visited

  def isInQueue: Boolean = visitFlag.inQueue

  override def toString: String = {
    "Name: " + this.name + ". Age: " + this.age + ". Number of friends: " + friends.length + ". Favorite filmtype is " +
    favoriteFilmType
  }
}
