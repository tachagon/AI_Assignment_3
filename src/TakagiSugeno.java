import org.jzy3d.demos.scatter.ScatterDemo;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jzy3d.demos.DemoLauncher;

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
    public TakagiSugeno(double start, double end, double step) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.inputList = new ArrayList();
        this.outputList = new ArrayList();
        this.ruleList = new ArrayList();
    }
    
    public TakagiSugeno(){
        this.start = 0.0;
        this.end = 0.0;
        this.step = 0.0;
        this.inputList = new ArrayList();
        this.outputList = new ArrayList();
        this.ruleList = new ArrayList();
    }
    
//    ==========================================================================
//    Function for set start value
//    ==========================================================================
    public void setStart(double start){
        this.start = start;
    }
    
//    ==========================================================================
//    Function for set end value
//    ==========================================================================
    public void setEnd(double end){
        this.end = end;
    }

//    ==========================================================================
//    Function for set step value
//    ==========================================================================
    public void setStep(double step){
        this.step = step;
    }

//    ==========================================================================
//    Function for ADD a Fuzzy Set into Takagi-Sugeno Input
//    ==========================================================================
    public void addInput(FuzzySet input) {
        this.inputList.add(input);
    }

//    ==========================================================================
//    Function for set output name
//    ==========================================================================
    public void setOutputName(String output) {
        this.output = output;
    }

//    ==========================================================================
//    Function for add a rule in ruleList
//    You should use genRule before and select some rule to input of this function.
//    ==========================================================================
    public void addRule(List rule) {
        this.ruleList.add(rule);
    }

//    ==========================================================================
//    Function for generate all rule that can be used
//    ==========================================================================
    public List genRule() {
// format of rules [ "If x is small then...", p, q, r, memberList]
        List rules = new ArrayList();
//        set Initial value of p, q and r parameter
        double p = 0, q = 0, r = 0;
// fotmat of member [FuzzySet, MFindex] for one Fuzzy set input or
// format of member [FuzzySet, MFindex], [FuzzySet, MFindex] for two Fuzzy set input
        List member = new ArrayList();
        String str = "";
//        for have one Fuzzy Set Input
        if (this.inputList.size() == 1) {
//            get fuzzy set input
            FuzzySet input = (FuzzySet) this.inputList.get(0);
            for (int i = 0; i < input.getMFNum(); i++) {
                str = "If "
                        + input.name
                        + " is "
                        + input.getMFName(i)
                        + " then "
                        + this.output
                        + " = ";

                List temp = new ArrayList();
                member.add(input);
                member.add(i);

                temp.add(str);
                temp.add(p);
                temp.add(q);
                temp.add(r);
                temp.add(member);
                member = new ArrayList();
                rules.add(temp);
                str = "";
            }
        } //        for have two Fuzzy Set Input
        else if (this.inputList.size() == 2) {
            FuzzySet input1 = (FuzzySet) this.inputList.get(0);
            FuzzySet input2 = (FuzzySet) this.inputList.get(1);
            for (int i = 0; i < input1.getMFNum(); i++) {
//                example of fotmat of temp1 is [X, small]
                List temp1 = new ArrayList();
                temp1.add(input1);
                temp1.add(i);

                str = "";
                for (int j = 0; j < input2.getMFNum(); j++) {
                    str += "If "
                            + input1.name
                            + " is "
                            + input1.getMFName(i)
                            + " and "
                            + input2.name
                            + " is "
                            + input2.getMFName(j)
                            + " then "
                            + this.output
                            + " = ";
//                    example of format of temp2 is [Y, small]
                    List temp2 = new ArrayList();
                    temp2.add(input2);
                    temp2.add(j);

                    member = new ArrayList();
                    member.add(str);
                    member.add(p);
                    member.add(q);
                    member.add(r);
                    member.add(temp1);
                    member.add(temp2);
                    str = "";

                    rules.add(member);
                }
            }
        }
        return rules;
    }

