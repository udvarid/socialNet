package donat.com.user

case class User(name: String, age: Int) {

  var friends: List[User] = List()

  def addFriend(friend: User): Unit =
    friends = friends :+ friend

  var visitFlag: VisitFlag = new VisitFlag

  var resultFlag: ResultFlag = new ResultFlag

  def visitToInit(): Unit = {
    visitFlag.size = 0
    visitFlag.visited = false
    visitFlag.inQueue = false
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
    "Name: " + this.name + ". Age: " + this.age + ". Number of friends: " + friends.length
  }
}
