
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


//         __________________    _________    _________
//        / _______/ _______ \  / _______ \  / _______/
//       / /      / /       \ \/ /       \ \/ /       
//      / /      / /________/ / /________/ / /______
//     / /      / ___________/ ___  ______/ _______/
//    / /      / /          / /   \ \    / /      
//   / /______/ /          / /     \ \  / /______
//  /________/_/          /_/       \_\/________/
//##############################################################################
//  FuzzySet Class
//  This class use for create FuzzySet object that operat in Takagi-Sugeno Model
//  Usage:  FuzzySet var = new FuzzySet(name, start, end, step);
//          var     is name of variable
//          name    is name of Fuzzy Set
//          start   is number for start of Universe
//          end     is number for end of Universe
//          step    is number for increase in each step of Membership Function
//##############################################################################
//****************************** Fuzzy Set *************************************
public class FuzzySet {
    String name;    // Name of Fuzzy Set
    double start;   // Start of Number of Universe 
    double end;     // End of Number of Universe
    double step;    // Number for increase each step of MF
    List MFList;    // List for keep Membership Function Objects
    
    
//    ==========================================================================
//    Constructor method for create FuzzySet Object
//    ==========================================================================
    public FuzzySet(String name, double start, double end, double step){
//        set Initial value for each variable
        MFList      = new ArrayList();
        this.name   = name;
        this.start  = start;
        this.end    = end;
        this.step   = step;
    }
    
    public void addMF(Object obj){
        MFList.add(obj);
    }
    
//    ==========================================================================
//    This function use for copy from Source Object to Destination Object
//    ==========================================================================
    public void copy(FuzzySet Source){
        
    }
    
    public String toString(){
        return "";
    }
    
//    ##########################################################################
//    ****************************** Main !!! **********************************
//    ##########################################################################
    public static void main(String args[]){
        Triangle test = new Triangle("small", 20, 60, 80);
        test.membershipGrade(0, 100, 1);
        
        println(test.getClass().getName());
        
        List x = new ArrayList();
        x.add(1);
        x.add("KOL");
        x.add(1.33);
        
        println(x);
        println("Access List via while");
        Iterator iter = x.iterator();
        while(iter.hasNext()){
            Object temp = iter.next();
            println(temp + "\t" + temp.getClass());
        }
        
        println("Access List via for");
        for(Object temp:x){
            println(temp + "\t" + temp.getClass());
        }
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
//****************************** End of file ***********************************