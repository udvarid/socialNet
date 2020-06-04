package donat.com

import donat.com.net.NetInspector

object Starter extends App {

  val inspector: NetInspector = NetInspector(50, edges = 1)

  inspector.startInspection()

  //inspector.printTheNet()

}
