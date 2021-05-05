
import java.util.ArrayList;

//node stuff
class HashNode<K, V> {
    K key;
    V value;


    HashNode<K, V> next;

    //constructor
    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}

//hashtable
class Hash<K, V> {
    //store array
    private ArrayList<HashNode<K, V> > bucketArray;


    private int numBuckets;


    private int size;


    public Hash()
    {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;

        // Create chains
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    //index
    private int getBucketIndex(K key)
    {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    public V remove(K key)
    {

        int bucketIndex = getBucketIndex(key);


        HashNode<K, V> head = bucketArray.get(bucketIndex);


        HashNode<K, V> prev = null;
        while (head != null) {

            if (head.key.equals(key))
                break;


            prev = head;
            head = head.next;
        }

        // if not   there
        if (head == null)
            return null;


        size--;


        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        return head.value;
    }


    public V get(K key)
    {

        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);


        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        return null;
    }


    public void add(K key, V value)
    {

        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);


        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }


        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode
                = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);


        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    // driver
    public static void main(String[] args)
    {
        Hash<String, Integer> hash = new Hash<>();
        hash.add("test", 1);
        hash.add("second", 2);
        hash.add("test", 4);
        hash.add("yoyoyo", 5);
        System.out.println(hash.size());
        System.out.println(hash.remove("test"));
        System.out.println(hash.remove("test"));
        System.out.println(hash.size());
        System.out.println(hash.isEmpty());
    }
}