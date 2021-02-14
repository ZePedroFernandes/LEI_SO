import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MMatriz implements Runnable {
    Matriz a, b, c;
    int linha, numeroThreads;
    static int max = 10;
    static int min = 0;

    MMatriz( Matriz a, Matriz b, Matriz c, int linha, int numeroThreads ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.linha = linha;
        this.numeroThreads = numeroThreads;
    }

    public void run() {
        int inicio = linha * a.getNumeroLinhas() / numeroThreads;
        int fim = inicio + a.getNumeroLinhas() / numeroThreads;

        for ( int i = inicio; i < fim; i++ ) {
            for ( int j = 0; j < b.getNumeroColunas(); j++ ) {
                for ( int k = 0; k < a.getNumeroColunas(); k++ ) {
                    c.setValor( i, j, c.getValor( i, j ) + a.getValor( i, k ) * b.getValor( k, j ) );
                }
            }
        }
    }

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

    public static void main(String[] args) throws InterruptedException {
        if ( args.length != 1 ) {
            System.err.println( "Modo de execução: MMatrix <número de threads>" );
            System.exit(0);
        }

        Matriz a = defineMatriz( "A" );
        Matriz b = defineMatriz( "B" );

        if ( a.getNumeroLinhas() != b.getNumeroColunas() ) {
            System.err.println( "O número de linhas na matriz A (" + a.getNumeroLinhas() + ") é diferente do número de colunas da matriz B (" + b.getNumeroColunas() + ")" );
            System.exit(0);
        }

        int numeroThreads = Integer.parseInt( args[0] );

        Matriz c = new Matriz( a.getNumeroLinhas(), b.getNumeroColunas() );
        Thread[] t = new Thread[ numeroThreads ];

        long start = System.nanoTime();

        for ( int i = 0; i < numeroThreads; i++ ) {
            t[i] = new Thread( new MMatriz( a, b, c, i, numeroThreads ), "[Th" + i + "]" );
            t[i].start();
        }

        for ( int i = 0; i < numeroThreads; i++ ) {   // aguardo pelo término dos threads criados
            t[i].join();
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
