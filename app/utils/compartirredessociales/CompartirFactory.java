package utils.compartirredessociales;

/**
 * Created by Omar on 14/11/2015.
 */
public class CompartirFactory {
    private static CompartirFactory instance = new CompartirFactory();

    public static CompartirFactory getInstance() {
        return instance;
    }

    private CompartirFactory() {
    }

    public ICompartirRedSocial getCompartirRed(String proveedor) {
        if (proveedor.equalsIgnoreCase("facebook"))
            return new Facebook();
        else if (proveedor.equalsIgnoreCase("twitter"))
            return new Twitter();
        return null;
    }
}
