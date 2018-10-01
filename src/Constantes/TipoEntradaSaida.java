package Constantes;

public enum TipoEntradaSaida {
    ENTRADA(0),
    SAIDA(1);

    private final int value;
    private String name;

    TipoEntradaSaida(int value) {
        this.value = value;
    }

    public static TipoEntradaSaida parse(int value) {
        for (TipoEntradaSaida tipo : TipoEntradaSaida.values()) {
            if (tipo.getValue() == value) {
                return tipo;
            }
        }
        return null;
    }

    public String Name(int e) {
        String name = null;
        switch (e) {
            case 0:
                name = "ENTRADA";
                break;
            case 1:
                name = "SAIDA";
                break;
            default:
                name = "ERRO";
                break;
        }
        return name;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
