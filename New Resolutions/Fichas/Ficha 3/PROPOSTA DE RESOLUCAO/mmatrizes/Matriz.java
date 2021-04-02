public class Matriz {
    private int [][] m;

    Matriz( int linhas, int colunas ) {
        this.m = new int[linhas][colunas];
    }

    public int getNumeroLinhas() {
        return m.length;
    }

    public int getNumeroColunas() {
        return m[0].length;
    }

    public int getValor( int i, int j ) {
        return m[i][j];
    }

    public void setValor( int i, int j, int valor ) {
        m[i][j] = valor;
    }

    public void imprime( String nome ) {
        System.out.println( nome );

        for ( int i = 0; i < m.length; i++ ) {
           for ( int j = 0; j < m[0].length; j++ ) {
               System.out.print( m[i][j] + " " );
           }

           System.out.println();
       }

        System.out.println();
    }
}

