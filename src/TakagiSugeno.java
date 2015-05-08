
import java.util.ArrayList;
import java.util.List;


//         __________________    _________    _________
//        / _______/ _______ \  / _______ \  / _______/
//       / /      / /       \ \/ /       \ \/ /       
//      / /      / /________/ / /________/ / /______
//     / /      / ___________/ ___  ______/ _______/
//    / /      / /          / /   \ \    / /      
//   / /______/ /          / /     \ \  / /______
//  /________/_/          /_/       \_\/________/
//##############################################################################
//********************** Takagi-Sugeno Fuzzy Model !!! *************************
//##############################################################################
public class TakagiSugeno {
//    inputList is List of Fuzzy Set input
//    format is [FuzzySet1, FuzzySet2, ..., FuzzySetN]
    public List inputList;
//    outputList is List of output value in 3D
//    format is [output1, output2, ..., outputN]
//    output format is [valueX, valueY, valueZ]
    public List outputList;
//    ruleList is List of fuzzy if-then rule
//    format is [rule1, rule2, ..., ruleN]
    public List ruleList;
    public double start;    // Start of Number of Universe 
    public double end;      // End of Number of Universe
    public double step;     // Number for increase each step of MF
    public String output;   // name of Output
    
//    ==========================================================================
//    Constructor Method for create Takagi Sugeno Fuzzy Model
//    ==========================================================================
    public TakagiSugeno(double start, double end, double step){
        this.start = start;
        this.end = end;
        this.step = step;
        this.inputList = new ArrayList();
        this.outputList = new ArrayList();
        this.ruleList = new ArrayList();
    }
    
//    ==========================================================================
//    Function for ADD a Fuzzy Set into Takagi-Sugeno Input
//    ==========================================================================
    public void addInput(FuzzySet input){
        this.inputList.add(input);
    }
    
//    ==========================================================================
//    Function for set output name
//    ==========================================================================
    public void setOutputName(String output){
        this.output = output;
    }
    
//    ##########################################################################
//    ****************************** Main !!! **********************************
//    ##########################################################################
    public static void main(String args[]){
        TakagiSugeno TS = new TakagiSugeno(-10, 10, 1);
        
        FuzzySet X = new FuzzySet("X", TS.start, TS.end, TS.step);
        
        Triangle small = new Triangle("small", -10, -7, -4);
        small.membershipGrade(-10, 10, 0.1);
        Triangle medium = new Triangle("medium", -6, 0, 6);
        medium.membershipGrade(-10, 10, 0.1);
        Triangle large = new Triangle("large", 4, 7, 10);
        large.membershipGrade(-10, 10, 0.1);
        
        X.addMF(small);
        X.addMF(medium);
        X.addMF(large);
        
        TS.addInput(X);
        
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
