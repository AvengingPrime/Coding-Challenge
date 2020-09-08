public class Runner
{
	public static void main(String[] args)
	{
		Cluster cluster = new Cluster("ClusterPlot.csv");
		cluster.findClusters();
		System.out.println("Done... There are " + cluster.getNumClusters() + " clusters");
		
	}
}
