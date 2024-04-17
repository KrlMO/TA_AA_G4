package pe.edu.pucp.campoysoft.productotextil.model;

public class EspecificacionRollo {
    private int idEspecifiacionRollo;
    private TipoTela tipoTela;
    private TipoRollo tipoRollo;
    private double longitudRollo;
    private double achoRollo;
    private double areaRollo;
    private double pesoRollo;

    public EspecificacionRollo(int idEspecifiacionRollo, TipoTela tipoTela, TipoRollo tipoRollo, double longitudRollo, double achoRollo, double areaRollo, double pesoRollo) {
        this.idEspecifiacionRollo = idEspecifiacionRollo;
        this.tipoTela = tipoTela;
        this.tipoRollo = tipoRollo;
        this.longitudRollo = longitudRollo;
        this.achoRollo = achoRollo;
        this.areaRollo = areaRollo;
        this.pesoRollo = pesoRollo;
    }

    public int getIdEspecifiacionRollo() {
        return idEspecifiacionRollo;
    }

    public void setIdEspecifiacionRollo(int idEspecifiacionRollo) {
        this.idEspecifiacionRollo = idEspecifiacionRollo;
    }

    public TipoTela getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(TipoTela tipoTela) {
        this.tipoTela = tipoTela;
    }

    public TipoRollo getTipoRollo() {
        return tipoRollo;
    }

    public void setTipoRollo(TipoRollo tipoRollo) {
        this.tipoRollo = tipoRollo;
    }

    public double getLongitudRollo() {
        return longitudRollo;
    }

    public void setLongitudRollo(double longitudRollo) {
        this.longitudRollo = longitudRollo;
    }

    public double getAchoRollo() {
        return achoRollo;
    }

    public void setAchoRollo(double achoRollo) {
        this.achoRollo = achoRollo;
    }

    public double getAreaRollo() {
        return areaRollo;
    }

    public void setAreaRollo(double areaRollo) {
        this.areaRollo = areaRollo;
    }

    public double getPesoRollo() {
        return pesoRollo;
    }

    public void setPesoRollo(double pesoRollo) {
        this.pesoRollo = pesoRollo;
    }

}
