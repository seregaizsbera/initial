package snake.model.animal;

import java.util.*;
import snake.model.*;
import snake.view.cell.*;
import snake.view.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Snake overrides some Animal's methods according to snake's behaviour.
 */
public class Snake extends Animal {
  private final PlaceList places; // snake body's position
  private Place head;             // snake head's position
  private Place tail;             // snake tail's position
  private Place singleStep;       // displacement of last step
  private int length;             // snake's length
  private int direction;          // direction of next step, one of following constants
  private int score = 0;

  static public final int LEFT     = 1;
  static public final int RIGHT    = 2;
  static public final int FORWARD  = 3;

  /**
   * Constructor for the Snake object
   *
   * @param field reference to field
   */
  public Snake(Field field) {
    super(field);
    length = Param.minSnakeLength;
    places = new PlaceList();
    direction = FORWARD;
  }

  protected boolean moveTo(Place newPlace) {
    if(newPlace.getRow() < 0 || newPlace.getRow() >= field.getHeight() ||
       newPlace.getColumn() < 0 || newPlace.getColumn() >= field.getWidth())
      return false;
    synchronized(field) {
      if(tail.equals(newPlace)) {
        ListIterator i = places.listIterator();
        Place body = (Place)i.next();
        field.flipCells(tail, body);
        field.flipCells(head, body);
        i.remove();
        places.addLast(body);
        return true;
      }
      Cell cell = field.getCell(newPlace);
      if(cell.isBusy()) {
        Animal keeper = cell.getKeeper();
        if(keeper == this || !(keeper instanceof Eatable))
          return false;
        else
          return eat((Eatable)keeper);
      }
      field.flipCells(head, newPlace);
      ListIterator i = places.listIterator();
      Place body = (Place)i.next();
      field.flipCells(body, newPlace);
      field.flipCells(tail, newPlace);
      i.remove();
      places.addLast(body);
    }
    return true;
  }
  protected void putTo(Place place) {
    if(length < 3)
      length = 3;
    tail = new Place(place);
    for(int i = 0; i < length - 2; i++) {
      place.shiftDown();
      places.addLast(new Place(place));
    }
    place.shiftDown();
    head = new Place(place);
    synchronized(field) {
      field.putCell(tail, new SnakeTailCell(this));
      field.putCell(head, new SnakeHeadCell(this));
      for(ListIterator i = places.listIterator(); i.hasNext();)
        field.putCell((Place)i.next(), new SnakeBodyCell(this));
    }
    singleStep = new Place(1, 0);
  }
  protected boolean step() {
    Place body = places.last();
    switch(direction) {
      case LEFT:
        singleStep.setRowColumn(-singleStep.getColumn(), singleStep.getRow());
        break;
      case RIGHT:
        singleStep.setRowColumn(singleStep.getColumn(), -singleStep.getRow());
        break;
    }
    direction = FORWARD;
    Place temp = singleStep.plus(head);
    if(field.allowMovement(head,temp))
      return moveTo(temp);
    return false;
  }
  protected boolean eatBlueFrog(Place newPlace) {
    eatGreenFrog(newPlace);
    return false;
  }
  protected boolean eatRedFrog(Place newPlace) {
    field.putCell(newPlace, new EmptyCell());
    boolean temp = moveTo(newPlace);
    if(getLength() <= Param.minSnakeLength)
      return temp;
    ListIterator i = places.listIterator();
    Place body = (Place)i.next();
    i.remove();
    field.putCell(body, new EmptyCell());
    field.flipCells(tail, body);
    length--;
    return true;
  }
  protected boolean eatGreenFrog(Place newPlace) {
    field.putCell(newPlace, new EmptyCell());
    if(getLength() >= Param.maxSnakeLength)
      return moveTo(newPlace);
    field.flipCells(head, newPlace);
    Cell newCell = new SnakeBodyCell(this);
    field.putCell(newPlace, newCell);
    places.addLast(newPlace);
    length++;
    return true;
  }
  protected boolean eat(Eatable animal) {
    score += animal.price();
    return animal.youAreEaten(this);
  }
  protected int getLength() {
    return length;
  }
  protected int delay() {
    return Param.snakeDelay;
  }

  /**
   * turn the snake left
   */
  public void turnLeft() {
    direction = LEFT;
  }

  /**
   * turn the snake right
   */
  public void turnRight() {
    direction = RIGHT;
  }

  /**
   * get earned score
   *
   * @return score
   */
  public int getScore() {
    return score;
  }
}
