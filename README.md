Image Downloader
============

We had a requirement to share facebook image into facebook by using facebook stream api.For example the image which is there in facebook cdn,
if you try to share the same image to facebook again we may landup seeing an error message 
"FBCDN image is not allowed in stream".

What this indicates for us is "FBCDN image is not allowed in stream ..... ".

Finally we came with an work around to get rid of this problem, i.e to store the facebook images locally and upload it back.

You can use this code to download the images from image urls and put the image url which this code is returning as part 
of stream api.
