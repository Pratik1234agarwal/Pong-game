package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

public class Ball{
	
	
	volatile double xVel,yVel,x,y; 
	int score=0;
	double MULTIPLIER=1.1;
	long start;
	long current;
	public int level=30;
	public Ball() {
		
		current=0;
		x=350;
		y=250;
		xVel=-2*getDirection();
		yVel= 1*getDirection();
	}
	public void move()
	{
		if(score>level)
		{
			
			xVel*=MULTIPLIER;
			yVel*=MULTIPLIER;
			level+=20;
		}
		x+=xVel;
		y+=yVel;
		if(y<10)
			yVel=-yVel;
		else if(y>490)
		{
			yVel=-yVel;
		}
		
	}
	public int getDirection()
	{
		int rand=(int)(Math.random()*2);
		if(rand==0)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	public void checkPaddleCollision(Paddle p1,Paddle p2)
	{
		if(x<=50 && x>10)
		{
			int t1=p1.getY();
			int t2=t1+80;
			if(y>=t1 && y<=t2)
			{
				x=51;
				score+=10;
				xVel=-xVel;
			}
		}
		else if(x>=650) {
			int t1=p2.getY();
			int t2=t1+80;
			if(y>=t1 && y<=t2)
			{
				xVel=-xVel;
			}
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillOval(((int)x-10),(int)y-10,20,20);
	}
	public int getX()
	{
		return (int)x;
	}
	public int getY()
	{
		return (int)y;
	}
}
