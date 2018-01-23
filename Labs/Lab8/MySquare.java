
public class MySquare extends MyRectangle {

	public MySquare() {
		super();
	}

	public MySquare(int x, int y, int w) {
		super(x, y, w, w);
	}

	public int area() {
		return (super.width * super.height);
	}

	public String toString() {
		StringBuilder S = new StringBuilder();
		S.append("Width: " + super.width);
		S.append(" X: " + super.startX);
		S.append(" Y: " + super.startY);
		return S.toString();
	}

	public boolean isInside(int x, int y) {
		return (super.startX <= x && x <= super.startX + super.width)
				&& (super.startY <= y && y <= super.startY + super.height);
	}

	public void setSide(int w) {
		{
			if (w >= 0) {
				super.width = w;
				super.height = w;
			} else
				System.out.println("Illegal Value " + w + " for square side");
		}
	}

	public void setSize(int w, int h) {
		{
			if (w == h) {
				super.width = w;
				super.height = w;
			} else if (w != h)

				System.out.println("Sides must be equal. " + w + " != " + h + " so no action taken");
		}
	}

	public void setPosition(int x, int y) {
		{
			{
				if (x >= 0)
					super.startX = x;
				else
					System.out.println("Illegal Value " + x + " for X position");
			}
			{
				if (y >= 0)
					super.startY = y;
				else
					System.out.println("Illegal Value " + y + " for Y position");
			}
		}
	}

}
