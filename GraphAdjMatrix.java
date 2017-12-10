public class GraphAdjMatrix implements Graph{

	public int[][] edges;
	public int size;
	// public boolean[] known = new boolean[size];
	// public int[] cost = new int[size];
	// public int[] path = new int[size];
	
	public void addEdge(int v1, int v2){
		return;
	}
	public void topologicalSort(){
		return;
	}


	public GraphAdjMatrix(int vs){
		
		size = vs;
		edges = new int[size][size];

	}

	public void addEdge(int v1, int v2, int weight){

		try {
            
            edges[v1][v2] = weight;
            edges[v2][v1] = weight;
        
        }catch (ArrayIndexOutOfBoundsException indexBounce){
           
            System.out.println("the vertex is not present");
        }
	}
	public int getEdge(int v1, int v2){

		int result = -1;
		try {
            
            result = edges[v1][v2];
        
        }catch (ArrayIndexOutOfBoundsException indexBounce){
           
            System.out.println("the vertex is not present");
        }

        return result;
	}

	public int[] neighbors(int v){
		int[] results = new int[size];
		int count = 0;
		try{
			for(int i = 0; i < size; i++){
				if(edges[v][i] != 0){
					results[count] = i;
					count = count+1;
				}
			}
		
		}catch(ArrayIndexOutOfBoundsException indexBounce){
           
            System.out.println("the vertex is not present");
        }
        int[] resultsNew = new int[count];
        for(int j = 0; j < count; j++){

        	resultsNew[j] = results[j];
        }
        return resultsNew;
	}
	public int findTheBiggestNum(){
		int large = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(edges[i][j] > large){
					large = edges[i][j];
				}
			}
		}
		return large;
	}
	public int createSpanningTree(){

		int[][] newedges = new int[size][size];
		int finalcost = 0;
		boolean[] known = new boolean[size];
		int[] cost = new int[size];
		int[] path = new int[size];

		for(int i = 0; i < size; i++){
			cost[i] = findTheBiggestNum() * size;
			path[i] = -1;
		}
		known[0] = true;
		cost[0] = 0;

		int nextV = createSpanningTree(0, known, cost, path);
		int weight = edges[0][nextV];
		newedges[0][nextV] = weight;
		newedges[nextV][0] = weight;

		finalcost = finalcost + weight;

		known[nextV] = true;
		cost[nextV] = weight;
		path[nextV] = 0;

		for(int i = 0; i < size; i++){
			if(edges[nextV][i] < cost[i] && edges[nextV][i] != 0){
				cost[i] = edges[nextV][i];
				path[i] = nextV;
			}
		}

		while(checkKnown(known) == false){

			int nextVer = -1;
			int newcost = findTheBiggestNum() * size;
			for(int i = 0; i < size; i++){
				if(!known[i] && cost[i] < newcost && cost[i] != 0){
					nextVer = i;
					newcost = cost[i];
				}
			}
			newedges[path[nextVer]][nextVer] = newcost;
			newedges[nextVer][path[nextVer]] = newcost;
			finalcost = finalcost + newcost;
			known[nextVer] = true;
			for(int i = 0; i < size; i++){
				if(edges[nextVer][i] < cost[i] && edges[nextVer][i] !=0){
					cost[i] = edges[nextVer][i];
					path[i] = nextVer;
				}
			}
		}

		

		edges = newedges;
		return finalcost;



	}
	public boolean checkKnown(boolean[] k){
		for(int i = 0; i < size; i++){
			if(k[i] == false){
				return false;
			}
		}
		return true;
	}
	public int createSpanningTree(int v, boolean[] known, int[] cost, int[] path ){

		int[] n = neighbors(v);
		int nextV = 0;
		int weight = edges[v][n[0]];

		for(int i = 0; i < n.length; i++){
			path[n[i]] = v;
			cost[n[i]] = edges[v][n[i]];

			if(edges[v][n[i]] < weight && known[i] == false){
				nextV = n[i];
				weight = edges[v][n[i]];
			}
		}

		return nextV;
		
	}
	
}