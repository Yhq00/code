package dataStructrue;

//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
public class SingList<T> extends Object //单链表类，T表示数据元素的数据类型；默认继承Object
{
    public Node<T> head;                //头指针，指向单链表的头结点

    //(1)构造方法
    public SingList()                   //构造方法，构造空单链表。O(1)
    {
        this.head = new Node<T>();      //创建头结点，data和next值均为null
    }

    public SingList(T[] values)         //构造单链表，尾插入values数组元素，忽略其中空对象,O(n)
    {
        this();                         //创建空单链表，只有头结点
    }
}
