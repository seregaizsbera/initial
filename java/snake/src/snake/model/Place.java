package snake.model;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Place represents position in the game field.
 * It is determined with two integers: row and column
 * Place provides some vector-like opertions such as plus and minus.
 */
public class Place {
  private int row;
  private int column;

  /**
   * Default constructor for the Place object
   */
  public Place() {}

  /**
   * Constructor for the place object
   *
   * @param row number of row, begining from 0
   * @param column number of column, begining from 0
   */
  public Place(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Constructor for the place object
   *
   * @param place the place
   */
  public Place(Place place) {
    row = place.getRow();
    column = place.getColumn();
  }

  void shiftLeft() {
    column--;
  }
  void shiftRight() {
    column++;
  }
  void shiftUp() {
    row--;
  }
  Place minus(Place place) {
    int newRow = row - place.getRow();
    int newColumn = column - place.getColumn();
    Place res = new Place(newRow, newColumn);
    return res;
  }
  Place minus() {
    Place res = new Place(-row, -column);
    return res;
  }

  /**
   * Get column of the place
   *
   * @return column
   */
  public int getColumn() {
    return column;
  }

  /**
   * Get row of the place
   *
   * @return row
   */
  public int getRow() {
    return row;
  }

  /**
   * Set new column for the place
   *
   * @param column new column
   */
  public void setColumn(int column) {
    this.column = column;
  }

  /**
   * Set new row for the place
   *
   * @param row new row
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Set both new column and new row for the place
   *
   * @param column new column
   * @param row new row
   */
  public void setRowColumn(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Set both new column and new row for the place
   *
   * @param place new place
   */
  public void setPlace(Place place) {
    row = place.getRow();
    column = place.getColumn();
  }

  /**
   * Change the place to one row down
   */
  public void shiftDown() {
    row++;
  }

  /**
   * Compose two places in one
   *
   * @param place place to add
   * @return new place
   */
  public Place plus(Place place) {
    int newRow = row + place.getRow();
    int newColumn = column + place.getColumn();
    Place res = new Place(newRow, newColumn);
    return res;
  }

  public String toString() {
    String res = row + ":" + column;
    return res;
  }

  public boolean equals(Object o) {
    boolean res = ((Place)o).getRow() == row && ((Place)o).getColumn() == column;
    return res;
  }
}
