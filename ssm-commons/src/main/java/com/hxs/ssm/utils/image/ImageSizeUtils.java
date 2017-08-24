package com.hxs.ssm.utils.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class ImageSizeUtils {

	/*
	 * 根据尺寸图片居中裁剪
	 */
	public static void cut(String format , InputStream src, OutputStream dest, int w,
                           int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName(format);
		ImageReader reader = (ImageReader) iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(src);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		// 进行图片拉缩
		BufferedImage bi = reader.read(0, param);
		int oldWidth = bi.getWidth();
		int oldHeight = bi.getHeight();
		double wr = 1.0;
		double hr = 1.0;
		// 横图(宽 > 高)
		if (oldWidth > oldHeight) {
			hr = h * 1.0 / oldHeight;
			wr = hr;
			//拉伸后宽度不足
			if(wr * oldWidth < w){
				wr = w * 1.0 / oldWidth;
				hr = wr;
			}
		}
		// 竖图
		else {
			wr = w * 1.0 / oldWidth;
			hr = wr;
			//拉伸后高度不足
			if(hr * oldHeight < h){
				hr = h * 1.0 / oldHeight;
				wr = hr;
			}
		}
		AffineTransformOp ato = new AffineTransformOp(
				AffineTransform.getScaleInstance(wr, hr), null);
		BufferedImage image = (BufferedImage)ato.filter(bi, null);
		int newWidth = (int)(wr * oldWidth);
		int newHeight = (int)(hr * oldHeight);
	    int x = 0 , y = 0;
		//宽度大于预期宽度，裁剪左右
		if(newWidth > w){
			x  = -(newWidth - w) / 2;
		}else if(newHeight > h){//裁剪上下
			y  = -(newHeight - h) / 2;
		}     
	    BufferedImage newImage = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
	    Graphics g = newImage.getGraphics();
	    g.drawImage(image, x , y, Color.white,null);
        ImageIO.write(newImage, format, dest);
	}
}
