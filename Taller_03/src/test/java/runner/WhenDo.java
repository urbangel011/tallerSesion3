package runner;

public class WhenDo {

    private final String nomTituloNote;
    private final String nomDescripcionActividad;

    public WhenDo(String nomTituloNote, String nomDescripcionActividad) {
        this.nomTituloNote = nomTituloNote;
        this.nomDescripcionActividad = nomDescripcionActividad;
    }

    public String getNomTituloNote() {
        return nomTituloNote;
    }



    public String getNomDescripcionActividad() {
        return nomDescripcionActividad;
    }


}

