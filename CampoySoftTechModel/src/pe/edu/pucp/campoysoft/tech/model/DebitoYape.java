
package pe.edu.pucp.campoysoft.tech.model;

import java.util.Date;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;
import java.sql.Blob;

public class DebitoYape {
    private int id_debito_yape;
    private String codigo_pago_yape;
    private Blob qr;
    private String numero_cel;
    private Date fecha_hora_emision;
    private Date fecha_hora_pago;
    private boolean activo;
    private Atencion atencion;

    public DebitoYape() {
    }

    public Blob getQr() {
        return qr;
    }

    public void setQr(Blob qr) {
        this.qr = qr;
    }

    public DebitoYape(int id_debito_yape, String codigo_pago_yape, String numero_cel, Date fecha_hora_emision, Date fecha_hora_pago, boolean activo, Atencion atencion) {
        this.id_debito_yape = id_debito_yape;
        this.codigo_pago_yape = codigo_pago_yape;
        this.numero_cel = numero_cel;
        this.fecha_hora_emision = fecha_hora_emision;
        this.fecha_hora_pago = fecha_hora_pago;
        this.activo = activo;
        this.atencion = atencion;
    }

    

    public int getId_debito_yape() {
        return id_debito_yape;
    }

    public void setId_debito_yape(int id_debito_yape) {
        this.id_debito_yape = id_debito_yape;
    }

    public String getCodigo_pago_yape() {
        return codigo_pago_yape;
    }

    public void setCodigo_pago_yape(String codigo_pago_yape) {
        this.codigo_pago_yape = codigo_pago_yape;
    }

    public String getNumero_cel() {
        return numero_cel;
    }

    public void setNumero_cel(String numero_cel) {
        this.numero_cel = numero_cel;
    }

    public Date getFecha_hora_emision() {
        return fecha_hora_emision;
    }

    public void setFecha_hora_emision(Date fecha_hora_emision) {
        this.fecha_hora_emision = fecha_hora_emision;
    }

    public Date getFecha_hora_pago() {
        return fecha_hora_pago;
    }

    public void setFecha_hora_pago(Date fecha_hora_pago) {
        this.fecha_hora_pago = fecha_hora_pago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Atencion getAtencion() {
        return atencion;
    }

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }
    
    
    
}
