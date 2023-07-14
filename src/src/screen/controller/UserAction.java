package src.screen.controller;

import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;

public interface UserAction {
    public void run() throws NodeExistedException, NodeFullChildrenException, NodeNotExistsException;
    public void undo();
}
