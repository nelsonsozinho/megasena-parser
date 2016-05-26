package br.com.nmsalone.parser.content;

import br.com.nmsalone.parser.model.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class TestParserContent {

    private ParserHTMLContent parserContent;

    @Before
    public void prepare() {
        java.net.URL url = ClassLoader.getSystemClassLoader().getResource("loteria/D_MEGA.HTM");
        InputStream inputStream = null;
        try {
            File file = new File(url.toURI());
            inputStream = new FileInputStream(file);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        parserContent = new ParserHTMLContent(inputStream);
    }

    @Test
    public void testRecoverToupleValues() {
        final List<Game> values = parserContent.recoverToupleValues();
        assertNotNull(values);
        assertFalse(values.isEmpty());
    }

}
