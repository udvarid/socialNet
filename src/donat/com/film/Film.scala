package donat.com.film


object Category extends Enumeration {
  val SciFi, Action, Romantic, History = Value
}

case class Film(name: String, category: Category.Value) {
  override def toString: String = {
    s"Title: $name. Category: $category"
  }
}
