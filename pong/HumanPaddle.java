package pong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle{
	private final double GRAVITY = 0.96;
	double y,yVelocity;
	boolean upAccel,downAccel;
	int player,x;
	public HumanPaddle(int player)
	{
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
		if(upAccel)
		{
			yVelocity-=2;
		}
		else if(downAccel)
		{
			yVelocity+=2;
		}
		else
		{
			yVelocity*=GRAVITY;
		}
		if(yVelocity>=5)
		{
			yVelocity=5;
		}
		else if(yVelocity<=-5)
		{
			yVelocity=-5;
		}
		y=y+yVelocity;
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
	public void setUpAccel(boolean upAccel) {
		this.upAccel = upAccel;
	}
	public void setDownAccel(boolean downAccel) {
		this.downAccel = downAccel;
	}

}
