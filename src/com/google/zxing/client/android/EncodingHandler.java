package com.google.zxing.client.android;

import java.util.Hashtable;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
/**
 * @author Ryan Tang
 *
 */
public final class EncodingHandler {
	private static final int BLACK = 0xff000000;
	
	public static Bitmap createQRCode(String str,int width, int height) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, width, height);
		int w = matrix.getWidth();
		int h = matrix.getHeight();
		int[] pixels = new int[w * h];
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (matrix.get(x, y)) {
					pixels[y * w + x] = BLACK;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(w, h,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
		return bitmap;
	}
}
