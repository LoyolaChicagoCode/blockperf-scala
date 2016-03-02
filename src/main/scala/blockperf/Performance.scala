import squants.time._
import squants.storage._

package object blockperf {

  case class Time(t: Double, outputUnit : String = "nanoseconds") {
    val nanoseconds = Nanoseconds(t)
    val microseconds = nanoseconds in Microseconds
    val milliseconds = nanoseconds in Milliseconds

    def +(another: Time): Time = Time(t + another.t)

    // TODO: Use map and getOrElse to format.

    override def toString(): String =
      if (outputUnit == "nanoseconds")
        s"Time(${nanoseconds})"
      else if (outputUnit == "microseconds")
        s"Time(${nanoseconds})"
      else if (outputUnit == "milliseconds")
        s"Time(${milliseconds})"
      else
        s"Time(${nanoseconds})"

  }

  case class Space(m: Long, outputUnit : String = "GB") {
    val memUsed = Bytes(m)
    val memFree = Bytes(Runtime.getRuntime.freeMemory)
    val memTotal = Bytes(Runtime.getRuntime.totalMemory)

    // TODO: Use map and getOrElse to format.

    override def toString(): String =
      if (outputUnit == "KB")
        s"Space(used=${memUsed in Kilobytes}, free=${memFree in Kilobytes}, total=${memTotal in Kilobytes})"

      else if (outputUnit == "MB")
        s"Space(used=${memUsed in Megabytes}, free=${memFree in Megabytes}, total=${memTotal in Megabytes})"

      else if (outputUnit == "GB")
        s"Space(used=${memUsed in Gigabytes}, free=${memFree in Gigabytes}, total=${memTotal in Gigabytes})"

      else // outputUnit is assumed to be B=Bytes
        s"Space(used=${memUsed}, free=${memFree}, total=${memTotal})"

  }
  // time a block of Scala code - useful for timing everything!
  // return a Time object so we can obtain the time in desired units

  def performance[R](block: => R): (Time, Space, R) = {
    val t0 = System.nanoTime()
    val m0 = Runtime.getRuntime.freeMemory
    // This executes the block and captures its result
    // call-by-name (reminiscent of Algol 68)
    val result = block
    val t1 = System.nanoTime()
    val m1 = Runtime.getRuntime.freeMemory
    val deltaT = t1 - t0
    val deltaM = m0 - m1
    (Time(deltaT), Space(deltaM), result)
  }
}
