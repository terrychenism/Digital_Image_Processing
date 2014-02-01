
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Power_Transform implements PlugInFilter {

	public int setup(String arg, ImagePlus img) {
		return DOES_8G; 
	}
    
	public void run(ImageProcessor ip) {
		
		int w = ip.getWidth();
		int h = ip.getHeight();

		for (int v = 0; v < h; v++) {
			for (int u = 0; u < w; u++) {
				int i = ip.getPixel(u, v);
				ip.putPixelValue(u, v, Math.pow(i,0.9));
			}
		}

		
	}
}