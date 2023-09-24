package Config;

/**
 * @author JoaCa
 */

public final class Config {
    public static String getConnectionString() throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return  "jdbc:sqlserver://localhost;databasename=FACTURACION_PANADERIA;user=sa;password=sa;";
    }
}
