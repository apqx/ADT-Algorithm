package me.apqx.adt;

/**
 * Created by apqx on 2017/4/15.
 */
public class Stack {

}

/**
 * 表示Stack的接口，定义了Stack应具备的功能。
 * @param <T>
 */
interface StackInterface<T>{
    void push(T t)throws StackException;
    T pop()throws StackException;
    T peek() throws StackException;
    boolean isEmpty();
    void popAll();
}
class StackException extends Exception{}

/**
 * 基于数组的抽象Stack实现。
 * @param <T>
 */
class StackArrayBased<T> implements StackInterface<T>{
    private static final int MAX_SIZE=50;
    private T[] array;
    private int top;
    public StackArrayBased(){
        array=(T[])new Object[MAX_SIZE];
        top=-1;
    }
    @Override
    public void push(T t) throws StackException{
        if (top!=MAX_SIZE-1){
            array[++top]=t;
        }else {
            throw new StackException();
        }
    }
    @Override
    public T pop() throws StackException{
        if (!isEmpty()){
            //其实这里应该将数组中的这个引用设为null，防止出现内存泄露
            return array[top--];
        }else {
            throw new StackException();
        }
    }
    @Override
    public T peek() throws StackException{
        if (!isEmpty()){
            return array[top];
        }else {
            throw new StackException();
        }
    }
    @Override
    public boolean isEmpty() {
        return top==-1;
    }
    @Override
    public void popAll() {
        array=(T[])new Object[MAX_SIZE];
        top=-1;
    }
}

/**
 * 基于链表的抽象Stack实现。
 * @param <T>
 */
class StackLinkedListBased<T> implements StackInterface<T> {
    private Node<T> top;
    @Override
    public void push(T t) throws StackException {
        top=new Node<T>(t,top);
    }
    @Override
    public T pop() throws StackException {
        if (!isEmpty()){
            return (T)top.getNext().getData();
        }else {
            throw new StackException();
        }
    }
    @Override
    public T peek() throws StackException {
        if (!isEmpty()){
            return (T)top.getNext().getData();
        }else {
            throw new StackException();
        }
    }
    @Override
    public boolean isEmpty() {
        return top==null;
    }
    @Override
    public void popAll() {
        top=null;
    }
}


