package me.apqx.adt;

/**
 * Created by apqx on 2017/4/15.
 * 分别使用数组和链表实现List
 */
public class List {

}

/**
 * 表示抽象List的接口，定义了List应具备的功能
 * @param <T>
 */
interface ListInterface<T>{
    boolean isEmpty();
    int size();
    void add(int index,T data);
    void remove(int index);
    T get(int index);
    void removeAll();
}

/**
 * 基于数组的List实现
 * @param <T>
 */
class ListArrayBased<T> implements ListInterface<T> {
    //指定数组的最大长度
    private static final int MAX_LIST=50;
    private T[] array;
    //记录List中元素的数量
    private int numberOfItem;
    public ListArrayBased(){
        array=(T[])new Object[MAX_LIST];
        numberOfItem=0;
    }
    @Override
    public boolean isEmpty() {
        return (numberOfItem==0);
    }
    @Override
    public int size() {
        return numberOfItem;
    }
    @Override
    public void add(int index, T t) {
        if (index>MAX_LIST){
            throw new IndexOutOfBoundsException();
        }else if (index>=0&&index<=numberOfItem){
            for (int i=numberOfItem;i>index;i--){
                //之后的元素后移一位
                array[i]=array[i-1];
            }
            array[index]=t;
            numberOfItem++;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public void remove(int index) {
        if (index>MAX_LIST||index<0){
            throw new IndexOutOfBoundsException();
        }else if (index>=0&&index<numberOfItem-1){
            //之后的元素前移一位
            for (int i=index;i<numberOfItem;i++){
                array[i]=array[i+1];
            }
            numberOfItem--;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public T get(int index) {
        if (index>=0&&index<numberOfItem){
            return array[index];
        }else {
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public void removeAll() {
        array=(T[])new Object[MAX_LIST];
        numberOfItem=0;
    }
}

/**
 * 链表的节点，每个节点保存着本节点的数据和指向下一个节点的引用。
 * @param <T>
 */
class Node<T>{
    private T data;
    //每一个节点都应该保存指向下一个节点的引用
    private Node next;
    public Node(T data){
        this.data=data;
        next=null;
    }
    public Node(T data,Node next){
        this.data=data;
        this.next=next;
    }
    public void setData(T data){
        this.data=data;
    }
    public T getData(){
        return data;
    }
    public void setNext(Node next){
        this.next=next;
    }
    public Node getNext(){
        return next;
    }
}

/**
 * 基于链表的List实现。
 * @param <T>
 */
class ListLinkedBased<T> implements ListInterface<T>{
    private Node<T> head;
    private int numberOfItem;
    @Override
    public boolean isEmpty() {
        return numberOfItem==0;
    }
    @Override
    public int size() {
        return numberOfItem;
    }
    @Override
    public void add(int index, T t) throws IndexOutOfBoundsException {
        if (index>=0&&index<=numberOfItem){
            if (index==0){
                Node<T> node=new Node<T>(t,head);
                head=node;
            }else {
                Node<T> preview=find(index-1);
                Node<T> node=new Node<T>(t,preview.getNext());
                preview.setNext(node);
            }
            numberOfItem++;
        }else {
            throw new IndexOutOfBoundsException("from add");
        }
    }
    @Override
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index>=0&&index<=numberOfItem-1){
            if (index==0){
                head=head.getNext();
            }else {
                Node<T> preview=find(index-1);
                Node<T> current=find(index);
                preview.setNext(current.getNext());
            }
            numberOfItem--;
        }else {
            throw new IndexOutOfBoundsException("from remove");
        }
    }
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index>=0&&index<numberOfItem){
            return find(index).getData();
        }else {
            throw new IndexOutOfBoundsException("from get");
        }
    }
    @Override
    public void removeAll() {
        head=null;
        numberOfItem=0;
    }
    private Node<T> find(int index){
        Node<T> current=head;
        for (int i=0;i<index;i++){
            current=current.getNext();
        }
        return current;
    }
}


