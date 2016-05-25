package br.com.nmsalone.parser.content;

import br.com.nmsalone.parser.model.Game;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ParserHTMLContent {

    private static final Logger logger = LogManager.getLogger(ParserHTMLContent.class);

    private File documentFile;

    private Document mainDocument;

    public ParserHTMLContent(final File documentFile) {
        this.documentFile = documentFile;
        makeObjectParser();
    }

    private void makeObjectParser() {
        try {
            mainDocument = Jsoup.parse(documentFile, "UTF-8", "http://example.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get values from HTML File
     * @return
     */
    public List<Game> recoverToupleValues() {
        final List<Game> gameValues = new ArrayList<>();
        final Elements tableElement = mainDocument.getElementsByTag("table");
        final Element table = tableElement.get(0);
        final Elements touples = table.getElementsByTag("tr");
        for(Element touple : touples) {
            if(touple.getElementsByTag("th").isEmpty()) {
                final Elements values = touple.getElementsByTag("td");
                final String id = values.get(0).text();

                if(!id.isEmpty() && isNumber(id)) {
                    logger.debug("Id: " + id);

                    Game game = new Game();

                    game.setId(Long.parseLong(id));
                    game.setDateDraw(parseToDate(values.get(1).text()));
                    game.setWinnersCount(Integer.parseInt(values.get(9).text()));
                    game.setN1(Integer.parseInt(values.get(2).text()));
                    game.setN2(Integer.parseInt(values.get(3).text()));
                    game.setN3(Integer.parseInt(values.get(4).text()));
                    game.setN4(Integer.parseInt(values.get(5).text()));
                    game.setN5(Integer.parseInt(values.get(6).text()));
                    game.setN6(Integer.parseInt(values.get(7).text()));

                    gameValues.add(game);
                }
            }

        }

        return gameValues;
    }

    private boolean isNumber(final String number) {
        char[] charNumber = number.toCharArray();
        return Character.isDigit(charNumber[0]);
    }

    private Date parseToDate(final String date) {
        Calendar cal = Calendar.getInstance();
        String[] dateSplit = date.split("/");

        cal.set(Calendar.DATE, Integer.parseInt(dateSplit[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]));
        cal.set(Calendar.YEAR, Integer.parseInt(dateSplit[2]));

        return cal.getTime();
    }

}
