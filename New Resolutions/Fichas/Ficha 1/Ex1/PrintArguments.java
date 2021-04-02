public class PrintArguments{
    
    public static void main(String[] args){
        System.out.println("Total number of arguments: " + args.length);
        int i = 0;
        for(String arg : args)
            System.out.println("Argument " + i++ + ": " +  arg.toUpperCase());
    }
}