package br.com.nmsalone.parser.htmlcontent;

import br.com.nmsalone.parser.constants.GameKeys;
import br.com.nmsalone.parser.model.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nelsonsozinho on 24/05/16.
 */
public class TestParserContent {

    private ParserContent parserContent;

    @Before
    public void prepare() {
        java.net.URL url = ClassLoader.getSystemClassLoader().getResource("loteria/D_MEGA.HTM");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        parserContent = new ParserContent(file);
    }

    @Test
    public void testRecoverToupleValues() {
        final List<Game> values = parserContent.recoverToupleValues();
        assertNotNull(values);
        assertFalse(values.isEmpty());
    }

}
