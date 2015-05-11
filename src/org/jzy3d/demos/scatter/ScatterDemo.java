/*
 * Copyright (c) 2010-2011, Martin Pernollet
 * All rights reserved. 
 *
 * Redistribution in binary form, with or without modification, is permitted.
 * Edition of source files is allowed.
 * Redistribution of original or modified source files is FORBIDDEN.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jzy3d.demos.scatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.demos.AbstractDemo;
import org.jzy3d.demos.DemoLauncher;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;


public class ScatterDemo extends AbstractDemo{
	
        List dataPlot = new ArrayList(); // use for get input data.
        List inner  = new ArrayList();   // use for get each data from dataPlot. 
	public ScatterDemo(List dataSim){ // get List that contains coordinate x y z.
            this.dataPlot = dataSim;
	}
	
	public void init(){
        int size = this.dataPlot.size();
        // they're coordinate x y z.
        float x;
        float y;
        float z;
        float a;
        
        Coord3d[] points = new Coord3d[size];
        Color[]   colors = new Color[size];
        
        for(int i=0; i<size; i++){ // this loop will plot entire point from this.dataPlot.
            this.inner = (List) this.dataPlot.get(i);
            /*/ 
                    Structure of dataPlot is 2 dimentions List.It's look like [[x0, y0,z0], [x1, y1, z1], ..., [xn, yn, zn]].
                Data that be inside dataPlot is List. We access data(x, y, z) by this.inner because it's pointer that can
                get each coordinates.
            /*/
            String tran1 = inner.get(0).toString(); 
            String tran2 = inner.get(1).toString();
            String tran3 = inner.get(2).toString();
            /*/ 
                    convert each coordinates to string because it can not direct convert to float.After convert to String we
                will convert that coordinate to float.
            /*/
            x = Float.parseFloat(tran1);
            y = Float.parseFloat(tran2);
            z = Float.parseFloat(tran3);

            points[i] = new Coord3d(x, y, z); // set coordinate to graph.
            a = 2f;  // set resolution point
            colors[i] = new Color(1, 0, 0, a); // set color
        }
        
        Scatter scatter = new Scatter(points, colors); // import every points and colors for ploting.
        chart = new Chart(Quality.Advanced, getCanvasType());
        chart.getScene().add(scatter);
    }
        
    public static void main(String[] args) throws Exception {
        
    }
        
}
