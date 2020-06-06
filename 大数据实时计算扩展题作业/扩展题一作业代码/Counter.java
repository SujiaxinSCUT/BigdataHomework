package net.bingosoft.flink.demo;

public class Counter {

    public static int counts(String str,String target){
        int count = 0;
        while (str.contains(target)){
            str=str.substring(str.indexOf(target)+1);
            ++count;
        }
        return count;
    }
}
