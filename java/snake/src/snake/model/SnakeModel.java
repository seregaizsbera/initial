package snake.model;

import java.util.*;
import snake.model.animal.*;
import snake.util.*;
import snake.view.*;
import snake.view.cell.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * SnakeModel contents everything concerning to game logic,
 * i.e. field, animals, snake, the specific animal, and game state.
 * It is responsible to notify Observers about changes and perform
 * user commands.
 */
public class SnakeModel extends Observable implements Runnable, ModelState {
  private final Field field;
  private final LinkedList animals;
  private Thread mainThread;
  private Snake snake;
  private int state;               // One of ModelState constants

  // Following constants determines possibility of creation frog of
  // certain kinds. Their values are in accendig order. The bigger
  // the difference of a constant from previous one, the larger
  // possibility for new frog to be of corresponding kind.
  static private final int GREEN_FROG = 7;
  static private final int RED_FROG = 8;
  static private final int BLUE_FROG = 11;
  static private final int LAST_FROG = BLUE_FROG + 1;

  static private Frog getFrogInstance(Field field) {
    int temp = Util.random(LAST_FROG);
    if(temp <= GREEN_FROG)
      return new GreenFrog(field);
    if(temp <= RED_FROG)
      return new RedFrog(field);
    if(temp <= BLUE_FROG)
      return new BlueFrog(field);
    return new GreenFrog(field);
  }
  private void clear() {
    animals.clear();
    Place p = new Place();
    for(int i = 0; i < field.getHeight(); i++)
      for(int j = 0; j < field.getWidth(); j++) {
        p.setRowColumn(i, j);
        if(!(field.getCell(p) instanceof EmptyCell))
          field.putCell(p, new EmptyCell());
      }
    snake = null;
    mainThread = null;
  }
  private void initField() {
    addSnake(new Snake(field), new Place());
    for(int i = 0; i < Param.numFrogs; i++)
      putNewFrog();
  }
  private void setState(int state) {
    this.state = state;
  }
  private void addAnimal(Animal animal, Place p) {
    animals.addLast(animal);
    animal.putTo(p);
  }
  private void addSnake(Snake snake, Place p) {
    this.snake = snake;
    snake.putTo(p);
  }
  private void putNewFrog() {
    putNewFrog(false);
  }
  private void putNewFrog(boolean toStart) {
    Animal frog = getFrogInstance(field);
    if(field.getCapacity() < 1)
      return;
    Place p = new Place();
    do {
      p.setRowColumn(Util.random(0,field.getHeight()),Util.random(0,field.getWidth()));
    } while(!field.getCell(p).isVacant());
    addAnimal(frog, p);
    if(toStart)
      frog.start();
  }

  /**
   * Constructor for the SnakeModel object
   */
  public SnakeModel() {
    field = new Field(Param.hCells, Param.vCells);
    Place p = new Place();
    for(int i = 0; i < field.getHeight(); i++)
      for(int j = 0; j < field.getWidth(); j++) {
        p.setRowColumn(i, j);
        field.putCell(p, new EmptyCell());
      }
    animals = new LinkedList();
    state = STOPPED;
  }

  /**
   * Start game
   */
  public void startGame() {
    if(getState() == STARTED)
      return;
    clear();
    initField();
    mainThread = new Thread(this);
    mainThread.start();
    setState(STARTED);
    setChanged();
    notifyObservers();
  }

  /**
   * Stop game
   */
  public void stopGame() {
    if(getState() == STOPPED)
      return;
    snake.stop();
    Thread temp = mainThread;
    mainThread = null;
    if(temp != null)
      temp.interrupt();
    temp = null;
    for(ListIterator ai = animals.listIterator(); ai.hasNext();)
      ((Animal)(ai.next())).stop();
    setState(STOPPED);
    setChanged();
    notifyObservers();
    System.gc();
  }

  /**
   * pause game
   */
  public void pauseGame() {
    if(getState() != STARTED)
      return;
    for(ListIterator i = animals.listIterator(); i.hasNext();)
      ((Animal)(i.next())).pause();
    snake.pause();
    setState(PAUSED);
    setChanged();
    notifyObservers();
  }

  /**
   * resume game
   */
  public void resumeGame() {
    if(getState() != PAUSED)
     return;
    setState(STARTED);
    for(ListIterator i = animals.listIterator(); i.hasNext();)
      ((Animal)(i.next())).resume();
    snake.resume();
    setChanged();
    notifyObservers();
  }

  /**
   * move snake right
   */
  public void snakeRight() {
    snake.turnRight();
  }

  /**
   * move snake left
   */
  public void snakeLeft() {
    snake.turnLeft();
  }

  /**
   * get score
   *
   * @return current score
   */
  public int getScore() {
    if(snake != null)
      return snake.getScore();
    return 0;
  }

  /**
   * get state of game
   *
   * @return state of game
   */
  public int getState() {
    return state;
  }

  /**
   * get field of game
   *
   * @return reference to filed of game
   */
  public Field getField() {
    return field;
  }

  public void run() {
    snake.start();
    for(ListIterator i = animals.listIterator(); i.hasNext();)
      ((Animal)(i.next())).start();
    while(mainThread != null) {
      if(!snake.isAlive()) {
        stopGame();
        return;
      }
      int deadCnt = 0;
      for(ListIterator ai = animals.listIterator(); ai.hasNext();) {
        Animal a = (Animal)(ai.next());
        if(!a.isAlive()) {
          ai.remove();
          deadCnt++;
        }
      }
      for(int i = 0; i < deadCnt; i++)
        putNewFrog(true);
      try {
        Thread.sleep(100);
      }
      catch(InterruptedException e) {}
    }
    stopGame();
  }
  public synchronized void addObserver(Observer o) {
    super.addObserver(o);
    field.addObserver(o);
  }
  public synchronized void deleteObserver(Observer o) {
    super.deleteObserver(o);
    field.deleteObserver(o);
  }
  public synchronized void deleteObservers() {
    super.deleteObservers();
    field.deleteObservers();
  }
}
