public class Ficha1{
	public static void main (String[] args){
		System.out.println("Total de argumentos: " + args.length);
		Integer i = 0;
		for(String s: args){
			System.out.println("Argumento n " + i +": " + s);
			i++;
		}

	}
}