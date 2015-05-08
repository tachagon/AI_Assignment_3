
import java.util.List;
import java.util.ArrayList;

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
}

//##############################################################################
//******************************* Triangle !!! *********************************
//##############################################################################
class Triangle extends MF{
//    Parameter for Triangle
    String name;
    int a;
    int b;
    int c;
    
//    ==========================================================================
//    Contructor method for create Triangle Object
//    ==========================================================================
    public Triangle(String name, int a, int b, int c){
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
}
//****************************** End of file ***********************************