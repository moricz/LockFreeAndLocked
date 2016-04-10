package locked.base;

import common.interfaces.SelfOrganizingListInterface;
public class SelfOrganizingListFineGrained implements SelfOrganizingListInterface<LockedNode> {

	public LockedNode head;
	public LockedNode tail;

	public SelfOrganizingListFineGrained() {
		this.head = new LockedNode(-1, null);
		this.tail = new LockedNode(-1, head);

	}

	public boolean add(int value) {
		LockedNode prev = head;

		while (true) {
			prev.lock();
			if (prev.next == null) {

				LockedNode newNode = new LockedNode(value, null);
				prev.next = newNode;
				prev.unlock();
				return true;
			}
			prev.unlock();
			prev = prev.next;

		}

	}

	public boolean remove(int value) {
		LockedNode prev = head;
		LockedNode curr = prev.next;

		while (true) {
			maskLock(prev,curr);
			if (curr.value == value) {

				prev.next = curr.next;

				maskUnLock(prev,curr);
				return true;
			}

			maskUnLock(prev, curr);

			if (curr.next == null)
				return false;

			prev = curr;
			curr = curr.next;

		}

	}
	
	
	public void maskLock(LockedNode a, LockedNode b){
		if(a.trylock())
				b.lock();
	}
	
	public void maskUnLock(LockedNode a, LockedNode b){
		a.unlock();
		b.unlock();
	}


	public LockedNode search(int value) {

		LockedNode node = head;
		LockedNode nextNode = node.next;
		nextNode.trylock();
		if (nextNode.value == value){
			nextNode.unlock();
			return nextNode;
		}
		nextNode.unlock();
		
		while (true) {
			maskLock(node,nextNode);

			if (nextNode.value == value && node.value!= -1) {
				int aux = nextNode.value;
				nextNode.value = node.value;
				node.value = aux;

				maskUnLock(node,nextNode);

				return node;
			}

			maskUnLock(node,nextNode);
			if (nextNode.next == null)
				return null;

			node = nextNode;
			nextNode = nextNode.next;
		}
		
		
	}

	public boolean contains(int value) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean list() {
		LockedNode node = head.next;
		System.out.println("The List: ");
		System.out.print("[ ");
		int k = 1, row = 0;
		System.out.print(row + ": ");
		do {
			if (k == 10) {
				System.out.println(node.value);
				row++;
				k = 1;
				System.out.print(row + "0: ");
			} else {
				k++;
				System.out.print(node.value + " ");
			}

			node = node.next;
		} while (node != null);
		System.out.println(" ]");
		return false;
	}

	public int size() {
		LockedNode node = head.next;
		int size = 0;
		for (node = head.next; node != null; node = node.next) {
			size++;
		}
		return size;
	}

}
