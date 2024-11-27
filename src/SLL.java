/**
 * Class to implement a singly linked list
 * @author Olohi
 * @version Fall 2024
 */

 @SuppressWarnings("rawtypes")
public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase4SLL<T>{
    
        /** The head of the node */
        private  NodeSL<T> head;
    
        /** The tail of the node */
        private  NodeSL<T> tail;
    
        /**
         * Constructor to create a new linkedlist that takes in a head
         * @param head
         */
        public SLL(NodeSL <T> head){
            this.head = head;
            this.tail = head;
        }
    
        /**
         * Constructor to create an empty linked list
         * @param head
         */
        public SLL(){
            this.head = null;
            this.tail = null;
        }

        /**
         * Constructor to create a linkedlist that is a deep copy of the linkedlist passed in
         * @param linkedList
         */
        public SLL(SLL<T> linkedList){
            /** creates an empty linkedlist if an empty linkedlist is passed */
            if (linkedList.isEmpty()){ 
                this.head = null;
                this.tail = null;
                return;
            }

            /** create a new empty linkedlist and initialize its head such that it contains same data as head of linkedlist passed in */
            NodeSL<T> headNode = new NodeSL<T>(linkedList.head.getData(), null);
            SLL<T> newLinkedList = new SLL<>(headNode);
            NodeSL<T> current = linkedList.getHead().getNext();

            /** iterate through linkedlist */
            while (current != null){
                newLinkedList.addLast(current.getData()); //append data associated with current to the linkedlist being created
                current = current.getNext();
            }
            this.head = newLinkedList.head;
            this.tail = newLinkedList.tail;
        }
        
        /**
         * accessor to return the head node
         * @return head node
         */
        public NodeSL<T>getHead() {
            return this.head;
        }
    
        /**
         * accessor to return the tail node
         * @return tail node
         */
        public NodeSL<T>getTail() {
            return this.tail;
        }
    
        /**
         * checks if the list is empty
         * @return true or false
         */
        public boolean isEmpty() {
            return (this.head == null);
        }

        /**
         * helper function that finds and returns the node before a given node
         * @param nodeA
         * @return nodeB, node before the nodeA
         */
        public NodeSL<T> findBefore(NodeSL<T> nodeA){ 
            if (this.head == nodeA){
                System.out.println("no elements exist before node A");
                throw new MissingElementException();
            }
            NodeSL<T> current = this.head; //sets current to the head node

            /** loops through linkedlist to find the node before ndoeA  */
            while (current != null){
                if (current.getNext() == nodeA){return current;}
                current = current.getNext();
            }
            return null; /** handles special case--- missing node */
        }
       
        /**
         * adds node of data v to the head of the linked list
         * @param  v
        */
        @Override
        public void addFirst(T v) {
            NodeSL<T> newNode = new NodeSL<T>(v, head); //creates a new node
            this.head = newNode; //sets the head to the new node
            if (this.head.getNext() == null){
                this.tail = newNode;} //sets the tail to the new node
        }

        /** 
         * Displays the linkedlist in a string representation
         * @return the linkedlist converted to a string
         */
        public String toString(){
            StringBuilder linkedList = new StringBuilder("["); //initialize output string
            if (this.head != null){ //collects each node and adds to the string 
                NodeSL current = this.head;

                /** loops through the linkedlist and appends the nodes' data to the sringbuilder, linkedlist */
                while (current.getNext() != null){
                    linkedList.append(current.getData()).append(", ");
                    current = current.getNext();}
                linkedList.append(current.getData());
                }
            linkedList.append("]"); //insert the closing bracket
            return linkedList.toString(); 
        }

        /**
         * appends a new node containing data, v, to the linkedlist
         * @param data to be inserted
        */
        @Override
        public void addLast(T v) {
            NodeSL<T> newNode = new NodeSL<>(v, null); //create a new node

            /** Special case--- empty list */
            if (this.head == null){
            addFirst(v);}else{

            /** Normal case */
            tail.setNext(newNode); //set tail.next to the new node
            this.tail = newNode;} //set tail to the new node}
        }

        /**
         * inserts a new node containing data, v, after node, here
         * @param here and v
        */
        @Override
        public void addAfter(NodeSL<T> here, T v) {
            // assumption-- won't have to handle edge case of here not existing
            /** create a new node */
            NodeSL<T> newNode = new NodeSL<T>(v, null);

            /** if no next on here */
            if (here.getNext() == null && this.tail == here){
                here.setNext(newNode); //set here.next to newNode
                this.tail = newNode; //update the tail
            }     
            /** if here has a next */
            else{
                newNode.setNext(here.getNext()); //set newNode.next to here.next
                here.setNext(newNode); //set here.next to newNode
            }
        }

        /**
         * removes the first element in the linkedlist
         * @return the data contained in the removed node
        */
        @Override
        public T removeFirst() {
            /** special case for empty sets */
            if (this.head == null){
                System.out.println("there is no element to remove");
                throw new MissingElementException();
            }
            /** special case for singletons */
            if (this.head == this.tail){
                NodeSL<T> temp = this.head;
                this.head = null;
                this.tail = null;
                return temp.getData();
            }
            /** normal case */
            else{
                NodeSL<T> temp = this.head;
                this.head = this.head.getNext();
                temp.setNext(null);
                return temp.getData();
            }

        }

        /**
         * removes the last element in the linkedlist
         * @return the data contained in the removed node
         */
        @Override
        public T removeLast() {
            /** Special case-- empty list */
            if (isEmpty()){
                System.out.println("list is empty so there's nothing to remove");
                throw new MissingElementException();
            }
            /** Special case-- singleton */
            else if (this.head == this.tail){
                NodeSL<T> temp = this.head;
                this.head = null;
                this.tail = null;
                return temp.getData();
            }
            /** Normal case */
            else{
            NodeSL<T> nodeBefore = findBefore(tail);
            NodeSL<T> temp = this.tail;
            nodeBefore.setNext(null);
            this.tail = nodeBefore;
            return temp.getData();
            }
        }

        /**
         * removes the element after here
         * @return the removed element or null if no element is removed
         */
        @Override
        public T removeAfter(NodeSL<T> here) {
            /** Throws an exception when list is empty or when list is a singleton */
            if (isEmpty() || this.head == this.tail && here!= null){
                System.out.println("there are no elements after here");
                throw new MissingElementException();
            }
            /** Handles case where here is null */
            else if (here==null){
                NodeSL<T> temp = this.head;
                removeFirst();
                return temp.getData();
            }
            /** Handles cases where the element to be removed is a tail*/
            else if (here.getNext() == this.tail){
                NodeSL<T> temp = here.getNext(); 
                here.setNext(null); //sets the next pointer to null
                this.tail = here; //sets the tail pointer to here
                return temp.getData(); //returns the data of the removed node
            }
             /** Handles normal cases*/
            else{
                NodeSL<T> temp = here.getNext();  
                here.setNext(here.getNext().getNext()); //sets the next pointer to skip the connected node (node to be removed)
                return temp.getData(); //returns the data of the removed node
            }
        }

        /**
         * returns the number of nodes in the linkedlist
         * @return size of the linkedlist
        */
        @Override
        public int size() {
            int length = 0; //initializes the length to 0
            NodeSL<T> current = this.head;

            /** loops through the linkedlist and counts the non-null nodes */
            while (current!=null){
                length ++;
                current = current.getNext();
            }
            return length;
        }

        /**
         * splice a copy of the passed in list into the active list, as in activelist.splice(list, afterHere)
         * @param list, afterHere
        */
        @Override
        public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
            if (this.head == list.head){
                System.out.println("you attempted to splice a list into itself");
                throw new SelfInsertException();
            }
            SLL<T> listCopy = new SLL<>(list); //creates a deep copy of list
            /** special case-- list is null, simply do nothing */
            if (list.getHead() == null){
                return;
            }
            /** special case-- afterHere or afterHere.getData() is null */
            if (afterHere == null){
                afterHere = new NodeSL<T>(null, afterHere);
            }
            if (afterHere.getData() == null){
                listCopy.tail.setNext(this.head); //set listCopy to be head of original list
                this.head = listCopy.getHead();
                return;
            }
           /** normal case */
            NodeSL<T> temp = afterHere.getNext();
            listCopy.tail.setNext(afterHere.getNext());
            afterHere.setNext(listCopy.getHead());
            }
        
        /**
         * splice the passed in list into the active list, as in activelist.splice(list, afterHere)
         * @param list, afterHere
        */
        @Override
        public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
            /** special case-- splicing a list into itself */
            if (this.head == list.getHead()){
                System.out.println("you attempted to splice a list into itself");
                throw new SelfInsertException();
            }
            /** special case-- list is empty */
            if (list.head == null){
                return; 
            }
            /** special case-- afterHere is null */
            if (afterHere == null){afterHere = new NodeSL<T>(null, null);}
            if (afterHere.getData() == null){
                list.tail.setNext(this.head);
                this.head = list.head;
            }
            /** normal case */
            else{
                list.tail.setNext(afterHere.getNext()); //connects the list to be spliced to the nodes after afterHere
                afterHere.setNext(list.getHead()); //sets afterHere to point to the list to be spliced
                if (this.tail == afterHere){
                    this.tail = list.getTail(); //sets the tail of the original list to the tail of the spliced-in list if afterHere is the tail
                }
            }
            /** set the original list to null */
            list.head = null;
            list.tail = null;
        }

        /**
         * slices a subsequence of the list without chaging the original list
         * @param here and n, the node to be sliced after and number of nodes to be returned
         * @return subseq, the sliced sequence
         */
        @Override
        public SLL<T> subseqByCopy(NodeSL<T> here, int n) {
            NodeSL<T> headNode = new NodeSL<T>(here.getData(), null); //create new head node
            SLL<T> subseq = new SLL<>(headNode); //create new linkedlist, with its head set to the head node above
            NodeSL<T> current = here.getNext(); 
            int i = 1; //counter intialized to one because head has been counted already

            /** iterate through linkedlist */
            while (i < n) {
                if (current == null){
                    throw new RuntimeException("original list is too short"); //throws if original list is shorter than requested subseq
                }
                subseq.addLast(current.getData());
                current = current.getNext();
                i+=1;
            }
            return subseq;
        }

        /**
         * slices a subsequence of the list from afterHere to toHere
         * the original list is mutated
         * @param afterHere and toHere, the node to be sliced after and node to be sliced up to
         * @return subseq, the sliced sequence
         */
        @SuppressWarnings("unused")
        @Override
        public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere) {
            SLL<T> newLinkedList = new SLL<>(); //creates a newlinkedlist (the to-be subsequence)
            /** special case-- result is empty */
            if (afterHere == null){
                afterHere = new NodeSL<T>(null, null); //creates an empty node to prevent confusions with getData()
            }
            if (toHere == null){
                toHere = new NodeSL<T>(null, null); //creates an empty node
            }
            if (isEmpty() || afterHere == toHere || (afterHere.getData() == null & toHere.getData() == null)){
                return null;
            }
            /** special case-- afterHere is null */
            if (afterHere.getData() == null){
                newLinkedList.head = this.getHead();
                newLinkedList.tail = toHere;
                this.head = toHere.getNext();
                newLinkedList.tail.setNext(null);
                System.out.println(newLinkedList);
                return newLinkedList;
            }
            /** special case-- toHere is null */
            if (toHere.getData() == null){
                toHere = this.getTail();
            }
            /** Normal case */
            newLinkedList.head = afterHere.getNext();
            System.out.println("extracted linkedlist is now" + newLinkedList);
            afterHere.setNext(toHere.getNext()); //updates afterHere to point to the node after toHere
            newLinkedList.tail = toHere; 
            newLinkedList.tail.setNext(null);
            System.out.println(newLinkedList);
            return newLinkedList; 
        }


        // public static void main(String[] args) {
        //     SLL<String> newLinkedList = new SLL<>();
        //     newLinkedList.addFirst("D");
        //     newLinkedList.addLast("A");
        //     newLinkedList.addLast("C");
        //     System.out.println(newLinkedList + " when created.");
        //     SLL<String> splicedList = new SLL<>();
        //     splicedList.addFirst("E");
        //     splicedList.addLast("B");
        //     System.out.println(splicedList + " list to be spliced");
        //     NodeSL emptyNode = new NodeSL<>(null, null);
        //     newLinkedList.spliceByCopy(splicedList, emptyNode);
        //     System.out.println(newLinkedList + " when spliced after an empty node");
        // }

 }
