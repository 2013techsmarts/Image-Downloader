

package com.image.downloader;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;


public class Main {    
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://"+Constants.HOST).port(Constants.PORT).build();
    }

    protected static HttpServer startServer() throws IOException {
        System.out.println("Starting server...");
        ResourceConfig rc = new PackagesResourceConfig("com.image.downloader.resources");
        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(Constants.BASE_URI, rc);
        NetworkListener networkListener = new NetworkListener("sample-listener", Constants.HOST, Constants.PORT);
        httpServer.addListener(networkListener);                
        httpServer.getServerConfiguration().addHttpHandler(new StaticHttpHandler(Constants.FILE_PATH),"/images");
        return httpServer;
    }
    
    public static void main(String[] args) throws IOException {
    	
    	if (args.length == 3) {
    		Constants.HOST = args[0];
    		Constants.PORT = Integer.parseInt(args[1]);
    		Constants.FILE_PATH = args[2];
    		Constants.BASE_URI=getBaseURI();
    	} else {
    		System.out.println("USAGE: java -jar <hostname> <port> <filepath to store downloaded image>");
    	}
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %simage\nHit enter to stop it...",
                Constants.BASE_URI+"/", Constants.BASE_URI+"/"));
        System.in.read();
        httpServer.stop();
    }
}
