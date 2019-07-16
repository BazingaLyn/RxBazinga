package org.lyncc.bazinga.rx.bazinga.algorithm.answer.graph;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 求解无向图的单源最短路径
 */
public class NonDirectedGraph {

    private class Vertex{
        private String vertexLabel;//顶点标识
        private List<Edge> adjEdges;//与该顶点邻接的边(点)
        private int dist;//顶点距离(该顶点到起始顶点的距离)
        private Vertex preNode;

        public Vertex(String vertexLabel) {
            this.vertexLabel = vertexLabel;
            adjEdges = new LinkedList<>();
            dist = Integer.MAX_VALUE;
            preNode = null;
        }
    }
    private class Edge{
        private Vertex endVertex;
        public Edge(Vertex endVertex) {
            this.endVertex = endVertex;
        }
    }

    private Map<String, Vertex> nonDirectedGraph;//保存了图中所有的顶点，边的关系以List形式保存在Vertex类中
    private Vertex startVertex;//图的起始顶点

    public NonDirectedGraph(String graphContent) {
        nonDirectedGraph = new LinkedHashMap<>();
        buildGraph(graphContent);
    }

    private void buildGraph(String graphContent){
        String[] lines = graphContent.split("\n");

        String startNodeLabel, endNodeLabel;
        Vertex startNode, endNode;
        for(int i = 0; i < lines.length; i++){
            String[] nodesInfo = lines[i].split(",");
            startNodeLabel = nodesInfo[1];
            endNodeLabel = nodesInfo[2];

            endNode = nonDirectedGraph.get(endNodeLabel);
            if(endNode == null){
                endNode = new Vertex(endNodeLabel);
                nonDirectedGraph.put(endNodeLabel, endNode);
            }

            startNode = nonDirectedGraph.get(startNodeLabel);
            if(startNode == null){
                startNode = new Vertex(startNodeLabel);
                nonDirectedGraph.put(startNodeLabel, startNode);
            }
            Edge e = new Edge(endNode);
            //对于无向图而言,起点和终点都要添加边
            endNode.adjEdges.add(e);
            startNode.adjEdges.add(e);
        }
        startVertex = nonDirectedGraph.get(lines[0].split(",")[1]);//总是以文件中第一行第二列的那个标识顶点作为源点
    }

    public void unweightedShortestPath(){
        unweightedShortestPath(startVertex);
    }


    /*
     * 计算源点s到无向图中各个顶点的最短路径
     * 需要一个队列来保存图中的顶点,初始时,源点入队列,然后以广度的形式向外扩散求解其他顶点的最短路径
     */
    private void unweightedShortestPath(Vertex s){
        //初始化
        Queue<Vertex> queue = new LinkedList<>();
        s.dist = 0;
        queue.offer(s);//将源点dist设置为0并入队列

        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            for (Edge e : v.adjEdges) {//扫描v的邻接边(点)
                if(e.endVertex.dist == Integer.MAX_VALUE){//如果这个顶点(e.endVertex)未被访问(每个顶点只会入队列一次)
                    e.endVertex.dist = v.dist + 1;//更新该顶点到源点的距离
                    queue.offer(e.endVertex);
                    e.endVertex.preNode = v;//设置该顶点的前驱顶点
                }//end if
            }//end for
        }//end while
    }

    //打印图中所有顶点到源点的距离及路径
    public void showDistance(){
        Collection<Vertex> vertexs = nonDirectedGraph.values();
        for (Vertex vertex : vertexs) {
            System.out.print(vertex.vertexLabel + "<--");
            Vertex tmpPreNode = vertex.preNode;
            while(tmpPreNode != null){
                System.out.print(tmpPreNode.vertexLabel + "<--");
                tmpPreNode = tmpPreNode.preNode;
            }
            System.out.println("distance=" + vertex.dist);
        }
    }
}

