package viewer.db;

import java.sql.Types;
import java.util.HashMap;
import viewer.util.Util;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 * @todo comments
 */

/**
 * Attribute class represents one element of table structure.
 * It's determined with three values: name, type and length.
 */
public class Attribute {
  static private final String VARCHAR = "VARCHAR";
  static private final String INTEGER = "INTEGER";

  static public final HashMap ATTRIBUTE_TYPES = new HashMap();

  static {
    ATTRIBUTE_TYPES.put(VARCHAR, new Integer(Types.VARCHAR));
    ATTRIBUTE_TYPES.put(INTEGER, new Integer(Types.INTEGER));
  }
  private String name;
  private String type;
  private Integer length;

  public Attribute() {
    name = "NewAttribute";
    type = VARCHAR;
    length = new Integer(20);
  }
  public Attribute(String name, String typeName, Integer length) {
    this.name = name;
    this.type = typeName;
    this.length = length;
  }
  public Attribute(Attribute a) {
    name = a.name;
    type = a.type;
    length = a.length;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public String getName() {
    return name;
  }
  public int getSQLType() {
    return ((Integer)ATTRIBUTE_TYPES.get(type)).intValue();
  }
  public String getType() {
    return type;
  }
  public Integer getLength() {
    return length;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setLength(Integer length) {
    this.length = length;
  }
  public void setType(String typeName) {
    type = typeName;
  }
}
