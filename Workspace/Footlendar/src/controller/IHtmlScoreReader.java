package controller;

import model.Match;
import model.Score;

public interface IHtmlScoreReader {
	
	public Score getScore(Match match);
}
