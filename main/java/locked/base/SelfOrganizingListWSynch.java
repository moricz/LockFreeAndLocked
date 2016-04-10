package locked.base;

import common.interfaces.SelfOrganizingListInterface;

public class SelfOrganizingListWSynch implements SelfOrganizingListInterface<Node> {

	public Node head;
	public Node tail;

	public SelfOrganizingListWSynch() {
		this.head = new Node(-1, null);
		this.tail = new Node(-1, head);

	}

	public boolean add(int value) {
		Node node = head;
		
		for (node = head; node != null; node = node.next) {
			synchronized (node) {
				if (node.next == null) {
					Node newNode = new Node(value, null);
					node.next = newNode;

					return true;
				}
			}
		}

		return false;
	}

	public boolean remove(int value) {

		return false;
	}

	public Node search(int value) {
		Node node = head;
		if (head.next.value == value)
			return head.next;
		for (node = head; node.next != null; node = node.next) {
			synchronized (node) {
				if (node.next.value == value) {
					int aux = node.next.value;
					node.next.value = node.value;
					node.value = aux;
					return node;
				}
			}
		}
		return node;
	}

	public boolean contains(int value) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean list() {
		Node node = head.next;
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
		Node node = head.next;
		int size = 0;
		for (node = head.next; node != null; node = node.next) {
			size++;
		}
		return size;
	}

}
