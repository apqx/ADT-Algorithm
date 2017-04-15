package me.apqx.adt;

/**
 * Created by apqx on 2017/4/15.
 * 二叉树的基于引用的实现
 */
public class Tree {
    public static void main(String[] args){

    }
}

/**
 * 树的节点，每一个树的节点包含数据，左右两个子节点的引用
 * @param <T>
 */
class TreeNode<T>{
    private T data;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    public TreeNode(T data){
        this.data=data;
        leftChild=null;
        rightChild=null;
    }
    public TreeNode(T data,TreeNode<T> leftChild,TreeNode<T> rightChild){
        this.data=data;
        this.leftChild=leftChild;
        this.rightChild=rightChild;
    }
    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data=data;
    }
    public TreeNode<T> getLeftChild(){
        return leftChild;
    }
    public void setLeftChild(TreeNode<T> leftChild){
        this.leftChild=leftChild;
    }
    public TreeNode<T> getRightChild(){
        return rightChild;
    }
    public void setRightChild(TreeNode<T> rightChild){
        this.rightChild=rightChild;
    }
}
class TreeException extends RuntimeException{
    public TreeException(String string){
        super(string);
    }
}

/**
 * 二叉树的基本定义，是个抽象类。
 * 每个二叉树包含一个根节点，树与树之间的父子关系由根节点来记录，因为每个节点都保存着指向两个子节点的引用。
 * @param <T>
 */
abstract class BinaryTreeBasis<T>{
    protected TreeNode<T> root;
    public BinaryTreeBasis(){
        root=null;
    }
    public BinaryTreeBasis(T rootData){
        root=new TreeNode<T>(rootData,null,null);
    }
    public boolean isEmpty(){
        return root==null;
    }
    public void makeEmpty(){
        root=null;
    }
    public T getRootData() throws TreeException{
        if (root==null){
            throw new TreeException("");
        }else {
            return root.getData();
        }
    }
    public abstract void setRootData(T rootData);
}

/**
 * 具体的二叉树
 * @param <T>
 */
class BinaryTree<T> extends BinaryTreeBasis{
    public BinaryTree(){

    }
    public BinaryTree(T rootData){
        super(rootData);
    }
    public BinaryTree(TreeNode<T> root){
        this.root=root;
    }
    public BinaryTree(T rootData,BinaryTree<T> leftTree,BinaryTree<T> rightTree){
        root=new TreeNode(rootData,null,null);
    }
    @Override
    public void setRootData(Object rootData) {

    }

    /**
     * 设置本树的左子树，实际上是通过设置根节点的左子节点实现的
     * @param leftTree
     * @throws TreeException
     */
    public void attachLeftSubTree(BinaryTree<T> leftTree) throws TreeException{
        if (isEmpty()){
            throw new TreeException("Empty tree");
        }else if (root.getLeftChild()!=null){
            throw new TreeException("Cannot override left subtree");
        }else {
            root.setLeftChild(leftTree.root);
        }
    }

    /**
     * 设置本树的右子树，实际上是通过设置根节点的右子节点实现的
     * @param rightTree
     * @throws TreeException
     */
    public void attachRightSubTree(BinaryTree<T> rightTree) throws TreeException{
        if (isEmpty()){
            throw new TreeException("Empty tree");
        }else if (root.getRightChild()!=null){
            throw new TreeException("Cannot override right subtree");
        }else {
            root.setRightChild(rightTree.root);
        }
    }
    public BinaryTree<T> detachLeftSubTree() throws TreeException{
        if (isEmpty()){
            throw new TreeException("Empty tree");
        }else{
            BinaryTree<T> leftBinaryTree=new BinaryTree<T>(root.getLeftChild());
            root.setLeftChild(null);
            return leftBinaryTree;
        }
    }
    public BinaryTree<T> detachRightSubTree() throws TreeException{
        if (isEmpty()){
            throw new TreeException("Empty tree");
        }else{
            BinaryTree<T> rightBinaryTree=new BinaryTree<T>(root.getRightChild());
            root.setRightChild(null);
            return rightBinaryTree;
        }
    }
    public void attachLeft(T data){
        if (!isEmpty()&&root.getLeftChild()!=null){
            root.setLeftChild(new TreeNode<T>(data,null,null));
        }
    }
    public void attachRight(T data){
        if (!isEmpty()&&root.getRightChild()!=null){
            root.setRightChild(new TreeNode<T>(data,null,null));
        }
    }
}


