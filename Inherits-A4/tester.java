public class tester {
	public static void main(String[] args) {
		B b = new D1();
		B b2 = new D2();
		SD2 sd2 = new SD2();
		D2 d2 = sd2;

	}
}

class B {
  public B() {

  }

  public  B( int i ) {
    // sets value for i from i
  }

  protected int i;
}

class D1 extends B {
  public  D1() {

  }

  public D1( int i ) {

  }

  protected int i;
}

class  D2 extends B {
  public  D2() {

  }

  public D2( int i ) {

  }

  protected int j;
}
class SD2 extends D2 {
  public SD2() {
  }

  public SD2( int i ) {

  }

  protected int j;
}
