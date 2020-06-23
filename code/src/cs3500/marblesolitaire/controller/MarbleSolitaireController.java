package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the controller for a game of MarbleSolitaire.
 */
public interface MarbleSolitaireController {
  /**
   * Play a game of marble solitaire.
   * @param model the implementation of the MSM that is being used
   * @throws IllegalArgumentException if the provided model is null
   * @throws IllegalStateException if the controller is unable to receive input
   *                               or unable to transmit output
   *
   */
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException;
}
