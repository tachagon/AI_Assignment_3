
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
    double Ymax = 1.2, Ymin = 0;
    final Color[] color = {
        new Color(47, 79, 79),      // DarkSlateGray
        new Color(121, 205, 205),   // DarkSlateGray3
        new Color(82, 139, 139),    // DarkSlateGray4
        new Color(238, 232, 170),   // PaleGoldenrod
        new Color(160, 82, 45),     // Sienna
        new Color(244, 164, 96),    // SandyBrown
        new Color(124, 252, 0),     // LawnGreen
        new Color(50, 205, 50),     // Lime
        new Color(34, 139, 34),     // ForestGreen
        new Color(255, 255, 0),     // Yellow1
        new Color(238, 201, 0),     // Gold2
        new Color(255, 193, 193),   // RosyBrown1
        new Color(205, 155, 155),   // RosyBrown3
        new Color(255, 106, 106),   // IndianRed1
        new Color(139, 58, 58),     // IndianRed4
        new Color(205, 104, 57),    // Sienna3
        new Color(255, 20, 147),    // DeepPink1
        new Color(205, 16, 118)     // DeepPink3
    };

//    ==========================================================================
//    Constructor Method for create a GraphPanel Object
//    ==========================================================================
    public GraphPanel(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.dataList = new ArrayList();
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

    }

    public void setData(String name, List data) {
//        format of data is [dataX, dataY]
//        format of temp is [name, DataList]
        List temp = new ArrayList();
        temp.add(name);
        temp.add(data);
//        format of dataList is [[name, DataList], [name, DataList], ...]
        this.dataList.add(temp);
    }

//    ==========================================================================
//    \/\/\/\/\/\/\/\/\/\ this function for draw graph  /\/\/\/\/\/\/\/\/\/\/\/\
//    ==========================================================================
    public void drawGraph(Graphics g) {
        if (this.dataList.size() > 0) {
            for (int i = 0; i < this.dataList.size(); i++) {
                List data = (List) ((List) this.dataList.get(i)).get(1);
                int Xmax = data.size();
                if (Xmax > 1) {
                    Graphics2D g2d = (Graphics2D) g.create();   // create graphic 2D
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    double x = 50;
                    Color c = color[i];
                    for (int j = 1; j < Xmax; j++) {
                        List dataBefore = (List) data.get(j - 1);
                        List dataAfter = (List) data.get(j);
//                  set x,y coordinate for draw line of graph
//                  (x1, y1) _____________________Line____________________(x2, y2)
                        double x1 = x;
                        //double y1 = (HEIGHT - 50) - (this.changeScale((double)dataBefore.get(1)));
                        double y1 = (HEIGHT - 50) - ((double) dataBefore.get(1) * (HEIGHT - 50));
                        //double y1 = (double) (HEIGHT - this.changeScale((double) dataBefore.get(1)));
                        x += (double) (WIDTH - 50) / Xmax;

                        double x2 = x;
                        double y2 = (HEIGHT - 50) - ((double) dataAfter.get(1) * (HEIGHT - 50));
                        //double y2 = (double) (HEIGHT - this.changeScale((double) dataAfter.get(1)));

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
                    println(textwidth + ", " + textheight);

                    g2d.drawLine(WIDTH - 30, (i+1)*(textheight-1), WIDTH, (i+1)*(textheight-1));
                    g2d.setFont(new Font("Serif", Font.PLAIN, 12));
                    g2d.setColor(Color.black);
                    g2d.drawString(name, (WIDTH-40)-textwidth, (i+1)*textheight);
                }
            }
        }
    }

    private double changeScale(double data) {
        final double MinScale = 0, MaxScale = HEIGHT-50;
        double a = (MaxScale - MinScale) / (Ymax - Ymin);
        double b = MinScale - (Ymin * a);
        return (data * a) + b;
    }

//    ==========================================================================
//    this function for draw grid in panel
//    ==========================================================================
    private void drawGrid(Graphics g) {
        final int gap = 20; // set distance between line
        Graphics2D g2d = (Graphics2D) g.create();   // create graphic 2D
        g2d.setColor(new Color(207, 207, 207));     // set color for draw grid
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        // coordinate for draw line
        int x1 = 50, y1 = 0, x2 = 50, y2 = HEIGHT - 50;
//        Draw line in vertical
//        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        for (int i = 0; i <= ((WIDTH - 50) / gap); i++) {
            g2d.drawLine(x1, y1, x2, y2);
            x1 += gap;
            x2 += gap;
        }
//        reset coordination for draw line
        x1 = 50;
        y2 = 0;
        x2 = WIDTH;
//        Draw line in horizontal
//        ______________________________________________________________________
//        ______________________________________________________________________
//        ______________________________________________________________________
        for (int i = 0; i <= (HEIGHT - 50) / gap; i++) {
            g2d.drawLine(x1, y1, x2, y2);
            y1 += gap;
            y2 += gap;
        }

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // วาดตัวเลขสำหรับค่าน้อยสุดและมากสุดในแกน X
        g2d.setColor(Color.black);
        g2d.drawString(Integer.toString(0), 40, HEIGHT - 40);
        g2d.drawString(Integer.toString(100), WIDTH, HEIGHT - 40);

        g2d.setColor(Color.orange);
        g2d.drawLine(50, HEIGHT - 30, 80, HEIGHT - 30);
        g2d.setFont(new Font("Serif", Font.PLAIN, 12));
        g2d.setColor(Color.black);
        g2d.drawString("Membership Function", 85, HEIGHT - 25);

        // โค้ดสำหรับหาความกว้างและความยาว ของตัวอักษร
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = new Font("Serif", Font.PLAIN, 12);
        int textwidth = (int) (font.getStringBounds("Membership Function", frc).getWidth());
        int textheight = (int) (font.getStringBounds("Membership Function", frc).getHeight());

    }

//    ##########################################################################
//    ****************************** Main !!! **********************************
//    ##########################################################################
    public static void main(String args[]) {
        Triangle test = new Triangle("small", 50, 90, 100);
        test.membershipGrade(0, 100, 1);

        Triangle tri2 = new Triangle("medium", 20, 40, 60);
        tri2.membershipGrade(0, 100, 1);

        GraphPanel panel1 = new GraphPanel(800, 600);
        panel1.setSize(600, 600);
        panel1.setOpaque(false);
        panel1.setVisible(true);
        panel1.setData(test.name, test.member);
        panel1.setData(tri2.name, tri2.member);

        JFrame f = new JFrame();
        f.setSize(870, 620);
        f.add(panel1);

        f.setAutoRequestFocus(true);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

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
