package com.image.downloader.resources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.image.downloader.Constants;
@Path("/image")
public class ImageDownloaderResource {

    // The Java method will process HTTP GET requests
    @GET    
    @Produces("text/plain")
    public String downloadTheImage(@QueryParam("imageUrl") String imageUrl) {
    	long nano = 0L;
    	int t1 = imageUrl.lastIndexOf(".");
    	String extension = null;
    	if (t1 != 1) {
    		extension = imageUrl.substring(t1+1);
    	} else {
    		extension = "png";
    	}
		try {			
			BufferedImage image = null;	        
	        URL url = new URL(imageUrl);
	        image = ImageIO.read(url);	       
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, extension, baos);
			byte[] bytes = baos.toByteArray();
			
			nano = System.nanoTime();
			writeToFile(bytes, Constants.FILE_PATH+nano+"."+extension);
			
		} catch (Exception exception){
			exception.printStackTrace();
		}
		return "http://"+Constants.HOST+":"+Constants.PORT+"/images/"+nano+"."+extension;
    }
    
    private static void writeToFile(byte[] imageAsByteArray, String fileName) {
		FileOutputStream image = null;
		try {
			image = new FileOutputStream(fileName);
			image.write(imageAsByteArray);
		} catch (FileNotFoundException pFileNotFoundException) {
			System.out.println("Exception while writing the image into a file");
			pFileNotFoundException.printStackTrace();
		} catch (IOException pIoException) {
			pIoException.printStackTrace();
		} finally {
			try {
				if (image != null) {
					image.close();
				}
			} catch (IOException pIoException2) {
				pIoException2.printStackTrace();
			}
		}
	}

}