/** Keeps track of position in a linked list */
public class SLL_Iterator<T> implements Phase5SLL_Iterator<T> {
    /** list being iterated through */
    private SLL<T> list;

    /** position is on either side of the node pointed to */
    private NodeSL<T> position;

    /** leftmost attribute: are we on left or right? */
    private boolean leftmost;

    /** lookedOver pointer points to node we've looked over already */
    private NodeSL<T> lookedOver;

    /**
     * Creates a new iterator on the given list.
     * Default position is leftmost
     * 
     * @param list the list to iterate on
     */
    public SLL_Iterator(SLL<T> list) {
       this.position = list.getHead();
       this.leftmost = true;
       this.lookedOver = null;
       this.list = list;
    }

    /**
     * Tests whether there are any more
     * 
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext() {
        /** checks there is a head if leftmost is true */
        if (this.leftmost == true){
            return this.position != null;}
        /** if leftmost is false, checks if there is an element that has not been looked over */
        return (this.lookedOver.getNext() != null);
    }

    /**
     * Returns next or throws an exception
     * and advances the iterator
     * 
     * @return the next element
     */
    public T next() {
        if (hasNext()){
            if (leftmost == true){leftmost = false; } //update leftmost if needed
            this.lookedOver = position; //store current position as lookedOver
            position = position.getNext(); //move the pointer
            return this.lookedOver.getData(); //return the lookedOver node
        }
        else{
            throw new MissingElementException(); //throw exception if there is in fact, no next
        }
    }

    /**
     * Sets the data for the element just passed
     * 
     * @param data value to set
     */
    public void set(T data) {
       /** sets data of lookedOver Node*/
       if (lookedOver != null){
        lookedOver.setData(data);}
       else{
        throw new MissingElementException();
       }
    }

    /**
     * Returns the data for the element just passed
     * 
     * @return data value in the element just passed
     */
    public T get() {
       /** returns the data of the lookedOver Node if present */
       if (lookedOver != null){
        return lookedOver.getData();}
       else{
        throw new MissingElementException();
       }
    }

    /**
     * Inserts a node with the specified data
     * Cannot be called twice in a row without intervening next()
     * 
     * @param data the value to insert
     */
    public void add(T data) {
        //implement add separately
        NodeSL<T> dataNode = new NodeSL<T>(data, null);
        if (lookedOver == null){
            System.out.println("lookedover is " + lookedOver);
            if (leftmost == true){
                this.list.addFirst(data);}
            else{
                throw new MissingElementException();}
        }
        else if (lookedOver != null){
            System.out.println("lookedover is " + lookedOver);
            this.list.addAfter(lookedOver, data);}
        
        System.out.println(this.list);
    }

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove() {
        // TODO
    }
}