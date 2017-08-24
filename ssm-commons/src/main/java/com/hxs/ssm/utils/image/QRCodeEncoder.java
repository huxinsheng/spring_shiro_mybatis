package com.hxs.ssm.utils.image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class QRCodeEncoder {

	private static final int BLACK = 0xff000000;

	private static final int WHITE = 0xFFFFFFFF;
	
	private String charset = "utf-8";
	
	public QRCodeEncoder(){
		this.charset = "utf-8";
	}
	
	public QRCodeEncoder(String charset){
		this.charset = charset;
	}

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public BufferedImage encode(String input , int width , int height) throws Exception {
		BitMatrix matrix;
		Hashtable<EncodeHintType, Object> hints ;
		hints =  new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET,  charset); 
		hints.put(EncodeHintType.MARGIN, 1);
		MultiFormatWriter writr = new MultiFormatWriter();
		matrix = writr.encode(input, BarcodeFormat.QR_CODE, width, height , hints);
		return toBufferedImage(matrix);
	}
}
