
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.Timer;

//         __________________    _________    _________
//        / _______/ _______ \  / _______ \  / _______/
//       / /      / /       \ \/ /       \ \/ /       
//      / /      / /________/ / /________/ / /______
//     / /      / ___________/ ___  ______/ _______/
//    / /      / /          / /   \ \    / /      
//   / /______/ /          / /     \ \  / /______
//  /________/_/          /_/       \_\/________/
//##############################################################################
//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/ DrawGraph \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
//##############################################################################
public class GraphPanel extends JPanel implements ActionListener {

    Timer tm = new Timer(0, this);
//    format of dataList is [[name, DataList], [name, DataList], ...]
    List dataList;
    int WIDTH;  // set Width of Panel
    int HEIGHT; // set Height of Panel
    double Ymax = Double.MAX_VALUE, Ymin = 0;
    double Xmax = Double.MAX_VALUE, Xmin = 0;
//    for margin left, margin right, magin bottom and margin tops
    int marginLeft, marginRight, marginBottom, marginTop;
    final Color[] color = {
        Color.BLUE,
        Color.GREEN,
        Color.MAGENTA,
        Color.ORANGE,
        Color.PINK,
        Color.RED,
        Color.YELLOW,
        new Color(47, 79, 79), // DarkSlateGray
        new Color(205, 16, 118), // DeepPink3
        new Color(121, 205, 205), // DarkSlateGray3
        new Color(238, 201, 0), // Gold2
        new Color(50, 205, 50), // Lime
        new Color(82, 139, 139), // DarkSlateGray4
        new Color(238, 232, 170), // PaleGoldenrod
        new Color(139, 58, 58), // IndianRed4
        new Color(160, 82, 45), // Sienna
        new Color(244, 164, 96), // SandyBrown
        new Color(124, 252, 0), // LawnGreen
        new Color(34, 139, 34), // ForestGreen
        new Color(255, 255, 0), // Yellow1
        new Color(255, 193, 193), // RosyBrown1
        new Color(205, 155, 155), // RosyBrown3
        new Color(255, 106, 106), // IndianRed1
        new Color(205, 104, 57), // Sienna3
        new Color(255, 20, 147), // DeepPink1
    };

//    ==========================================================================
//    Constructor Method for create a GraphPanel Object
//    ==========================================================================
    public GraphPanel(int WIDTH, int HEIGHT) {
        this.setSize(WIDTH, HEIGHT);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.dataList = new ArrayList();
        this.marginLeft = 50;
        this.marginRight = 50;
        this.marginBottom = 50;
        this.marginTop = 50;
        tm.start();
    }
    
//    ==========================================================================
//    Function for set margin left, margin right, margin top and margin bottom
//    ==========================================================================
    public void setMargin(int left, int right, int top, int bottom){
        this.marginLeft = left;
        this.marginRight = right;
        this.marginTop = top;
        this.marginBottom = bottom;
    }

//    ==========================================================================
//    Overiding Method for draw something
//    ==========================================================================
    public void paintComponent(Graphics g) {
        this.drawGrid(g);                       // Draw Grid
        if (this.dataList != null) {
            this.drawGraph(g);  // Draw Graph
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    public void setData(String name, List data) {
//        format of data is [dataX, dataY]
//        format of temp is [name, DataList]
        List temp = new ArrayList();
        temp.add(name);
        temp.add(data);
//        format of dataList is [[name, DataList], [name, DataList], ...]
        this.dataList.add(temp);
        this.findMinMax();
    }

//    ==========================================================================
//    Function for find X min value, X max value, Y min value and Ymax value
//    ==========================================================================
    private void findMinMax() {
        double Ymax = 0, Ymin = Double.MAX_VALUE;
        double Xmax = 0, Xmin = Double.MAX_VALUE;
        for (int i = 0; i < this.dataList.size(); i++) {
//        format of dataList is [[name, DataList], [name, DataList], ...]
            List data = (List) ((List) this.dataList.get(i)).get(1);
//            format of data is DataList = [[x1, y1], [x2, y2], ...]
            for (int j = 0; j < data.size(); j++) {
                List xyCoor = (List) data.get(j);
                Xmin = Double.min(Xmin, (double) xyCoor.get(0));
                Xmax = Double.max(Xmax, (double) xyCoor.get(0));
                Ymin = Double.min(Ymin, (double) xyCoor.get(1));
                Ymax = Double.max(Ymax, (double) xyCoor.get(1));
            }
        }
        this.Xmin = Xmin;
        this.Xmax = Xmax;
        this.Ymin = Ymin;
        this.Ymax = Ymax;
    }

//    ==========================================================================
//    \/\/\/\/\/\/\/\/\/\ this function for draw graph  /\/\/\/\/\/\/\/\/\/\/\/\
//    ==========================================================================
    public void drawGraph(Graphics g) {
        if (this.dataList.size() > 0) {
            for (int i = 0; i < this.dataList.size(); i++) {
                List data = (List) ((List) this.dataList.get(i)).get(1);
//              format of data is [[valueX1, valueY1], [valueX2, valueY2], ...]
                int Xmax = data.size();
                if (Xmax > 1) {
                    Graphics2D g2d = (Graphics2D) g.create();   // create graphic 2D
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    double x = this.marginLeft;
                    Color c = color[i];
                    for (int j = 1; j < Xmax; j++) {
                        List dataBefore = (List) data.get(j - 1);
                        List dataAfter = (List) data.get(j);
//                  set x,y coordinate for draw line of graph
//                  (x1, y1) ____________________Line___________________(x2, y2)
                        double x1 = x;
                        double y1 = (HEIGHT - this.marginBottom + this.marginTop) - (this.changeScale((double) dataBefore.get(1)));

                        x += (double) (WIDTH - (this.marginLeft + this.marginRight)) / Xmax;

                        double x2 = x;
                        double y2 = (HEIGHT - this.marginBottom + this.marginTop) - (this.changeScale((double) dataAfter.get(1)));

                        g2d.setColor(c);
                        g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                    }
                    String name = (String) ((List) this.dataList.get(i)).get(0);
//                    code for get Width and length of drawString
                    AffineTransform affinetransform = new AffineTransform();
                    FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
                    Font font = new Font("Serif", Font.PLAIN, 12);
                    int textwidth = (int) (font.getStringBounds(name, frc).getWidth());
                    int textheight = (int) (font.getStringBounds(name, frc).getHeight());
//                    code for draw name of Line of graph
                    g2d.drawLine(
                            WIDTH - this.marginRight - 30,
                            (int) (((i + 1) * (textheight)) - (textheight * 0.2) + this.marginTop),
                            WIDTH - this.marginRight,
                            (int) (((i + 1) * (textheight)) - (textheight * 0.2) + this.marginTop)
                    );
                    g2d.setFont(new Font("Serif", Font.PLAIN, 12));
                    g2d.setColor(Color.black);
                    g2d.drawString(
                            name,
                            (WIDTH - this.marginRight - 40) - textwidth,
                            ((i + 1) * textheight) + this.marginTop
                    );

//                     draw String for minimum X value
                    textwidth = (int) (font.getStringBounds((Double.toString(this.Xmin)), frc).getWidth());
                    textheight = (int) (font.getStringBounds((Double.toString(this.Xmin)), frc).getHeight());
                    g2d.setColor(Color.black);
                    Double Ypos = this.changeScale(0);
                    g2d.drawString(
                            Double.toString(this.Xmin),
                            this.marginLeft - (textwidth / 2),
                            (float)((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos) + textheight
                    );
//                    draw String for maximum X value
                    textwidth = (int) (font.getStringBounds(Double.toString(this.Xmax), frc).getWidth());
                    textheight = (int) (font.getStringBounds((Double.toString(this.Xmax)), frc).getHeight());
                    g2d.drawString(Double.toString(
                            this.Xmax),
                            (WIDTH - this.marginRight) - (textwidth / 2),
                            (float)((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos) + textheight
                    );
//                    draw String for 50% of X length
                    double x50 = (this.Xmin + this.Xmax) * 0.5;
                    textwidth = (int) (font.getStringBounds(Double.toString(x50), frc).getWidth());
                    textheight = (int) (font.getStringBounds(Double.toString(x50), frc).getHeight());
                    float Xpos = (float) ((this.WIDTH - (this.marginLeft + this.marginRight)) * 0.5 + this.marginLeft);
                    g2d.drawString(
                            Double.toString(x50),
                            (float) (Xpos - (textwidth / 2)),
                            (float)((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos) + textheight
                    );
//                    draw String for 25% of X length
                    double x25 = (this.Xmin + x50) * 0.5;
                    textwidth = (int) (font.getStringBounds(Double.toString(x25), frc).getWidth());
                    textheight = (int) (font.getStringBounds(Double.toString(x25), frc).getHeight());
                    Xpos = (float) ((this.WIDTH - (this.marginLeft + this.marginRight)) * 0.25 + this.marginLeft);
                    g2d.drawString(
                            Double.toString(x25),
                            (float) (Xpos - (textwidth / 2)),
                            (float)((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos) + textheight
                    );
//                    draw String for 75% of X length
                    double x75 = (x50 + this.Xmax) * 0.5;
                    textwidth = (int) (font.getStringBounds(Double.toString(x75), frc).getWidth());
                    textheight = (int) (font.getStringBounds(Double.toString(x75), frc).getHeight());
                    Xpos = (float) ((this.WIDTH - (this.marginLeft + this.marginRight)) * 0.75 + this.marginLeft);
                    g2d.drawString(
                            Double.toString(x75),
                            (float) (Xpos - (textwidth / 2)),
                            (float)((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos) + textheight
                    );
//                    draw String for 0% of Y length
                    double y0 = this.Ymin;
                    //            convert y0 as double 2 digit after point
                    DecimalFormat df = new DecimalFormat("#.##");
                    y0 = Double.valueOf(df.format(y0));
                    textwidth = (int) (font.getStringBounds((Double.toString(y0)), frc).getWidth());
                    textheight = (int) (font.getStringBounds((Double.toString(y0)), frc).getHeight());
                    g2d.drawString(
                            Double.toString(y0),
                            this.marginLeft - textwidth,
                            (float)((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos)
                    );
//                    draw String for 100% of Y length
                    double y100 = this.Ymax;
                    //            convert y100 as double 2 digit after point
                    df = new DecimalFormat("#.##");
                    y100 = Double.valueOf(df.format(y100));
                    textwidth = (int) (font.getStringBounds((Double.toString(y100)), frc).getWidth());
                    textheight = (int) (font.getStringBounds((Double.toString(y100)), frc).getHeight());
                    g2d.drawString(
                            Double.toString(y100),
                            this.marginLeft - textwidth,
                            this.marginTop + (textheight/2)
                    );
//                    draw String for 50% of Y length
                    double y50 = (y100 + y0) * 0.5;
//                    convert x as double 2 digit after point
                    df = new DecimalFormat("#.##");
                    y50 = Double.valueOf(df.format(y50));
                    textwidth = (int) (font.getStringBounds(Double.toString(y50), frc).getWidth());
                    textheight = (int) (font.getStringBounds(Double.toString(y50), frc).getHeight());
                    Ypos = ((this.HEIGHT - (this.marginBottom + this.marginTop)) * 0.5 + this.marginTop);
                    g2d.drawString(
                            Double.toString(y50),
                            this.marginLeft - textwidth,
                            (float)(Ypos + (textwidth / 2))
                    );
//                    draw String for 25% of Y length
                    double y25 = (y50 + y0) * 0.5;
//                    convert x as double 2 digit after point
                    df = new DecimalFormat("#.##");
                    y25 = Double.valueOf(df.format(y25));
                    textwidth = (int) (font.getStringBounds(Double.toString(y25), frc).getWidth());
                    textheight = (int) (font.getStringBounds(Double.toString(y25), frc).getHeight());
                    Ypos = ((this.HEIGHT - (this.marginBottom + this.marginTop)) * 0.75 + this.marginTop);
                    g2d.drawString(
                            Double.toString(y25),
                            this.marginLeft - textwidth,
                            (float) (Ypos + (textwidth / 2))
                    );
//                    draw String for 75% of Y length
                    double y75 = (y100 + y50) * 0.5;
//                    convert x as double 2 digit after point
                    df = new DecimalFormat("#.##");
                    y75 = Double.valueOf(df.format(y75));
                    textwidth = (int) (font.getStringBounds(Double.toString(y75), frc).getWidth());
                    textheight = (int) (font.getStringBounds(Double.toString(y75), frc).getHeight());
                    Ypos = ((this.HEIGHT - (this.marginBottom + this.marginTop)) * 0.25 + this.marginTop);
                    g2d.drawString(
                            Double.toString(y75),
                            this.marginLeft - textwidth,
                            (float) (Ypos + (textwidth / 2))
                    );
                }
            }
        }
    }

    private double changeScale(double data) {
        final double MinScale = this.marginTop, MaxScale = HEIGHT - this.marginBottom;
        double a = (MaxScale - MinScale) / (Ymax - Ymin);
        double b = MinScale - (Ymin * a);
        return (data * a) + b;
    }

//    ==========================================================================
//    this function for draw grid in panel
//    ==========================================================================
    private void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();   // create graphic 2D
        g2d.setColor(new Color(207, 207, 207));     // set color for draw grid
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        // coordinate for draw line
        int x1 = this.marginLeft, y1 = this.marginTop, x2 = this.marginLeft, y2 = (int) (HEIGHT - this.changeScale(0));
//        Draw line in vertical
//        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        float Xpos;
        for (double i = 0; i <= 1; i += 0.05) {
//            convert i as double 4 digit after point
            DecimalFormat df = new DecimalFormat("#.####");
            i = Double.valueOf(df.format(i));
            Xpos = (float) ((this.WIDTH - (this.marginLeft + this.marginRight)) * i + this.marginLeft);
            g2d.drawLine((int) Xpos, y1, (int) Xpos, y2); // Line of 25%
        }

        //        reset coordination for draw line
        x1 = this.marginLeft;
        x2 = this.WIDTH - this.marginRight;
        //        Draw line in horizontal
        //        ______________________________________________________________________
        //        ______________________________________________________________________
        //        ______________________________________________________________________
        for (double i = 0; i <= 1; i += 0.25) {
//            convert i as double 4 digit after point
            DecimalFormat df = new DecimalFormat("#.####");
            i = Double.valueOf(df.format(i));
            Double Ypos = this.changeScale(this.Ymax * i);
            y1 = (int) ((this.HEIGHT - this.marginBottom + this.marginTop) - Ypos);
            g2d.drawLine(x1, y1, x2, y1);
        }

//        test
        g2d.setColor(Color.red);
        g2d.drawLine(this.WIDTH, 0, this.WIDTH, this.HEIGHT);
        g2d.drawLine(0, this.HEIGHT, this.WIDTH, this.HEIGHT);
    }

//    ##########################################################################
//    ****************************** Main !!! **********************************
//    ##########################################################################
//    public static void main(String args[]) {
//        Bell small = new Bell("small", 5, 2.06 , -5);
//        small.membershipGrade(-5, 5, 0.01);
//
//        Bell large = new Bell("large", 5, 2.06, 5);
//        large.membershipGrade(-5, 5, 0.01);
//        
//        GraphPanel panel1 = new GraphPanel(800, 300);
//        //panel1.setSize(800, 600);
//        panel1.setOpaque(false);
//        panel1.setVisible(true);
//        panel1.setData(small.name, small.member);
//        panel1.setData(large.name, large.member);
//
//        JFrame f = new JFrame();
//        f.setSize(850, 650);
//        f.add(panel1);
//
//        f.setAutoRequestFocus(true);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//    }

//    ##########################################################################
//    Function for easy show something
//    ##########################################################################
    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }
}
//****************************** End of file ***********************************
