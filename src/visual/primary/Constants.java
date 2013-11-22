package visual.primary;

class Constants {
	//absolute visualization dimensions:
	
	//file display position
	public static int fileDisplayStartX = 75;
	public static int fileDisplayStartY = 20;
	//file bar
	private static float lineStripeWidth = 5f;	
	private static float fileBarSpacing = 150f;	
	public static float lineStripeHeight= 450f;
	
	public static float getLineStripeWidth(){
		return lineStripeWidth*zoomRatio;
	}
	
	public static float getFileBarSpacing(){
		return fileBarSpacing*zoomRatio;
	}
	
	//horizontal scroll bar
	public static int horizontalScrollBarOffset = 40;
	public static int horizontalScrollBarX = 75;
	public static int scrollBarWidth = 1200;
	public static int scrollBarHeight = 20;
	public static int scrollBarLooseness = 20;
	//zoom
	public static int zoomOffset = 40;
	public static int zoomX = 75;
	public static int zoomWidth = 1200;
	public static int zoomHeight = 20;
	public static int zoomLooseness = 20;
	private static float zoomRatio = 1;
	//timeline
	public static int timeOffset = 40;
	public static int timeX = 75;
	public static int timeWidth = 1200;
	public static int timeHeight = 20;
	public static int timeLooseness = 10;
	private static float timeRatio = 1;
	
	public static void setZoomRatio(float x){
		zoomRatio = x;
	}
	
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
		GREENTRANS(0xCC00FF00),
		REDTRANS(0xCCFF0000),		
		CYAN(0xDD00FFFF),	
		PINK(0xCCFF3D98),
		BLUETRANS(0xA10000FF),
		YELLOW(0xCCFFFF00),
		LIME(0xFFBCF600);
		
		private int hexVal;
		//offset to adjust primary colours by (to extend colour library)
		private static int offset = 0x95;
		
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
