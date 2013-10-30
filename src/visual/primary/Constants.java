package visual.primary;

import processing.core.PApplet;

class Constants {
	//primary visualization dimensions as fractions of screen size
	private static float lineStripeWidth = 0.008f;
	private static float lineStripeHeight= 0.25f;
	private static float fileBarSpacing = 0.05f;
	
	//colors
	public static int red = 0xFFFF0000;
	public static int blue = 0xFF1826B0;
	public static int green = 0xFF00C618;
	public static int yellow = 0xFFFFF800;
	public static int purple = 0xFF7608AA;	
	public static int pink = 0xFFE93A90;
	public static int orange = 0xFFFF9000;
	public static int teal = 0xFF60D4AE;	
	public static int lime = 0xFFCCF600;
	public static int black = 0xFF000000;
	public static int white = 0xFFFFFFFF;
	
	//author colors enum
	public enum Colour {
		RED(0xFFFF0000),
		BLUE(0xFF1B1BB3),
		YELLOW(0xFFFFFF00),
		GREEN(0xFF00CC00),		
		PURPLE(0xFF7109AA),	
		PINK(0xFFE93A90),
		ORANGE(0xFFFF9000),
		LIME(0xFFCCF600);
		
		private int hexVal;
		//offset to adjust primary colours by (to extend colour library)
		private static int offset = 0x35;
		
		private Colour(int value){
			this.hexVal = value;
		}
		
		public int get(){
			return hexVal;
		}
		
		public static int offset(){
			return offset;
		}
	};
	
	//parent PApplet
	private static PApplet parent = null;
	
	public static void init(PApplet p){
		parent = p;	
	}
	
	private Constants(){}
	
	public static float getLineStripeWidth() {
		return lineStripeWidth*parent.getWidth();
	}

	public static float getLineStripeHeight() {
		return lineStripeHeight*parent.getHeight();
	}

	public static float getFileBarSpacing() {
		return fileBarSpacing*parent.getWidth();
	}
}
