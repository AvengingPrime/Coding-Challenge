import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Cluster
{
	PriorityQueue<Node> nodes;
	Node root;
	int clusters;
	int dendogramLevel = 0;

	public Cluster(String fileName)
	{
		Scanner console = null;
		nodes = new PriorityQueue<>();
		try
		{
			console = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File Not Found");
		}
		console.nextLine();
		while(console.hasNextLine())
		{
			Scanner line = new Scanner(console.nextLine());
			line.useDelimiter(",");
			line.nextInt();
			nodes.offer(new Node(line.nextDouble(), line.nextDouble()));
		}
		setAllNodes();
	}

	public void setAllNodes()
	{
		for(Node n : nodes)
		{
			n.setNodes(nodes.iterator());
		}
	}

	public void findClusters()
	{
		while(nodes.size() > 1)
		{
			step();
		}
		root = nodes.poll();
		findMaxEdgeLevel(root, findMaxEdge(root), 1);

		clusters = dendogramLevel * 2;
	}

	public void step()
	{
		Node temp = nodes.poll();
		Node temp2 = nodes.poll();

		ArrayList<Node> arr = new ArrayList<>();

		while(!temp.closestNode.equals(temp2))
		{
			arr.add(temp2);
			temp2 = nodes.poll();
		}
		Node n = new Node(temp, temp2);
		nodes.offer(n);
		for(Node n2 : arr)
		{
			nodes.offer(n2);
		}
		setAllNodes();
	}

	public double findMaxEdge(Node n)
	{
		if(n.left == null && n.right == null)
		{
			return 0;
		}
		else
		{
			return Math.max(n.weight, Math.max(findMaxEdge(n.left), findMaxEdge(n.right)));
		}
	}

	public void findMaxEdgeLevel(Node n, double max, int level)
	{
		if(n == null)
		{
			return;
		}
		if(n.weight == max)
		{
			dendogramLevel = level;
			return;
		}
		else
		{
			level++;
			findMaxEdgeLevel(n.left, max, level);
			findMaxEdgeLevel(n.right, max, level);
		}
	}

	public int getNumClusters()
	{
		return clusters;
	}

	public String toString()
	{
		return "" + root;
	}
}


