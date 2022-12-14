package bibloteca;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import java.util.HashMap;

public class LibraryConsoleSystem {
    private HashMap<String, Book> books = new HashMap<>();
    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal terminal = textIO.getTextTerminal();
    private String titulo;
    private String autor;
    private String fecha;
    private int paginas;
    private String editorial;
    private String genero;
    private String ISBN;
    private String ubicacion;
    private Boolean prestado;
    private String descripcion;
    private Boolean loop;

    public LibraryConsoleSystem() {
        this.loop = true;
    }

    public SystemError addBook(String titulo, String autor, String fecha, int paginas, String editorial, String genero, String ISBN, String ubicacion, Boolean prestado, String descripcion){
        Book book = new Book(titulo, autor, fecha, paginas, editorial, genero, ISBN, ubicacion, prestado, descripcion);
        try {
            if (books.containsKey(book.getISBN())) {
                return SystemError.BOOK_ALREADY_EXISTS;
            }
            books.put(book.getISBN(), book);
            return SystemError.SUCCESS;
        } catch (Exception e) {
            return SystemError.UNKNOWN;
        }
    }

    public SystemError searchBookByTitle(String titulo){
        var found = false;
        for (Book book : books.values()) {
            if (book.getTitulo().equals(titulo)) {
                terminal.println(book.getTitulo());
                found = true;
            }
        }
        if (found) {
            return SystemError.SUCCESS;
        } else {
            return SystemError.BOOK_NOT_FOUND;
        }
    }

    public SystemError searchBookByAuthor(String autor){
        var found = false;
        for (Book book : books.values()) {
            if (book.getAutor().equals(autor)) {
                terminal.println(book.getTitulo());
                found = true;
            }
        }
        if (found){
            return SystemError.SUCCESS;
        } else {
            return SystemError.BOOK_NOT_FOUND;
        }
    }

    public SystemError  searchBookByISBN(String ISBN){
        var found = false;
        for (Book book : books.values()) {
            if (book.getISBN().equals(ISBN)) {
                terminal.println(book.getTitulo());
                found = true;
            }
        }
        if (found){
            return SystemError.SUCCESS;
        } else {
            return SystemError.BOOK_NOT_FOUND;
        }
    }

    public SystemError editBook(String ISBN, String titulo, String autor, String fecha, int paginas, String editorial, String genero, String ubicacion, String descripcion){
        try {
            if (!books.containsKey(ISBN)) {
                return SystemError.BOOK_NOT_FOUND;
            }
            Book book = books.get(ISBN);
            book.setTitulo(titulo);
            book.setAutor(autor);
            book.setFecha(fecha);
            book.setPaginas(paginas);
            book.setEditorial(editorial);
            book.setGenero(genero);
            book.setUbicacion(ubicacion);
            book.setDescripcion(descripcion);
            return SystemError.SUCCESS;
        } catch (Exception e) {
            return SystemError.UNKNOWN;
        }
    }

    public SystemError removeBook(String ISBN){
        try {
            if (!books.containsKey(ISBN)) {
                return SystemError.BOOK_NOT_FOUND;
            }
            books.remove(ISBN);
            return SystemError.SUCCESS;
        } catch (Exception e) {
            return SystemError.UNKNOWN;
        }
    }

    public SystemError editBookState(String ISBN){
        try {
            if (!books.containsKey(ISBN)) {
                return SystemError.BOOK_NOT_FOUND;
            }
            Book book = books.get(ISBN);
            if (book.getPrestado()) {
                book.setEstado(false);
            } else {
                book.setEstado(true);
            }
            return SystemError.SUCCESS;
        } catch (Exception e) {
            return SystemError.UNKNOWN;
        }
    }

