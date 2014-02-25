import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

public class Laplacian_Filter_with_c implements PlugInFilter {

    public int setup(String arg, ImagePlus imp) {
        return DOES_8G;
    }

    public void run(ImageProcessor orig) {
        int w = orig.getWidth();
        int h = orig.getHeight();
        //3x3 filter matrix
        double[][] filter = {{0,1,0},
                             {1,-4,1},
                             {0,1,0}};
        
        ImageProcessor copy = orig.duplicate();

		for (int v = 1; v <= h - 2; v++) {
			for (int u = 1; u <= w - 2; u++) {
				// compute filter result for position (u,v)
				double sum = 0;
				for (int j = -1; j <= 1; j++) {
					for (int i = -1; i <= 1; i++) {
						int p = copy.getPixel(u + i, v + j);
						// get the corresponding filter coefficient
						double c = filter[j + 1][i + 1];
						sum = sum + c * p;
					}
				}
				int q = (int) sum;
				orig.putPixel(u, v, q);
			}
		}
    }

}