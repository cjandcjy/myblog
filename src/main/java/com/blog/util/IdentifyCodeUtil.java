package com.blog.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class IdentifyCodeUtil {
	//默认的验证码长度
	private static final int STRINGSIZE = 5;
	//验证码图片高度
	private static final int HEIGHT = 33;
	//验证码图片宽度
	private static final int WIDTH = 100;
	//干扰线条数
	private static final int LINESIZE = 80;
	//随机生成码数组
	private static final char[] codes = "01234ABCDEFGHIJKLMNOPQRSTUVWXYZ56789".toCharArray();
	//验证码图片
	private  BufferedImage buffImag = null;
	
   
	/*
	 * 产生随机数
	 */
	public  String RandomString(){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<STRINGSIZE;i++){
			sb.append(codes[random.nextInt(codes.length)]);
		}
		return sb.toString();
	}
	
	/*
	 * 画线
	 */
	public  void DrawImage(String str){
		Random random = new Random();
		int x = 0, fontHeight = 0, codeY = 0;
        int r = 0, g = 0, b = 0;//rgb
        x = WIDTH / (str.length() + 1);//每个字符的宽度
        fontHeight = HEIGHT - 2;//字体的高度
        codeY = HEIGHT - 4;
        
		buffImag = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) buffImag.getGraphics();
		
		Font font = new Font("Arial",Font.PLAIN + Font.BOLD, fontHeight);
	    graphics.setFont(font);
	    
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        
        //画随即干扰线
        for(int i=0;i<LINESIZE;i++){
        	int xe = random.nextInt(WIDTH);
        	int ye = random.nextInt(HEIGHT);
        	int xs = xe+random.nextInt(WIDTH/8);
        	int ys = ye+random.nextInt(HEIGHT/8);
        	r = random.nextInt(255);
        	g = random.nextInt(255);
        	b = random.nextInt(255);
        	graphics.setColor(new Color(r, g, b));
        	graphics.drawLine(xe, ye, xs, ys);
        }
        
        //画随机验证码
        for(int i=0;i<STRINGSIZE;i++){
        	String code = Character.toString(str.charAt(i));
        	r = random.nextInt(255);
        	g = random.nextInt(255);
        	b = random.nextInt(255);
        	graphics.setColor(new Color(r, g, b));
        	graphics.drawString(code,i * x,codeY);
        }
	}
	
	/*
	 * 向客户端输出图像
	 */
	public  void write(OutputStream os){
		try {
			ImageIO.write(buffImag, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
