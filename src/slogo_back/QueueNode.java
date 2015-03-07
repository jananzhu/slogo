package slogo_back;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueNode implements ISyntaxNode {

    Queue<ISyntaxNode> myValueQueue;

    public QueueNode(String valueBlock, Parser parser){
        Queue<String> tokenQueue = parser.inputTokenizer(valueBlock);
        Queue<ISyntaxNode> valueQueue = new LinkedList<ISyntaxNode>();
        while(!tokenQueue.isEmpty()){
            valueQueue.add(parser.buildParseTree(tokenQueue));
        }
    }

    @Override
    public double getValue () {
        ISyntaxNode value;
        try{
            value = myValueQueue.poll();
        } catch(NoSuchElementException e){
            throw e;
        }
        return value.getValue();
    }

}
