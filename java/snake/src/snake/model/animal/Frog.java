package snake.model.animal;

import java.util.*;
import java.awt.*;
import snake.model.*;
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
 * Frog overrides some Animal's methods according to frogs' behaviour.
 */
abstract public class Frog extends Animal implements Eatable {
  private Place place;       // Frog's position
  private final Color color; // Frog's color

  protected Frog(Field field, Color color) {
    super(field);
    place = new Place();
    this.color = color;
  }

  protected boolean moveTo(Place newPlace) {
    field.flipCells(place, newPlace);
    return true;
  }
  protected void putTo(Place place) {
    this.place = place;
    field.putCell(place, new FrogCell(this, color));
  }
  protected Place getPlace() {
    return place;
  }
  protected boolean step() {
    PlaceList places = field.getVacant(place);
    ListIterator i = places.listIterator();
    if(!i.hasNext())
      return true;
    Place bestPlace = (Place)i.next();
    Place sp = field.getSnakePlace();
    while(i.hasNext()) {
      Place p = (Place)i.next();
      if(field.distance(sp, p) > field.distance(sp, bestPlace))
        bestPlace = p;
    }
    places.remove(bestPlace);
    int coin = Util.random(10);
    if(coin >= 8 && places.size() != 0)
      bestPlace = places.at(Util.random(0, places.size()));
    moveTo(bestPlace);
    return true;
  }
  protected int delay() {
    int res = super.delay() + Util.random(20) * 20;
    return res;
  }
}
