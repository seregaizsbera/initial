package snake.view;

import java.awt.*;
import snake.model.*;
import snake.view.awtimpl.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * FieldPanel draws the game field.
 */
class FieldPanel extends SCanvas {
  private final int hCellSize; // horizontal size of cells in the game field
  private final int vCellSize; // vertical size of cells in the game field
  private final int hElemSize; // horizontal size of cells with borders
  private final int vElemSize; // vertical size of cells with borders
  private final int hPicSize;  // horizontal size of drawable elements in cells
  private final int vPicSize;  // vertical size of drawable elements in cells
  private final int hSpace;    // horizontal space between cells
  private final int vSpace;    // vertical space between cells
  private final int hCells;    // number of cells along horizontal
  private final int vCells;    // number of cells along vertical
  private final int width;     // total width of field in pixels
  private final int height;    // total height of field in pixels
  private final int vIndent;   // horizontal space between drawable element and cell's edge
  private final int hIndent;   // vertical space between drawable element and cell's edge
  private Image cellImage;     // Image buffer for double buffering
  private Field field;         // field to be drawn

  FieldPanel() {
    setBackground(Param.background);
    setForeground(Param.foreground);

    hCells = Param.hCells;
    vCells = Param.vCells;
    hSpace = Param.hSpace;
    vSpace = Param.vSpace;
    hCellSize = Param.hCellSize;
    vCellSize = Param.vCellSize;
    hElemSize = hSpace + hCellSize;
    vElemSize = vSpace + vCellSize;
    vIndent = 2;
    hIndent = 2;
    hPicSize = hCellSize - hIndent * 2;
    vPicSize = vCellSize - vIndent * 2;

    width = hCells * (hSpace + hCellSize);
    height = vCells * (vSpace + vCellSize);
  }
  void drawCell(Cell cell, Place p) {
    int y1 = vElemSize * p.getRow();
    int x1 = hElemSize * p.getColumn();
    if(cellImage == null) {
      cellImage = createImage(width, height);
    }
    Graphics ig = cellImage.getGraphics();
    ig.draw3DRect(x1, y1, hCellSize, vCellSize, true);
    ig.clearRect(x1 + hIndent, y1 + vIndent, hPicSize, vPicSize);
    cell.draw(ig, x1 + hIndent, y1 + vIndent, hPicSize, vPicSize);
    ig.dispose();
  }
  void setField(Field field) {
    this.field = field;
  }

  public void update(Graphics g) {
    Place p = new Place();
    for(int i = 0; i < vCells; i++)
      for(int j = 0; j < hCells; j++) {
        p.setRowColumn(i, j);
        Cell cell = field.getCell(p);
        if(cell.isChanged())
          drawCell(cell, p);
      }
    g.drawImage(cellImage, 0, 0, null);
  }
  public void paint(Graphics g) {
   update(g);
  }
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }
  public boolean isDoubleBuffered() {
    return true;
  }
}
