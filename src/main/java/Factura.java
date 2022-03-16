import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Factura {
    private String codigo;
    private String descripcion;
    private int precio;
    private int cantidad;
    private Date fecha;

    public Factura(String descripcion, int precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Factura(String codigo, String descripcion, int precio, int cantidad, Date fecha) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCantidad() {return cantidad;}

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return " \n Factura{ " +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }

    public static void main(String[] args) {

        Date date = new Date(122,3,12);
        Date date2 = new Date(120,4,10);
        Date date3 = new Date(123,5,9);
        Date date4 = new Date(122,6,8);

        Factura factura = new Factura("0000","Antel",2000,2, date);
        Factura factura2 = new Factura("1111","ordenador",1000,0, date2);
        Factura factura3 = new Factura("2222","impresora",200,1, date3);
        Factura factura4 = new Factura("3333","imac",1500,2, date4);

        List<Factura> lista = new ArrayList<Factura>();

        lista.add(factura);
        lista.add(factura2);
        lista.add(factura3);
        lista.add(factura4);

    //----------------------------------------------[Factura Precio]----------------------------------------------------

        Predicate<Factura> predicado = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                return factura.getPrecio()>300;
            }

        };

        Factura facturaFiltro = lista.stream()
                .filter(predicado).findFirst().get();
        System.out.println("FACTURA UNICA: precio mayor(>) a 300 " + "precio: " + facturaFiltro.getPrecio() + "\n");

        //------------------------------------------[Factura Fecha Mayor]-----------------------------------------------

        Predicate<Factura> predicadoFechaMayor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                Date fecha = new Date(122,3,16);
                return factura.getFecha().after(fecha);
            }

        };

        List <Factura> facturaFechaMayor = lista.stream()
                .filter(predicadoFechaMayor).collect(Collectors.toList());
        System.out.println("FACTURA UNICA: fecha mayor que la actual (16/3/2022) " + facturaFechaMayor.toString() + "\n");

        //------------------------------------------[Factura Fecha Menor]-----------------------------------------------

        Predicate<Factura> predicadoFechaMenor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                Date fecha = new Date(122,3,16);
                return factura.getFecha().before(fecha);
            }
        };

        List <Factura> facturaFechaMenor = lista.stream()
                .filter(predicadoFechaMenor).collect(Collectors.toList());
        System.out.println("FACTURA UNICA: fecha menor que la actual (16/3/2022) " + facturaFechaMenor.toString() + "\n");

        //---------------------------------------[Factura Cantidad igual]-----------------------------------------------

        Predicate<Factura> predicadoCantidad = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                return factura.getCantidad()==2;
            }
        };

        List<Factura> facturaCantidad = lista.stream()
                .filter(predicadoCantidad).collect(Collectors.toList());
        System.out.println("FACTURA UNICA: cantidad igual(=) a 2 " + facturaCantidad.toString() + "\n" );

        //---------------------------------------[Factura Cantidad Mayor]-----------------------------------------------

        Predicate<Factura> predicadoCantidadMayor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                return factura.getCantidad()>0;
            }
        };

        List<Factura> facturaCantidadMayor = lista.stream()
                .filter(predicadoCantidadMayor).collect(Collectors.toList());
        System.out.println("FACTURA UNICA: cantidad mayor(>) a 0 " + facturaCantidadMayor.toString() + "\n");

        //---------------------------------------[Factura Cantidad Menor]-----------------------------------------------
        Predicate<Factura> predicadoCantidadMenor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                return factura.getCantidad()<1;
            }
        };

        List<Factura> facturaCantidadMenor = lista.stream()
                .filter(predicadoCantidadMenor).collect(Collectors.toList());
        System.out.println("FACTURA UNICA: cantidad menor(<) a 1 " + facturaCantidadMenor.toString() + "\n");

    }

}