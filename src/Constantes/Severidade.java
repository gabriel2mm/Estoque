package Constantes;

public enum Severidade {
    INSERIR(0),
    ALTERAR(1),
    EXCLUIR(2),
    CONSULTAR(3),
    LISTAR(4),
    SCHEDULE(5),
    INFO(6),
    EXCECAO(7);

    private int value;
    private String name;

    Severidade(int value) {
        this.value = value;
    }

    public static Severidade parse(int value) {
        for (Severidade tipoLog : Severidade.values()) {
            if (tipoLog.value == value) {
                return tipoLog;
            }
        }
        return null;
    }

    public String Name(int e) {
        String name = null;
        switch (e) {
            case 0:
                name = "INSERIR";
                break;
            case 1:
                name = "ALTERAR";
                break;
            case 2:
                name = "EXCLUIR";
                break;
            case 3:
                name = "CONSULTAR";
                break;
            case 4:
                name = "LISTAR";
                break;
            case 5:
                name = "SCHEDULE";
                break;
            case 6:
                name = "INFO";
                break;
            case 7:
                name = "EXCECAO";
                break;
            default: name = "ERRO"; break;
        }
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
