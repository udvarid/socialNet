package donat.com

import donat.com.net.NetInspector

object Starter extends App {

  val inspector: NetInspector = NetInspector(10)

  inspector.startInspection()

  inspector.printTheNet()

}
