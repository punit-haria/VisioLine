package visual.primary;

class Constants {
	//absolute visualization dimensions:
	
	//file display position
	public static int fileDisplayStartX = 75;
	public static int fileDisplayStartY = 50;
	//file bar
	public static float lineStripeWidth = 5f;	
	public static float fileBarSpacing = 20f;
	public static float lineStripeHeight= 200f;
	//horizontal scroll bar
	public static int horizontalScrollBarOffset = 40;
	public static int horizontalScrollBarX = 75;
	public static int scrollBarWidth = 400;
	public static int scrollBarHeight = 20;
	public static int scrollBarLooseness = 20;
	
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
		GREEN(0xFF00CC00),
		YELLOW(0xFFFFFF00),
		PURPLE(0xFF7109AA),	
		PINK(0xFFE93A90),
		ORANGE(0xFFFF9000),
		LIME(0xFFCCF600);
		
		private int hexVal;
		//offset to adjust primary colours by (to extend colour library)
		private static int offset = 0x15;
		
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

	private Constants(){}

}
