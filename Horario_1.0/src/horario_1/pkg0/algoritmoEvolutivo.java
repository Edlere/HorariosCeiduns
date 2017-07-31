package horario_1.pkg0;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class algoritmoEvolutivo {

    ArrayList<int[][]> cromosomas; //Todas los cromosomas
    ArrayList<int[][]> cro1;
    ArrayList<int[][]> cro2;
    int cromosomasObjetivo[];
    int contC1, contC2;

    public algoritmoEvolutivo(ArrayList<int[][]> cromosomas) {
        //Generacion de Poblacion
        this.cromosomas = new ArrayList<>();
        this.cromosomas = cromosomas;
        //Inicializacion de contadores
        contC1 = 0;
        contC2 = 0;
    }

    public void evolucionPoblacion() {
        funcionObjetivo(); //Resultado de la funcion objetivo para cada cromosoma
        dividirParejas();
        for (int i = 0; i < cromosomas.size() / 2; i++) {
            crucePadres(seleccionCromosomas());
        }
        mutacionIndividuo(); //El inidividuo se selecciona aleotiramente
    }

    public void crucePadres(ArrayList<int[][]> seleccionCromosomas) {
        int padre[][], madre[][];
        //Se extrae el padre y la madre
        padre = seleccionCromosomas.get(0);
        madre = seleccionCromosomas.get(1);
        //Hijos
        int hijo1[][] = new int[padre.length][padre[0].length];
        int hijo2[][] = new int[padre.length][padre[0].length];
        for (int i = 0; i < padre.length; i++) {
            int puntoC = (int) (Math.random() * padre.length); //Posicion aleatoria del cruce
            for (int j = 0; j < padre[i].length; j++) {
                //Se realiza el cruce, llenando los hijos
                if (i > puntoC) {
                    hijo1[i][j] = padre[i][j];
                    hijo2[i][j] = madre[i][j];
                } else {
                    hijo1[i][j] = madre[i][j];
                    hijo2[i][j] = padre[i][j];
                }
            }
        }
        //Se cambian los padres por los Hijos
        cromosomas.add(contC1 * 2, hijo1);
        cromosomas.add((contC1 * 2) + 1, hijo2);
    }

    public void funcionObjetivo() {
        cromosomasObjetivo = new int[cromosomas.size()];
        for (int i = 0; i < cromosomas.size(); i++) {
            int n=0,nc=0;//nc=Numero de cruces horarios, n=cantidad de secciones(los valores aun no se asignan)
            cromosomasObjetivo[i] = 100 * ((n - nc) / n);
        }
        ordenarSegunfuncion();//Se ordena segun los resuldos de la funcion
    }

    public void mutacionIndividuo() {
        //Se cambia un valor el cual se demoraria en obtener por evolucion
    }

    public ArrayList<int[][]> seleccionCromosomas() {
        //Seleccionan 2 individuos y se aumentan los contadores para no repetir cromosomas
        ArrayList<int[][]> cromosomasSeleccionados = new ArrayList<>();
        cromosomasSeleccionados.add(cromosomas.get(contC1));
        cromosomasSeleccionados.add(cromosomas.get(contC2));
        contC1++;
        contC2++;
        return cromosomasSeleccionados;
    }

    public void ordenarSegunfuncion() {
        //Se utiliza el metodo de la burbuja para ordenar
        for (int i = 1; i < cromosomasObjetivo.length; i++) {
            for (int j = 0; j < cromosomasObjetivo.length - 1; j++) {
                if (cromosomasObjetivo[j + 1] < cromosomasObjetivo[j]) {
                    //Cambio de lugar valor de cromosomaObjetivo
                    int tmp1;
                    tmp1 = cromosomasObjetivo[j + 1];
                    cromosomasObjetivo[j + 1] = cromosomasObjetivo[j];
                    cromosomasObjetivo[j] = tmp1;
                    //Cambio de lugar valor de cromosomasArrayList
                    int tmp2[][];
                    tmp2 = cromosomas.get(j + 1);
                    cromosomas.add(j + 1, cromosomas.get(j));
                    cromosomas.add(j, tmp2);
                }
            }
        }
    }

    public void dividirParejas() {
        //Se dividen los cromosomas en 2 grupos para emparejarlos
        cro1 = new ArrayList<>();
        cro2 = new ArrayList<>();
        for (int i = 0; i < cromosomas.size(); i++) {
            if (i % 2 == 0) {
                cro1.add(cromosomas.get(i));
            } else {
                cro2.add(cromosomas.get(i));
            }
        }
    }
}
