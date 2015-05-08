
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

//         __________________    _________    _________
//        / _______/ _______ \  / _______ \  / _______/
//       / /      / /       \ \/ /       \ \/ /       
//      / /      / /________/ / /________/ / /______
//     / /      / ___________/ ___  ______/ _______/
//    / /      / /          / /   \ \    / /      
//   / /______/ /          / /     \ \  / /______
//  /________/_/          /_/       \_\/________/
public class MF {
    // use for keep member of MF format is [member, MemberShip Grade]
    public List member;
    
    public static void main(String args[]){
        Sigmoidal test = new Sigmoidal("small", 1, -5);
        test.membershipGrade(-10, 10, 1);

//        Bell tri2 = new Bell("medium", 20, 4, 50);
//        tri2.membershipGrade(0, 100, 0.1);

        GraphPanel panel1 = new GraphPanel(800, 600);
        panel1.setSize(600, 600);
        panel1.setOpaque(false);
        panel1.setVisible(true);
        panel1.setData(test.name, test.member);
//        panel1.setData(tri2.name, tri2.member);

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
    private static void print(Object o){
        System.out.print(o);
    }
    private static void println(Object o){
        System.out.println(o);
    }
}

//##############################################################################
//******************************* Triangle !!! *********************************
//##############################################################################
class Triangle extends MF{
//    Parameter for Triangle
    String name;
    double a; //first point
    double b; //middle pouint
    double c; //last point
    
//    ==========================================================================
//    Contructor method for create Triangle Object
//    ==========================================================================
    public Triangle(String name, double a, double b, double c){
//        set initial value for each variable
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        member = new ArrayList();
    }
    
//    ==========================================================================
//    calculate and keep membership grade for each member
//    ==========================================================================
    public void membershipGrade(double start, double end, double step){
//        Iteration for calculate membership grade of all member
        for(double x = start; x <= end; x += step){
//            temp List for keep member and membership grade of member
//            format: [member(x), membership grade(u(x))]
            List temp = new ArrayList();
            temp.add(x);
//            find membership grade follow formular No. 7-19 in page No. 79
            temp.add(Double.max(Double.min((x-a)/(b-a), (c-x)/(c-b)), 0));
            member.add(temp);   // add all into member list
        }
    }
    
//    ==========================================================================
//    calculate membership grade for x
//    ==========================================================================
    public double calMG(double x){
        return Double.max(Double.min((x-a)/(b-a), (c-x)/(c-b)), 0);
    }
    
//    ==========================================================================
//    Copy Object for use in other function
//    ==========================================================================
    public void copy(Triangle source){
        this.a = source.a;
        this.b = source.b;
        this.c = source.c;
        this.name = source.name;
    }
}

//##############################################################################
//******************************* Trapezoid !!! ********************************
//##############################################################################
class Trapezoid extends MF{
//    Parameter for Trapezoid
    String name;
    double a; // first point
    double b; // first top point
    double c; // last top point
    double d; // last point
    
//    ==========================================================================
//    Contructor method for create Trapezoid Object
//    ==========================================================================
    public Trapezoid(String name ,double a, double b, double c, double d){
//        set initial value for each variable
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        member = new ArrayList();
    }
    
//    ==========================================================================
//    calculate and keep membership grade for each member
//    ==========================================================================
    public void membershipGrade(double start, double end, double step){
//        Iteration for calculate membership grade of all member
        for(double x = start; x <= end; x += step){
//            temp List for keep member and membership grade of member
//            format: [member(x), membership grade(u(x))]
            List temp = new ArrayList();
            temp.add(x);
//            find membership grade follow formular No. 7-21 in page No. 80
            temp.add(Double.max(Double.min(Double.min((x-a)/(b-a), 1), (d-x)/(d-c)), 0));
            member.add(temp);   // add all into member list
        }
    }
    
//    ==========================================================================
//    calculate membership grade for x
//    ==========================================================================
    public double calMG(double x){
        return (Double.max(Double.min(Double.min((x-a)/(b-a), 1), (d-x)/(d-c)), 0));
    }
    
//    ==========================================================================
//    Copy Object for use in other function
//    ==========================================================================
    public void copy(Trapezoid source){
        this.a = source.a;
        this.b = source.b;
        this.c = source.c;
        this.d = source.d;
        this.name = source.name;
    }
}

//##############################################################################
//******************************* Gaussian !!! *********************************
//##############################################################################
class Gaussian extends MF{
//    Parameter for Trapezoid
    String name;
    double c;       // Center of Membership Function
    double sigma;   // Width of Membership Function
    
//    ==========================================================================
//    Contructor method for create Trapezoid Object
//    ==========================================================================
    public Gaussian(String name , double c, double sigma){
//        set initial value for each variable
        this.name = name;
        this.c = c;
        this.sigma = sigma;
        member = new ArrayList();
    }
    
//    ==========================================================================
//    calculate and keep membership grade for each member
//    ==========================================================================
    public void membershipGrade(double start, double end, double step){
//        Iteration for calculate membership grade of all member
        for(double x = start; x <= end; x += step){
//            temp List for keep member and membership grade of member
//            format: [member(x), membership grade(u(x))]
            List temp = new ArrayList();
            temp.add(x);
//            find membership grade follow formular No. 7-21 in page No. 80
            temp.add(Math.exp((-0.5)*Math.pow(((x-c)/sigma), 2)));
            member.add(temp);   // add all into member list
        }
    }
    
//    ==========================================================================
//    calculate membership grade for x
//    ==========================================================================
    public double calMG(double x){
        return (Math.exp((-0.5)*Math.pow(((x-c)/sigma), 2)));
    }
    
//    ==========================================================================
//    Copy Object for use in other function
//    ==========================================================================
    public void copy(Gaussian source){
        this.c = source.c;
        this.sigma = source.sigma;
        this.name = source.name;
    }
}

//##############################################################################
//********************************* Bell !!! ***********************************
//##############################################################################
class Bell extends MF{
//    Parameter for Trapezoid
    String name;
    double a; // Width of Function
    double b; // Slope of Function
    double c; // Center of Function
    
//    ==========================================================================
//    Contructor method for create Trapezoid Object
//    ==========================================================================
    public Bell(String name , double a, double b, double c){
//        set initial value for each variable
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        member = new ArrayList();
    }
    
//    ==========================================================================
//    calculate and keep membership grade for each member
//    ==========================================================================
    public void membershipGrade(double start, double end, double step){
//        Iteration for calculate membership grade of all member
        for(double x = start; x <= end; x += step){
//            temp List for keep member and membership grade of member
//            format: [member(x), membership grade(u(x))]
            List temp = new ArrayList();
            temp.add(x);
//            find membership grade follow formular No. 7-21 in page No. 80
            temp.add(1/(1+Math.pow(Math.abs((x-c)/a), 2*b)));
            member.add(temp);   // add all into member list
        }
    }
    
//    ==========================================================================
//    calculate membership grade for x
//    ==========================================================================
    public double calMG(double x){
        return (1/(1+Math.pow(Math.abs((x-c)/a), 2*b)));
    }
    
//    ==========================================================================
//    Copy Object for use in other function
//    ==========================================================================
    public void copy(Bell source){
        this.a = source.a;
        this.b = source.b;
        this.c = source.c;
        this.name = source.name;
    }
}
    
//##############################################################################
//********************************* Sigmoidal !!! ******************************
//##############################################################################
class Sigmoidal extends MF{
//    Parameter for Trapezoid
    String name;
    double a; // Slope of Function
    double c; // x when y = 0.5
    
//    ==========================================================================
//    Contructor method for create Trapezoid Object
//    ==========================================================================
    public Sigmoidal(String name , double a, double c){
//        set initial value for each variable
        this.name = name;
        this.a = a; 
        this.c = c; 
        member = new ArrayList();
    }
    
//    ==========================================================================
//    calculate and keep membership grade for each member
//    ==========================================================================
    public void membershipGrade(double start, double end, double step){
//        Iteration for calculate membership grade of all member
        for(double x = start; x <= end; x += step){
//            temp List for keep member and membership grade of member
//            format: [member(x), membership grade(u(x))]
            List temp = new ArrayList();
            temp.add(x);
//            find membership grade follow formular No. 7-21 in page No. 80
            temp.add(1/(1+Math.exp(-a*(x-c))));
            member.add(temp);   // add all into member list
        }
    }
    
//    ==========================================================================
//    calculate membership grade for x
//    ==========================================================================
    public double calMG(double x){
        return (1/(1+Math.exp(-a*(x-c))));
    }
    
//    ==========================================================================
//    Copy Object for use in other function
//    ==========================================================================
    public void copy(Sigmoidal source){
        this.a = source.a;
        this.c = source.c;
        this.name = source.name;
    }
}

//****************************** End of file ***********************************