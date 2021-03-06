package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class LongestFilmUnder16 extends Page {
    public LongestFilmUnder16(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    /**
     * Executes a query on the database and shows all results.
     */
    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT TOP 1 Titel\n" +
                "FROM Film\n" +
                "WHERE CAST(SUBSTRING(Leeftijdsindicatie, 0, CHARINDEX(' jaar en ouder', Leeftijdsindicatie)) AS INT) < 16\n" +
                "ORDER BY Tijdsduur DESC", results -> {
                    while(results.next()) getContentView().addChild(new TextView(results.getString("Titel")));
                    if(getContentView().getChildCount() == 0) getContentView().addChild(new TextView("Er zijn geen films voor kinderen jonder dan 16 jaar"));
                });
    }

    @Override public String getTitle() {
        return "Langste film (16- jaar)";
    }
}
