package src.screen.controller.operation;

import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.exception.TreeException;

public interface UserAction {
    public void run();
    public void undo();
}
