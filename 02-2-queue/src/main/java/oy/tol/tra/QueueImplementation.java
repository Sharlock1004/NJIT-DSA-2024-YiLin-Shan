package oy.tol.tra;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete
 * StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value
 * of 10.
 * - StackImplementation(int size), which allocates an array of Object's with
 * size.
 * -- remember to maintain the capacity and/or currentIndex when the stack is
 * manipulated.
 */
@SuppressWarnings("unused")
public class QueueImplementation<E> implements QueueInterface<E> {

   private Object[] itemArray;
   private int capacity;
   private int size=0;
   private int head=0;
   private int tail=0;
   


   public QueueImplementation(int capacity) throws QueueAllocationException {
      if (capacity <= 0) {
         throw new QueueAllocationException("Stack capacity must be at least 2");
      }
      try {
         itemArray = new Object[capacity];
      } catch (Exception e) {
         throw new QueueAllocationException("Failed to allocate room for the internal array.");
      }
      this.capacity = capacity;
      
   }


   @Override
   public int capacity() {
      return capacity;
   }

   @Override
   public void enqueue(E element) throws QueueAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException("The element is null");
      }
      if (tail >= capacity ) {
         try {
            int newCapacity = 2 * capacity;
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < capacity; i++) {
               newArray[i] = itemArray[i];
            }
            itemArray = newArray;
            capacity = newCapacity;
         } catch (OutOfMemoryError e) {
            throw new QueueAllocationException("Fail to allocate more room for the stack.");

         }
         
      }
      itemArray[tail] = element;
      tail++;
      if(head==capacity-1){
         tail=0;}
         size++;
      
     
   }

   @SuppressWarnings("unchecked")
   @Override
   public E dequeue() throws QueueIsEmptyException {
      if (head==tail) {
         throw new QueueIsEmptyException("Stack is empty");
      }
      Object dequeueElement=itemArray[head];
      head++;
      if(head>=capacity){
         head=0;
      }
      size--;
      return(E) dequeueElement;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E element() throws QueueIsEmptyException {
      if (head==tail) {
         throw new QueueIsEmptyException("Stack is empty");
      }
      Object elemenObject=itemArray[head];
      return (E)elemenObject;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void clear() {
       head=0;
       tail=0;
       size=0;
   }

   @Override
   public boolean isEmpty() {
     if(head==tail&& size==0){
      return(true);
     }else{
      return(false);
     }

   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <=(size-1); index++) {
      if(head+index>=capacity){
         builder.append(itemArray[index+head-capacity].toString());
      }else{
         builder.append(itemArray[index+head].toString());
      }
      if (index<size-1) {
         builder.append(", ");
      }
      }
      builder.append("]");
      return builder.toString();
   }
}
