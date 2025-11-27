package smbomba;

public class smBBA {
    final int sme = -10; // Refactorización

    int[][] mt = { // Refactorización
            // a b c d t
            { 1, sme, sme, sme, sme },
            { sme, 2, sme, sme, sme },
            { sme, sme, 3, sme, sme },
            { sme, sme, sme, 4, sme },
            { sme, sme, sme, sme, 5 },
            { sme, sme, sme, sme, 5 }
    };

    public int smobtenerColumna(char c) { // Refactorización
        switch (c) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 't':
                return 4;
            default:
                return -1;
        }
    }

    public boolean smautomataBBA(String smTipoArsenal) { // Refactorización
        int estadoActual = 0;
        for (int i = 0; i < smTipoArsenal.length(); i++) {
            char simbolo = smTipoArsenal.charAt(i);
            int columna = smobtenerColumna(simbolo);

            if (columna == -1) {
                return false;
            }

            estadoActual = mt[estadoActual][columna];

            if (estadoActual == sme) {
                return false;
            }
        }

        return estadoActual == 5;
    }

}
