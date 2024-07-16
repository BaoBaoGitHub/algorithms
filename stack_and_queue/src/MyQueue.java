import java.util.LinkedList;
import java.util.Stack;

class MyQueue {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    private void flush() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

    public int pop() {
        if (outStack.isEmpty()) {
            flush();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            flush();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */