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
 * Содержит данные, представляющие денежную величину.
 */

public class Currency implements Serializable {
  /**
   * Порядок дробной части.
   */
  private int ORDER = 100;

  /**
   * Целая часть.
   */
  private int integerValue;

  /**
   * Дробная часть.
   */
  private int fractionalValue;

  /**
   * Денежная величина.
   */
  private BigDecimal value;

  /**
   * Создает объект.
   */
  public Currency() {
    this(new BigDecimal(0));
  }

  /**
   * Создает объект.
   */
  public Currency(int integerValue, int fractionalValue) {
    this.integerValue = integerValue;
    this.fractionalValue = fractionalValue;
  }

  /**
   * Создает объект.
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
   * Устанавливает денежную величину.
   */
  public void setCurrency(BigDecimal value) {
    this.value = value;
  }

  /**
   * Возращает денежную величину.
   */
  public BigDecimal getCurrency() {
    return value;
  }

  /**
   * Возвращает целую часть.
   */
  public int getIntegerValue() {
    return integerValue;
  }

  /**
   * Устанавливает целую часть.
   */
  public void setIntegerValue(int integerValue) {
    this.integerValue = integerValue;
  }

  /**
   * Возвращает дробную часть.
   */
  public int getFractionalValue() {
    return fractionalValue;
  }

  /**
   * Устанавливает дробную часть.
   */
  public void setFractionalValue(int fractionalValue) {
    this.fractionalValue = fractionalValue;
  }

  /**
   * Возвращает объект, чей результат равен сложению двух денежных величин.
   * Обе величины в результате выполнения данной операции не изменяются.
   */
  public Currency add(Currency currency) {
    this.fractionalValue += currency.getFractionalValue();
    this.integerValue += currency.getIntegerValue() + (this.fractionalValue / ORDER);
    this.fractionalValue = this.fractionalValue % ORDER;
    return this;
  }

  /**
   * Возвращает объект, чей результат равен вычитанию двух денежных величин.
   * Обе величины в результате выполнения данной операции не изменяются.
   */
  public Currency substruct(Currency currency) {
    return this;
  }

  public String toString() {
    return "" + getIntegerValue() + "." + getFractionalValue();
  }
}

