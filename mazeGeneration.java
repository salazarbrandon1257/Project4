import java.util.ArrayList;
import java.util.Scanner;

public class mazeGeneration{

    ArrayList<Integer> arrlist = new ArrayList<Integer>(5);


    static class DisjSets
{
        /** Construct the disjoint sets object.
         * @param numElements the initial number of disjoint sets.
         */
        public DisjSets(int numElements)
        {
            s = new int [ numElements ];
            for( int i = 0; i < s.length; i++ )
                s[ i ] = -1;
        }

        /**   Union two disjoint sets.  
         *     Assume root1 and root2 are distinct and represent set names.
         * @param root1 the root of set 1.
         * @param root2 the root of set 2.          */
        public void union( int root1, int root2 )
        {
            s[ root2 ] = root1;
        }
        
        /** Perform a find.   Error checks omitted again for simplicity.
         * @param x the element being searched for.
         * @return the set containing x.         */
        public int find( int x )
        {
            if( s[ x ] < 0 )
                return x;
            else
                return find( s[ x ] );
        }

        private int [ ] s;
}



    public static void main(String[] args){ 
 
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
        int elementNumber;
        System.out.println("Input a value for the row number(Integer < 20): ");
        String userInput = myObj.nextLine();  // Read user input
        System.out.println("Input a value for the column number(Integer < 20): ");
        String userInput2 = myObj2.nextLine();  // Read user input

        elementNumber = Integer.parseInt(userInput) * Integer.parseInt(userInput2);
        System.out.println(elementNumber);

        DisjSets sets = new DisjSets(elementNumber);
        System.out.println(sets.find(1));
        sets.union(0, 1);
        System.out.println(sets.find(1));
        

    } 
}