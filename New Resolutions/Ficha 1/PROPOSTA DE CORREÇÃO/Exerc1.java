public class Exerc1 {

    public static void main(String[] args) {
        System.out.print("Escreva o primeiro argumento: ");
        String arg1 = System.console().readLine();

        System.out.print("Escreva o segundo argumento: ");
        String arg2 = System.console().readLine();

        try {
            float oper1 = Float.parseFloat( arg1 );
            float oper2 = Float.parseFloat( arg2 );

            System.out.printf( "%.1f + %.1f = %.1f\n", oper1, oper2, oper1 + oper2);
            System.out.printf( "%.1f - %.1f = %.1f\n", oper1, oper2, oper1 - oper2);
            System.out.printf( "%.1f / %.1f = %.1f\n", oper1, oper2, oper1 / oper2);
            System.out.printf( "%.1f * %.1f = %.1f\n", oper1, oper2, oper1 * oper2);
        }
        catch ( NumberFormatException nfex ) {
            System.out.println("Os argumentos introduzidos devem ser números");
        }
    }
} 
