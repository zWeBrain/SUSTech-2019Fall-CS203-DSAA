package lab6;

import java.util.TreeSet;

class Node implements Comparable{
		int depth = 0;
		int value;
		Node father;
		TreeSet<Node> sons = new TreeSet();
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node() {
			this.value = -1;
		}
		
		public void addSon(Node son) {
			this.sons.add(son);
		}
		
		public static void addSonTwoWay(Node node1,Node node2) {
			node1.addSonTwoWay(node2);
		}
		
		public void addSonTwoWay(Node son) {
			this.sons.add(son);
			son.addSon(this);
		}
		
		public void cleanTwoWayNodes(boolean[] isChecked) {
			isChecked[this.value] = true;
			for(Node n_ : this.sons) {
				if(isChecked[n_.value] == false) {
					n_.father = this;
					n_.sons.remove(this);
					isChecked[n_.value] = true;
					n_.cleanTwoWayNodes(isChecked);
				}
			}
		}
		
		public void refreshNodesDepth() {
			for(Node n_ : this.sons) {
				n_.depth = this.depth + 1;
				n_.refreshNodesDepth();
			}
		}
		
		public void printAll() {
			printAll(this);
		}
		
		public static void printAll(Node n){

			for(Node n_ : n.sons) {
				System.out.printf("%d(%d),%d ",n_.value,n.value,n_.depth);
			}
			System.out.println();
			for(Node n_ : n.sons) {
				printAll(n_);
			}
			
		}

		@Override
		public int compareTo(Object o) {
			return this.value - ((Node)o).value;
		}
	}
