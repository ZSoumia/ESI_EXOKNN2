/**
 * 
 */
package mainPackage;

/**
 * @author soumia
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dataFilePath = "iris.csv";
		int k = 10;
		KNN algo = new KNN(k,dataFilePath);
		System.out.println("loading data ......");
		algo.loadData(dataFilePath);
		System.out.println("data have been loaded ");
		// now we will be breaking down the data set into 60% for training and 40% for testing */
		System.out.println("---------------------------------------------------------------\n\n");
		System.out.println("Now we will be breaking down the data set into training and test sets ");
		algo.breakDownDataSet(0.2);
		double error = 5;
		/* now let's calculate the error */
		 /* for second section display comment these two following instructions 
		  * to provide a clean display */
		 //error = algo.error(algo.getTestSet());
		 //System.out.println("the error = " +String.format("%.2f", error)+" % \n ");
		
		/* now to choose the right k value we should be running the algo over a large 
		 * amount of k values and then choose the one that gives us a better 
		 * result ( the smallest error ) now i'll be testing over from k = 10 to k = 19 
		 * because my testing set has only 30 examples 
		 * and i've chosen k =10 after multiple tests 
		 * and it seems to be a good value to start from 
		 */
		
		/* in order to provide a readable display please don't uncomment the following section 
		 * until you get to it 
		 * and when doing so please comment the previous one
		 * lines 30 and 31 in this code ( it's mentioned above ) 
		 */
		System.out.println("\n process of choosing the best k " );
		int bestK = 10 ;
		double leastError = error;
		for (int i = 10 ; i <= 19 ;i++) {
			error = algo.error(algo.getTestSet());
			if(error<leastError) {
				bestK = i;
				leastError = error;
			}
			i++;
			algo.setK(i);
		}
	
	System.out.println("the best k  = "+bestK +" it gaves us an error of "+leastError);
	}
}
