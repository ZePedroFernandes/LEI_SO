public class Exerc2 {

    public static void main(String[] args) {
        try {
            if ( args.length < 2 ) throw new Exception();

            float oper1 = Float.parseFloat( args[0] );
            float oper2 = Float.parseFloat( args[1] );

            System.out.printf( "%.1f + %.1f = %.1f\n", oper1, oper2, oper1 + oper2);
            System.out.printf( "%.1f - %.1f = %.1f\n", oper1, oper2, oper1 - oper2);
            System.out.printf( "%.1f / %.1f = %.1f\n", oper1, oper2, oper1 / oper2);
            System.out.printf( "%.1f * %.1f = %.1f\n", oper1, oper2, oper1 * oper2);
        }
        catch ( Exception ex ) {
            System.out.println("Modo de utilização: java Exerc2 NUM1 NUM2\nOs argumentos NUM1 e NUM2 devem ser numéricos");
        }
    }
} 
