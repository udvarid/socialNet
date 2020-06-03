package donat.com.net

import donat.com.user.User

import scala.collection.mutable

case class NetInspector(size: Int = 10, edges: Int = 3) {

  private val myNet: SocialNet = initNet

  private def initNet: SocialNet = {
    val netCreated: SocialNet = SocialNet(size, edges)
    netCreated.makeFriends()
    netCreated
  }

  def startInspection(): Unit = {

    for (i <- myNet.users.indices) {
      setEverybodyToNilState()
      var sizeOfNet: Int = 0
      var maxLenght: Int = 0
      myNet.users(i).visitFlag.size = 0
      val inspQueue: mutable.Queue[User] = mutable.Queue(myNet.users(i))
      while (inspQueue.nonEmpty) {
        val userToInspect: User = inspQueue.dequeue()
        userToInspect.visited()
        userToInspect.friends
          .filter(f => !f.isVisited && !f.isInQueue)
          .foreach(f => {
            val newLenght: Int = userToInspect.visitFlag.size + 1
            f.visitFlag.size = newLenght
            f.putIntoQueue()
            maxLenght = maxLenght.max(newLenght)
            inspQueue.enqueue(f)
            sizeOfNet += 1
          })
      }
      println(s"At user ${myNet.users(i).name} the size of net is $sizeOfNet. Maximum length is $maxLenght")
      myNet.users(i).resultFlag.sizeOfNet = sizeOfNet
      myNet.users(i).resultFlag.maximumLenght = maxLenght
      myNet.users(i).resultFlag.canReachEverybody = myNet.users.length - 1 == sizeOfNet

    }
    println("----------------")

  }

  private def setEverybodyToNilState(): Unit

  = {
    myNet.users.foreach(u => u.visitToInit())
  }

  def printTheNet(): Unit = {
    myNet.printUsers()
  }

}
