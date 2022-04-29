package dao;

public class DAOFactory {
    public static String[] listaTabelas() {
        return new String[] {"Municipio", "Categoria de reporte"};
    }

    public static DataAccessObject<?> CriarDAO(String tabela, DataSource dataSource) {
        return switch (tabela) {
            case "Municipio" -> new DAOMunicipio(dataSource);
            case "Categoria de reporte" -> new DAOCategoriaReporte(dataSource);
            default -> throw new IllegalArgumentException("Nome de tabela invalido.");
        };
    }
}
