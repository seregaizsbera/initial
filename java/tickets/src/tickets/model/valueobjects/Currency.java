package tickets.model.valueobjects;

import java.io.*;
import java.math.BigDecimal;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * �������� ������, �������������� �������� ��������.
 */

public class Currency implements Serializable {
  /**
   * ������� ������� �����.
   */
  private int ORDER = 100;

  /**
   * ����� �����.
   */
  private int integerValue;

  /**
   * ������� �����.
   */
  private int fractionalValue;

  /**
   * �������� ��������.
   */
  private BigDecimal value;

  /**
   * ������� ������.
   */
  public Currency() {
    this(new BigDecimal(0));
  }

  /**
   * ������� ������.
   */
  public Currency(int integerValue, int fractionalValue) {
    this.integerValue = integerValue;
    this.fractionalValue = fractionalValue;
  }

  /**
   * ������� ������.
   */
  public Currency(BigDecimal value) {
    this.value = value;
    if (value == null) {
      this.integerValue = 0;
      this.fractionalValue = 0;
      return;
    }
    String str = value.toString();
    int i = str.indexOf(".");
    if (i < 0) {
      this.integerValue = Integer.parseInt(str);
      this.fractionalValue = 0;
    } else {
      this.integerValue = Integer.parseInt(str.substring(0, i));
      String left = str.substring(i + 1);
      if (left.length() >= 2) {
        this.fractionalValue = Integer.parseInt(left.substring(0, 2));
      } else {
        this.fractionalValue = Integer.parseInt(left);
      }
    }
  }

  /**
   * ������������� �������� ��������.
   */
  public void setCurrency(BigDecimal value) {
    this.value = value;
  }

  /**
   * ��������� �������� ��������.
   */
  public BigDecimal getCurrency() {
    return value;
  }

  /**
   * ���������� ����� �����.
   */
  public int getIntegerValue() {
    return integerValue;
  }

  /**
   * ������������� ����� �����.
   */
  public void setIntegerValue(int integerValue) {
    this.integerValue = integerValue;
  }

  /**
   * ���������� ������� �����.
   */
  public int getFractionalValue() {
    return fractionalValue;
  }

  /**
   * ������������� ������� �����.
   */
  public void setFractionalValue(int fractionalValue) {
    this.fractionalValue = fractionalValue;
  }

  /**
   * ���������� ������, ��� ��������� ����� �������� ���� �������� �������.
   * ��� �������� � ���������� ���������� ������ �������� �� ����������.
   */
  public Currency add(Currency currency) {
    this.fractionalValue += currency.getFractionalValue();
    this.integerValue += currency.getIntegerValue() + (this.fractionalValue / ORDER);
    this.fractionalValue = this.fractionalValue % ORDER;
    return this;
  }

  /**
   * ���������� ������, ��� ��������� ����� ��������� ���� �������� �������.
   * ��� �������� � ���������� ���������� ������ �������� �� ����������.
   */
  public Currency substruct(Currency currency) {
    return this;
  }

  public String toString() {
    return "" + getIntegerValue() + "." + getFractionalValue();
  }
}

