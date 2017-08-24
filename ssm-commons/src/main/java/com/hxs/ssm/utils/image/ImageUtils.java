package com.hxs.ssm.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageUtils {
	
	/**
	 * 读取图片
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage read(InputStream input) throws IOException {
		return ImageIO.read(input);
	}
	
	/**
	 * 读取图片
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage read(String url) throws IOException {
		return ImageIO.read(new URL(url));
	}
	
	/**
	 * 输出图片
	 * @param image
	 * @param format
	 * @param output
	 * @return 
	 * @throws IOException
	 */
	public static void write(RenderedImage image , String format , OutputStream output) throws IOException {
		ImageIO.write(image, format , output);
		output.close();
	}

	/**
	 * 画Logo
	 * @param source
	 * @param logo
	 * @throws IOException
	 */
	public static BufferedImage drawLogo(BufferedImage source , URL logoUrl) throws IOException {
		int qrWidth = source.getWidth();
		int qrHeight = source.getHeight();
		Image logo = ImageIO.read(logoUrl);
        int width = logo.getWidth(null);  
        int height = logo.getHeight(null);  
        //缩放Logo
		 if (true) { 
			int WIDTH = qrWidth / 4;
			int HEIGHT = qrHeight / 4;
		    if (width > WIDTH) {  
                width = WIDTH;  
            }  
            if (height > HEIGHT) {  
                height = HEIGHT;  
            }  
            logo = logo.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(logo, 0, 0, null); // 绘制缩小后的图  
            g.dispose();
		 }
        //计算logo的位置
        int x = (qrWidth - width) / 2;  
        int y = (qrHeight - height) / 2;  
        BufferedImage newImage = new BufferedImage(qrWidth , qrHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = newImage.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setBackground(Color.WHITE);//设置背景色
	    //绘上二维码
	    g.drawImage(source , 0 , 0 , Color.white , null);
	    //绘上一个圆角
	    g.clearRect(x, y, width, height);
	    Shape shape = new RoundRectangle2D.Float(x, y, width, height, 6, 6);
        g.setStroke(new BasicStroke(3f));
        g.draw(shape);  
//	    绘上logo
	    g.drawImage(logo , x , y , Color.white , null);

		return newImage;
	}
}
