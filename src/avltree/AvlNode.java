package avltree;


public class AvlNode {
    private int key;
    private AvlNode left, right, parent;
    protected int balance;
    
    
    /**
     * Parameterised Constructor with key value
     * @param key sets t
     */
    public AvlNode(int key){
        this.key = key;
        this.left = this.right = this.parent = null;
        this.balance = 0;
    }
    
    /**
     * Parameterised Constructor
     * @param key
     * @param left
     * @param right 
     */
//    public AvlNode(int key, AvlNode left, AvlNode right, AvlNode parent){
//        this.key = key;
//        this.left = left;
//        this.right = right;
//        this.parent = parent;
//    }
    
    /**
     * Gets the key value of the current Node 
     * @return the key value of the Node
     */
    public int getKey(){
        return this.key;
    }
    
    /**
     * Gets the balance of the node
     * @return balance
     */
    public int getBalance(){
        return this.balance;
    }
    
    /**
     * Gets the left Node of the current Node
     * @return the left Node
     */
    public AvlNode getLeft(){
        return this.left;
    }
    
    /**
     * Gets the right Node of the current Node
     * @return the right node
     */
    public AvlNode getRight(){
        return this.right;
    }
    
    
    /**
     * Gets the parent node
     * @return the parent of the Node 
     */
    public AvlNode getParent(){
        return this.parent;
    }
    /**
     * Sets the key value of the Node 
     * @param key the value of the Node  
     */
    public void setKey(int key){
        this.key = key;
    }
    
    /**
     * Sets the left Node 
     * @param leftNode 
     */
    public void setLeft(AvlNode left){
        
        this.left = left;
    }
    
    /**
     * Sets the right Node
     * @param right
     */
    public void setRight(AvlNode right){
        
        this.right = right;
    }
    
    
    /**
     * Sets the parent of the Node 
     * @param parent 
     */
    public void setParent(AvlNode parent){
        this.parent = parent; 
    }
    

    
}
