# Algos-in-giraph
This repository contains the famous algorithmic code of graphs that are implemented in Apache Giraph.
Algorithms implemented

1. Single Source Shortest Path (It comes with the default examples in Giraph)
2. PageRank Algorithm
3. Stochastic Gradient Descend (Used Agrregrator Class)
4. Label Propagation Algorithm
5. InDegree and OutDegree
6. MaxComputation

###Following are the screenshots and the conceots describing the above algorithms
**Note** InDegree OutDegree and MaxComputation is not explained here. It can be easily understood when learning the concepts of graphs or you can Google it.

#### Single Source Shortest Path

An algorithm to search for the shortest path(s) between two vertices—or the shortest path between a
vertex and any other (reachable) vertex—is called a single-source shortest paths (SSSP) algorithm. A very
intuitive and simple algorithm that can be used to this end is the so-called breadth-first search (BFS). 1
Starting from a source vertex, the algorithm visits all its neighbors. These neighbors are considered to be at
distance 1, because they are separated from the source vertex by one edge. The next vertices to be visited are
the neighbors of these neighbors, except those that have been already visited. These vertices are considered
to be at distance 2 from the source, or one hop more distant than the vertices they were visited from. The
algorithm continues like this until all vertices have been visited and all distances have been computed.
Intuitively, the way BFS visits the vertices in a graph starting from a source vertex follows a wave-like
pattern, just like the waves produced on a flat surface by a stone thrown into water. The paths are explored in
breadth, all of them one hop at a time.

In the case of a weighted graph, edge weights represent distances between neighbors. Hence, the length
of a path is computed as the sum of the weights of the edges that constitute the path. To support weights, the
algorithm has to be slightly modified. Screenshot below shows the execution of SSSP on a weighted graph starting
from the leftmost vertex, Mark. Any time vertices are visited, instead of adding one hop to the distance of
the vertices they were visited from, the weight of the traversed edge is added. Look, for example, at how
the distance of John is defined as the weight of the edge that connects him to Mark, and how the distance
of Peter is this value plus the distance from John. If a vertex is visited multiple times, either in the same
iteration or in a future one, its distance is updated only if the distance has improved.

![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/SSSP.png)

#### PageRank

Imagine that you were to open your browser and point it to a
random URL (assuming you could pick a valid URL at random). You would then click a random link on the
page and jump to that page. You would follow this procedure forever: an infinitely long sequence of random
clicks from page to page. The question is, would you end up on certain pages more often than on others?
After all, if a page is important on the Web, there should be many links pointing to that page. More links means more probability of clicking such links and more probability of ending up on certain pages. Moreover,
it should be safe to assume that important pages often contain links to other important pages. This increases
the likelihood of clicking a link to an important page.

The formula used in the famous PageRank Algorithm is 
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/pagerank1.png)

One thing to notice in the definition of the PageRank is that it is recursive. The PageRank of each page
depends on the PageRank of the incoming neighbors. How can you then compute the PageRank values?
Every vertex PageRank depends on that of its neighbors. You need a starting point. Well, you can assign
an initial value to each page and then iteratively compute the new PageRank value for each page with the
formula. Although each vertex starts with the same initial value—typically 1 divided by the number of
vertices in the graph—to mimic the initial random choice of page to start the random surf, iteration after
iteration certain pages collect a higher PageRank due to the underlying structure of the graph. After some
iterations, the PageRank values converge to stability, meaning the values change only slightly between the
previous value and the next one.


The concept is explained below diagramatically
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/pagerank2.png)
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/pagerank3.png)
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/pagerank4.png)

#### Stochastic Gradient Descend
The formula used in SGD is 
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/SGD.png)

The concept used is:

![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/SGD1.png)
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/SGD2.png)

#### Label Propagation Algorithm

Each vertex has a label, initially its own ID, that it sends to its direct
neighbors through messages. The simple heuristic that each vertex applies is to acquire the label that is
occurring most frequently among its neighbors, breaking ties randomly (that is, if two labels occur most
frequently across neighbors and have the same frequency, choose randomly). When a vertex acquires a new
label, it sends the label to its neighbors. This is why the algorithm is called the label propagation algorithm.
For example, Lady Gaga initially propagates the string “Lady Gaga”, which is her ID. At each iteration, each
vertex acquires a new label if it finds a label that occurs more frequently across its neighbors than its current
one. In the case of Lady Gaga, after some iterations, the label appears more frequently in the neighborhood
of her fans should be “Lady Gaga”. In that case, the vertex propagates the label to its neighbors through a
message. The algorithm halts when no vertex changes label. At the end of the computation, each vertex
holds the label representing its community. This label corresponds to the ID of a vertex in its community—a
vertex that is more “central” to the community it represents (in this example, “Lady Gaga”).

![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/LPA1.png)
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/LPA2.png)
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/LPA3.png)
![](https://raw.githubusercontent.com/LakshayNagpal/Algos-in-giraph/master/images/LPA4.png)
