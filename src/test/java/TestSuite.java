import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuite {
    private final LibraryConsoleSystem system;
    private SystemError result;

    public TestSuite() {
        this.system = new LibraryConsoleSystem();
    }

    @BeforeEach
    private void reloadDefaults() {
        this.system.reloadDefaults();
    }

    @Test
    public void testSearchExistingBookByTitle() {
        this.result = this.system.searchBookByTitle("El señor de los anillos");
        assertEquals(SystemError.SUCCESS, this.result);
    }
    
    @Test
    public void testSearchNonExistingBookyTitle() {
        this.result = this.system.searchBookByTitle("El señor de los anillos 2");
        assertEquals(SystemError.BOOK_NOT_FOUND, this.result);
    }

    @Test
    public void testSearchExistingBookByAuthor() {
        this.result = this.system.searchBookByAuthor("J. R. R. Tolkien");
        assertEquals(SystemError.SUCCESS, this.result);
    }

    @Test
    public void testSearchNonExistingBookByAuthor() {
        this.result = this.system.searchBookByAuthor("J. R. R. Tolkien 2");
        assertEquals(SystemError.BOOK_NOT_FOUND, this.result);
    }

    @Test
    public void testSearchExistingBookByISBN() {
        this.result = this.system.searchBookByISBN("978-84-322-2719-3");
        assertEquals(SystemError.SUCCESS, this.result);
    }

    @Test
    public void testSearchNonExistingBookByISBN() {
        this.result = this.system.searchBookByISBN("978-84-322-2719-4");
        assertEquals(SystemError.BOOK_NOT_FOUND, this.result);
    }


    @Test
    public void testAddExistingBook() {
        this.result = this.system.addBook("El principito", "Antoine de Saint-Exupéry", "1943", 96, "Seix Barral", "Infantil", "978-84-322-2719-3", "A1", false, "El principito es un cuento infantil escrito por Antoine de Saint-Exupéry y publicado en 1943. Es una de las obras más traducidas y vendidas de la literatura francesa. El libro es una fábula filosófica que trata sobre la amistad, la soledad, la pérdida, la sabiduría y la experiencia.");
        assertEquals(SystemError.BOOK_ALREADY_EXISTS, this.result);
    }

    @Test
    public void testAddNotExistingBook() {
        this.result = this.system.addBook("El principito 2", "Antoine de Saint-Exupéry", "1943", 96, "Seix Barral", "Infantil", "978-84-322-2719-4", "A1", false, "El principito es un cuento infantil escrito por Antoine de Saint-Exupéry y publicado en 1943. Es una de las obras más traducidas y vendidas de la literatura francesa. El libro es una fábula filosófica que trata sobre la amistad, la soledad, la pérdida, la sabiduría y la experiencia.");
        assertEquals(SystemError.SUCCESS, this.result);
    }

    @Test
    public void testChageBookStatusToAvailable() {
        this.result = this.system.editBookState("978-84-322-2719-3");
        assertEquals(SystemError.SUCCESS, this.result);
        this.result = this.system.searchBookByISBN("978-84-322-2719-3");
        assertEquals(SystemError.SUCCESS, this.result);
    }

    @Test
    public void testChageBookStatusToUnavailable() {
        this.result = this.system.editBookState("978-84-322-2719-3");
        assertEquals(SystemError.SUCCESS, this.result);
        this.result = this.system.searchBookByISBN("978-84-322-2719-3");
        assertEquals(SystemError.SUCCESS, this.result);
    }

    @Test
    public void TestChangeUnexistingBookStatus() {
        this.result = this.system.editBookState("978-84-322-2719-4");
        assertEquals(SystemError.BOOK_NOT_FOUND, this.result);
    }

    @Test
    public void TestEditExistingBook() {
        this.result = this.system.editBook("978-84-322-2719-3", "El principito", "Antoine de Saint-Exupéry", "1943", 96, "Seix Barral", "Infantil", "A1", "El principito es un cuento infantil escrito por Antoine de Saint-Exupéry y publicado en 1943. Es una de las obras más traducidas y vendidas de la literatura francesa. El libro es una fábula filosófica que trata sobre la amistad, la soledad, la pérdida, la sabiduría y la experiencia.");
        assertEquals(SystemError.SUCCESS, this.result);
    }

    @Test
    public void TestEditNonExistingBook() {
        this.result = this.system.editBook("978-84-322-2719-4", "El principito", "Antoine de Saint-Exupéry", "1943", 96, "Seix Barral", "Infantil", "A1", "El principito es un cuento infantil escrito por Antoine de Saint-Exupéry y publicado en 1943. Es una de las obras más traducidas y vendidas de la literatura francesa. El libro es una fábula filosófica que trata sobre la amistad, la soledad, la pérdida, la sabiduría y la experiencia.");
        assertEquals(SystemError.BOOK_NOT_FOUND, this.result);
    }
}