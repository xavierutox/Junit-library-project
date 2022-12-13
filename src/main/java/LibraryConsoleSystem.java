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
    private String estado;
    private String descripcion;
    private Boolean loop;

    public LibraryConsoleSystem() {
        this.loop = true;
    }

    private void addBook(String titulo, String autor, String fecha, int paginas, String editorial, String genero, String ISBN, String ubicacion, String estado, String descripcion){
        Book book = new Book(titulo, autor, fecha, paginas, editorial, genero, ISBN, ubicacion, estado, descripcion);
        books.put(book.getISBN(), book);
    }

    private void searchBookByTitle(String titulo){
        for (Book book : books.values()) {
            if (book.getTitulo().equals(titulo)) {
                terminal.println(book.getTitulo());
            }
        }
    }

    private void searchBookByAuthor(String autor){
        for (Book book : books.values()) {
            if (book.getAutor().equals(autor)) {
                terminal.println(book.getTitulo());
            }
        }
    }

    private void searchBookByISBN(String ISBN){
        for (Book book : books.values()) {
            if (book.getISBN().equals(ISBN)) {
                terminal.println(book.getTitulo());
            }
        }
    }

    private void editBook(String ISBN, String titulo, String autor, String fecha, int paginas, String editorial, String genero, String ubicacion, String estado, String descripcion){
        Book book = books.get(ISBN);
        book.setTitulo(titulo);
        book.setAutor(autor);
        book.setFecha(fecha);
        book.setPaginas(paginas);
        book.setEditorial(editorial);
        book.setGenero(genero);
        book.setUbicacion(ubicacion);
        book.setEstado(estado);
        book.setDescripcion(descripcion);
    }

    private void removeBook(String ISBN){
        books.remove(ISBN);
    }

    private void editBookState(String ISBN, String estado){
        Book book = books.get(ISBN);
        book.setEstado(estado);
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
        terminal.println("5. Editar estado de un libro");
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
                estado = textIO.newStringInputReader().read("Ingrese estado: ");
                descripcion = textIO.newStringInputReader().read("Ingrese descripcion: ");
                addBook(titulo, autor, fecha, paginas, editorial, genero, ISBN, ubicacion, estado, descripcion);
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
                estado = textIO.newStringInputReader().read("Ingrese estado: ");
                descripcion = textIO.newStringInputReader().read("Ingrese descripcion: ");
                editBook(ISBN, titulo, autor, fecha, paginas, editorial, genero, ubicacion, estado, descripcion);
                break;
            case 4:
                ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                removeBook(ISBN);
                break;
            case 5:
                ISBN = textIO.newStringInputReader().read("Ingrese ISBN: ");
                estado = textIO.newStringInputReader().read("Ingrese estado: ");
                editBookState(ISBN, estado);
                break;
            case 6:
                loop = false;
                System.exit(0);
        }
    }
    }
    
}
