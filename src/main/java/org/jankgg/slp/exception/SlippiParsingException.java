
package org.jankgg.slp.exception;

public class SlippiParsingException extends RuntimeException {

  public SlippiParsingException(String string) {
    super(string);
  }

  public SlippiParsingException(String string, Exception e) {
    super(string, e);
  }

  /**
   * 
   */
  private static final long serialVersionUID = -9144300781972515079L;
  
}