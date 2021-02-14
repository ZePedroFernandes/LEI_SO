import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SMMatriz {
    Matriz a, b, c;
    int linha;
    static int max = 10;
    static int min = 0;

    private static Matriz defineMatriz( String nomeMatriz ) {
        Scanner s = new Scanner( System.in );
        System.out.print( "Introduza o número de linhas da matriz " + nomeMatriz + ": " );
        int linhas = s.nextInt();

        System.out.print( "Introduza o número de colunas da matriz " + nomeMatriz + ": " );
        int colunas = s.nextInt();

        Matriz m = new Matriz( linhas, colunas );

        for ( int i = 0; i < linhas; i++ ) {
            for ( int j = 0; j < colunas; j++ ) {
                m.setValor( i, j, ThreadLocalRandom.current().nextInt( min, max + 1 ) );
            }
        }

        return m;
    }

    public static void main(String[] args) {
        Matriz a = defineMatriz( "A" );
        Matriz b = defineMatriz( "B" );

        if ( a.getNumeroLinhas() != b.getNumeroColunas() ) {
            System.err.println( "O número de linhas na matriz A (" + a.getNumeroLinhas() + ") é diferente do número de colunas da matriz B (" + b.getNumeroColunas() + ")" );
            System.exit(0);
        }

        Matriz c = new Matriz( a.getNumeroLinhas(), b.getNumeroColunas() );

        long start = System.nanoTime();

        for ( int i = 0; i < a.getNumeroLinhas(); i++ ) {
            for ( int j = 0; j < b.getNumeroColunas(); j++ ) {
                for ( int k = 0; k < a.getNumeroColunas(); k++ ) {
                    c.setValor( i, j, c.getValor( i, j ) + a.getValor( i, k ) * b.getValor( k, j ) );
                }
            }
        }

        long elapsedTime = System.nanoTime() - start;
/*
        a.imprime( "Matriz A" );
        b.imprime( "Matriz B" );
        c.imprime( "Matriz C" );
*/
        System.out.println( "Runtime = " + elapsedTime / 1000000 + "ms" );              
    }
}
