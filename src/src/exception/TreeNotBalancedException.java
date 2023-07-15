package src.exception;

import src.treedatastructure.Tree;

public class TreeNotBalancedException extends TreeException {
    public TreeNotBalancedException(String message){
        super(message);
    }
}
