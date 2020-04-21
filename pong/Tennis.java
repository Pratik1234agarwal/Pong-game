package pong;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable,KeyListener{
	Thread thread;
	final int WIDTH=700,HEIGHT=500;
	HumanPaddle p1;
	Ball ball;
	AiPaddle p2;
	boolean levelup=false;
	public volatile boolean gameStarted;
	Graphics gfx;
	Image img;
	
	
	
	public void init() {
		gameStarted=false;
		ball=new Ball();
		p1=new HumanPaddle(1);
		p2=new AiPaddle(2,ball);
		img=createImage(WIDTH, HEIGHT);
		gfx=img.getGraphics();
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		thread=new Thread(this);
		thread.start();
	}
	public void paint(Graphics g)
	{
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		gfx.setColor(Color.white);
		gfx.fillRect(290,0,100, 30);
		gfx.setColor(Color.blue);
		gfx.setFont(new Font("Georgia",Font.BOLD,13));
		gfx.drawString("Score : "+ball.score,310, 15);
		if(ball.getX()<-10 || ball.getX()>710)
		{
			gfx.setColor(Color.red);
			gfx.setFont(new Font(Font.MONOSPACED,Font.BOLD,70));
			gfx.drawString("GAME OVER",160, 250);
		}
		else {
		p1.draw(gfx);
		p2.draw(gfx);
		ball.draw(gfx);
		}
		if(!gameStarted)
		{
			gfx.setColor(Color.white);
			gfx.drawString("TENNIS ... ",310,100);
			gfx.drawString("Please Press Enter to Begin ", 270,130);
		}
		if(ball.score>ball.level) {
			levelup=true;
			gfx.setColor(Color.yellow);
			gfx.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
			gfx.drawString("Level Up .. Keep Going",80, 250);
		}
		
		
		g.drawImage(img,0,0,this);
		if(ball.score>ball.level) {
			try {
				Thread.sleep(1000);
				levelup=false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(Graphics g)
	{
		paint(g);
	}
	@Override
	public void run() {
		
		
		for(;;)
		{
			if(gameStarted && !levelup) {
				ball.move();
				p1.move();
				p2.move();
				ball.checkPaddleCollision(p1, p2);
				repaint();
			}
		    try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			p1.setUpAccel(true);
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			p1.setDownAccel(true);
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			gameStarted=true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			p1.setUpAccel(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			p1.setDownAccel(false); 
		}
	}

}
