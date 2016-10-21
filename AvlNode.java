public class AvlNode {
	public AvlNode left;
	public AvlNode right;
	public AvlNode parent;
	public int key;
	public int balance;
	public Object value;

	public AvlNode(int k, Object val) {
		left = right = parent = null;
		balance = 0;
		key = k;
		value = val;
	}

	public String toString() {
		return "" + key;
	}

}
