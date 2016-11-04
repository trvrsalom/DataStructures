public class SortsA6_Salom {
   public static void main(String[] args) {
      System.out.println("Hey");
      YoArrayList l = new YoArrayList(3);
      l.add(1);
      l.add(2);
      l.add(3);
      l.add(4);
      l.add(5);
      l.add(99, 3);
			System.out.println(l.size());
      for(int i = 0; i < l.size(); i++) {
         System.out.print(l.get(i) + ", ");
      }
   }
}

class YoArrayList {
   private int size;
   private Object[] arr;
   private int desiredSize;

   public YoArrayList(int s) {
      arr = new Object[s];
      desiredSize = s;
      size = 0;
   }

   public void add(Object o) {
      add(o, size);
   }

   public int size() { return size; }

   public Object get(int loc) {
      if(loc >= size) throw new IndexOutOfBoundsException();
      return arr[loc];
   }

   public void add(Object o, int location) {
      if(location > size-1) {
         desiredSize *= 2;
         Object[] newArr = new Object[desiredSize];
         for(int i = 0; i < arr.length; i++) newArr[i] = arr[i];
         arr = newArr;
         location = size;
      }
      for(int i = size; i >= location; i--) {
         arr[i + 1] = arr[i];
      }
      arr[location] = o;
      size++;
   }

	 public Object remove(int location) {
		 if(location > size-1) throw new IndexOutOfBoundsException();
		 Object ret = get(location);
		 for(int i = location; i < size-1; i++) arr[i] = arr[i+1];
		 arr[size--] = null;
		 return ret;
	 }
}
