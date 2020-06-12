package donat.com.film

case class FilmRating(film: Film, rating: Int) {
  override def toString: String = {
    s"Title: ${film.name}, category: ${film.category}. Rating: $rating"
  }
}


