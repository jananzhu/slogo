package slogo_back;

import java.util.LinkedList;
import java.util.Queue;

public class QueueNode implements ISyntaxNode {

    Queue<ISyntaxNode> myValueQueue;

    public QueueNode(String valueBlock, Parser parser){
        Queue<String> tokenQueue = parser.inputTokenizer(valueBlock);
        
        myValueQueue = new LinkedList<ISyntaxNode>();
        while(!tokenQueue.isEmpty()){
            myValueQueue.add(parser.buildParseTree(tokenQueue));
        }
    }

    @Override
    public double getValue () {
        ISyntaxNode value;
        value = myValueQueue.remove();
        return value.getValue();
    }

    @Override
    public boolean hasMultipleValues () {
        return (!myValueQueue.isEmpty());
    }

}
