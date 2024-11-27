import org.w3c.dom.Node;

class HeapImpl<T extends Comparable<? super T>> implements Heap<T> {
	private static final int INITIAL_CAPACITY = 128;
	private T[] _storage;
	private int _numElements;

	@SuppressWarnings("unchecked")
	public HeapImpl () {
		_storage = (T[]) new Comparable[INITIAL_CAPACITY];
		_numElements = 0;
	}

	@SuppressWarnings("unchecked")
	public void add (T data) {
		_storage[_numElements] = data;
        _numElements += 1;
		heapifyDown();
    }

	private void heapifyDown(){
		// start at the root node
		int index = 0;
        int left = 2*index+1;
		// while there are still nodes below to check
        while(left < _numElements) {
			// look at the left and right child nodes
            int min = left;
			int right = ++left;
            if(right < _numElements) {
                if(_storage[left].compareTo(_storage[right]) > 1) {
                    min++;
                }
            }
			// if the smaller of the two children is larger than the parent, switch them
            if(_storage[index].compareTo(_storage[min]) > 1) {
				swap(index, min);
            }
			// increment the index down the tree
            index = min;
            left = 2*index+1;
        }
	}

	private void swap(int i1, int i2){
		// switch two elements in the array based off of their indexes
		T temp = _storage[i1];
		_storage[i1] = _storage[i2];
		_storage[i2] = temp;
	}

	public T removeFirst () {
		T returnT = _storage[0];
		heapifyUp();
        _numElements -= 1;
		return returnT;
	}

	private void heapifyUp(){
		// start at the element at the end
		int index = _numElements;
        while(index > 0){
			// 
            if(_storage[(index-1)/2].compareTo(_storage[index]) > 1) {
				swap(index, (index-1)/2);
				index = (index-1)/2;
            }else {
                break;
            }
        }
	}

	public int size () {
		return _numElements;
	}
}
