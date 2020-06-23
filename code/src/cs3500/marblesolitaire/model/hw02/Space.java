package cs3500.marblesolitaire.model.hw02;

/**
 * Used to represent the three possibilities for a space in a game of Marble Solitaire.
 * And the values they print to.
 */
public enum Space {
  INVALID(" "), MARBLE("O"), EMPTY("_");

  private String printVal;

  private Space(String val) {
    this.printVal = val;
  }

  public String getVal() {
    return this.printVal;
  }
}
