public class MyRectangle {
		StringBuilder S;
		int width=0;
		int height=0;
		int startX=0;
		int startY=0;
		public MyRectangle() {}
		public MyRectangle(int x, int y, int w, int h)
		{
			startX=x;
			startY=y;
			width=w;
			height=h;
		}
		public int area(){
			return(width*height);
		}
		public String toString()
		{
			StringBuilder S = new StringBuilder();
			S.append("Width: " + width);
			S.append(" Height: " + height);
			S.append(" X: " + startX);
			S.append(" Y: " + startY);
			return S.toString();
		}
		public boolean isInside(int x, int y)
		{
			return (startX <= x && x <= startX + width )&&
		               (startY <= y && y <= startY + height);
		}
		public void setSize(int w, int h){
			{
				if(w>=0)
					width=w;
				else
					System.out.println("Illegal Value "+w+" for width");
			}
			{
				if(h>=0)
					height=h;
				else
					System.out.println("Illegal Value "+h+" for height");
			}
		}
		public void setPosition(int x, int y)
		{
			{
				if(x>=0)
					startX=x;
				else
					System.out.println("Illegal Value "+x+" for X position");
			}
			{
				if(y>=0)
					startY=y;
				else
					System.out.println("Illegal Value "+y+" for Y position");
			}
		}
	}