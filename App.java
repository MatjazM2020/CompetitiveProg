import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
 
public class App{
    public static void main(String[] args){
      //************** reading user input ************************** 
      Scanner sc = new Scanner(System.in);
      int as = Integer.parseInt(sc.nextLine()); //arrangement doubleList size 
      ArrayList<String> chg = new ArrayList<>(); 
      DoublyLinked arr = new DoublyLinked(); //create a doublyLinked list instance
      HashMap<String,DoublyLinked.Node> mapa = new HashMap<>(); 
      for(int i = 0; i < as; i++){
         String x = sc.nextLine();
         mapa.put(x,arr.insert(x)) ; 
      }
      int cs = Integer.parseInt(sc.nextLine()); //changes arrayList size
      for (int i = 0; i < cs; i++){
          chg.add(sc.nextLine());
      }
      //iteracija skozi changes seznam 
      //prvo delete, in pol urines pred unga k hoces prehitet 
      for (int i = 0; i < cs; i++){
        String[] pair = chg.get(i).split(" "); 
        arr.delete(mapa.get((chg.get(i)).split(" ")[0])); 
        DoublyLinked.Node x = (arr.insertBefore((mapa.get(pair[1])),pair[0])); 
        mapa.put(pair[0],x);  
      }
      sc.close();
      //********************************************* */
      arr.print();  //print out the result 
  }
}
 
 
class DoublyLinked{
  Node head; //ref to the first element
  Node tail; //ref to the last element
 
  class Node{
    String data;
    Node next;
    Node prev; 
    Node(String data){
      this.data = data; 
    }
  }
  public Node insert(String data){
    Node newNode = new Node(data); 
    if(head == null){
      head = tail = newNode; 
      head.prev = null;
      tail.next = null; 
    }else{
      (tail.next) = newNode; 
      newNode.prev = tail; 
      newNode.next = null; 
      tail = newNode; 
    }
    return newNode; 
  }
  public void delete(Node toDel){
 
    if(toDel.prev == null){
      if(toDel.next == null){//case when we only have one node
        head = tail = null; 
      }else{
         head = toDel.next; 
        (toDel.next).prev = null; 
      }
    }else if(toDel.next == null){
         tail = toDel.prev; 
        (toDel.prev).next = null; 
    }else{
        (toDel.next).prev = toDel.prev;
        (toDel.prev).next = toDel.next;
    }
  }
  public Node insertBefore(Node beforeThis, String replace){
    Node newNode = new Node(replace); 
 
    if(beforeThis.prev == null){
         newNode.next = beforeThis; 
         newNode.prev = null;
         beforeThis.prev = newNode; 
         head = newNode; 
    }else{
       newNode.prev = beforeThis.prev; 
       (beforeThis.prev).next = newNode;
       newNode.next = beforeThis; 
       beforeThis.prev = newNode; 
    }
    return newNode; 
  }
  public void print(){ 
    Node propagate = head; 
    while(propagate != null){
    System.out.println(propagate.data);
     propagate = propagate.next; 
    }
  }
 
}