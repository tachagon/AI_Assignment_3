
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
    
    public FuzzySet(){
//        set Initial value for each variable
        MFList      = new ArrayList();
        this.name   = "";
        this.start  = 0;
        this.end    = 0;
        this.step   = 0;
    }
    
//    ==========================================================================
//    Function for add a Membership Function into FuzzySet
//    ==========================================================================
    public void addMF(Object obj){
        MFList.add(obj);
    }
  
//    ==========================================================================
//    Function for get membership function object from index of MFList
//    ==========================================================================
    public Object getMF(int index){
        Object obj = this.MFList.get(index);
        String className = this.MFList.get(index).getClass().getName();
//        Operation of code below
//        1. check object class
//        2. create new object and copy all value from obj to new object
//        3. return new object
        if(className == "Triangle"){
            Triangle tri = new Triangle();
            tri.copy((Triangle) obj);
            return tri;
        }
        else if(className == "Trapezoid"){
            Trapezoid tra = new Trapezoid();
            tra.copy((Trapezoid) obj);
            return tra;
        }
        else if(className == "Gaussian"){
            Gaussian gau = new Gaussian();
            gau.copy((Gaussian) obj);
            return gau;
        }
        else if(className == "Bell"){
            Bell bell = new Bell();
            bell.copy((Bell) obj);
            return bell;
        }
        else if(className == "Sigmoidal"){
            Sigmoidal sig = new Sigmoidal();
            sig.copy((Sigmoidal) obj);
            return sig;
        }
        else{
            return null;
        }
    }
    
//    ==========================================================================
//    Function for get number of Membership Function
//    ==========================================================================
    public int getMFNum(){
        return this.MFList.size();
    }
    
//    ==========================================================================
//    Function for get membership function name from index of MFList
//    ==========================================================================
    public String getMFName(int index){
        Object obj = this.MFList.get(index);
        String className = this.MFList.get(index).getClass().getName();
//        Operation of code below
//        1. check object class
//        2. create new object and copy all value from obj to new object
//        3. get membership function name and return it
        if(className == "Triangle"){
            Triangle tri = new Triangle();
            tri.copy((Triangle) obj);
            return tri.name;
        }
        else if(className == "Trapezoid"){
            Trapezoid tra = new Trapezoid();
            tra.copy((Trapezoid) obj);
            return tra.name;
        }
        else if(className == "Gaussian"){
            Gaussian gau = new Gaussian();
            gau.copy((Gaussian) obj);
            return gau.name;
        }
        else if(className == "Bell"){
            Bell bell = new Bell();
            bell.copy((Bell) obj);
            return bell.name;
        }
        else if(className == "Sigmoidal"){
            Sigmoidal sig = new Sigmoidal();
            sig.copy((Sigmoidal) obj);
            return sig.name;
        }
        else{
            return "can not find class of Object";
        }
    }
    
//    ==========================================================================
//    Function for get membership function grade value from index of MFList
//    and input x value
//    ==========================================================================
    public double getMG(int index, double x){
        Object obj = this.MFList.get(index);
        String className = this.MFList.get(index).getClass().getName();
//        Operation of code below
//        1. check object class
//        2. create new object and copy all value from obj to new object
//        3. calculate membership grade value from input x and return it
        if(className == "Triangle"){
            Triangle tri = new Triangle();
            tri.copy((Triangle) obj);
            return tri.calMG(x);
        }
        else if(className == "Trapezoid"){
            Trapezoid tra = new Trapezoid();
            tra.copy((Trapezoid) obj);
            return tra.calMG(x);
        }
        else if(className == "Gaussian"){
            Gaussian gau = new Gaussian();
            gau.copy((Gaussian) obj);
            return gau.calMG(x);
        }
        else if(className == "Bell"){
            Bell bell = new Bell();
            bell.copy((Bell) obj);
            return bell.calMG(x);
        }
        else if(className == "Sigmoidal"){
            Sigmoidal sig = new Sigmoidal();
            sig.copy((Sigmoidal) obj);
            return sig.calMG(x);
        }
        else{
            return 0.0;
        }
    }
    
//    ==========================================================================
//    This function use for copy from Source Object to Destination Object
//    ==========================================================================
    public void copy(FuzzySet source){
        this.name = source.name;
        this.start = source.start;
        this.end = source.end;
        this.step = source.step;
        this.MFList = new ArrayList();
        for(int i=0; i<source.getMFNum(); i++){
            Object mf = source.getMF(i);
            this.addMF(mf);
        }
    }
    
    public String toString(){
        return this.name;
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