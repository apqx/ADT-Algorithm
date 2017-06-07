package me.apqx.adt;

import java.util.LinkedList;
/**
 * Created by apqx on 2017/6/7.
 */
public class Graph {
    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        GraphNode n5 = new GraphNode(5);

        n1.neighbors = new GraphNode[]{n2,n3,n5};
        n2.neighbors = new GraphNode[]{n1,n4};
        n3.neighbors = new GraphNode[]{n1,n4,n5};
        n4.neighbors = new GraphNode[]{n2,n3,n5};
        n5.neighbors = new GraphNode[]{n1,n3,n4};
        breathFirstSearch(n1,3);
        depthFirstSearch(n1,3);
    }

    //广度优先搜索
    private static void breathFirstSearch(GraphNode root,int value){
        if (root.value==value){
            System.out.println("find in root");
        }
        SortQueue queue=new SortQueue();
        root.visited=true;
        queue.enqueue(root);
        while (queue.first!=null){
            GraphNode node=(GraphNode)queue.dequeue();
            for (GraphNode graphNode:node.neighbors){
                if (!graphNode.visited){
                    System.out.print(graphNode+" ");
                    graphNode.visited=true;
                    if (graphNode.value==value){
                        System.out.println("Find "+graphNode);
                    }
                    queue.enqueue(graphNode);
                }
            }
        }
    }
    //深度优先搜索
    private static void depthFirstSearch(GraphNode root,int value){
        if (root.value==value){
            System.out.println("find in root");
        }
        root.visited=true;
        for (GraphNode node:root.neighbors){
            if (!node.visited){
                System.out.print(node+" ");
                if (node.value==value){
                    System.out.println("Find "+node);
                }
                depthFirstSearch(node,value);
            }
        }
    }
}

//顶点
class GraphNode{
    int value;
    GraphNode[] neighbors;
    GraphNode next;
    boolean visited;

    GraphNode(int value){
        this.value=value;
    }

    GraphNode(int value,GraphNode[] neighbors){
        this.value=value;
        this.neighbors=neighbors;
    }

    @Override
    public String toString() {
        return "value = "+value;
    }
}

//用于广度优先搜索的队列
class SortQueue{
    GraphNode first, last;

    public void enqueue(GraphNode n){
        if(first == null){
            first = n;
            last = first;
        }else{
            last.next = n;
            last = n;
        }
    }

    public GraphNode dequeue(){
        if(first == null){
            return null;
        }else{
            GraphNode temp = new GraphNode(first.value, first.neighbors);
            first = first.next;
            return temp;
        }
    }
}


