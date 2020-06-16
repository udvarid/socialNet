package donat.com

import donat.com.net.SocialNet

object FilmStarter extends App{

  val myNet: SocialNet = SocialNet(100)

  //myNet.printUsers()

  //println("-------------")

  //myNet.printFilms()

  //println("-------------")
  myNet.everyBodyToMovie()
  //myNet.printUsersWithFilms()

  myNet.giveAdvise()


}
