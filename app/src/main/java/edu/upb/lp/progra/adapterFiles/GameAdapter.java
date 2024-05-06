package edu.upb.lp.progra.adapterFiles;

import edu.upb.lp.progra.newGame.NewGameUI;
/**
 * This class allows to select what UI will be used by the Android library.
 * 
 * @author Alexis Marechal
 * @author Alfredo Villalba
 *
 */
public class GameAdapter {
	public static AppConnector selectGame(AndroidLibrary gui) {
		return new NewGameUI(gui);
	}	
}