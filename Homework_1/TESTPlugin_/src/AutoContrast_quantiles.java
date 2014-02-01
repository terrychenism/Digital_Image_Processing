
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class AutoContrast_quantiles implements PlugInFilter {

	public int setup(String arg, ImagePlus img) {
		return DOES_8G; 
	}
	int min,max;
	public void run(ImageProcessor ip) {
		
		
		int[] H = new int[256]; // histogram array
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		for (int v = 0; v < h; v++) {
			for (int u = 0; u < w; u++) {
				int i = ip.getPixel(u, v);
				H[i] = H[i] + 1;
			}
		}
		
		
		double a=w*h*0.01;
		int low = (int)a;
		
		double b= w*h*0.99;
		int high = (int)b;
		
		
		for(int i=0;i<=255;i++){
			if(H[i] > low)  {
				min=i;
				break;
			}
			
		}
		for(int i=255;i>=0;i--){
			if(H[i] < high) {
				max=i;
				break;
			}
			
		}
		
		

		for (int v = 0; v < h; v++) {
			for (int u = 0; u < w; u++) {
				int j = ip.getPixel(u, v);
					if(j<min) 
						ip.putPixelValue(u, v, 0);
					else if(j>max) 
						ip.putPixelValue(u, v, 1);
					else 
						ip.putPixelValue(u, v, (j-min)*255/(max-min) );
					
				
			}
		}
		
	}
}