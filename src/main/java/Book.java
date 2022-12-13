public class Book {
    public Book (String titulo, String autor, String fecha, int paginas, String editorial, String genero, String ISBN, String ubicacion, Boolean prestado, String descripcion){
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.paginas = paginas;
        this.editorial = editorial;
        this.genero = genero;
        this.ISBN = ISBN;
        this.ubicacion = ubicacion;
        this.prestado = prestado;
        this.descripcion = descripcion;
    }
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

    public void put (String titulo, String autor, String fecha, int paginas, String editorial, String genero, String ISBN, String ubicacion, Boolean prestado, String descripcion){
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.paginas = paginas;
        this.editorial = editorial;
        this.genero = genero;
        this.ISBN = ISBN;
        this.ubicacion = ubicacion;
        this.prestado = prestado;
        this.descripcion = descripcion;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public void setPaginas(int paginas){
        this.paginas = paginas;
    }

    public void setEditorial(String editorial){
        this.editorial = editorial;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public void setEstado(Boolean prestado){
        this.prestado = prestado;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public Boolean getPrestado(){
        return this.prestado;
    }
    
}
