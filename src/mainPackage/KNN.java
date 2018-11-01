package mainPackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class KNN {
	private ArrayList<Point4D> traningSet;
	private String  csvDataSetFilePath;
	private ArrayList<Point4D> testSet;
	private int k ;
	
	public KNN(int count , String path ){
		this.traningSet = new ArrayList<Point4D>();
		this.csvDataSetFilePath = path;
		this.k = count;
		this.testSet = new ArrayList<Point4D>();
		
	}
	
	public void loadData(String path) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitChar = ",";
        int i = 0;

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitChar);
                i++;
                
               Point4D point = new Point4D(Double.valueOf(country[0]).doubleValue(),
                							Double.valueOf(country[1]).doubleValue(),
                							Double.valueOf(country[2]).doubleValue(),
                							Double.valueOf(country[3]).doubleValue(),
                							country[4].trim());
                this.traningSet.add(point);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                	}
            	}
        	}
        System.out.println("we loaded "+i+" examples ");
	}
	
	private double getDistance(Point4D a, Point4D b) { // squared distance 
		return Math.sqrt(Math.pow((a.getX1() -b.getX1()),2.0)+
						Math.pow((a.getX2() -b.getX2()),2.0)+
						Math.pow((a.getX3() -b.getX3()),2.0)+
						Math.pow((a.getX4() -b.getX4()),2.0)
						);
	}
	
	public void breakDownDataSet(double testSetPercentage) {
		int dataSize = this.traningSet.size();
		int count = (int) (dataSize * testSetPercentage);
		/* i will be cutting from the end because i shifted the data separately 
		i used python pandas  for this is more simple and quick otherwise 
		i provided a  to do so in the previous exercise with introducing the noise 
		it can also be done using Excel or google Sheet ( the last tool is much easier ) 
		*/ 
		int i = 0, j = dataSize-1;
		while(i< count){
			this.testSet.add(this.traningSet.get(j));
			this.traningSet.remove(j);
			j--;
			i++;
			
		}
		System.out.println("breaking down the data set is done ; results are : ");
		System.out.println("test set size is "+testSet.size()+"  examples ");
		System.out.println("training set size is "+this.traningSet.size()+"  examples ");
	}
	
	public Point4D selectedClass(Point4D point){
		/* from the dataDescription we have 3 classes :
		 * -- Iris Setosa
      		-- Iris Versicolour
      		-- Iris Virginica
		 */
		int c1 = 0,c2 = 0,c3 = 0;/*here we could use a list (dynamic structure ) 
									for a variable number of classes  (to make the function more generic 
									but i decided not to do it because 3 int vars take less memory space
									 than a list of 3 int elements (with pointers ...) 
									 ( i privileged the memory space over the genericity purpose) */ 
		   for(int j = 0; j < this.traningSet.size() ; j++) {
			   this.traningSet.get(j).setDistanceFromConsideredPoint(this.getDistance(point, this.traningSet.get(j)));
		   }
		  Collections.sort(this.traningSet);

		  System.out.println("classes of the "+this.getK()+" neighbours are ");
		  
		  for (int i = 0; i <this.k ; i++) {
			  
			  System.out.println("hoho" +"|"+this.traningSet.get(i).getPointClass()+"|");
			 if(this.traningSet.get(i).getPointClass().equals( "Iris-setosa"))
				c1++;
			 else if (this.traningSet.get(i).getPointClass().equals("Iris-versicolor"))
				 c2++;
			  else c3++;	  
		  }
		  
		  int max = Math.max(c1, Math.max(c2, c3));
	
		  if(max == c1)
			  point.setPointClass("Iris-setosa");
		  else if (max == c2)
			  point.setPointClass("Iris-versicolor");
		  else 
			  point.setPointClass("Iris-virginica");
		  
		  System.out.println("====> the selected class is "+point.getPointClass());
		 return point;  
	}
	
	public double error(ArrayList<Point4D> testSet){
		   double result = 0;
		   //first we calculate the sum of wrong results
		   System.out.println("now here is the process of calculating the labels for a tranning set \n");
		   for(int i = 0; i < testSet.size() ; i ++) {
			   Point4D p = new Point4D(testSet.get(i).getX1(),
					   					testSet.get(i).getX2(),
					   					testSet.get(i).getX3(),
					   					testSet.get(i).getX4());
			   p = this.selectedClass(p);
			   if(!p.getPointClass().equals(testSet.get(i).getPointClass()))
				   result += 1;
		   }
		   System.out.println("**********\n ");
		   return (result / testSet.size())*100;
	 }

	public ArrayList<Point4D> getTraningSet() {
		return traningSet;
	}

	public void setTraningSet(ArrayList<Point4D> traningSet) {
		this.traningSet = traningSet;
	}

	public String getCsvDataSetFilePath() {
		return csvDataSetFilePath;
	}

	public void setCsvDataSetFilePath(String csvDataSetFilePath) {
		this.csvDataSetFilePath = csvDataSetFilePath;
	}

	public ArrayList<Point4D> getTestSet() {
		return testSet;
	}

	public void setTestSet(ArrayList<Point4D> testSet) {
		this.testSet = testSet;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
}
