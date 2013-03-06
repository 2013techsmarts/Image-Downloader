Image Downloader
============

We had a requirement to share facebook image into facebook by using facebook stream api.For example the image which is there in facebook cdn,
if you try to share the same image to facebook again we may landup seeing an error message 
"FBCDN image is not allowed in stream".

What this indicates for us is "FBCDN image is not allowed in stream ..... ".

Finally we came with an work around to get rid of this problem, i.e to store the facebook images locally and upload it back.

You can use this code to download the images from image urls and put the image url which this code is returning as part 
of stream api.

In this code i have used grizzly light weight server with REST api.

To work with this launch the Main.java and pass hostname/ip address,port and path where the downloaded images need to store as
command line arguments.

For example
root@local$java Main localhost 9190 /opt/images

Keep in mind the mentioned jars should be in class path.
asm-3.3.1.jar,
grizzly-framework-2.2.16.jar,
grizzly-http-2.2.16.jar,
grizzly-http-server-2.2.16.jar,
jackson-core-asl-1.8.5.jar,
jackson-mapper-asl-1.8.5.jar,
jersey-core-1.17.jar,
jersey-grizzly2-1.17.jar,
jersey-server-1.17.jar,
jsr311-api-1.1.1.jar

Now the send request by using the below url 
http://localhost:9190/image?imageUrl=<image_url>

<image_url> will be the link to the image.

When the request has processed, you will able to see image under the path you have specified, and you will get 
the image url back from the server.Like http://localhost:9190/images/13131414.jpg

You can use this url as part of your stream api.
