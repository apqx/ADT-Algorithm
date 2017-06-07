package me.apqx.adt;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by apqx on 2017/5/2.
 */
public class Heap<T> {
    private ArrayList<T> items;
    private Comparator<? super T> comparator;
    public Heap(){
        items=new ArrayList<T>();
    }
    public Heap(Comparator<? super T> comparator){
        items=new ArrayList<T>();
        this.comparator=comparator;
    }
    public boolean heapIsEmpty(){
        return items.size()==0;
    }

    /**
     * 插入一个元素，原理是先插入到树的叶节点，再上移到合适的位置。
     * @param newItem 要插入的数据
     * @throws Exception
     */
    public void heapInsert(T newItem) throws Exception{
        if (!items.add(newItem)){
            throw new Exception("Insert failed");
        }else {
            int place=items.size()-1;
            int parent=(place-1)/2;
            while ((parent>=0)&&(compareItems(items.get(place),items.get(parent)))<0){
                T temp=items.get(parent);
                items.set(parent,items.get(place));
                items.set(place,temp);
                place=parent;
                parent=(place-1)/2;
            }
        }
    }

    /**
     * 删除根节点，将最后一个页节点上移到根节点，然后重建堆。
     * @return 根节点
     */
    public T heapDelete(){
        T rootItem=null;
        int location;
        if (!heapIsEmpty()){
            rootItem=items.get(0);
            location=items.size()-1;
            items.set(0,items.get(location));
            items.remove(location);
            heapRebuild(0);
        }
        return rootItem;
    }

    /**
     * 重建堆，将根节点下移到合适的位置
     * @param root 根节点
     */
    private void heapRebuild(int root){
        int child=2*root+1;
        if (child<items.size()){
            int rightChild=child+1;
            if (rightChild<items.size()&&compareItems(items.get(rightChild),items.get(child))<0){
                child=rightChild;
            }
            if (compareItems(items.get(root),items.get(child))>0){
                T temp=items.get(root);
                items.set(root,items.get(child));
                items.set(child,temp);
                heapRebuild(child);
            }
        }
    }
    private int compareItems(T item1,T item2){
        if (comparator==null){
            return ((Comparable<T>)item1).compareTo(item2);
        }else {
            return comparator.compare(item1,item2);
        }
    }
}
