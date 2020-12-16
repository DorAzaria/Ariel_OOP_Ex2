



<img src="C:\Users\dolev\OneDrive\שולחן העבודה\background.jpg" alt="background" style="zoom:90%;" />



## Pokémon Game

Project as part of the object-oriented course JAVA language.

The above project deals with the construction of the data structure Directed and weighted graph -

Which will be a platform for the game "Find the Pokémon".

The game is divided into two parts:

1. The game platform

2. The game construction

   

   ## Part 1. The game platform - Directed Weighted Graph

   

**In this part you can find algorithms that deal with solving various problems:**

1. Connectivity of graph.
2. The length of the shortest path between two nodes.
3. The list of nodes that are in the shortest path between two nodes.

The classes of the part :

1. **DWGraph_DS** which implements the interface : **directed_weighted_graph**

| Data members: | Description                                                |
| ------------- | ---------------------------------------------------------- |
| vertices      | representing by HashMap                                    |
| adjacency     | representing by HashMap                                    |
| edges         | representing by HashSet                                    |
| e             | The number of edges in the graph (int type)                |
| mc            | The number of operations performed in the graph (int type) |

| Methods:   | Description                                                  | Time complexity |
| ---------- | ------------------------------------------------------------ | --------------- |
| DWGraph_DS | constructor                                                  | O(1)            |
| getNode    | return an object of type node_data that associated with the initial key | O(1)            |
| getEdge    | return an object of type edge_data that associated with the initial keys | O(1)            |
| hasNode    | return an Boolean value about if the initial node are in the graph | O(1)            |
| addNode    | add a new node to the graph                                  | O(1)            |
| Connect    | connect between two nodes in the graph with positive weight  | O(1)            |
| getV       | return the collection of all nodes in the graph              | O(1)            |
| getV       | return a collection of all the edges that associated with the initial key. | O(K)            |
| removeNode | remove a node and all the edges that are linked to that node in the graph. | O(K)            |
| removeEdge | remove the edge between two nodes in the graph.              | O(1)            |

2. **DWGraph_Algo** which implements the interface : **dw_graph_algorithms**

| Data members | Description                                                 |
| ------------ | ----------------------------------------------------------- |
| graph        | an object (directed_weighted_graph) that represents a graph |
| paths        | HashSet that contains all paths combinations                |
| GRAY         | Color object that use to mark spesific nodes                |
| EPSILON      | a constant that ensures the most effective ratio            |

