package leetcode;

import java.util.Stack;

class MinStack {
    private static class Node{
        int val;
        int min;
        public Node(int val,int min){
            this.val = val;
            this.min = min;
        }

    }
    Stack<Node> mStack;
    public MinStack() {
        mStack = new Stack<Node>();
    }

    public void push(int x) {
        if(mStack.empty()){
            mStack.push(new Node(x,x));
        }else {
            mStack.push(new Node(x,Math.min(mStack.peek().min, x)));
        }
    }

    public void pop() {
        mStack.pop();
    }

    public int top() {
        return mStack.peek().val;
    }

    public int getMin() {
        return mStack.peek().min;
    }
}

