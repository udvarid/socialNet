package donat.com.user

case class User(name: String, age: Int) {

  var friends: List[User] = List()

  def addFriend(friend: User): Unit = friends :+ friend

  var visitFlag: VisitFlag = new VisitFlag

  var resultFlag: ResultFlag = new ResultFlag

  def visitToInit(): Unit = {
    visitFlag.size = 0
    visitFlag.visited = false
  }

  def visited(): Unit = {
    visitFlag.visited = true
  }

  def isVisited(): Boolean = visitFlag.visited
}