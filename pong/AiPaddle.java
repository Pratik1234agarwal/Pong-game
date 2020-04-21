package pong;

import java.awt.Color;
import java.awt.Graphics;

public class AiPaddle implements Paddle{
	//private final double GRAVITY = 0.96;
	
	double y,yVelocity;
	boolean upAccel,downAccel;
	int player,x;
	Ball b1;
	public AiPaddle(int player,Ball b)
	{
	
		b1=b;
		upAccel=false;
		downAccel=false;
		y=210;yVelocity=0.0;
		if(player==1)
		{
			x=20;
		}
		else
		{
			x=660;
		}
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}

	@Override
	public void move() {
		y=b1.getY()-40;
		if(y<0)
		{
			y=0;
		}
		else if(y>420)
		{
			y=420;
		}
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int)y;
	}

}