//    ==========================================================================
//    Function for calculate Output and keep output value in outputList
//    Requirement   1. have inputList size greater than 0
//                  2. have output name
//                  3. have ruleList size greater than 0
//    Format of outputList
//          [
//              [valueX, valueY, valueZ], [valueX, valueY, valueZ], ...
//          ]
//    ==========================================================================
    public void calOutput() {
//        if have one fuzzy set input
        if (this.inputList.size() == 1) {
            println("calculate output for one Input Start...");
//            Iteration for access all x in fuzzy set
            for (double x = this.start; x <= this.end; x += this.step) {
//              convert x as double 4 digit after point
                DecimalFormat df = new DecimalFormat("#.####");
                x = Double.valueOf(df.format(x));

                double y = 0;       // create y for keep output value
                double wSum = 0;    // create wSum for keep summation of weight
//                Iterator for access each rule in ruleList
                for (int i = 0; i < this.ruleList.size(); i++) {
//                    get a rule from ruleList
//                    format of rule ["If X is ...", p, q, r, memberList]
                    List rule = (List) this.ruleList.get(i);
                    double p = (double) rule.get(1);    // get p value
                    double r = (double) rule.get(3);    // get r value
//                    format of member [Fuzzyset, MFindex]
                    List member = (List) rule.get(4);
                    FuzzySet fs = (FuzzySet) member.get(0); // get a Fuzzy Set
                    int MFindex = (int) member.get(1);      // get MFindex

                    // calculate weight of current rule
                    double w = fs.getMG(MFindex, x);
                    double Ytemp = (p * x) + r;

                    wSum += w;  // summation of weight for all rule
                    y += Ytemp * w; // summary of result of each rule
                }
                y = y / wSum;
                //println("y = " + y);
                //println("");
                List temp = new ArrayList();
                temp.add(x);
                temp.add(y);
                temp.add((double) 0.0);
                this.outputList.add(temp);
            }
        } //        if have two fuzzy set input
        else if (this.inputList.size() == 2) {
            println("calculate output for two Input Start...");
//            Iteration for access all x in fuzzy set
            for (double x = this.start; x <= this.end; x += this.step) {
//                  convert x as double 4 digit after point
                DecimalFormat df = new DecimalFormat("#.####");
                x = Double.valueOf(df.format(x));
//                  Iteration for access all y in fuzzy set
                for (double y = this.start; y <= this.end; y += this.step) {
//              convert y as double 4 digit after point
                    DecimalFormat df2 = new DecimalFormat("#.####");
                    y = Double.valueOf(df2.format(y));

                    double z = 0;       // create y for keep output value
                    double wSum = 0;    // create wSum for keep summation of weight
//                    Iterator for access each rule in ruleList
                    for (int i = 0; i < this.ruleList.size(); i++) {
//                        get a rule from ruleList
//                        format of rule ["If X is ...", p, q, r, memberList, memberList]
                        List rule = (List) this.ruleList.get(i);
                        double p = (double) rule.get(1);    // get p value
                        double q = (double) rule.get(2);    // get q value
                        double r = (double) rule.get(3);    // get r value
//                        format of member [Fuzzyset, MFindex]
                        List member1 = (List) rule.get(4);
                        List member2 = (List) rule.get(5);

                        FuzzySet fs1 = (FuzzySet) member1.get(0); // get Fuzzy Set 1
                        int MFindex1 = (int) member1.get(1);      // get MFindex 1
                        FuzzySet fs2 = (FuzzySet) member2.get(0); // get a Fuzzy Set 2
                        int MFindex2 = (int) member2.get(1);      // get MFindex 2

                        // calculate weight of fuzzy set 1
                        double w1 = fs1.getMG(MFindex1, x);
                        // calculate weight of fuzzy set 2
                        double w2 = fs2.getMG(MFindex2, y);
                        // select minimum weight as weight of current rule
                        double w = Double.min(w1, w2);

                        double Ztemp = (p * x) + (q * y) + r;

                        wSum += w;  // summation of weight for all rule
                        z += Ztemp * w; // summary of result of each rule
                    }
                    z = z / wSum;
                    List temp = new ArrayList();
                    temp.add(x);
                    temp.add(y);
                    temp.add(z);
                    this.outputList.add(temp);
                }
            }
        }
    }
    
//    ==========================================================================
//    Function for reset inputList
//    ==========================================================================
    public void resetInputList(){
        this.inputList = new ArrayList();
    }
    
//    ==========================================================================
//    Function for reset ruleList
//    ==========================================================================
    public void resetRuleList(){
        this.ruleList = new ArrayList();
    }
    
//    ==========================================================================
//    Function for reset outputList
//    ==========================================================================
    public void resetOutputList(){
        this.outputList = new ArrayList();
    }

