import java.io.*;
import java.util.*;

public class Buddy {
  // Inner class for domain
  class Pair {
    
    int lowb, upb;
    Pair(int a, int b)
    {
      lowb = a;
      upb = b;
    }
  }
  
  int size;
  
  // Array for pair
  ArrayList<Pair> arr[];
  
  // Hashmap for store address & size
  HashMap<Integer, Integer> hm;
  
  // Warning for another compiler 
  @SuppressWarnings("unchecked")
  Buddy(int s)
  {
    
    size = s;
    hm = new HashMap<>();
    
    int x = (int)Math.ceil(Math.log(s) / Math.log(2));
    
    arr = new ArrayList[x + 1];
    
    for (int i = 0; i <= x; i++)
      arr[i] = new ArrayList<>();
    
    arr[x].add(new Pair(0, size - 1));
  }
  
  void allocate(int s)
  {
    
    int x = (int)Math.ceil(Math.log(s) / Math.log(2));
    
    int i;
    Pair temp = null;
    
    if (arr[x].size() > 0) {
      
      temp = (Pair)arr[x].remove(0);
      System.out.println("Memory from " + temp.lowb+ " to " + temp.upb + " allocated");
      
      hm.put(temp.lowb, temp.upb - temp.lowb + 1);
      return;
    }
    
    for (i = x + 1; i < arr.length; i++) {
      
      if (arr[i].size() == 0)
        continue;
      
      break;
    }
    
    if (i == arr.length) {  
      System.out.println("Sorry, failed to allocate memory");
      return;
    }
    
    temp = (Pair)arr[i].remove(0);
    
    i--;
    
    for (; i >= x; i--) {
      
      Pair newPair = new Pair(temp.lowb,temp.lowb + (temp.upb - temp.lowb) / 2);
      
      Pair newPair2 = new Pair(temp.lowb + (temp.upb - temp.lowb + 1) / 2,temp.upb);
      
      arr[i].add(newPair);
      arr[i].add(newPair2);
      
      temp = (Pair)arr[i].remove(0);
    }
    
    System.out.println("Memory from " + temp.lowb + " to " + temp.upb + " allocated");
    
    hm.put(temp.lowb,temp.upb - temp.lowb + 1);
  }
  
  void deallocate(int s)
  {
    if (!hm.containsKey(s)) {
      System.out.println("Sorry, invalid free request");
      return;
    }
    
    int x = (int)Math.ceil(Math.log(hm.get(s))/ Math.log(2));
    int i, buddyNumber, buddyAddress;
    
    arr[x].add(new Pair(s, s + (int)Math.pow(2, x) - 1));
    System.out.println("Memory block from " + s + " to " + (s + (int)Math.pow(2, x) - 1) + " freed");
    
    buddyNumber = s / hm.get(s);
    
    if (buddyNumber % 2 != 0) {
      buddyAddress = s - (int)Math.pow(2, x);
    }
    else {
      buddyAddress = s + (int)Math.pow(2, x);
    }
    
    for (i = 0; i < arr[x].size(); i++) {
      
      if (arr[x].get(i).lowb == buddyAddress) {
        
        if (buddyNumber % 2 == 0) {
          arr[x + 1].add(new Pair(s,s+ 2 * ((int)Math.pow(2, x)) - 1));
          System.out.println("Coalescing of blocks starting at "+ s + " and "+ buddyAddress + " was done");
        }
        else {      
          arr[x + 1].add(new Pair(buddyAddress,buddyAddress + 2 * ((int)Math.pow(2, x))- 1));
          System.out.println("Coalescing of blocks starting at "+ buddyAddress + " and "+ s + " was done");
        }
        arr[x].remove(i);
        arr[x].remove(arr[x].size() - 1);
        break;
      }
    }
    
    hm.remove(s);
  }
  
  public static void main(String args[]) throws IOException
  {
 // Initialization in the function
    int initialMemory = 1028;
    Buddy obj = new Buddy(initialMemory);
    obj.allocate(100);
    obj.allocate(1028);
    obj.allocate(64);
    obj.allocate(256);
    obj.deallocate(240);
    obj.deallocate(0);
    obj.allocate(75);
    obj.deallocate(64);
    obj.deallocate(32);
  }
}