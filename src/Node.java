import java.util.ArrayList;
import java.util.Iterator;

public class Node implements Comparable<Node>
{
	double x;
	double y;
	
	Node left;
	Node right;
	ArrayList<Node> nodes;
	double weight;
	double minDistance;
	Node closestNode;
	
	public Node(double x, double y)
	{
		left = null;
		right = null;
		this.x = x;
		this.y = y;
	}
	
	public Node(Node left, Node right)
	{
		this.left = left;
		this.right = right;
		setXY();
		weight = Math.sqrt(Math.pow(this.left.x - this.right.x, 2) + Math.pow(this.left.y - this.right.y, 2));
	}
	
	public void setXY()
	{
		ArrayList<Node> temp = new ArrayList<>();
		getLeaves(this, temp);
		double sumX = 0;
		double sumY = 0;
		int i;
		for(i = 0; i < temp.size(); i++)
		{
			sumX += temp.get(i).x;
			sumY += temp.get(i).y;
		}
		
		this.x = sumX/i;
		this.y = sumY/i;	
	}
	
	
	public void getLeaves(Node n, ArrayList<Node> nodes)
	{
		if(n == null)
		{
			return;
		}
		else if(n.left == null && n.right == null)
		{
			nodes.add(n);
			return;
		}
		else
		{
			getLeaves(n.left, nodes);
			getLeaves(n.right, nodes);
		}
	}
	
	public void setNodes(Iterator<Node> arr)
	{
		nodes = new ArrayList<>();
		boolean encounteredSelf = false;
		minDistance = Double.MAX_VALUE;
		closestNode = null;
		
		while(arr.hasNext())
		{
			Node n = arr.next();
			if(n.equals(this) && !encounteredSelf)
			{
				encounteredSelf = true;
				continue;
			}
			nodes.add(n);
			if(minDistance > getDistance(n))
			{
				minDistance = getDistance(n);
				closestNode = n;
			}
		}
	}
	
	private double getDistance(Node other)
	{
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	public int compareTo(Node other)
	{
		return (minDistance < other.minDistance)? -1 : ((minDistance == other.minDistance)? 0 : 1);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (this.x == ((Node)obj).x && this.y == ((Node)obj).y) && (this.left == ((Node)obj).left && this.right == ((Node)obj).right);
	}
	
	@Override
	public String toString()
	{
		return "X: " + x + ", Y: " + y + " { " + this.left + "; " + this.right + "}";
	}
	
	public String toString(Node n)
	{
		if(n == null)
		{
			return "null";
		}
		else
		{
			return "X: " + x + ", Y: " + y + " { " + toString(n.left) + "; " + toString(n.right) + "}";
		}
	}
	
	public static void main(String[] args)
	{
		Node n = new Node(new Node(3, 2), new Node(4, 5));
		System.out.println();
	}
}