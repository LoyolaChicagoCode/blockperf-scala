import squants.time._
import squants.information._

package object blockperf {

  case class ElapsedTime(t: Double) {
    val time = Nanoseconds(t)

    def +(another: ElapsedTime): ElapsedTime = ElapsedTime(t + another.t)

    override def toString(): String = s"Time(${time.toString})"

  }

  case class MemoryUsage(m: Long) {
    val used = Bytes(m) in Megabytes
    val free = Bytes(Runtime.getRuntime.freeMemory) in Gigabytes
    val total = Bytes(Runtime.getRuntime.totalMemory) in Gigabytes

    def +(another: MemoryUsage): MemoryUsage = MemoryUsage(m + another.m)

    override def toString(): String = s"Space(used=${used.toString}, free=${free.toString}, total=${total.toString})"
  }

  def performance[R](block: => R): (ElapsedTime, MemoryUsage, R) = {
    val t0 = System.nanoTime()
    val m0 = Runtime.getRuntime.freeMemory

    val result = block // This runs the block of code

    val t1 = System.nanoTime()
    val m1 = Runtime.getRuntime.freeMemory
    val deltaT = t1 - t0
    val deltaM = m0 - m1
    (ElapsedTime(deltaT), MemoryUsage(deltaM), result)
  }
}
