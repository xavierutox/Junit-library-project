import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.HashMap;
import java.util.List;

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
        addBook("El principito", "Antoine de Saint-Exupéry", "1943", 96, "Seix Barral", "Infantil", "978-84-322-2719-3", "(1,2)", false, "El principito es un cuento infantil escrito por Antoine de Saint-Exupéry y publicado en 1943. Es una de las obras más traducidas y vendidas de la literatura francesa. El libro es una fábula filosófica que trata sobre la amistad, la soledad, la pérdida, la sabiduría y la experiencia.");
        addBook("El señor de los anillos", "J. R. R. Tolkien", "1954", 1216, "Minotauro", "Fantasía", "978-84-450-0001-5", "(1,2)", false, "El Señor de los Anillos es una novela de fantasía épica escrita por el filólogo y académico británico J. R. R. Tolkien. Es la primera parte de una trilogía que también incluye las novelas El Hobbit y El Silmarillion. La trilogía narra la historia de la Tierra Media, un mundo ficticio creado por Tolkien, y se centra en la lucha del bien contra el mal, representada por la Guerra del Anillo, que se desarrolla en el curso de la historia.");
        addBook("El alquimista", "Paulo Coelho", "1988", 197, "Debolsillo", "Novela", "978-84-204-2029-1", "(1,2)", false, "El alquimista es una novela de aventuras y misterio escrita por el escritor brasileño Paulo Coelho. Fue publicada por primera vez en 1988. La novela cuenta la historia de un joven pastor andaluz que viaja a Egipto en busca de un tesoro escondido en las pirámides. Durante su viaje, el joven se encuentra con un alquimista que le enseña a seguir sus sueños y a convertirlos en realidad.");
        addBook("El código Da Vinci", "Dan Brown", "2003", 736, "Debolsillo", "Novela", "978-84-204-2029-1", "(1,2)", false, "El código Da Vinci es una novela de misterio y aventuras escrita por el autor estadounidense Dan Brown. Fue publicada en 2003 y se convirtió en un éxito de ventas internacional. La novela cuenta la historia de Robert Langdon, un profesor de simbología de la Universidad de Harvard, que se ve envuelto en una investigación sobre la misteriosa muerte de un sacerdote católico.");
        addBook("El diario de Ana Frank", "Ana Frank", "1947", 288, "Seix Barral", "Biografía", "978-84-322-2719-3", "(1,2)", false, "El diario de Ana Frank es un diario escrito por Ana Frank entre 1942 y 1944. Fue publicado por primera vez en 1947. El diario describe la vida de una familia judía holandesa que se escondió durante dos años en un anexo secreto de una casa en Amsterdam");
        addBook("El perfume", "Patrick Süskind", "1985", 471, "Seix Barral", "Novela", "978-84-322-2719-3", "(1,2)", false, "El perfume es una novela de Patrick Süskind publicada en 1985. La novela narra la historia de Jean-Baptiste Grenouille, un asesino en serie que mata a sus víctimas para robarles su olor. La novela se centra en la historia de Grenouille y en su búsqueda de la perfección olfativa.");
        addBook("Farenheit 451", "Ray Bradbury", "1953", 256, "Seix Barral", "Ciencia ficción", "978-84-322-2719-3", "(1,2)", false, "Fahrenheit 451 es una novela de ciencia ficción escrita por el autor estadounidense Ray Bradbury. Fue publicada en 1953. La novela se centra en Guy Montag, un bombero que trabaja en un futuro distópico en el que los libros están prohibidos y los bomberos tienen la tarea de quemarlos.");
    }


    public void console(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();
        while (loop) {
        terminal.println("Bienvenido a la liberia");
        terminal.println("Por favor seleccione una opción:");
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
