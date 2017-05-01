import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import javax.imageio.*;

/**
 * Interpreter for the brainloller language. Brainloller is an extension of the
 * brainfuck programming language. Brainloller source code is stored in an image.
 * HelloWorld can be seen in HelloWorld.png.
 * https://esolangs.org/wiki/Brainloller
 *
 * Example: `java SetMapA4_Salom HelloWorld.png`
 */

public class SetMapA4_Salom {
	/**
	 * main
	 * Interprets and runs the specificed brainloller image
	 * @param String[] args
	 * @return void
	 */
	public static void main(String[] args) {
		HashMap commands = new HashMap();

		commands.put(new ColorCommand(255, 0, 0).toString(), ">");
		commands.put(new ColorCommand(128, 0, 0).toString(), "<");
		commands.put(new ColorCommand(0, 255, 0).toString(), "+");
		commands.put(new ColorCommand(0, 128, 0).toString(), "-");
		commands.put(new ColorCommand(0, 0, 255).toString(), ".");
		commands.put(new ColorCommand(0, 0, 128).toString(), ",");
		commands.put(new ColorCommand(255, 255, 0).toString(), "[");
		commands.put(new ColorCommand(128, 128, 0).toString(), "]");
		commands.put(new ColorCommand(0, 255, 255).toString(), "CW");
		commands.put(new ColorCommand(0, 128, 128).toString(), "CCW");


		final int LENGTH = 30000;
		byte[] mem = new byte[LENGTH];
		int dataPointer = 0;

		BufferedImage img = null;

		ImagePointer point = null;
		String prog = "";

		int loopLevel = 0;
		try {
			img = ImageIO.read(new File(args[0]));
			point = new ImagePointer(img);
			while(point.isValid()) {
				ColorCommand cmd = point.read();
				String scmd = cmd.toString();
				Object ecmd = commands.get(scmd);
				scmd = (String)ecmd;
				if(scmd != "CW" && scmd != "CCW" && scmd != null) prog += scmd;
				else if(scmd == "CW") point.rotateCW();
				else if(scmd == "CCW") point.rotateCCW();
				point.move();
			}

			int l = 0;
			for(int i = 0; i < prog.length(); i++) {
				switch(prog.charAt(i)) {
				case '>':
					dataPointer = (dataPointer == LENGTH-1) ? 0 : dataPointer + 1;
					break;
				case '<':
					dataPointer = (dataPointer == 0) ? LENGTH-1 : dataPointer - 1;
					break;
				case '+':
					mem[dataPointer]++;
					break;
				case '-':
					mem[dataPointer]--;
					break;
				case '.':
					System.out.print((char) mem[dataPointer]);
					break;
				case '[':
					if(mem[dataPointer] == 0) {
						i++;
						while(l > 0 || prog.charAt(i) != ']') {
							if(prog.charAt(i) == '[') l++;
							if(prog.charAt(i) == ']') l--;
							i++;
						}
					}
					break;
				case ']':
					if(mem[dataPointer] != 0) {
						i--;
						while(l > 0 || prog.charAt(i) != '[') {
							if(prog.charAt(i) == ']') l++;
							if(prog.charAt(i) == '[') l--;
							i--;
						}
						i--;
					}
					break;
				}
			}
		}
		catch (IOException e) {
			System.out.println("Could not find input file.");
		}
		System.out.println("");
	}

}

/**
 * ColorCommand (class)
 * Holds color values
 */
class ColorCommand {
	public int red = 0;
	public int green = 0;
	public int blue = 0;

	public ColorCommand(int r, int g, int b) {
		red = r; green = g; blue = b;
	}

	/**
	 * toString
	 * Outputs as HEX
	 * @return String
	 */
	public String toString() {
		return String.format("%02x%02x%02x", red, green, blue);
	}

}

/**
 * ImagePointer (class)
 * Holds a brainloller image
 */
class ImagePointer {
	public Direction dir = Direction.east;
	public int x = 0;
	public int y = 0;
	private BufferedImage img = null;

	public ImagePointer(BufferedImage i) {
		img = i;
	}

	/**
	 * rotateCW
	 * Rotates the pointer
	 * @return void
	 */
	public void rotateCW() {
		switch(dir) {
		case east:
			dir = Direction.south;
			break;
		case south:
			dir = Direction.west;
			break;
		case west:
			dir = Direction.north;
			break;
		default:
			dir = Direction.east;
			break;
		}
	}

	/**
	 * rotateCCW
	 * Rotates the pointer
	 * @return void
	 */
	public void rotateCCW() {
		switch(dir) {
		case east:
			dir = Direction.north;
			break;
		case south:
			dir = Direction.east;
			break;
		case west:
			dir = Direction.south;
			break;
		default:
			dir = Direction.west;
			break;
		}
	}

	/**
	 * move
	 * Moves the pointer
	 * @return void
	 */
	public void move() {
		switch(dir) {
		case east:
			x++;
			break;
		case west:
			x--;
			break;
		case south:
			y++;
			break;
		default:
			y--;
			break;
		}
	}

	/**
	 * isValid
	 * Returns true if the pointer is in a valid position
	 * @return boolean
	 */
	public boolean isValid() {
		return (x < img.getWidth() && y < img.getHeight()) && (x >= 0 && y >= 0);
	}

	/**
	 * read
	 * Reads the color the pointer is pointed at
	 * @return ColorCommand
	 */
	public ColorCommand read() {
		int color =  img.getRGB(x,y);
		int red   = (color & 0x00ff0000) >> 16;
		int green = (color & 0x0000ff00) >> 8;
		int blue  =  color & 0x000000ff;
		return new ColorCommand(red, green, blue);
	}

}

/**
 * Direction (enum)
 * Holds the direction of the pointer
 */
enum Direction {
	east,
	south,
	west,
	north
}
