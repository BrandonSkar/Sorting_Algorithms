import java.util.Arrays;

/**
 * Sorting algorithm where we split an array into groups
 * and sort them as we add them to each group
 * @author Brandon Skar
 * @version 1.0
 */
public class SortingAssignment
{
    private Node[] group;
    private int lowest;
    private double groupSize;

    /**
     * A sorting algorithm that creates small groups and puts elements of an array
     * into the groups in sorted order and then remakes the array in sorted order
     * @param arr Integer array of random numbers
     * @param size Group size given from user used to split the array into smaller groups for faster sorting
     */
    public void sort(int[] arr, int size)
    {
        //create the groups of nodes
        group = new Node[size];

        //get lowest and highest elements in the array
        lowest = arr[0];
        int highest = arr[0];
        for(int el : arr) {
            if(lowest > el) { lowest = el; }
            if(highest < el) { highest = el; }
        }

        //min max
        System.out.println("Min/Max: [" + lowest + ", " + highest + "]");

        int values = highest - lowest + 1;
        groupSize = (values / (size * 1.0));
        System.out.println("Group size: " + groupSize);

        //get the upper bound for each node
        //group thresholds
        getThresholds(size);

        //add elements to the node array
        addElements(arr);

        remakeArray(arr);
    }

    private void getThresholds(int size)
    {
        System.out.print("Group Thresholds: [");
        for(int i = 0; i < size; i++) {
            group[i] = new Node((int)Math.round(lowest + (groupSize * (i + 1) - 1)), null);
            if(i != (size - 1)) {
                System.out.print(group[i].data + ", ");
            }
            else {
                System.out.println(group[i].data + "]\n");
            }
        }
    }

    private void addElements(int[] arr)
    {
        for(int el : arr) {
            for(Node node : group) {
                //find the index where the element gets inserted
                if(el <= node.data) {
                    //create the node for the element
                    Node current = new Node(el, null);
                    Node temp = node;

                    //if it is the first element being added
                    if(temp.next == null) {
                        temp.next = current;
                    }
                    else {
                        //loop through the list until the element is greater
                        //or equal to the element or null
                        while(temp.next != null &&
                                current.data > temp.next.data) {
                            temp = temp.next;
                        }

                        current.next = temp.next;
                        temp.next = current;
                    }
                    //found the group, go to next element
                    break;
                }
            }
        }
    }

    private void remakeArray(int[] arr)
    {
        int index = 0;
        for(int i = 0; i < group.length; i++) {
            Node temp = group[i];
            System.out.print(i);
            System.out.print("[" + (int)Math.round(lowest + groupSize * i) + "-" +
                    (int)Math.round(lowest + (groupSize * (i + 1) - 1)) + "]: ");

            if(temp.next == null) {
                System.out.print("null");
            }
            else {
                while(temp.next != null) {
                    System.out.print(temp.next.data);
                    arr[index] = temp.next.data;
                    index++;

                    if(temp.next.next != null) {
                        System.out.print(" -> ");
                    }
                    temp = temp.next;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String toString()
    {
        return "SortingAssignment{" +
                "group=" + Arrays.toString(group) +
                '}';
    }

    private class Node
    {
        private Node next;
        private int data;

        public Node(int element, Node next)
        {
            this.data = element;
            this.next = next;
        }

        @Override
        public String toString()
        {
            return "Node{" +
                    "next=" + next +
                    ", data=" + data +
                    '}';
        }
    }
}
