package snake.model;

import java.util.*;
import snake.util.*;
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
 * Field contains two-dimension array of cells.
 * It represents the game field. Filed notifies observes whether
 * it have changed.
 */
public class Field extends Observable {
  private final Cell cells[][];
  private final int width;
  private final int height;
  private int capacity;     // number of vacant cells
  private Place snakePlace; // position of the snake

  Field(int width, int height) {
    this.width = width;
    this.height = height;
    cells = new Cell[height][];
    for(int i = 0; i < height; i++)
      cells[i] = new Cell[width];
    capacity = 0;
    snakePlace = new Place();
  }

  private boolean isVacant(Place p) {
    adjustPlace(p);
    int row = p.getRow();
    int col = p.getColumn();
    if(row < 0 || row >= height || col < 0 || col >= width)
      return false;
    if(getCell(p).isBusy())
      return false;
    return true;
  }
  private void checkSnake(Place place) {
    if(getCell(place) instanceof SnakeHeadCell)
      snakePlace.setPlace(place);
  }
  private void setCell(Place place, Cell cell) {
    cells[place.getRow()][place.getColumn()] = cell;
    cell.setChanged(true);
  }
  private void adjustPlace(Place p) {
    int row = p.getRow();
    int column = p.getColumn();
    if(row >= height)
      row %= height;
    while(row < 0)
      row += height;
    if(column >= width)
      column %= width;
    while(column < 0)
      column += width;
    p.setRowColumn(row, column);
  }

  int getCapacity() {
    return capacity;
  }
  void tellToObservers() {
    setChanged();
    notifyObservers();
  }
  void tellToObservers(Object arg) {
    setChanged();
    notifyObservers(arg);
  }
  synchronized void clear() {
    for(int i = 0; i < cells.length; i++)
      for(int j = 0; j < cells[i].length; j++)
        cells[i][j] = null;
  }

  /**
   * Get cell from the specified place
   *
   * @param p place to inspect
   * @return cell from the place <code>p</code>
   */
  public synchronized Cell getCell(Place p) {
    Cell res = cells[p.getRow()][p.getColumn()];
    return res;
  }

  /**
   * Put cell to the specified place
   *
   * @param p the place
   * @param cell the cell
   */
  public synchronized void putCell(Place p, Cell cell) {
    Cell temp = getCell(p);
    if(temp != null && temp.isVacant())
      capacity--;
    if(cell.isVacant())
      capacity++;
    setCell(p, cell);
  }

  /**
   * exchange two cells at the specified places
   *
   * @param p1 position of the 1st cell
   * @param p2 position of the 2nd cell
   */
  public synchronized void flipCells(Place p1, Place p2) {
    int p1r = p1.getRow();
    int p1c = p1.getColumn();
    int p2r = p2.getRow();
    int p2c = p2.getColumn();
    Cell temp = getCell(p1);
    setCell(p1, getCell(p2));
    setCell(p2, temp);
    checkSnake(p1);
    checkSnake(p2);
    p1.setRowColumn(p2r, p2c);
    p2.setRowColumn(p1r, p1c);
  }

  /**
   * Get list of vacant places arround the specified place
   *
   * @param p the place
   * @return list of vacant places
   */
  public synchronized PlaceList getVacant(Place p) {
    PlaceList res = new PlaceList();
    Place temp = new Place(p);
    temp.shiftDown();
    if(isVacant(temp))
      res.addLast(temp);
    temp = new Place(p);
    temp.shiftUp();
    if(isVacant(temp))
      res.addLast(temp);
    temp = new Place(p);
    temp.shiftLeft();
    if(isVacant(temp))
      res.addLast(temp);
    temp = new Place(p);
    temp.shiftRight();
    if(isVacant(temp))
      res.addLast(temp);
    return res;
  }

  /**
   * Check if animal can move itself from one place to another
   *
   * @param p1 current position of animal
   * @param p2 desired position of animal, it will be changed according to real
   *           position in the field.
   * @return true if it's allowed for animal to move there
   */
  public boolean allowMovement(Place p1, Place p2) {
    adjustPlace(p2);
    return true;
  }

  /**
   * Get the position of the snake
   *
   * @return place of field where snake's head is situated
   */
  public Place getSnakePlace() {
    return snakePlace;
  }

  /**
   * Get width of field
   *
   * @return widht of field
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get height of field
   *
   * @return height of field
   */
  public int getHeight() {
    return height;
  }

  /**
   * get length of minimal way between two places.
   * changes p1 and p2 to fit range of coordinates
   *
   * @param p1 the first place
   * @param p2 the second place
   * @return distance
   */
  public int distance(Place p1, Place p2) {
    adjustPlace(p1);
    adjustPlace(p2);
    int x1 = p1.getColumn();
    int x2 = p2.getColumn();
    int y1 = p1.getRow();
    int y2 = p2.getRow();
    int a = Math.abs(x2 - x1);
    int b = Math.abs(y2 - y1);
    a = Math.min(a, Math.abs(width - a));
    b = Math.min(b, Math.abs(height - b));
    return a + b;
  }
}
