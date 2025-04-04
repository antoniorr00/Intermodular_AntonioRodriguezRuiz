import java.util.Scanner;

class Paciente {
    private String fechaNac;
    private String telefono;
    private String nombrePac;
    private String SIP_paciente;

    public Paciente(String fechaNac, String telefono, String nombrePac, String SIP_paciente) {
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.nombrePac = nombrePac;
        this.SIP_paciente = SIP_paciente;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombrePac() {
        return nombrePac;
    }

    public String getSIP_paciente() {
        return SIP_paciente;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombrePac(String nombrePac) {
        this.nombrePac = nombrePac;
    }

    @Override
    public String toString() {
        return "     <PACIENTE>\n" +
                    "        <SIP_paciente>" + SIP_paciente + "</SIP_paciente>\n" +
                    "        <nombrePac>" + nombrePac + "</nombrePac>\n" +
                    "        <telefono>" + telefono + "</telefono>\n" +
                    "        <fechaNac>" + fechaNac + "</fechaNac>\n" +
                "     </PACIENTE>";
    }
}
