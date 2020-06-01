package donat.com

import donat.com.net.SocialNet

object Starter extends App {
  val myNet = SocialNet(10)

  myNet.makeFriends()
  myNet.printUsers()

}
