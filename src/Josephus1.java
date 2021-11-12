import dataStructrue.SeqList;

public class Josephus1 {    //求解Josephus环问题
    //n个人，n>0,从start开始计数，0<=start<n ; 每次数到 distance 的人出环， 0<distance<n
    public Josephus1(int n, int start, int distance) {
        if (n <= 0 || start < 0 || start >= n || distance <= 0 || distance >= n) //若参数无效，则抛出无效参数异常
            throw new NullPointerException("n=" + n + ",start=" + start + ",distance=" + distance);
        System.out.print("Josephus(" + n + "," + start + "," + distance + "),");
        //创建顺序表实列，元素类型为字符串，构造方法参数指定顺序表容量，省略时取默认值
        SeqList<String> list = new SeqList<String>(n);
        for (int i = 0; i < n; i++)                        //顺序表尾插入n个元素
            list.insert((char) ('A' + i) + "");      //顺序表尾插入，O(1)
        System.out.println(list.toString());        //输出顺序表，O(n)
        while (n > 1) {
            start = (start + distance) % n; //按环形方式计数
            //输出删除的start位置对象和顺序表中剩余元素，两则均为O(n)
            System.out.println("删除" + list.remove(start) + "," + list.toString());
            n--;
        }
        System.out.println("被赦免者是" + list.get(0));  //get(0)获取元素，O(1)
    }

    public static void main(String[] args) {
        new Josephus1(5, 1, 2);
    }
}
