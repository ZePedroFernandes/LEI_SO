package sofp4;

/**
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
public class SOFP4 {

    /**
     * O programa corre num loop até encontrar um valor defeituoso, ou seja diferente de 999.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int number = 999;
        int[] array = arrayOneTo1000();

        while (number == 999) {
            
            SharedNumber sharedNumber = new SharedNumber();
            searchArrayHigher[] threads = createThreads(5, sharedNumber, array);

            for (searchArrayHigher thread : threads) {
                thread.start();
            }

            try {
                for (searchArrayHigher thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            number = sharedNumber.getNumber();
            System.out.println(number);
        }
    }

    /**
     * Cria um array com mil posições com a seguinte ordem
     * 501,502,503...999,0,1,...500. numbers[0] = 501; numbers[498] = 999;
     * numbers[499] = 0; numbers[999] = 500;
     *
     * @return o array com estas posições predefinidas.
     */
    private static int[] arrayOneTo1000() {
        int[] numbers = new int[1000];
        for (int i = 1; i < 1001; i++) {
            numbers[i - 1] = (i + 500) % 1000;
        }
        return numbers;
    }

    /**
     * Cria os Threads necessários para a resolução do exercício, cada thread
     * tem acesso ao objeto partilhado {@link SharedNumber} que irá conter o
     * maior número do array. 
     * Cada thread tem um nome especifico "Thread[i]" i
     * depende do número da thread. 
     * A posição inicial das threads reflete a posição em que a thread irá começar
     * a pesquisar no array e a posição final é a ultima posição que esta thread 
     * irá aceder no array de números com 1000 posições.
     *
     * @param threadNumber o número de threads a criar
     * @param sharedNumber o objeto partilhado {@link SharedNumber}
     * @param array o array de 1000 posições.
     * @return um array com todos os threads.
     */
    private static searchArrayHigher[] createThreads(int threadNumber, SharedNumber sharedNumber, int[] array) {
        searchArrayHigher[] threads = new searchArrayHigher[threadNumber];

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new searchArrayHigher(array, sharedNumber);

            /*Em vez de passar como argumento do construtor preferi passar os 
            valores alterados em baixo diretamente. (initialPosition / finalPosition / nome da thread)*/
            threads[i].initialPosition = (array.length / 5) * i;
            threads[i].finalPosition = threads[i].initialPosition + (array.length / 5) - 1;
            threads[i].setName("Thread[" + i + "]");
        }

        return threads;
    }

}

/**
 * Thread que procura o maior valor numérico dentro de um array.
 *
 * @author Nome : José Pedro Fernandes Número: 8190239 Turma: 1
 */
class searchArrayHigher extends Thread {

    int[] numbers;

    SharedNumber sharedHigherNumber;

    int initialPosition, finalPosition;

    /**
     * Constroi uma thread com um objeto partilhado e um array que será
     * parcialmente lido por esta thread. 
     * Cada thread apenas lê um numero específico de posições, neste caso 200 posições
     * para agilizar o processo de pesquisa.
     *
     * @param numbers array com as 1000 posições
     * @param sharedHigherNumber o objeto partilhado onde as threads vão colocar o maior valor encontrado.
     */
    public searchArrayHigher(int[] numbers, SharedNumber sharedHigherNumber) {
        this.sharedHigherNumber = sharedHigherNumber;
        this.numbers = numbers;
    }

    /**
     * Procura em 200 posições do array predefinidas pelo maior resultado e tenta
     * colocar este resultado no objeto partilhado
     */
    @Override
    public void run() {
        int result = 0;
        for (int i = initialPosition; i < finalPosition; i++) {
            //if (numbers[i] > sharedHigherNumber.getNumber()) {
               // result = numbers[i]; /*Se remover esta linha e a linha 129, 
               // e descomentar a linha abaixo o programa funciona corretamente e eu não percebo porquê*/
                
                sharedHigherNumber.setHigher(numbers[i]);
            //}
        }
        //sharedHigherNumber.setHigher(result);
    }
}

/**
 * Objeto partilhado entre as classes que possui um valor inteiro usado para 
 * armazenar o maior valor lido no array de 1000 posições.
 * 
 * @author Nome : José Pedro Fernandes
 * Número: 8190239
 * Turma: 1
 */
class SharedNumber {

    private int number = Integer.MIN_VALUE;

    public int getNumber() {
        return this.number;
    }

    /**
     * método sincronizado que altera o numero mais alto encontrado no array se 
     * o argumento for superior ao numero armazenado
     * 
     * @param number maior numero encontrado pela thread que irá ser armazenado 
     * se for maior do que o anterior.
     */
    public synchronized void setHigher(int number) {
        if (number > this.number) {
            this.number = number;
        }
    }
}
