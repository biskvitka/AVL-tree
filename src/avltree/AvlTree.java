package avltree;


public class AvlTree {
    
    private AvlNode root;
    
    
    /**
     * Default Constructor
     */
    public AvlTree(){
        this.root = null;
        
    }
    
    
    /**
     * Rotates the node so the node from the right side goes on its place taking it as its left child 
     * @param node - the parent node that will be rotated so it will become a child node
     * @return the node that was a right child but now is the parent of its parent
     */
    private AvlNode rotateLeft(AvlNode node){
        AvlNode right = node.getRight();
        
        right.setParent(node.getParent());
        node.setRight(right.getLeft());
        
        if(node.getRight() != null){
            node.getRight().setParent(node);
        }
        
        right.setLeft(node);
        node.setParent(right);
        
        if(right.getParent() != null){
            if(right.getParent().getRight() == node){
                right.getParent().setRight(right);
            }
            else if(right.getParent().getLeft() == node){
                right.getParent().setLeft(right);
            }
        }
        
        setBalance(node);
        setBalance(right);
        
        return right; 
    }
    
    /**
     * Same as the previous function but the left child is rotated
     * @param node
     * @return 
     */
    private AvlNode rotateRight(AvlNode node){
        AvlNode left = node.getLeft();
        
        left.setParent(node.getParent());
        node.setLeft(left.getRight());
        
        if(node.getLeft()!= null){
            node.getLeft().setParent(node);
        }
        
        node.setParent(left);
        left.setRight(node);
        
        if(left.getParent() != null){
            if(left.getParent().getRight() == node){
                left.getParent().setRight(left);
            }
            else{
                left.getParent().setLeft(left);
            }
        }
        
       
        setBalance(node);
        setBalance(left);
        
        return left;
    }
    
    /**
     * Rotates 2 times
     * @param node
     * @return 
     */
    private AvlNode doubleRotateLeftRight(AvlNode node){
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }
    
    /**
     * Same as previous but the opposite 
     * @param node
     * @return 
     */
    private AvlNode doubleRotateRightLeft(AvlNode node){
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }
    
    
    /**
     * Function for balancing the tree
     * @param node (root) that will be balanced
     */
    private void balanceNode(AvlNode node){
        
        setBalance(node);
        int balance = node.balance;
        
        if (balance == -2){
            if(height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())){
                node = rotateRight(node);
            }
            else{
                node = doubleRotateLeftRight(node);
            }
        }
        else if(balance == 2){
            if(height(node.getRight().getRight()) >= height(node.getRight().getLeft())){
                node = rotateLeft(node);
            }
            else{
                node = doubleRotateRightLeft(node);
            }
        }
        
