import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Intro-A2: Counted One Prog
 * @author: Trevor Salom
 */
public class Counted_Sal {
	public static void main (String[] args) throws Exception {
		Popup popup = new Popup("test", "run");
		Counted count = null;
		for(int i = 0; i < 10; i++) {
			count = new Counted();
		}
		System.out.println(count.getCount());
		Singleton singleton = Singleton.createSingleton();
	}
}

class Popup {
	/**
	 * Creates a new popup
	 * @param   Title
	 * @param   Message
	 */
	public Popup(String title, String message) throws Exception {
		URL imageLocation = new URL("http://greco.bcp.org/webs/bcp/bellSeal.jpg");
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
	}
}

class Counted {
	static int count = 0;
	/**
	 * Counted constructor
	 */
	public Counted() {
		count++;
	}

	/**
	 * counts counted objects
	 * @return counted object count
	 */
	public static int getCount() {
		return count;
	}
}

class Singleton {
	private static Singleton singletonInstance = null;

	private Singleton() {};

	/**
	 * Creates/returns the only singleton instance
	 * @return singleton
	 */
	public static Singleton createSingleton() {
		if(singletonInstance == null) {
			singletonInstance = new Singleton();
		}
		return singletonInstance;
	}
}
