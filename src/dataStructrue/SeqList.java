package dataStructrue;

public class SeqList<T> extends Object //顺序表类，T表示数据元素的数据类型，默认继承Object
{
    protected static final int MIN_CAPACITY = 16; // 常量，指定element数组容量的最小值
    protected int n;                  //顺序表的元素个数（长度）
    protected Object[] element;       //对象数组存储顺序表的元素，保护成员

    public SeqList(int length)  //构造空表，length指定数组容量，若length<MIN_CAPACITY,则取最小值
    { //
        if (length < MIN_CAPACITY)
            length = MIN_CAPACITY;
        this.element = new Object[length];  //申请数组空间，元素为null
        this.n = 0;
    }

    public SeqList() {                      //创建默认容量的空表，构造方法重载
        this(MIN_CAPACITY);                 //调用本类已声明的指定参数列表的构造方法
    }

    public SeqList(T[] values)              //构造顺序表，由values数组提供元素，忽略其中的空对象。O(n)
    {   //创建2倍values数组容量的空表，若values==null，则抛出NullPointerException空对象异常
        this(values.length * 2);
        for (int i = 0; i < values.length; i++) //复制非null的数组元素
            if (values[i] != null)
                this.element[this.n++] = values[i];    //对象引用赋值
    }

    public boolean isEmpty()                    //判断是否为空，若为空，则返回true .O(1)
    {
        return this.n == 0;
    }

    public int size()                           //返回元素个数。 O(1)
    {
        return this.n;
    }

    public T get(int i)                         //若0=<i<n,则返回第i个元素，否则返回null。 O(1)
    {
        if (i >= 0 && i < this.n)
            return (T) this.element[i];          //返回数组元素应用的对象，传递对象引用
        return null;
    }

    //若0=<i<n且x!=null,则设置第i个元素为x，否则抛出序号越界正常或空对象异常。 O(1)
    public void set(int i, T x) {
        if (x == null)
            throw new NullPointerException("x=null");//抛出空对象异常
        if (i >= 0 && i < this.n)
            this.element[i] = x;                    //对象引用赋值
        else
            throw new NullPointerException(i + " ");  //抛出越界异常
    }

    //返回所有元素的描述字符串，形式为“(,)”。覆盖Object类的toString()方法。顺序表遍历算法，O(n)
    public String toString() {
        String str = this.getClass().getName() + "("; //返回类名
        if (this.n > 0)
            str += this.element[0].toString();      //执行T类的toString()方法，运行时多态
        for (int i = 1; i < this.n; i++)
            str += ", " + this.element[i].toString();
        return str + ")";                             //空表返回()
    }

    public String toPreviousString() {
        String toP = "这啥啊，这，数据结构好难";
        return toP;
    }

    //插入x为第i个元素，x!=null，返回插入元素序号。对i容错，若i<0，则头插入；若i>长度，则尾插入。O(n)
    public int insert(int i, T x) {
        if (x == null)
            return -1;
        if (i < 0)
            i = 0;                                    // 插入位置i容错，插入在最前（头插入）
        if (i > this.n) ;
        i = this.n;                                   //插入在最后（为插入）
        Object[] source = this.element;             //数组变量引用赋值，source也引用element数组
        if (this.n == element.length)                 //若数组满，则扩充顺序表的数组容量
        {
            this.element = new Object[source.length * 2];//再申请一个容量更大的数组
            for (int j = 0; j < i; j++)                       //复制当前数组前i-1个元素
                this.element[j] = source[j];              //复制数组元素，传递对象引用
        }
        for (int j = this.n - 1; j >= i; j--)                  //从i开始至表尾的元素向后移动，次序从前向后
            this.element[j + 1] = source[j];                //复制数组元素，传递对象引用
        this.element[i] = x;
        this.n++;
        return i;                                      //返回插入元素序号
    }

    public int insert(T x)                          //顺序表尾插入x元素，O(1)。成员方法重载
    {
        return this.insert(this.n, x);               //调用insert(i,x)方法
    }

    public T remove(int i)                           //删除第i个元素，0=<i<n，返回被删除的元素。若i越界，则返回null 。 O(n)
    {
        if (i >= 0 && i < this.n) {
            T x = (T) this.element[i];               //x中存储被删除的元素
            for (int j = i; j < this.n - 1; j++)
                this.element[j] = this.element[j + 1];  //元素向前移动一个位置
            this.element[this.n - 1] = null;           //设置数组元素为空，释放原引用实列
            this.n--;
            return x;                                   //返回x局部变量引用的对象，传递对象引用
        }
        return null;
    }

    public void clear()                 //删除所有元素
    {
        this.n = 0;                     //设置长度为0，未释放数组空间
    }
    //在this引用的顺序表中，顺序查找首个与key相等的元素，返回元素序号i，0=<i<n；若查找不成功，则返回-1.
    //key元素包含作为查找的关键字数据项，由T类的equals()方法确定对象是否相等
    //若key==null，则java抛出NullPointerException空对象异常。 O(n)

    public int search(T key) {
        for (int i = 0; i < this.n; i++)
            if (key.equals(this.element[i]))
                return i;
        return -1;
    }

    public boolean equals(Object obj)           //比较this对象是否相等
    {
        return this == obj;   //若this和obj对象引用同一个实列，则返回true
    }

//    public boolean equals(Object obj)   //比较this与obj引用的顺序表是否相等，覆盖
//    {
//        if (this==obj)                  //若this和obj引用同一个顺序表实列，则相等
//            return true;
//        if (obj instanceof SeqList<?>)  //若obj引用顺序表实例。SeqList<?>是所有SeqList<T>的父类
//        {
//            SeqList<T> list = (SeqList<T>)obj;    //声明list也是obj所引用的实例
//            if (this.n==list.n)                   //则比较两者长度是否相等
//            {
//                for (int i = 0;i < this.n;i++)    //在比较两个顺序表的所有元素是否相等
//                    //一旦发现有两个对应元素不相等，则可确定两个顺序表不相等。equals(Object)运行时多态
//                    if(!(this.element[i].equals(list.element[i])))
//                        return false;
//                return true;
//            }
//        }
//        return false;
//    }
}
