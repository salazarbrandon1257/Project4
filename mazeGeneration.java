import java.util.*;

public class mazeGeneration{
    int elemNumber;
    int row;
    int col;
    ArrayList<Integer> wallList;

    public mazeGeneration(int n, int m){
        row = n;
        col = m;
        elemNumber = n * m;
        wallList = new ArrayList<>();
        for (int i = 0; i < elemNumber * 2; i++){
            wallList.add(i);
        }
        System.out.println(Arrays.toString(wallList.toArray()));
        mazeUi();
    }
    public void mazeUi(){
        int[] counter = new int[elemNumber];
        for (int i = 0; i < col; i++){
            System.out.print("_  ");
        }
        System.out.println("");
        for (int i = 0; i < wallList.size(); i++){
            if (i > elemNumber - 1){
                counter[i % elemNumber] += 2;
            }else{
                counter[i % elemNumber] += 1;
            }
        }
        for (int i = 0; i < counter.length; i++){
            if (counter[i] == 1){
                System.out.print("| ");
            }
            else if (counter[i] == 2){
                System.out.print(" _ ");
            }
            else if (counter[i] == 3){
                System.out.print("|_ ");
            }
            if (i % col == col - 1){
                System.out.println("");
            }
        }
        System.out.println("");
    }
//  _  _ 
// |_ _
// |_ 

    public void generateMaze(){
        DisjSets sets = new DisjSets(elemNumber);
        int count = 0;
        while (sets.find(elemNumber - 1) != 0){
            Random rand = new Random(); 
            int random = rand.nextInt((elemNumber * 2) - count) ; 
            if (random % col != 0 && random < elemNumber){
                if (sets.find(random - 1) != sets.find(random)){
                    sets.union(random - 1, random);
                    for (int i = 0; i < wallList.size(); i++){
                        if (wallList.get(i) == random)
                            wallList.remove(i);
                    }
                    count++;
                    System.out.println(Arrays.toString(wallList.toArray()));
                    System.out.println(sets.find(random));
                }
            }
            if (sets.find(elemNumber - 1) == 0){break;}
            if (random < elemNumber * 2 - col && random > elemNumber - 1){
                if (sets.find(random - elemNumber) != sets.find(random + col - elemNumber)){
                    sets.union(random - elemNumber, random + col - elemNumber);
                    for (int i = 0; i < wallList.size(); i++){
                        if (wallList.get(i) == random)
                            wallList.remove(i);
                    }
                    count++;
                    System.out.println(Arrays.toString(wallList.toArray()));
                    System.out.print("random: ");
                    System.out.println(random);

                    System.out.print("random -elem: ");
                    System.out.println(random - elemNumber);

                    System.out.print("random + col -elem: ");
                    System.out.println(random + col - elemNumber);

                    System.out.print("find: ");
                    System.out.println(sets.find(random + col - elemNumber));
                }
            }
        }
    }

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
 
        System.out.print("Input a value for the row number(Integer < 20): ");
        String userInput = myObj.nextLine();  // Read user input
        System.out.print("Input a value for the column number(Integer < 20): ");
        String userInput2 = myObj2.nextLine();  // Read user input
        mazeGeneration maze = new mazeGeneration(Integer.parseInt(userInput), Integer.parseInt(userInput2));
        maze.generateMaze();

        

    } 
}