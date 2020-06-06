package net.bingosoft.flink.demo
import java.text.SimpleDateFormat

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

import scala.collection.mutable

object Demo {
  val target="b"
  val emptyMap = new mutable.HashMap[String,Int]()
  val dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //Linux or Mac:nc -l 9999
    //Windows:nc -l -p 9999
    val text = env.socketTextStream("localhost", 9999)
    val stream = text.flatMap {
      _.toLowerCase.split("\\W+") filter {
        _.contains(target)
      }
    }.map(
      new MapFunction[String,(String,Int)](){
        override def map(t: String): (String, Int) = {
            return (target,Counter.counts(t,target))
        }
      }
    ).keyBy(0)
      .timeWindow(Time.seconds(60))  //定义一个60秒的翻滚窗口
      .sum(1)
    stream.print()
    env.execute("Window Stream WordCount")
  }
}