    public void reloadDefaults(){
        //reiniciar libros
        books.clear();
        addBook("El principito", "Antoine de Saint-Exup??ry", "1943", 96, "Seix Barral", "Infantil", "978-84-322-2719-3", "(1,2)", false, "El principito es un cuento infantil escrito por Antoine de Saint-Exup??ry y publicado en 1943. Es una de las obras m??s traducidas y vendidas de la literatura francesa. El libro es una f??bula filos??fica que trata sobre la amistad, la soledad, la p??rdida, la sabidur??a y la experiencia.");
        addBook("El se??or de los anillos", "J. R. R. Tolkien", "1954", 1216, "Minotauro", "Fantas??a", "978-84-450-0001-5", "(1,2)", false, "El Se??or de los Anillos es una novela de fantas??a ??pica escrita por el fil??logo y acad??mico brit??nico J. R. R. Tolkien. Es la primera parte de una trilog??a que tambi??n incluye las novelas El Hobbit y El Silmarillion. La trilog??a narra la historia de la Tierra Media, un mundo ficticio creado por Tolkien, y se centra en la lucha del bien contra el mal, representada por la Guerra del Anillo, que se desarrolla en el curso de la historia.");
        addBook("El alquimista", "Paulo Coelho", "1988", 197, "Debolsillo", "Novela", "978-84-204-2029-1", "(1,2)", false, "El alquimista es una novela de aventuras y misterio escrita por el escritor brasile??o Paulo Coelho. Fue publicada por primera vez en 1988. La novela cuenta la historia de un joven pastor andaluz que viaja a Egipto en busca de un tesoro escondido en las pir??mides. Durante su viaje, el joven se encuentra con un alquimista que le ense??a a seguir sus sue??os y a convertirlos en realidad.");
        addBook("El c??digo Da Vinci", "Dan Brown", "2003", 736, "Debolsillo", "Novela", "978-84-204-2029-1", "(1,2)", false, "El c??digo Da Vinci es una novela de misterio y aventuras escrita por el autor estadounidense Dan Brown. Fue publicada en 2003 y se convirti?? en un ??xito de ventas internacional. La novela cuenta la historia de Robert Langdon, un profesor de simbolog??a de la Universidad de Harvard, que se ve envuelto en una investigaci??n sobre la misteriosa muerte de un sacerdote cat??lico.");
        addBook("El diario de Ana Frank", "Ana Frank", "1947", 288, "Seix Barral", "Biograf??a", "978-84-322-2719-3", "(1,2)", false, "El diario de Ana Frank es un diario escrito por Ana Frank entre 1942 y 1944. Fue publicado por primera vez en 1947. El diario describe la vida de una familia jud??a holandesa que se escondi?? durante dos a??os en un anexo secreto de una casa en Amsterdam");
        addBook("El perfume", "Patrick S??skind", "1985", 471, "Seix Barral", "Novela", "978-84-322-2719-3", "(1,2)", false, "El perfume es una novela de Patrick S??skind publicada en 1985. La novela narra la historia de Jean-Baptiste Grenouille, un asesino en serie que mata a sus v??ctimas para robarles su olor. La novela se centra en la historia de Grenouille y en su b??squeda de la perfecci??n olfativa.");
        addBook("Farenheit 451", "Ray Bradbury", "1953", 256, "Seix Barral", "Ciencia ficci??n", "978-84-322-2719-3", "(1,2)", false, "Fahrenheit 451 es una novela de ciencia ficci??n escrita por el autor estadounidense Ray Bradbury. Fue publicada en 1953. La novela se centra en Guy Montag, un bombero que trabaja en un futuro dist??pico en el que los libros est??n prohibidos y los bomberos tienen la tarea de quemarlos.");
    }


    public void console(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();
        while (loop) {
        terminal.println("Bienvenido a la liberia");
        terminal.println("Por favor seleccione una opci??n:");
        terminal.println("1. Registrar un libro");
        terminal.println("2. Buscar un libro");
        terminal.println("3. Editar un libro");
        terminal.println("4. Eliminar un libro");
        terminal.println("5. Editar prestado de un libro");
        terminal.println("6. Salir");
        int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(6).read("Ingresa tu opcion: ");
        switch (option){
            case 1:
                titulo = textIO.newStringInputReader().read("Ingrese titulo: ");
                autor = textIO.newStringInputReader().read("Ingrese autor: ");
                fecha = textIO.newStringInputReader().read("Ingrese fecha: ");
                paginas = textIO.newIntInputReader().read("Ingrese paginas: ");
                editorial = textIO.newStringInputReader().read("Ingrese editorial: ");
                genero = textIO.newStringInputReader().read("Ingrese genero: ");
                ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                ubicacion = textIO.newStringInputReader().read("Ingrese ubicacion: ");
                prestado = false;
                descripcion = textIO.newStringInputReader().read("Ingrese descripcion: ");
                addBook(titulo, autor, fecha, paginas, editorial, genero, ISBN, ubicacion, prestado, descripcion);
                break;
            case 2:
                terminal.println("Desea buscar por: ");
                terminal.println("1. Titulo");
                terminal.println("2. Autor");
                terminal.println("3. ISBN");
                int option2 = textIO.newIntInputReader().withMinVal(1).withMaxVal(5).read("Ingresa tu opcion: ");
                switch (option2){
                    case 1:
                        titulo = textIO.newStringInputReader().read("Ingrese titulo: ");
                        searchBookByTitle(titulo);
                        break;
                    case 2:
                        autor = textIO.newStringInputReader().read("Ingrese autor: ");
                        searchBookByAuthor(autor);
                        break;
                    case 3:
                        ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                        searchBookByISBN(ISBN);
                        break;
                }
                break;
            case 3:
            titulo = textIO.newStringInputReader().read("Ingrese titulo: ");
                autor = textIO.newStringInputReader().read("Ingrese autor: ");
                fecha = textIO.newStringInputReader().read("Ingrese fecha: ");
                paginas = textIO.newIntInputReader().read("Ingrese paginas: ");
                editorial = textIO.newStringInputReader().read("Ingrese editorial: ");
                genero = textIO.newStringInputReader().read("Ingrese genero: ");
                ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                ubicacion = textIO.newStringInputReader().read("Ingrese ubicacion: ");
                descripcion = textIO.newStringInputReader().read("Ingrese descripcion: ");
                editBook(ISBN, titulo, autor, fecha, paginas, editorial, genero, ubicacion, descripcion);
                break;
            case 4:
                ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                removeBook(ISBN);
                break;
            case 5:
                ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                editBookState(ISBN);
                break;
            case 6:
                loop = false;
                System.exit(0);
        }
    }
    }
    
}
