package me.apqx.adt;

/**
 * Created by apqx on 2017/4/15.
 */
public class Queue {
}
//

/**
 * 表示抽象Queue接口，定义了Queue应具备的功能。
 * @param <T>
 */
interface QueueInterface<T>{
    void enqueue(T t)  throws QueueException;
    T dequeue()  throws QueueException;
    void dequeueAll();
    boolean isEmpty();
    T peek()  throws QueueException;
}
class QueueException extends Exception{}

/**
 * 基于数组的Queue实现。
 * 和数组实现的其它数据结构不同，队列是一组连续的数据，数据插入队尾却返回队首，当移除队首后不能像List那样平移数组，开销太大。
 * 应该记录队首的位置，队尾到数组末端时可以从数组头部开始添加，只要不首尾相接就行
 * @param <T>
 */
class QueueArrayBased<T> implements QueueInterface<T>{
    private T[] array;
    private final int MAX_SIZE=100;
    private int front,back,num;
    public QueueArrayBased() {
        array=(T[])new Object[MAX_SIZE];
        front=0;
        num=0;
        back=MAX_SIZE-1;
    }
    @Override
    public void enqueue(T t)  throws QueueException{
        if (!isFull()){
            back=(back+1)%MAX_SIZE;
            array[back]=t;
            num++;
        }else {
            throw new QueueException();
        }
    }
    @Override
    public T dequeue()  throws QueueException{
        if (!isEmpty()){
            T t=array[front];
            front=(front+1)%MAX_SIZE;
            num--;
            return t;
        }else {
            throw new QueueException();
        }
    }
    @Override
    public void dequeueAll() {
        array=(T[])new Object[MAX_SIZE];
        front=0;
        back=MAX_SIZE-1;
        num=0;
    }
    @Override
    public boolean isEmpty() {
        return num==0;
    }
    @Override
    public T peek()  throws QueueException{
        if (!isEmpty()){
            return array[front];
        }else {
            throw new QueueException();
        }
    }
    private boolean isFull(){
        return num==MAX_SIZE;
    }
}

