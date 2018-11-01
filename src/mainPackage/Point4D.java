package mainPackage;

public class Point4D implements Comparable{
	private double x1,x2,x3,x4;
	private String pointClass;
	double distanceFromConsideredPoint =0;
	
	public Point4D(double x1,double x2,double x3,double x4) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
	}
	public  Point4D(double x1,double x2,double x3,double x4 ,String c) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
		this.pointClass = c;
	}
	
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getX3() {
		return x3;
	}
	public void setX3(double x3) {
		this.x3 = x3;
	}
	public double getX4() {
		return x4;
	}
	public void setX4(double x4) {
		this.x4 = x4;
	}
	public String getPointClass() {
		return pointClass;
	}
	public void setPointClass(String pointClass) {
		this.pointClass = pointClass;
	}
	public double getDistanceFromConsideredPoint() {
		return distanceFromConsideredPoint;
	}
	public void setDistanceFromConsideredPoint(double distanceFromConsideredPoint) {
		this.distanceFromConsideredPoint = distanceFromConsideredPoint;
	}
	@Override
	public String toString() {
		return " point ("+this.x1+" , "+this.x2+" , "+this.x3+" , "+this.x4+" ) and class = "+this.pointClass+" and distance = "+this.getDistanceFromConsideredPoint();
	}
	@Override
	public int compareTo(Object o) {
		if((((Point4D) o).distanceFromConsideredPoint - this.getDistanceFromConsideredPoint())< 0) 
			return 1;
		else return -1;
	}

}
