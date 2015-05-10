
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
    public TakagiSugeno(double start, double end, double step) {
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
//    ==========================================================================
    public void addRule(List rule){
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

//    ##########################################################################
//    ****************************** Main !!! **********************************
//    ##########################################################################
    public static void main(String args[]) {
        TakagiSugeno TS = new TakagiSugeno(-10, 10, 1);
        TS.setOutputName("Z");

        FuzzySet X = new FuzzySet("X", TS.start, TS.end, TS.step);
        FuzzySet Y = new FuzzySet("Y", TS.start, TS.end, TS.step);

        Sigmoidal small = new Sigmoidal("small", -1, -4);
        small.membershipGrade(-10, 10, 0.1);

        Bell medium = new Bell("medium", 4, 5, 0);
        medium.membershipGrade(-10, 10, 0.1);

        Sigmoidal large = new Sigmoidal("large", 1, 4);
        large.membershipGrade(-10, 10, 0.1);

        X.addMF(small);
        X.addMF(medium);
        X.addMF(large);
        Y.addMF(small);
        Y.addMF(medium);
        Y.addMF(large);

        TS.addInput(X);
        TS.addInput(Y);
        
        List rules = TS.genRule();
        for(Object o:rules){
            //List mem = (List) ((List)o).get(4);
            //println(mem);
            println(o);
        }
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
