


class Edge implements Comparable<Edge> 
{ 
    int src, dest, weight; 
  
        // Comparator function used for sorting edges  
        // based on their weight 
    public int compareTo(Edge compareEdge) 
    {   
        if(this.weight < compareEdge.weight)
            return -1;
        if(this.weight > compareEdge.weight)
            return +1;
        return 0;
    } 
};
