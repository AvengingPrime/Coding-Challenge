# ACM Research Coding Challenge (Fall 2020)

## No Collaboration Policy

**You may not collaborate with anyone on this challenge.** You _are_ allowed to use Internet documentation. If you _do_ use existing code (either from Github, Stack Overflow, or other sources), **please cite your sources in the README**.

## Submission Procedure

Please follow the below instructions on how to submit your answers.

1. Create a **public** fork of this repo and name it `ACM-Research-Coding-Challenge`. To fork this repo, click the button on the top right and click the "Fork" button.
2. Clone the fork of the repo to your computer using . `git clone [the URL of your clone]`. You may need to install Git for this (Google it).
3. Complete the Challenge based on the instructions below.
4. Email the link of your repo to research@acmutd.co with the same email you used to submit your application. Be sure to include your name in the email.

## Question One

![Image of Cluster Plot](ClusterPlot.png)
<br/>
Given the following dataset in `ClusterPlot.csv`, determine the number of clusters by using any clustering algorithm. **You're allowed to use any Python library you want to implement this**, just document which ones you used in this README file. Try to complete this as soon as possible.

Regardless if you can or cannot answer the question, provide a short explanation of how you got your solution or how you think it can be solved in your README.md file.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

So I had not used any clustering algorithms before this project. First I tried to think about how a clustering algorithm might work. My first idea was to go through the points and try to find points that could be considered centre's of cluster. This would have been done by iteratively going through the points and finding points that maximized cluster density by having a maximum number of points in a given range. I thought the range could be set to a particular multiple of the standard deviation between the points, so as to not hard code it. 

Then I decided to actually look up examples of cluster algorithms. The first one I found was K-Means. This algorithm was similar to the concept I had come up with except it had a predetermined number of clusters. Since the problem statement asked to find the number of clusters I thought this algorithm might not be valid since it just finds the best possible cluster groups given the number of clusters. Next I looked at hierarchical clustering algorithm. 

The first one I looked at was Agglomerative Hierarchical Clustering. This algorithm begins by treating each point as its own cluster and then grouping the two closest clusters together. It then uses a "dendrogram" to divide the clusters back. My understanding of the dendrogram was that it found the two clusters with the highest distance between them and "cut" it so that the tree would divide into clusters at level of the "cut". 

Although the instructions suggested the use of Python and its libraries I decided to use Java for this program. I didn't want to depend on libraries since without an understanding of what is supposed to happen and how the libraries work I wouldn't be able to debug my code or identify problems. Even if I had successfully completed the program. I still wouldn't understand why and how some of the parts worked.

I had worked with a similar clustering approach in a highschool project where we used a binary tree formed of letters appearing in a text, weighted by their frequency, to find the huffman compression code for the text. Thus, I decided to go along with this algorithm. First I decided to build a node class which represented a node of the binary tree that was about to form. Each leaf would store the X and Y coordinates of a point. Each non-leaf node would store the mean X and Y of its leaves. A non-leaf node also stored the "weight" or the distance between the X and Y of its children. Each node would also have an array list of all the current nodes (updated at each step through a wrapper class)
which it would use to find the closest node and its distance to the current node. I then used a wrapper class to hold a priority queue of the nodes. First the priority queue would be filled with leaf nodes containing all the points. Then the queue would use the node's compare to (which was based around the minimum distance from the node to another node) to determine the next two nodes to combine. Then the queue would remove the values, combine them, and add them back. Then the arrays for each node would be reset manually, thus causing an update of the closest distance and closest node variables for each node. These steps are repeated until the queue only contains a single node. This node is stored as the root. I then use a recursive method to find the node with the highest weight, and the level of the node (root = level 1). After finding the level, the cluster is simply the level * 2, since it "cuts" the tree at that level.

Please see the attached pdf (also titled ReadMe) to get a better understanding of my brainstorming process.

These are the articles I read:
https://www.tutorialspoint.com/machine_learning_with_python/clustering_algorithms_overview.htm
https://www.tutorialspoint.com/machine_learning_with_python/clustering_algorithms_k_means_algorithm.htm
https://www.tutorialspoint.com/machine_learning_with_python/clustering_algorithms_hierarchical_clustering.htm