//    ##########################################################################
//    ****************************** Main !!! **********************************
//    ##########################################################################
//    public static void main(String args[]) {
////        example 9.3 ==========================================================
//        TakagiSugeno TS = new TakagiSugeno(-10, 10, 0.01);
//        TS.setOutputName("Y");
//
//        FuzzySet X = new FuzzySet("X", TS.start, TS.end, TS.step);
//
//        Sigmoidal small = new Sigmoidal("small", -1, -4);
//        small.membershipGrade(-10, 10, 0.1);
//
//        Bell medium = new Bell("medium", 4, 5, 0);
//        medium.membershipGrade(-10, 10, 0.1);
//
//        Sigmoidal large = new Sigmoidal("large", 1, 4);
//        large.membershipGrade(-10, 10, 0.1);
//
//        X.addMF(small);
//        X.addMF(medium);
//        X.addMF(large);
//
//        TS.addInput(X);
//
//        List rules = TS.genRule();
//
//        List rule1 = (List) rules.get(0);
//        List rule2 = (List) rules.get(1);
//        List rule3 = (List) rules.get(2);
//
//        rule1.set(1, (double) 0.1);
//        rule1.set(3, (double) 6.4);
//
//        rule2.set(1, (double) -0.5);
//        rule2.set(3, (double) 4);
//
//        rule3.set(1, (double) 1);
//        rule3.set(3, (double) -2);
//
//        TS.addRule(rule1);
//        TS.addRule(rule2);
//        TS.addRule(rule3);
//
//        TS.calOutput();
//
////        for(Object o:TS.outputList){
////            println(o);
////        }
////        ======================================================================
////        example 9.4 ==========================================================
//        TakagiSugeno TS2 = new TakagiSugeno(-5, 5, 0.05);
//        TS2.setOutputName("Z");
//
//        FuzzySet X2 = new FuzzySet("X", TS2.start, TS2.end, TS2.step);
//        FuzzySet Y2 = new FuzzySet("Y", TS2.start, TS2.end, TS2.step);
//
//        Sigmoidal small1 = new Sigmoidal("small", -3, 0);
//        small1.membershipGrade(TS2.start, TS2.end, TS2.step);
//
//        Sigmoidal large1 = new Sigmoidal("large", 3, 0);
//        large1.membershipGrade(TS2.start, TS2.end, TS2.step);
//
//        Bell small2 = new Bell("small", 5, 2.06, -5);
//        small2.membershipGrade(TS2.start, TS2.end, TS2.step);
//
//        Bell large2 = new Bell("large", 5, 2.06, 5);
//        large2.membershipGrade(TS2.start, TS2.end, TS2.step);
//
//        X2.addMF(small1);
//        X2.addMF(large1);
//
//        Y2.addMF(small2);
//        Y2.addMF(large2);
//
//        TS2.addInput(X2);
//        TS2.addInput(Y2);
//
//        List rules2 = TS2.genRule();
//
//        List rule21 = (List) rules2.get(0);
//        List rule22 = (List) rules2.get(1);
//        List rule23 = (List) rules2.get(2);
//        List rule24 = (List) rules2.get(3);
//
//        rule21.set(1, (double)-1);
//        rule21.set(2, (double)1);
//        rule21.set(3, (double)1);
//
//        rule22.set(1, (double)0);
//        rule22.set(2, (double)-1);
//        rule22.set(3, (double)3);
//
//        rule23.set(1, (double)-1);
//        rule23.set(2, (double)0);
//        rule23.set(3, (double)3);
//
//        rule24.set(1, (double)1);
//        rule24.set(2, (double)1);
//        rule24.set(3, (double)2);
//
//        TS2.addRule(rule21);
//        TS2.addRule(rule22);
//        TS2.addRule(rule23);
//        TS2.addRule(rule24);
//
//        TS2.calOutput();
//        
////        for(Object o: TS2.outputList){
////            double x = (double) ((List)o).get(0);
////            double y = (double) ((List)o).get(1);
////            double z = (double) ((List)o).get(2);
////            println(x + "," + y + "," + z);
////        }
//        
//        try {
//            DemoLauncher.openDemo(new ScatterDemo(TS2.outputList));
//        } catch (Exception ex) {
//            Logger.getLogger(TakagiSugeno.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