| Methods:         | Description                                                  | Time complexity                                              |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| init             | initialize the graph                                         | O(1)                                                         |
| getGraph         | return a graph object                                        | O(1)                                                         |
| Copy             | return a deep copy of graph object                           | <img src="C:\Users\dolev\OneDrive\שולחן העבודה\e1e99764e23be92b694aef042c6460ff921357e3.svg" style="zoom:%;" /> |
| isConnected      | return true or false if the graph is a strongly connected. this method use Kosaraju's algorithm. | [![img](https://camo.githubusercontent.com/9a8e653df4fb157f321f251832ccd619e1099ef1c24774ab8eb9cfdc53c085d7/68747470733a2f2f77696b696d656469612e6f72672f6170692f726573745f76312f6d656469612f6d6174682f72656e6465722f7376672f61376366333137666265333936356165333136346632386331663638353836393661646232336634)](https://camo.githubusercontent.com/9a8e653df4fb157f321f251832ccd619e1099ef1c24774ab8eb9cfdc53c085d7/68747470733a2f2f77696b696d656469612e6f72672f6170692f726573745f76312f6d656469612f6d6174682f72656e6465722f7376672f61376366333137666265333936356165333136346632386331663638353836393661646232336634) |
| graphTranspose   | transpose directed wighted graph                             | O(V)                                                         |
| shortestPathDist | return the length (int) of the shortest path between two nodes this method use Dijkstra's algorithm. | [![img](https://camo.githubusercontent.com/08a6b9eea3147d57cf08a7a8cb4f2c3255694d82081bcc6ba26dcedf8b6ce5d4/68747470733a2f2f77696b696d656469612e6f72672f6170692f726573745f76312f6d656469612f6d6174682f72656e6465722f7376672f65323231363262653835643036623334366633623766376161643937343664613063313031396339)](https://camo.githubusercontent.com/08a6b9eea3147d57cf08a7a8cb4f2c3255694d82081bcc6ba26dcedf8b6ce5d4/68747470733a2f2f77696b696d656469612e6f72672f6170692f726573745f76312f6d656469612f6d6174682f72656e6465722f7376672f65323231363262653835643036623334366633623766376161643937343664613063313031396339) |
| shortestPath     | return a collection that contains all the nodes in the path between two nodes. this method us Dijkstra's algorithm. | [![img](https://camo.githubusercontent.com/08a6b9eea3147d57cf08a7a8cb4f2c3255694d82081bcc6ba26dcedf8b6ce5d4/68747470733a2f2f77696b696d656469612e6f72672f6170692f726573745f76312f6d656469612f6d6174682f72656e6465722f7376672f65323231363262653835643036623334366633623766376161643937343664613063313031396339)](https://camo.githubusercontent.com/08a6b9eea3147d57cf08a7a8cb4f2c3255694d82081bcc6ba26dcedf8b6ce5d4/68747470733a2f2f77696b696d656469612e6f72672f6170692f726573745f76312f6d656469612f6d6174682f72656e6465722f7376672f65323231363262653835643036623334366633623766376161643937343664613063313031396339) |
| save             | graph object serialization (to JSON file)                    |                                                              |
| load             | graph object deserialization (from JSON file)                |                                                              |
| Reset            | return all the data members of the graph to defalut values   | O(V)                                                         |

​	3. **Nodes** which implements the interface : **node_data**

| Data members | Description                                       |
| ------------ | ------------------------------------------------- |
| pos          | represent the location of the node (geo_location) |
| key          | represent the key of each node (Integer)          |
| tag          | represent the color of each node (Color)          |
| weight       | represent the weight of each node (Double)        |
| info         | represent the info of each node (String)          |

| Methods:                    | Description                                                  | Time complexity |
| --------------------------- | ------------------------------------------------------------ | --------------- |
| Nodes                       | constructor                                                  | O(1)            |
| Nodes                       | copy constructor                                             | O(1)            |
| getLocation and SetLocation | return or set the location(geo_Location) that assoiciated with the node | O(1)            |
| getKey and setKey           | return or set the key (int) that associated with the node    | O(1)            |
| getInfo and setInfo         | return or set the info (String) that associated with the node | O(1)            |
| getTag and setTag           | return or set the color (Color) that associated with the node | O(1)            |
| getWeight and setWeight     | return or set the weight (Double) that assoiciated with the node | O(1)            |

​	3.1  **GeoLocation** which implements the interface : **geo_location**

| Data members | Description                                             |
| ------------ | ------------------------------------------------------- |
| point        | represent the location of the node (Point3D - (x,y,z) ) |

| Methods:    | Description                     | Time complexity |
| ----------- | ------------------------------- | --------------- |
| GeoLocation | constructor                     | O(1)            |
| x           | return the value of x cordinate | O(1)            |
| y           | return the value of y cordinate | O(1)            |
| z           | return the value of z cordinate | O(1)            |

​	4. **Edges** which implements the interface : **edge_data**

| Data members | Description                                            |
| ------------ | ------------------------------------------------------ |
| src          | represent the key of the source node (Integer)         |
| dest         | represent the key of the destination node (Integer)    |
| w            | represent the w eight of the edge (Double)             |
| source       | represent the source node of the edge (node_data)      |
| destination  | represent the destination node of the edge (node_data) |

| Methods:                   | Description                                                  | Time complexity |
| -------------------------- | ------------------------------------------------------------ | --------------- |
| Edge                       | constructor                                                  | O(1)            |
| Edge                       | copy constructor                                             | O(1)            |
| getSrc and getSource       | return the value of the source node or the node itself.      | O(1)            |
| getDest and getDestination | return the value of the destination node or the node itself. | O(1)            |
| getWeight and setWeight    | return or set the weight (Double) that assoiciated with the edge | O(1)            |



