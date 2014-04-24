SimpleImagePicker
=================

This project contains two Activities:

1/ The first Activity (MainActivity) contains :
- an Image loaded from the web, which will be replaced by the chosen picture.
- a button , which loads the URLs of the images in the SD card and send them to send Activity.

2/ The second Activity (ImagePickerActivity)  contains :
- a GridView showing all the pictures.
- a click listener is added to every item, to take the correspondent URL and send it back to the first Activity.
