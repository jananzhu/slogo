package slogo_back;


public interface ISyntaxNode {

/**
 * Returns double value of the abstract syntax tree with current node as its head
 * @return
 */
public double getValue();

/*
 * Returns true if there are more values that need to be read from current node
 */
public boolean hasMultipleValues();
}