        if(node.getParent() != null){
            balanceNode(node.getParent());
        }
        else{
            this.root = node;
        }
        
    }
    
    
    /**
     * Set the "balance" parameter of the node
     * @param node that will be balanced
     */
    private void setBalance(AvlNode node){
        node.balance = height(node.getRight()) - height(node.getLeft());
    }
    

    /**
     * Shows what is the height of some node
     * @param n - The node 
     * @return - The height of the node
     */
    private int height(AvlNode n) {
        if (n == null)
            return -1;
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }
    
    public void insert(int element){
        AvlNode newNode = new AvlNode(element);
        
        if(this.root == null){
            this.root = newNode;
        }
        else{
            insertNode(this.root, newNode);
            
        }
    }
    
    /**
     * This function inserts a new node in the tree
     * @param node the root of the tree
     * @param newNode  the node that will be inserted in the tree
     */
    private void insertNode(AvlNode node,AvlNode newNode){
        
        if (newNode.getKey() < node.getKey()){
            if(node.getLeft() == null){
                node.setLeft(newNode);
                newNode.setParent(node);
                balanceNode(node);
            }
            else{
                insertNode(node.getLeft(), newNode);
            }            
        }
        
        else if(newNode.getKey() > node.getKey()){
            if(node.getRight() == null){
                node.setRight(newNode);
                newNode.setParent(node);
                balanceNode(node);
                
            }
            else{
                insertNode(node.getRight(), newNode);
            }         
        }
    }
    
    /**
     * Deletes an element 
     * @param element 
     */
    public void remove(int element){
        removeNode(this.root, element);
    }
    
    private void removeNode(AvlNode node, int element){
        if(node == null){
            return;
        }
        else if(node.getKey() > element){
            removeNode(node.getLeft(), element);
        }
        else if(node.getKey() < element){
            removeNode(node.getRight(), element);
        }
        
        //If the node is found
        else{
            removeFoundNode(node);
        }
    }
    
    private void removeFoundNode(AvlNode node){
            //If the node doesn't have children
            if(node.getLeft() == null && node.getRight() == null ){
                if(node == this.root){
                    node = null;
                }
                else if(node.getParent().getLeft() == node){
                    node.getParent().setLeft(null);
                }
                else{
                    node.getParent().setRight(null);
                }
            }
            
            //If the node has only right child
            else if(node.getLeft() == null){
                if(node == this.root){
                    node.getRight().setParent(null);
                    this.root = node.getRight();
                }
                else{
                    node.getRight().setParent(node.getParent());
                
                    if(node.getParent().getLeft() == node){
                        node.getParent().setLeft(node.getRight());
                    }
                    else{
                        node.getParent().setRight(node.getRight());
                    }
               
                }
                
            }
            
            //If the node has only left child
            else if(node.getRight() == null){
                
                if(node == this.root){
                    node.getLeft().setParent(null);
                    this.root = node.getLeft();
                }
                else{
                    node.getLeft().setParent(node.getParent());
                    if(node.getParent().getLeft() == node){
                        node.getParent().setLeft(node.getLeft());
                    }
                    else{
                        node.getParent().setRight(node.getLeft());
                    }
                }
            }   
            
            //If the node has 2 children
            else{
                if(node == this.root){
                    int max = findMax(node.getLeft());
                    remove(max);
                    node.setKey(max);
                }
                else if(node.getParent().getRight() == node){
                    int min = findMin(node);
                    remove(min);
                    node.setKey(min);
                }
                else{
                    int max = findMax(node);
                    remove(max);
                    node.setKey(max);
                }
                
                balanceNode(root);
            }
        
    }
    
    /**
     * Finds the node with max key in the branch with root node
     * @param node - the root of the branch
     * @return the max key in the branch
     */
    private int findMax(AvlNode node){
        if(node.getRight() == null){
            return node.getKey();
        }
        return findMax(node.getRight());
    }
    
    private int findMin(AvlNode node){
        if(node.getLeft() == null){
            return node.getKey();
        }
        return findMin(node.getLeft());
    }
    
   
    
    /**
     * This functions sums the size of the tree
     * @return size of the tree
     */
    public int size(){
        return sizeOfNode(this.root);
    }
    
    /**
     * This function sums the size of the tree whose node n is its root
     * @param n the tree's root
     * @return the size of the tree with root " n "
     */
    public int sizeOfNode(AvlNode n) {
        
        if (n == null) {
            return 0;
        }
        
        return sizeOfNode(n.getLeft()) + sizeOfNode(n.getRight()) + 1;
    }
    
    
    
    public void inorder(AvlNode n) {
        if (n == null) {

            return;
        }

        inorder(n.getLeft());
        System.out.print(n.getKey() + "    ");
        
//        System.out.print(n.getKey() + "    ");
        inorder(n.getRight());
//        System.out.println();
       
    }
    
    public void iterateTheTree(){
        inorder(this.root);
    }
    
    
    public boolean searchNode(AvlNode node, int key){
        System.out.println("hello");
        if (node == null) {
            return false;
        }
        else if(node.getKey() == key){
            return true;
        }
        else if(key > node.getKey()){
            return searchNode(node.getRight(), key);
        }
        else{
            return searchNode(node.getLeft(), key);
        }
        
    }
    
    
    
    public boolean search(int key){
        return searchNode(this.root, key);
    }


 

    
    public static void main(String[] args) {
        // TODO code application logic here
        AvlTree a = new AvlTree();
        a.insert(12);
        a.insert(8);
        a.insert(6);
        a.insert(32);
        a.insert(54);
        a.insert(89);     
        a.insert(-12);
        a.insert(1);
        
        a.remove(5);
        a.iterateTheTree();
        System.out.println(a.root.getKey());
    }
    
}
