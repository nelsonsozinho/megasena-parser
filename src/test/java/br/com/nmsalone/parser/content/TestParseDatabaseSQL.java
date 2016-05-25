package br.com.nmsalone.parser.content;


import br.com.nmsalone.parser.content.sql.ParserDatabaseSQL;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestParseDatabaseSQL {

    private ParserDatabaseSQL parser;

    @Before
    public void prepare() {
        parser = new ParserDatabaseSQL();
    }

    @Test
    public void testLoadInsert() {
        String content = null;
        try {
            content = parser.loadInsert();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertTrue(content.contains("insert"));
    }

}