## Part 2. The game constructor 

<img src="C:\Users\dolev\OneDrive\שולחן העבודה\S.png" style="zoom:70%;" />



> **In this part you will find different classes and methods which can be used to build the game** :



### **Game user interface - Using the classes JFRAME & JPanel** 

1. **ourFrame** which extends the class : **JFrame**

| Data members: | Description                                       |
| ------------- | ------------------------------------------------- |
| Arena         | represent all the charecters that use in the game |
| ourPanel      | inner frame that running every graphic object     |

| Methods:  | Description            |
| --------- | ---------------------- |
| ourFrame  | constructor            |
| initFrame | initialize the frame   |
| getPanel  | return ourPanel object |

1.1 **ourPanel** which extends the class : **JPanel**

| Data members: | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| Arena         | object that contains the functions of the characters         |
| Range2Range   | object that orders the resolution of the objects             |
| r             | constant that arranges the proportions of the inner frame (Integer) |
| grade         | represents the agent's score (Double)                        |
| moves         | represents the agent's moves (Integer)                       |

| Methods:       | Description                                               |
| -------------- | --------------------------------------------------------- |
| ourPanel       | constructor                                               |
| paint          | paint in each run-step all graphics contexts              |
| resize         | resize the frame                                          |
| drawPokemon    | draws the pokemons and their location in the panel        |
| drawAgents     | draws the agents and their location in the panel          |
| drawGraph      | draws the graph and its nodes and edges in the panel      |
| drawNode       | drasws the nodes and their keys in the panel              |
| drawEdge       | draws the edges and their keys in the panel               |
| drawArrow      | draws an arrow between two given points                   |
| removeEdge     | remove the edge between two nodes in the graph.           |
| importPictures | method that imports images that will be used for the game |

### Game Client  

The classes diagram :

<img src="C:\Users\dolev\OneDrive\שולחן העבודה\ללא שם.png" style="zoom:70%;" />

**Arena** -  This is a class where there are different methods regarding characters in the game.

You will find in the above class the methods :

1. setPokemons ans getPokemons
2. setAgents and getAgents 
3. setGraph and getGraph
4. getGame and setGame

**Agent** - this is a class that represent the agent “Ash”

You will find in the above class the methods :

1. update - method that update in run time the agents
2. getSrcNode 
3. setPoints
4. setNextNode
5. setCurrNode
6. getLocation

**Pokeon** - this is a class that represent the pokemons :  “mewtwo” , “charizard” , “pikachu”

You will find in the above class the methods :

1. update - method that update in run time the agents
2. getEdges  and setEdges
3. getLocation
4. getType
5. getValue
6. getDest
7. getSrc

### Ex2  - class that represents the game strategy

| Data members: | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| Frame         | represent the frame of the game                              |
| ManageGame    | represent all the charecters that use in the game (Arena object) |
| num_level     | repersent the game level that taken from the server          |
| graph         | an object (directed_weighted_graph) that represents a graph  |
| graph_algo    | an object (directed_weighted_graph_algo) that represents a graph using algorithms |
| client        | Thread Object                                                |
| dt            | the sleeping time for the thread (Integer)                   |
| attack        | represent the target (pokemons) of each agent using HashMap  |

| Methods:       | Description                                                  |
| -------------- | ------------------------------------------------------------ |
| login          | a login system                                               |
| run            | override the run method from the Thread class , this method tun the game and also move the agents in the graph |
| loadGraph      | loads the graph from the server                              |
| init           | init the game - first get the pokemons from the server and put the agent in a strategic location |
| moveAgents     | moves the agents on the graph                                |
| nextNode       | find the shortest path from agent to pokemon using the Dijkstra algorithm |
| ComparatorDist | comparator that use for PriorityQueue to arrange the Pokemon based on the shortest distance |



