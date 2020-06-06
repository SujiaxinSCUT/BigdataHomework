# 扩展题一

![image.png](https://upload-images.jianshu.io/upload_images/17501422-1c1c9d14c6532f12.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 运行截图：
![image.png](https://upload-images.jianshu.io/upload_images/17501422-241dfb42a45f23a1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/17501422-721d82a247b91365.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 关键代码：

```
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
```

# 扩展题二

![image.png](https://upload-images.jianshu.io/upload_images/17501422-aa0445955baec40f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 运行截图：

![image.png](https://upload-images.jianshu.io/upload_images/17501422-4915af35294a1bf8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 关键代码：

```
  val cu = new CountUtil();

  inputKafkaStream.map({
      x => {
        var des = x.split(",")(3).split(":")(1)
        des = des.substring(2,des.length()-1)
        cu.add(des)
//        println(x)
        cu.printN(5)
      }
    })
```

# 扩展题三

![image.png](https://upload-images.jianshu.io/upload_images/17501422-ecd47e8a4021fa15.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 运行截图：

![image.png](https://upload-images.jianshu.io/upload_images/17501422-5784e4c63b81dfed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
