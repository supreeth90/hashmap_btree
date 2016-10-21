import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HashMapRBTree<KeyType, ValueType> extends AbstractMap<KeyType ,ValueType> implements Map<KeyType, ValueType>{
	
	static class Entry<KeyType, ValueType> implements Map.Entry<KeyType, ValueType> {
		int bucket;
		long hash;
		KeyType key;
		ValueType value;
		RBTree tree;
		
		//Empty or Dummy Entries
		Entry() {
			bucket = -1;
			hash = -1;
			key = null;
			value =  null;
		}
		
		Entry(int bucket, int hash, KeyType key, ValueType value) {
			this.bucket = bucket;
			this.hash = hash;
			this.key = key;
			this.value =  value;
		}
		@Override
		public KeyType getKey() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ValueType getValue() {
			return value;
		}
		@Override
		public ValueType setValue(ValueType value) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	protected List<Entry> table = null; 
	
	protected int initialCapacity = 16;
	
	protected int capacity = initialCapacity;
	
	public HashMapRBTree() {
		table = new ArrayList<Entry>();
		
		for(int i=0;i<initialCapacity;i++) {
			//System.out.println("Table Init Done");
			table.add(new Entry<>());
		}
	}
	
	public HashMapRBTree(int capacity) {
		this.initialCapacity = capacity;
		table = new ArrayList<Entry>();
		
		for(int i=0;i<initialCapacity;i++) {
			//System.out.println("Table Init Done");
			table.add(new Entry<>());
		}
	}
	
	@Override
	public ValueType get(Object key) {
//		System.out.println("GET CALLED"+key);
//		System.out.println("Hashcode::"+key.hashCode());
		//Find the bucket
		
		return getFromTree(key);
	}

	@Override
	public ValueType put(KeyType key, ValueType value) {
//		System.out.println("PUT CALLED"+key);
//		System.out.println("PUT::key.hashCode()"+key.hashCode());
		
//		System.out.println("Hashcode::"+key.hashCode());
		int bucket = key.hashCode() % capacity;
//		System.out.println("Bucket"+bucket);
		addToBucket(bucket, key, value);
		return value;
	}

	protected void addToBucket(int bucket, KeyType key, ValueType value) {
//		System.out.println("addToBucket::"+bucket);
		
		Entry<KeyType, ValueType> entry=table.get(bucket);
		if (entry.bucket == -1) {
			//No entry in the Bucket now
//			System.out.println("Bucket is empty");
			entry=new Entry<KeyType, ValueType>(bucket, key.hashCode(), key, value);
			entry.tree=new RBTree(Integer.MIN_VALUE,Integer.MIN_VALUE);
			entry.tree.insert(key.hashCode(),value);;
			table.add(bucket, entry);
		} else {
//			System.out.println("Collision::");
			entry.tree.insert(key.hashCode(), value);
		}
	}
	
	protected ValueType getFromTree(Object key) {
		RedBlackNode retNode = null;
		boolean temp=false;
		int bucket = key.hashCode()%capacity;
//		System.out.println("Bucket"+bucket);
		Entry<KeyType, ValueType> entry=table.get(bucket);
		if(entry.bucket != -1) {
			retNode = entry.tree.search(key.hashCode());
			
		}
		if(retNode == null) {
			//return null;
			//System.out.println("returning False");
			return null;
		}
		//return (ValueType)retNode.value;
		//System.out.println("returning true");
		return (ValueType) retNode.value;
	}	

}
