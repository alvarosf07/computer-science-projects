#include <stdio.h>
#include <stdlib.h>
#define F 32
#define C 21

FILE *salida_partida;

//Definimos funciones

int menu (void);
void juego_comecocos (int modo_juego, int tablero [F][C]);
void jugar_ordenador (int tablero [F][C]);
void jugar_usuario (int tablero [F][C]);

void cargar_tablero (int tablero[F][C], char nombre_tablero[90]);
void crear_tablero (int tablero [F][C]);
void crear_tablero_usuarios (int tablero [F][C]);
void guardar_tablero (int tablero [F][C], char tablero_guardado[90]);
void imprimir_tablero (int tablero [F][C]);

int movimiento_usuario (int tablero[F][C]);
void movimiento_fantasma (int tablero[F][C], int n);
int comprobar_ganador_ordenador (int tablero[F][C]);

int movimiento_usuario_7 (int tablero[F][C]);
int movimiento_usuario_8 (int tablero[F][C]);
int comprobar_fin_usuarios (int tablero[F][C], int puntuacion_global_7, int puntuacion_global_8);



int main (int argc, char *argv[])
{
	int modo_juego;	//definimos variables
	int tablero [F][C];
	
	srand (time(NULL));
	
	FILE *tablero_inicial;
	
	if(argc==3) //comprobaciones previas de los ficheros
	{
		salida_partida=fopen(argv[1],"a");
		if(salida_partida==NULL)
		{
			perror("ERROR");
			return -1;
		}
	
		tablero_inicial=fopen(argv[2],"r");
		if (tablero_inicial==NULL)
		{
			perror("Error al leer el fichero");
			return -1;
		}
	
		printf ("\n\n\n                                                               ¡¡ BIENVENIDO AL JUEGO DEL COMECOCOS !!\n");
		printf ("\n                                                            ==============================================\n");
	
		fprintf (salida_partida,"\n\n\n                                                                ¡¡ BIENVENIDO AL JUEGO DEL COMECOCOS !!\n");
		fprintf (salida_partida,"\n                                                            ==============================================\n");
		
		do // Se repetira este bucle con el menu y el juego en si hasta que se pulse 3 para salir
		{
			modo_juego = menu (); //bucle de la partida: va a repetir el menu al acabar un modo de juego siempre que no pulsemos un 3 y salgamos
			juego_comecocos (modo_juego, tablero);
		}
		while (modo_juego!=3); 
	
		printf ("\n\n                                                                ¡¡ GRACIAS POR JUGAR AL COMECOCOS !!\n\n\n\n");
		fprintf (salida_partida,"\n\n                                                            ¡¡ GRACIAS POR JUGAR AL COMECOCOS !!\n\n\n\n");
		
		fclose(salida_partida); //Cerramos ficheros antes de finalizar el programa
		fclose(tablero_inicial);
		
	}
	else (printf ("ERROR: Número de argumentos incorrecto (%d)\n", argc));	
	
	return 0;
}


int menu (void)
{
	int modo_juego;
	
	do //Bucle del menu para seleccionar el tipo de juego
	{
		printf ("\n\n                                                              --------------------------------------------\n");
		printf ("\n                                                                  Seleccione un modo de juego:\n");
		printf ("\n                                                                     1. Jugar contra el ordenador");
		printf ("\n                                                                     2. Jugar contra otro usuario");
		printf ("\n                                                                     3. Salir\n");
		printf ("\n                                                              --------------------------------------------\n\n                                                                                  ");
		scanf ("%d", &modo_juego);
		printf ("\n                                                            ==============================================\n\n\n\n\n");
		
		fprintf (salida_partida,"\n\n                                                            --------------------------------------------\n");
		fprintf (salida_partida,"\n                                                            1. Jugar contra el ordenador");
		fprintf (salida_partida,"\n                                                            2. Jugar contra otro usuario");
		fprintf (salida_partida,"\n                                                            3. Salir\n");
		fprintf (salida_partida,"\n                                                            --------------------------------------------\n\n                                                               ");
		fprintf (salida_partida,"%d", modo_juego);
		fprintf (salida_partida,"\n                                                            ==============================================\n\n\n\n\n");
	
		switch (modo_juego)
		{
			case 1: 
			case 2:
			case 3:
				return modo_juego;
				break;
			default:
				printf ("Comando Inválido");
				fprintf (salida_partida,"Comando Inválido");				
		}
	}
	
	while (modo_juego!=3 || modo_juego!=2 || modo_juego!=1);
}


void juego_comecocos (int modo_juego, int tablero [F][C])
{
	
	if (modo_juego==1)
	{
		printf ("Partida contra el ordenador\n\n\n");
		printf ("     HISTORIA: \n\n - En el pais de los numeros algo importante esta a punto de ocurrir: el numero 7 es un ladron profesional que se dispone a robar la caja fuerte de un banco.\n");
		printf (" - Sin embargo, esta cuenta con fuertes medidas de seguridad, ya que esta blindada y obstaculizada por numeros 1.");
		printf ("\n -  Ayudale a robar todo el dinero de la caja fuerte sin que le pillen los 3 guardias que la custodian, que van disfrazados de numero 8. ¿Podras conseguirlo?\n");
		printf("\n     OTROS DATOS DE LA MISIÓN: \n\n - Controles:  w--> arriba // s--> abajo // a--> izquierda // d--> derecha. \n\n - Cada casilla de $ tiene un valor de 100 $.");
		printf ("\n\n - ¿Conseguira el numero 7 llevarse el botin con tu ayuda o acabara entre rejas? Vamos a comprobarlo:\n\n\n");
		
		fprintf (salida_partida,"Partida contra el ordenador\n\n");
		fprintf (salida_partida,"     HISTORIA: \n\n En el pais de los numeros algo importante esta a punto de ocurrir: el numero 7 es un ladron profesional que se dispone a robar la caja fuerte de un banco.\n");
		fprintf (salida_partida," Sin embargo, esta cuenta con fuertes medidas de seguridad, ya que esta blindada y obstaculizada por numeros 1.");
		fprintf (salida_partida,"\n Ayudale a robar todo el dinero de la caja fuerte sin que le pillen los 3 guardias que la custodian, que van disfrazados de numero 8. ¿Podras conseguirlo?\n");
		fprintf(salida_partida,"\n\n     OTROS DATOS DE LA MISIÓN: \n\n Controles:  w--> arriba // s--> abajo // a--> izquierda // d--> derecha. \n\n Cada casilla de $ tiene un valor de 100 $.");
		fprintf (salida_partida,"\n\n ¿Conseguira el numero 7 llevarse el botin con tu ayuda o acabara entre rejas? Vamos a comprobarlo:\n\n\n");
		
		jugar_ordenador(tablero);
		
	}
	
	
	if (modo_juego==2)
	{
		printf ("Juego contra otro usuario");
		fprintf (salida_partida, "Juego contra otro usuario");
		
		jugar_usuario (tablero);
	}
	
	
}


void jugar_ordenador (int tablero [F][C])
{
	int  dificultad, tipo_tablero, modo_juego, turno=1, outcome, puntuacion_global=0, puntuacion_turno, ganador;
	char nombre_tablero[90];
	
	do 
	{
		printf ("\n\nIntroduzca la dificultad de la partida:");
		printf (" \n\n                                                    0--> dificultad facil (Los guardias que vigilan son sheriff de pueblo)");
		printf (" \n                                                    1--> dificultad imposible (Los guardias que vigilan son agentes profesionales del FBI. Se mueven más rapido.)\n\n ===== ");
		scanf ("%d",&dificultad);
		
		fprintf (salida_partida,"\n\nIntroduzca la dificultad de la partida:");
		fprintf (salida_partida," \n\n                                                   0--> dificultad facil (Los guardias que vigilan son sheriff de pueblo)");
		fprintf (salida_partida," \n                                                   1--> dificultad imposible (Los guardias que vigilan son agentes profesionales del FBI. Se mueven más rapido.)\n\n ===== ");
		fprintf (salida_partida,"%d",dificultad);
		
	}
	while (dificultad!=0 && dificultad!=1);
	
	do 
	{
		printf ("\n\nIntroduzca que tipo de tablero desea:");
		printf (" \n\n                                                    0--> tablero cargado de fichero");
		printf (" \n                                                    1--> cargar tablero guardado (partida a medias)");
		printf (" \n                                                    2--> tablero aleatorio (partida empieza de 0)\n\n ===== ");
		scanf ("%d",&tipo_tablero);
		
		fprintf (salida_partida,"\n\nIntroduzca que tipo de tablero desea:");
		fprintf (salida_partida," \n\n                                                    0--> tablero cargado de fichero");
		fprintf (salida_partida," \n                                                    1--> cargar tablero guardado (partida a medias)");
		fprintf (salida_partida," \n                                                    2--> tablero aleatorio (partida empieza de 0)\n\n ===== ");
		fprintf (salida_partida,"%d",tipo_tablero);
		
	}
	while (tipo_tablero!=0 && tipo_tablero!=1 && tipo_tablero!=2);
		
	if (tipo_tablero==0)
	{
		cargar_tablero (tablero, "tablero_inicial.txt");
	}
	
	if (tipo_tablero==1)
	{
		printf ("\n\n                        Introduzca el nombre del tablero guardado que quieras cargar: (por defecto sera '' tablero_guardado.txt '') \n\n =====  ");
		scanf ("%s", nombre_tablero);
		
		fprintf (salida_partida, "\n\n                        Introduzca el nombre del tablero guardado que quieras cargar: (por defecto sera '' tablero_guardado.txt '') \n\n");
		
		cargar_tablero (tablero, nombre_tablero);
	}
	
	if (tipo_tablero==2)
	{
		crear_tablero (tablero);
		imprimir_tablero (tablero);
	}
	
	do
	{
		printf ("\n\n                                          ----------------------------------------------------------------------------------\n\n\n                        Movimiento número %d:", turno);
		fprintf (salida_partida,"\n\n                                                  ----------------------------------------------------------------------------------\n\n\n Movimiento número %d:", turno);		
				
		do //bucle que detecta el movimiento del comecocos. Si la casilla es no navegable, se vuelve a mover
		{
			outcome = movimiento_usuario (tablero); // -1=casilla no navegable (repite) // 0=2º vez visitada // 1=1ºvez visitada(100$) // 2= caer en casilla € // 3= sobornar a un poli(500$) (estos dos ultimos no funcionan)
			if (outcome==-1)
				printf ("\n\n\n==========     Casilla no navegable\n\n\n                                         ");
				fprintf (salida_partida,"\n\n\n==========     Casilla no navegable\n\n\n                                    ");
		}
		while (outcome==-1);
		
		if (dificultad==0)
		{
			movimiento_fantasma (tablero,1);
			movimiento_fantasma (tablero,2);
			movimiento_fantasma (tablero,3);
		}
		
		if (dificultad==1)
		{
			movimiento_fantasma (tablero,1);
			movimiento_fantasma (tablero,1);
			movimiento_fantasma (tablero,2);
			movimiento_fantasma (tablero,2);
			movimiento_fantasma (tablero,3);
			movimiento_fantasma (tablero,3);
		}


		switch (outcome) 
		{
			case 0:
				puntuacion_turno = 0;
				break;
			case 1:
				puntuacion_turno = 100;
				break;
			case 2:
				puntuacion_turno = 100;
				break;
			case 3:
				puntuacion_turno = 500;
				break;
		}
		
		puntuacion_global = puntuacion_global + puntuacion_turno; //Puntuaciones
		if (puntuacion_global==109)
			puntuacion_global=100;
	
		printf ("\n\n\n                           -------------------------------------    Botín de este turno: %d $   -------------------------------------\n",puntuacion_turno);
		printf ("\n\n                          -------------------------------------     Botín total de la partida: %d $     -------------------------------------\n\n",puntuacion_global);
		
		fprintf (salida_partida,"\n\n\n-------------------------------------    Botín de este turno: %d $   -------------------------------------\n",puntuacion_turno);
		fprintf (salida_partida,"\n\n-------------------------------------     Botín total de la partida: %d $     -------------------------------------\n\n",puntuacion_global);
		
		ganador = comprobar_ganador_ordenador (tablero); //comprobar ganador
		
		turno++;
		
		imprimir_tablero (tablero);
	}
	while (ganador==2);
	
	if (ganador==0)
		{
			printf ("\n\n                                                     Game Over. El numero 7 ira a la cárcel una buena temporada...");
			fprintf (salida_partida,"\n\n                                                     Game Over. El numero 7 ira a la cárcel una buena temporada...");
		}	
		
	if (ganador==1)
		{
			printf ("\n\n                                                                     ¡¡ WINNER !! ¡El numero 7 ahora es millonario!                 ");
			fprintf (salida_partida,"\n\n                                                                     ¡¡ WINNER !! ¡El numero 7 ahora es millonario!                 ");
		}
		
	printf ("\n\n\n                                                                       Botín total final: %d $       \n\n", puntuacion_global);
	fprintf (salida_partida,"\n\n\n                                                                          Botín total final: %d $       \n\n", puntuacion_global);
}


void jugar_usuario (int tablero [F][C])
{
	int  tipo_tablero, modo_juego, turno=1, outcome7, outcome8, puntuacion_global_7=0, puntuacion_global_8=0, puntuacion_turno_7, puntuacion_turno_8, ganador;
	char nombre_tablero[90];
	
	do 
	{
		printf ("\n\nIntroduzca que tipo de tablero desea:");
		printf (" \n\n                                                    1--> cargar tablero guardado y partida guardada");
		printf (" \n                                                    2--> tablero aleatorio y partida nueva\n\n =====  ");
		scanf ("%d",&tipo_tablero);
		
		fprintf (salida_partida,"\n\nIntroduzca que tipo de tablero desea:");
		fprintf (salida_partida," \n\n                                                    1--> cargar tablero guardado y partida guardada");
		fprintf (salida_partida," \n                                                    2--> tablero aleatorio y partida nueva\n");
	}
	while (tipo_tablero!=1 && tipo_tablero!=2);
		
		if (tipo_tablero==1)
	{
		printf ("\n\n                         Introduzca el nombre del tablero guardado que quieras cargar: (por defecto sera '' tablero_guardado.txt '') \n\n                         Recordatorio: asegurarse de que el tablero que se carga es de juego entre usuarios y no contra el ordenador\n\n =====  ");
		scanf ("%s", nombre_tablero);
		
		fprintf (salida_partida,"\n\n                        Introduzca el nombre del tablero guardado que quieras cargar: (por defecto sera '' tablero_guardado.txt '') \n\n                          Recordatorio: asegurarse de que el tablero que se carga es de juego entre usuarios y no contra el ordenador\n\n");
		
		cargar_tablero (tablero, nombre_tablero);
	}
	
	if (tipo_tablero==2)
	{
		crear_tablero_usuarios (tablero);
		imprimir_tablero (tablero);
	}
	
	do
	{
		printf ("\n\n                                          ----------------------------------------------------------------------------------\n\n\n                Movimiento número %d:\n\n\n", turno);
		fprintf (salida_partida,"\n\n                                      ----------------------------------------------------------------------------------                  \n\n\n Movimiento número %d:\n\n", turno);
			
		do //outcome guarda el tipo de casilla en la que cae el numero 7, y vuelve a tirar otra vez si esta es no navegable
		{
			outcome7 = movimiento_usuario_7 (tablero); // -1=casilla no navegable (repite) // 0=2º vez visitada // 1=1ºvez visitada(100$) // 2= sobornar a un poli(500$) // 3=poli+casilla no visitada(600)
			if (outcome7==-1)
				printf ("\n\n==========     Casilla no navegable \n\n");
				fprintf (salida_partida,"\n\n==========    Casilla no navegable.     =====================\n");
		}
		while (outcome7==-1);

		
		do 
		{
			outcome8 = movimiento_usuario_8 (tablero); // -1=casilla no navegable (repite) // 0=2º vez visitada // 1=1ºvez visitada(100$) 
			if (outcome8==-1)
				printf ("\n\n==========     Casilla no navegable \n\n");
				fprintf (salida_partida,"\n\n==========    Casilla no navegable.     =====================\n");
		}
		while (outcome8==-1);
		
		
		
		switch (outcome7) 
		{
			case 0:
				puntuacion_turno_7 = 0;
				break;
			case 1:
				puntuacion_turno_7 = 100;
				break;
		}
		
		switch (outcome8) 
		{
			case 0:
				puntuacion_turno_8 = 0;
				break;
			case 1:
				puntuacion_turno_8 = 100;
				break;
		}
		
		
		puntuacion_global_7 = puntuacion_global_7 + puntuacion_turno_7;
		if (puntuacion_global_7==109)
			puntuacion_global_7=100;
	
		puntuacion_global_8 = puntuacion_global_8 + puntuacion_turno_8;
		if (puntuacion_global_8==109)
			puntuacion_global_8=100;
		
		printf ("\n\n\n            -------------------------------------    Botín de este turno: 7--> %d $  //   8--> %d $ -------------------------------------\n",puntuacion_turno_7,puntuacion_turno_8);
		printf ("\n\n            -------------------------------------     Botín total de la partida: 7--> %d $  //   8--> %d $  -------------------------------------\n\n",puntuacion_global_7,puntuacion_global_8);
		
		fprintf (salida_partida,"\n\n\n-------------------------------------    Botín de este turno: 7--> %d $  //   8--> %d $ -------------------------------------\n",puntuacion_turno_7,puntuacion_turno_8);
		fprintf (salida_partida,"\n\n-------------------------------------     Botín total de la partida: 7--> %d $  //   8--> %d $  -------------------------------------\n\n",puntuacion_global_7,puntuacion_global_8);
		
		ganador = comprobar_fin_usuarios (tablero, puntuacion_global_7, puntuacion_global_8); //Devuelve=  0--> si no hay ganador  //  7--> si ha ganado el numero 7  //  8--> si ha hanado el numero 8  // 1--> si es un empate.
		
		turno++;
		
		imprimir_tablero (tablero);
	}
	while (ganador==0);
	
	
	if (ganador==7)
	{
		printf ("\n\n---------------------------------------    PLAYER 7 WINS    ------------------------------------------------");
		printf ("\n\n ---------------------------        Botin Final: \n  1) %d $\n  2) %d $\n\n\n", puntuacion_global_7, puntuacion_global_8 );
		
		fprintf (salida_partida,"\n\n---------------------------------------    PLAYER 7 WINS    ------------------------------------------------");
		fprintf (salida_partida,"\n\n ---------------------------        Botin Final: \n  1) %d $\n  2) %d $\n\n\n", puntuacion_global_7, puntuacion_global_8 );
	}	
	
	if (ganador==8)
	{
		printf ("\n\n--------------------------------------       PLAYER 8 WINS   ------------------------------------------------");
		printf ("\n\n ---------------------------         Botin Final: \n  1) %d $\n  2) %d $\n\n\n", puntuacion_global_8, puntuacion_global_7 );
		
		fprintf (salida_partida,"\n\n--------------------------------------       PLAYER 8 WINS   ------------------------------------------------");
		fprintf (salida_partida,"\n\n ---------------------------         Botin Final: \n  1) %d $\n  2) %d $\n\n\n", puntuacion_global_8, puntuacion_global_7 );
	}
	
	if (ganador==1)
	{
		printf ("\n\n--------------------------------------        IT'S A TIE    ----------------------------------------------------");
		printf ("\n\n------------------------------     Botin Final: \n  -) %d $\n  -) %d $   \n\n\n", puntuacion_global_7, puntuacion_global_8 );
		
		fprintf (salida_partida,"\n\n--------------------------------------        IT'S A TIE    ----------------------------------------------------");
		fprintf (salida_partida,"\n\n------------------------------     Botin Final: \n  -) %d $\n  -) %d $   \n\n\n", puntuacion_global_7, puntuacion_global_8 );
	}
	
}


//---------------------------------------------------------------------------------------------------------------------------------


void cargar_tablero (int tablero [F][C], char nombre_tablero [90])
{
	int numero, i;
	int f=0;
	int c=0;
	
		
	FILE *tablero_cargado;
	
	tablero_cargado=fopen(nombre_tablero,"r");
	
	while (tablero_cargado==NULL)
	{
		perror("\n\n ========== ERROR. ARCHIVO NO ENCONTRADO");
		printf ("\n\n                        Introduzca el nombre del tablero guardado que quieras cargar: (por defecto sera '' tablero_guardado.txt '') \n\n ===== ");
		scanf ("%s", nombre_tablero);
	
		fprintf (salida_partida,"\n\nIntroduzca el nombre del tablero guardado que quieras cargar: (por defecto sera '' tablero_guardado.txt '') \n\n ===== ");
		
		tablero_cargado=fopen(nombre_tablero,"r");
	}
	
		
	
	printf ("\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");
	fprintf (salida_partida,"\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");

	
	while (fscanf(tablero_cargado,"%d",&numero)!= EOF)
	{
		tablero [f][c]=numero;
		
		if ((tablero [f][c])==0)
		{
			printf ("$ ");
			fprintf (salida_partida,"$ ");
		}
		else if ((tablero [f][c])==2)
		{
			printf ("_ ");
			fprintf (salida_partida,"_ ");
		}
		else if ((tablero [f][c])==3)
		{
			printf ("8 ");
			fprintf (salida_partida,"8 ");
		}
		else if ((tablero [f][c])==4)
		{
			printf ("8 ");
			fprintf (salida_partida,"8 ");
		}
		else if ((tablero[f][c]==5))
		{	
			printf ("7 ");
			fprintf (salida_partida,"7 ");
		}
		else if ((tablero[f][c]==6))
		{	
			printf ("? ");
			fprintf (salida_partida,"? ");
		}
		else if ((tablero [f][c])==9)
		{	
			printf ("1 ");
			fprintf (salida_partida,"1 ");
		}
		else
		{
			printf ("%d ", tablero [f][c]);
			fprintf (salida_partida,"%d ", tablero [f][c]);
		}
		
		if (c==21)
		{
			printf ("\n                                                             ");
			fprintf (salida_partida,"\n                                                             ");
			f++;
			c=0;
		}
		else
		{
			c++;
		}
		
	}
	
	fclose (tablero_cargado);
	
	for (i=0; i<=21; i++)
	{
		tablero [32][i]= 9;
	}
}


void crear_tablero (int tablero [F][C])
{
	int i, f, c, aleatf, aleatc;
	
	
	printf ("\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");
	fprintf (salida_partida,"\n\n                                          ----------------------------------------------------------------------------------\n\n                                                            ");
	
	for (f=0;f<=F;f++)
	{
		for (c=0;c<=C;c++)
		{
			if (f==0 || f==(F-1))
			{
				tablero [f][c]=1;
				tablero [0][21]=1;
			}
			
			else if (c==0 || c==C)
				tablero [f][c]=1;
			
			else if (f==32)
			{
				tablero [f][c]=9;
			}
			
			else
				tablero [f][c]=0;
		}
	}
	
	for (i=0; i<150; i++) //genera 150 casillas no navegables o numeros 1
	{
		do
		{
			aleatf= rand()%(30-1+1)+1;
			aleatc= rand()%(20-1+1)+1;
		}
		while ((tablero [aleatf][aleatc])==1);
		
		tablero [aleatf][aleatc]=1;
	}
	
	for (i=0; i<3; i++) //genera 3 fantasmas o numeros 8
	{
		do
		{
		aleatf= rand()%(30-1+1)+1;
		aleatc= rand()%(20-1+1)+1;
		}
		while ((tablero [aleatf][aleatc])==1 || (tablero [aleatf][aleatc])==8);
		
		tablero [aleatf][aleatc]=8;
		printf ("\nFantasmas--> [%d]  [%d]", aleatf, aleatc);
		fprintf (salida_partida,"\nFantasmas--> [%d]  [%d]", aleatf, aleatc);
	}
	 
	//Genera el comecocos o numero 7
	do
	{
	aleatf= rand()%(30-1+1)+1;
	aleatc= rand()%(20-1+1)+1;
	}
	while ((tablero [aleatf][aleatc])==1 || (tablero [aleatf][aleatc])==8);
	
	tablero [aleatf][aleatc]=7;
	printf ("\nComecocos--> [%d]  [%d]", aleatf, aleatc);
	fprintf (salida_partida,"\nComecocos--> [%d]  [%d]", aleatf, aleatc);
	
	printf ("\n\n");
	fprintf (salida_partida,"\n\n");
}


void crear_tablero_usuarios (int tablero [F][C])
{
	int i, f, c, aleatf, aleatc;
	
	
	printf ("\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");
	fprintf (salida_partida,"\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");
	
	for (f=0;f<=F;f++)
	{
		for (c=0;c<=C;c++)
		{
			if (f==0 || f==(F-1))
			{
				tablero [f][c]=1;
				tablero [0][21]=1;
			}
			
			else if (c==0 || c==C)
				tablero [f][c]=1;
			
			else if (f==32)
			{
				tablero [f][c]=9;
			}
			
			else
				tablero [f][c]=0;
		}
	}
	
	for (i=0; i<150; i++) //genera 150 casillas no navegables o numeros 1
	{
		do
		{
			aleatf= rand()%(30-1+1)+1;
			aleatc= rand()%(20-1+1)+1;
		}
		while ((tablero [aleatf][aleatc])==1);
		
		tablero [aleatf][aleatc]=1;
	}
	
	//genera el numero 8
		do
		{
		aleatf= rand()%(30-1+1)+1;
		aleatc= rand()%(20-1+1)+1;
		}
		while ((tablero [aleatf][aleatc])==1);
		
		tablero [aleatf][aleatc]=8;
		printf ("\nFantasma--> [%d]  [%d]", aleatf, aleatc);
		fprintf (salida_partida,"\nFantasma--> [%d]  [%d]", aleatf, aleatc);
	
	 
	//Genera el numero 7
	do
	{
	aleatf= rand()%(30-1+1)+1;
	aleatc= rand()%(20-1+1)+1;
	}
	while ((tablero [aleatf][aleatc])==1 || (tablero [aleatf][aleatc])==8);
	
	tablero [aleatf][aleatc]=7;
	printf ("\nComecocos--> [%d]  [%d]", aleatf, aleatc);
	fprintf (salida_partida,"\nComecocos--> [%d]  [%d]", aleatf, aleatc);
	
	printf ("\n\n");
	fprintf (salida_partida,"\n\n");
}

void guardar_tablero (int tablero [F][C], char tablero_guardado[90])
{
	int f, c;
	
	FILE *guardar;
	guardar=fopen(tablero_guardado,"w");
	
	fprintf (guardar,"\n\n                                                             ");
		
	for (f=0;f<=31;f++)
	{
		for (c=0;c<=C;c++)
		{
			if ((tablero [f][c])==3)
				fprintf (guardar,"8 ");
			else if ((tablero [f][c])==4)
				fprintf (guardar,"8 ");
			else if ((tablero[f][c]==5))
				fprintf (guardar,"7 ");
			else if ((tablero [f][c])==9)
				fprintf (guardar,"1 ");
			else
				fprintf (guardar,"%d ", tablero [f][c]);
		}
		
		fprintf (guardar,"\n                                                             ");
	}
	fprintf (guardar,"\n\n");
	
	fclose(guardar);
	
}


void imprimir_tablero (int tablero [F][C])
{
	int f, c;
	
	printf ("\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");
	fprintf (salida_partida,"\n\n                                          ----------------------------------------------------------------------------------\n\n                                                             ");
		
	for (f=0;f<=31;f++)
	{
		for (c=0;c<=C;c++)
		{
			if ((tablero [f][c])==0)
			{
				printf ("$ ");
				fprintf (salida_partida,"$ ");
			}
			else if ((tablero [f][c])==2)
			{
				printf ("_ ");
				fprintf (salida_partida,"_ ");
			}
			else if ((tablero [f][c])==3)
			{
				printf ("8 ");
				fprintf (salida_partida,"8 ");
			}
			else if ((tablero [f][c])==4)
			{
				printf ("8 ");
				fprintf (salida_partida,"8 ");
			}
			else if ((tablero[f][c]==5))
			{	
				printf ("7 ");
				fprintf (salida_partida,"7 ");
			}
			else if ((tablero[f][c]==6))
			{	
				printf ("? ");
				fprintf (salida_partida,"? ");
			}
			else if ((tablero [f][c])==9)
			{	
				printf ("1 ");
				fprintf (salida_partida,"1 ");
			}
			else
			{
				printf ("%d ", tablero [f][c]);
				fprintf (salida_partida,"%d ", tablero [f][c]);
			}
			
		}
		
		printf ("\n                                                             ");
		fprintf (salida_partida,"\n                                                             ");
	}
	printf ("\n\n");
	fprintf (salida_partida,"\n\n");
}


//---------------------------------------------------------------------------------------------------------------------------------


int movimiento_usuario (int tablero[F][C])
{
	char direccion [10], tablero_guardado[90];
	int f, c,  outcome, modo_juego;

do	
{	
	printf (" Introduzca en que dirección mover al número 7: W--> arriba  //  S--> abajo  //  A--> izquierda  //  D--> derecha //  G--> guardar   \n\n =====     ");
	scanf ("%s", direccion);
	
	fprintf (salida_partida,": Introduzca en que dirección mover al número 7: W--> arriba  //  S--> abajo  //  A--> izquierda  //  D--> derecha //  G--> guardar   \n\n =====   ");
	fprintf (salida_partida,"%s", direccion);
	
	if (direccion[0]=='a')// izquierda
	{
		for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==7)
				{
					if ((tablero[f][c-1]!=1)) 
					{
						if (tablero[f][c-1]==0)
						{
							outcome= 1;
						}
						
						if (tablero[f][c-1]==2)
						{
							 outcome = 0;
						}
						
						if (tablero[f][c-1]==4)
						{
							 outcome = 2;
						}
						
						if (tablero[f][c-1]==6)
						{
							 outcome = 3;
						}
						
						tablero [f][c]=2;
						
						if (tablero[f][c-1]==8)
							tablero  [f][c-1]=8;
						
						else
							(tablero[f][c-1]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='d')//derecha
	{
		for (f=0;f<=F;f++)
		{
			for (c=C;c>=0;c--)
			{	
				if ((tablero[f][c])==7)
				{
					if ((tablero[f][c+1]!=1)) 
					{
						if (tablero[f][c+1]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f][c+1]==2)
						{
							outcome = 0;
						}
						
						if (tablero[f][c-1]==4)
						{
							 outcome = 2;
						}
						
						if (tablero[f][c-1]==6)
						{
							 outcome = 3;
						}
						
						tablero [f][c]=2;
						
						if (tablero[f][c+1]==8)
							tablero  [f][c+1]=8;
						
						else
							(tablero[f][c+1]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='w')//arriba
	{
		for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==7)
				{
					if ((tablero[f-1][c]!=1)) 
					{
						if (tablero[f-1][c]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f-1][c]==2)
						{
							outcome = 0;
						}
						
						if (tablero[f][c-1]==4)
						{
							 outcome = 2;
						}
						
						if (tablero[f][c-1]==6)
						{
							 outcome = 3;
						}
						
						tablero [f][c]=2;
						
						if (tablero[f-1][c]==8)
							tablero  [f-1][c]=8;
						
						else
							(tablero[f-1][c]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='s')//abajo
	{
		for (f=F;f>=0;f--)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==7)
				{
					if ((tablero[f+1][c]!=1)) 
					{
						if (tablero[f+1][c]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f+1][c]==2)
						{
							outcome = 0;
						}
						
						if (tablero[f][c-1]==4)
						{
							 outcome = 2;
						}
						
						if (tablero[f][c-1]==6)
						{
							 outcome = 3;
						}
						
						tablero [f][c]=2;
						
						if (tablero[f+1][c]==8)
							tablero[f+1][c]=8;
						
						else
							(tablero[f+1][c]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='g')//guardar partida
	{
		printf ("\n\n                        Introduzca nombre para guardar el tablero: (se recomienda usar por defecto: ''tablero_guardado.txt'')\n\n =====  ");
		scanf ("%s", tablero_guardado);
		
		fprintf (salida_partida,"\n\nIntroduzca nombre para guardar el tablero: (se recomienda usar por defecto: ''tablero_guardado.txt'')\n\n ");
		fprintf (salida_partida,"%s", tablero_guardado);
		
		guardar_tablero(tablero, tablero_guardado);
		
		printf ("\n\n\n==========     Tablero Guardado.   \n\n");
		fprintf (salida_partida,"\n\n\n==============     Tablero Guardado.   \n\n");
	}
	
	else 
	{
		printf ("\n\n\n==========     Comando Invalido \n\n\n                        ");
		fprintf (salida_partida,"\n\n\n============   Comando Invalido.    \n\n\n                        ");
	}
}	
	while (direccion[0]!='a' && direccion[0]!='d' && direccion[0]!='w' && direccion[0]!='s'); 
	
	return outcome;
	
}


void movimiento_fantasma (int tablero[F][C],int n)
{
	int i=0, f, c, fcoco, ccoco, ffant, cfant, ffant2, cfant2, ffant3, cfant3, diff, difc, movaleat;

	for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if (tablero [f][c]==7)
				{
					fcoco=f;
					ccoco=c;
				}
				
				if (tablero [f][c]==8 || tablero[f][c]==4 || tablero [f][c]==3) //8--> primera jugada // 3--> si el 8 ha ocupado casilla ya visitada (2) // 4-->si el 8 ha ocupado casilla sin visitar (0) // 9--> si 8 esta en 10 turnos de soborno
				{
					i++;
					
					if (i==n)					
					{					
						ffant=f;
						cfant=c;
					}
						
				}
			}
		}

//------------------------------------------------------------1---------------------------------------------------------------------	
	if ((fcoco-ffant)>=0) //hallamos la distancia entre las filas y las columnas de los fantasmas y del comecocos
		diff=fcoco-ffant;
	else 
		diff=ffant-fcoco;
	
	if ((ccoco-cfant)>=0)
		difc=ccoco-cfant;
	else
		difc=cfant-ccoco;
	
	if (diff>=difc) //el que este mas cerca, intentara el fantasma moverse hacia alli. Si no, el movimiento sera aleatorio
	{
		if ((fcoco>ffant) && (tablero[ffant+1][cfant])!=1 && (tablero[ffant+1][cfant])!=8 && (tablero[ffant+1][cfant])!=3 && (tablero[ffant+1][cfant])!=4)
		{
			if ((tablero [ffant+1][cfant])==0)
				{tablero [ffant+1][cfant]= 4;}				
			if ((tablero [ffant+1][cfant])==2)
				{tablero [ffant+1][cfant]= 3;}				
			if ((tablero [ffant+1][cfant])==7)
				{tablero [ffant+1][cfant]= 8;}
		}
		
		else if ((fcoco<ffant) && (tablero[ffant-1][cfant])!=1 && (tablero[ffant-1][cfant])!=8 && (tablero[ffant-1][cfant])!=3 && (tablero[ffant-1][cfant])!=4)
		{
			if ((tablero [ffant-1][cfant])==0)
				{tablero [ffant-1][cfant]= 4;}
			if ((tablero [ffant-1][cfant])==2)
				{tablero [ffant-1][cfant]= 3;}
			if ((tablero [ffant-1][cfant])==7)
				{tablero [ffant-1][cfant]= 8;}
		}
			
		else
		{	
			if ((ccoco>cfant) && (tablero[ffant][cfant+1])!=1 && (tablero[ffant][cfant+1])!=8 && (tablero[ffant][cfant+1])!=3 && (tablero[ffant][cfant+1])!=4)
			{
				if ((tablero [ffant][cfant+1])==0)
					{tablero [ffant][cfant+1]= 4;}
				if ((tablero [ffant][cfant+1])==2)
					{tablero [ffant][cfant+1]= 3;}
				if ((tablero [ffant][cfant+1])==7)
					{tablero [ffant][cfant+1]= 8;}
			}	
			
			else if ((ccoco<cfant) && (tablero[ffant][cfant-1])!=1 && (tablero[ffant][cfant-1])!=8 && (tablero[ffant][cfant-1])!=3 && (tablero[ffant][cfant-1])!=4)
			{
				if ((tablero [ffant][cfant-1])==0)
					{tablero [ffant][cfant-1]= 4;}
				if ((tablero [ffant][cfant-1])==2)
					{tablero [ffant][cfant-1]= 3;}
				if ((tablero [ffant][cfant-1])==7)
					{tablero [ffant][cfant-1]= 8;}
			}
			
			else
			{
				do 
				{
					movaleat=rand()%(4-1+1)+1;
												
					if (movaleat==1)
					{
						if ((tablero[ffant][cfant-1])!=1 && (tablero[ffant][cfant-1])!=8 && (tablero[ffant][cfant-1])!=3 && (tablero[ffant][cfant-1])!=4)
						{
							if ((tablero [ffant][cfant-1])==0)
								{tablero [ffant][cfant-1]= 4;}
							if ((tablero [ffant][cfant-1])==2)
								{tablero [ffant][cfant-1]= 3;}
							if ((tablero [ffant][cfant-1])==7)
								{tablero [ffant][cfant-1]= 8;}
							movaleat=0;
						}
					}
			
					if(movaleat==2)
					{
						if ((tablero[ffant-1][cfant])!=1 && (tablero[ffant-1][cfant])!=8 && (tablero[ffant-1][cfant])!=3 && (tablero[ffant-1][cfant])!=4)
						{	
							if ((tablero [ffant-1][cfant])==0)
								{tablero [ffant-1][cfant]= 4;}
							if ((tablero [ffant-1][cfant])==2)
								{tablero [ffant-1][cfant]= 3;}
							if ((tablero [ffant-1][cfant])==7)
								{tablero [ffant-1][cfant]= 8;}
							movaleat=0;
						}
					}
			
					if (movaleat==3)
					{
						if ((tablero[ffant][cfant+1])!=1 && (tablero[ffant][cfant+1])!=8 && (tablero[ffant][cfant+1])!=3 && (tablero[ffant][cfant+1])!=4)
						{
							if ((tablero [ffant][cfant+1])==0)
								{tablero [ffant][cfant+1]= 4;}
							if ((tablero [ffant][cfant+1])==2)
								{tablero [ffant][cfant+1]= 3;}
							if ((tablero [ffant][cfant+1])==7)
								{tablero [ffant][cfant+1]= 8;}
							movaleat=0;
						}
					}
				
					if (movaleat==4)
					{
						if ((tablero[ffant+1][cfant])!=1 && (tablero[ffant+1][cfant])!=8 && (tablero[ffant+1][cfant])!=3 && (tablero[ffant+1][cfant])!=4)
						{
							if ((tablero [ffant+1][cfant])==0)
								{tablero [ffant+1][cfant]= 4;}
							if ((tablero [ffant+1][cfant])==2)
								{tablero [ffant+1][cfant]= 3;}
							if ((tablero [ffant+1][cfant])==7)
								{tablero [ffant+1][cfant]= 8;}
							movaleat=0;
						}
					}
				}
				while (movaleat!=0);
			}//else2
		}//else1
	}//diff>=difc
	
	
	if (diff<difc)
	{
		if ((ccoco>cfant) && (tablero[ffant][cfant+1])!=1 && (tablero[ffant][cfant+1])!=8 && (tablero[ffant][cfant+1])!=3 && (tablero[ffant][cfant+1])!=4)
		{
			if ((tablero [ffant][cfant+1])==0)
				{tablero [ffant][cfant+1]= 4;}				
			if ((tablero [ffant][cfant+1])==2)
				{tablero [ffant][cfant+1]= 3;}				
			if ((tablero [ffant][cfant+1])==7)
				{tablero [ffant][cfant+1]= 8;}
		}
		
		else if ((ccoco<cfant) && (tablero[ffant][cfant-1])!=1 && (tablero[ffant][cfant-1])!=8 && (tablero[ffant][cfant-1])!=3 && (tablero[ffant-1][cfant])!=4)
		{
			if ((tablero [ffant][cfant-1])==0)
				{tablero [ffant][cfant-1]= 4;}
			if ((tablero [ffant][cfant-1])==2)
				{tablero [ffant][cfant-1]= 3;}
			if ((tablero [ffant][cfant-1])==7)
				{tablero [ffant][cfant-1]= 8;}
		}
			
		else
		{	
			if ((fcoco>cfant) && (tablero[ffant+1][cfant])!=1 && (tablero[ffant+1][cfant])!=8 && (tablero[ffant+1][cfant])!=3 && (tablero[ffant+1][cfant])!=4)
			{
				if ((tablero [ffant+1][cfant])==0)
					{tablero [ffant+1][cfant]= 4;}
				if ((tablero [ffant+1][cfant])==2)
					{tablero [ffant+1][cfant]= 3;}
				if ((tablero [ffant+1][cfant])==7)
					{tablero [ffant+1][cfant]= 8;}
			}	
			
			else if ((ccoco<cfant) && (tablero[ffant-1][cfant])!=1 && (tablero[ffant-1][cfant])!=8 && (tablero[ffant-1][cfant])!=3 && (tablero[ffant-1][cfant])!=4)
			{
				if ((tablero [ffant-1][cfant])==0)
					{tablero [ffant-1][cfant]= 4;}
				if ((tablero [ffant-1][cfant])==2)
					{tablero [ffant-1][cfant]= 3;}
				if ((tablero [ffant-1][cfant])==7)
					{tablero [ffant-1][cfant]= 8;}
			}
			
			else
			{
				do 
				{
					movaleat=rand()%(4-1+1)+1;
												
					if (movaleat==1)
					{
						if ((tablero[ffant][cfant-1])!=1 && (tablero[ffant][cfant-1])!=8 && (tablero[ffant][cfant-1])!=3 && (tablero[ffant][cfant-1])!=4)
						{
							if ((tablero [ffant][cfant-1])==0)
								{tablero [ffant][cfant-1]= 4;}
							if ((tablero [ffant][cfant-1])==2)
								{tablero [ffant][cfant-1]= 3;}
							if ((tablero [ffant][cfant-1])==7)
								{tablero [ffant][cfant-1]= 8;}
							movaleat=0;
						}
					}
			
					if(movaleat==2)
					{
						if ((tablero[ffant-1][cfant])!=1 && (tablero[ffant-1][cfant])!=8 && (tablero[ffant-1][cfant])!=3 && (tablero[ffant-1][cfant])!=4)
						{	
							if ((tablero [ffant-1][cfant])==0)
								{tablero [ffant-1][cfant]= 4;}
							if ((tablero [ffant-1][cfant])==2)
								{tablero [ffant-1][cfant]= 3;}
							if ((tablero [ffant-1][cfant])==7)
								{tablero [ffant-1][cfant]= 8;}
							movaleat=0;
						}
					}
			
					if (movaleat==3)
					{
						if ((tablero[ffant][cfant+1])!=1 && (tablero[ffant][cfant+1])!=8 && (tablero[ffant][cfant+1])!=3 && (tablero[ffant][cfant+1])!=4)
						{
							if ((tablero [ffant][cfant+1])==0)
								{tablero [ffant][cfant+1]= 4;}
							if ((tablero [ffant][cfant+1])==2)
								{tablero [ffant][cfant+1]= 3;}
							if ((tablero [ffant][cfant+1])==7)
								{tablero [ffant][cfant+1]= 8;}
							movaleat=0;
						}
					}
				
					if (movaleat==4)
					{
						if ((tablero[ffant+1][cfant])!=1 && (tablero[ffant+1][cfant])!=8 && (tablero[ffant+1][cfant])!=3 && (tablero[ffant+1][cfant])!=4)
						{
							if ((tablero [ffant+1][cfant])==0)
								{tablero [ffant+1][cfant]= 4;}
							if ((tablero [ffant+1][cfant])==2)
								{tablero [ffant+1][cfant]= 3;}
							if ((tablero [ffant+1][cfant])==7)
								{tablero [ffant+1][cfant]= 8;}
							movaleat=0;
						}
					}
				}
				while (movaleat!=0);
			}//else2
		}//else1
	}

	
	if ((tablero [ffant][cfant])==8 || tablero [ffant][cfant]==4)//si la casilla estaba sin visitar
						{
							tablero [ffant][cfant]=0;//dejar la casilla sin visitar
						}
	if ((tablero [ffant][cfant])==3)//si la casilla estaba visitada
						{
							tablero [ffant][cfant]=2;//dejar la casilla visitada
						}

}


int comprobar_ganador_ordenador (int tablero[F][C])
{
	int f, c, vida=0, dolares_restantes=0;
	
		for (f=0;f<=31;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==0 || tablero [f][c]==4)
				{
					dolares_restantes++;
				}
				
				if ((tablero[f][c])==7)
				{
					vida++;
				}
			}
		}
	
	printf ("\n                       Dolares Restantes por recoger\n                        --> %d $", dolares_restantes);
	fprintf (salida_partida,"\n                       Dolares Restantes por recoger\n                        --> %d $", dolares_restantes);

	if (dolares_restantes==0)
		return 1;
	
	else if (vida==0)
		return 0;
	
	else 
		return 2;
}


//---------------------------------------------------------------------------------------------------------------------------------


int movimiento_usuario_7 (int tablero[F][C])
{
	char direccion [10], tablero_guardado[90];
	int f, c,  outcome, modo_juego;

do	
{	
	printf ("                         Introduzca en que dirección mover al número 7: W--> arriba  //  S--> abajo  //  A--> izquierda  //  D--> derecha //  G--> guardar  \n\n =====  ");
	scanf ("%s", direccion);
	
	fprintf (salida_partida,"                         Introduzca en que dirección mover al número 7: W--> arriba  //  S--> abajo  //  A--> izquierda  //  D--> derecha //  G--> guardar  \n\n =====     ");
	fprintf (salida_partida,"%s", direccion);
	
	if (direccion[0]=='a')
	{
		for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ( (tablero[f][c])==7 || (tablero[f][c])==6)
				{
					if ((tablero[f][c-1]!=1) && (tablero[f][c-1]!=8)) 
					{
						if (tablero[f][c-1]==0)
						{
							outcome= 1;
						}
						
						if (tablero[f][c-1]==2)
						{
							 outcome = 0;
						}
						
						if ((tablero[f][c])==7)
							tablero [f][c]=2;
						if ((tablero[f][c])==6)
							tablero[f][c]==8;
						
						(tablero[f][c-1]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='d')
	{
		for (f=0;f<=F;f++)
		{
			for (c=C;c>=0;c--)
			{	
				if ((tablero[f][c])==7 || (tablero[f][c])==6)
				{
					if ((tablero[f][c+1]!=1) && (tablero[f][c+1]!=8)) 
					{
						if (tablero[f][c+1]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f][c+1]==2)
						{
							outcome = 0;
						}
						
						if ((tablero[f][c])==7)
							tablero [f][c]=2;
						if ((tablero[f][c])==6)
							tablero[f][c]==8;
						
						(tablero[f][c+1]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='w')
	{
		for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==7 || (tablero[f][c])==6)
				{
					if ((tablero[f-1][c]!=1) && (tablero[f-1][c]!=8)) 
					{
						if (tablero[f-1][c]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f-1][c]==2)
						{
							outcome = 0;
						}
						
						if ((tablero[f][c])==7)
							tablero [f][c]=2;
						if ((tablero[f][c])==6)
							tablero[f][c]==8;
						
						(tablero[f-1][c]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='s')
	{
		for (f=F;f>=0;f--)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==7 || (tablero[f][c])==6)
				{
					if ((tablero[f+1][c]!=1) && (tablero[f+1][c]!=8)) 
					{
						if (tablero[f+1][c]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f+1][c]==2)
						{
							outcome = 0;
						}
						
						if ((tablero[f][c])==7)
							tablero [f][c]=2;
						if ((tablero[f][c])==6)
							tablero[f][c]==8;
						
						(tablero[f+1][c]=7);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='g')
	{
		printf ("\n\n                        Introduzca nombre para guardar el tablero: (se recomienda usar por defecto: ''tablero_guardado.txt'')\n\n ");
		scanf ("%s", tablero_guardado);
		
		fprintf (salida_partida,"\n\n                        Introduzca nombre para guardar el tablero: (se recomienda usar por defecto: ''tablero_guardado.txt'')\n\n ");
		fprintf (salida_partida,"%s", tablero_guardado);
		
		guardar_tablero(tablero, tablero_guardado);
		
		printf ("\n\n\n==========     Tablero Guardado\n\n");
		fprintf (salida_partida,"\n\n\n==========     Tablero Guardado\n\n");
	}
	
	else 
	{
		printf ("\n\n\n==========     Comando Invalido. Vuelva a introducir de nuevo:   \n\n");
		fprintf (salida_partida,"\n\n\n==========     Comando Invalido. Vuelva a introducir de nuevo:   \n\n");
	}
}	
while (direccion[0]!='a' && direccion[0]!='d' && direccion[0]!='w' && direccion[0]!='s'); 
	
	return outcome;
}


int movimiento_usuario_8 (int tablero[F][C])
{
	char direccion [10], tablero_guardado[90];
	int f, c,  outcome, modo_juego;

do	
{	
	printf ("\n                         Introduzca en que dirección mover al número 8: W--> arriba  //  S--> abajo  //  A--> izquierda  //  D--> derecha //  G--> guardar  \n\n =====     ");
	scanf ("%s", direccion);
	
	fprintf (salida_partida,"\n                         Introduzca en que dirección mover al número 8: W--> arriba  //  S--> abajo  //  A--> izquierda  //  D--> derecha //  G--> guardar  \n\n =====     ");
	fprintf (salida_partida,"%s", direccion);
	
	if (direccion[0]=='a')
	{
		for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==8 || (tablero[f][c])==5 || (tablero[f][c])==6)
				{
					if ((tablero[f][c-1]!=1)) 
					{
						if (tablero[f][c-1]==0)
						{
							outcome= 1;
						}
						
						if (tablero[f][c-1]==2)
						{
							 outcome = 0;
						}
						
						if (tablero[f][c]==8 || tablero[f][c]==6)
							tablero [f][c]=2;
						if (tablero[f][c]==5)
							tablero[f][c]==7;
						
						if (tablero[f][c-1]==7)
							tablero [f][c-1]=6;
						
						else
							(tablero[f][c-1]=8);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='d')
	{
		for (f=0;f<=F;f++)
		{
			for (c=C;c>=0;c--)
			{	
				if ((tablero[f][c])==8  || (tablero[f][c])==5  || (tablero[f][c])==6)
				{
					if ((tablero[f][c+1]!=1)) 
					{
						if (tablero[f][c+1]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f][c+1]==2)
						{
							outcome = 0;
						}
						
						if (tablero[f][c]==8  || (tablero[f][c])==6)
							tablero [f][c]=2;
						if (tablero[f][c]==5)
							tablero[f][c]==7;
						
						if (tablero[f][c+1]==7)
							tablero [f][c+1]=6;
						
						else
							(tablero[f][c+1]=8);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='w')
	{
		for (f=0;f<=F;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==8  || (tablero[f][c])==5  || (tablero[f][c])==6)
				{
					if ((tablero[f-1][c]!=1)) 
					{
						if (tablero[f-1][c]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f-1][c]==2)
						{
							outcome = 0;
						}
						
						if (tablero[f][c]==8  || (tablero[f][c])==6)
							tablero [f][c]=2;
						if (tablero[f][c]==5)
							tablero[f][c]==7;
						
						if (tablero[f-1][c]==7)
							tablero [f-1][c]=6;
						
						else
							(tablero[f-1][c]=8);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='s')
	{
		for (f=F;f>=0;f--)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==8  || (tablero[f][c])==5  || (tablero[f][c])==6)
				{
					if ((tablero[f+1][c]!=1)) 
					{
						if (tablero[f+1][c]==0)
						{
							outcome = 1;
						}
						
						if (tablero[f+1][c]==2)
						{
							outcome = 0;
						}
						
						if (tablero[f][c]==8  || (tablero[f][c])==6)
							tablero [f][c]=2;
						if (tablero[f][c]==5)
							tablero[f][c]==7;
						
						if (tablero[f+1][c]==7)
							tablero [f+1][c]=6;
						
						else
							(tablero[f+1][c]=8);
					}
					else
						{outcome = -1;}
				}
			}
		}
	}
	
	else if (direccion[0]=='g')
	{
		printf ("\n\n                        Intruduzca nombre para guardar el tablero: (se recomienda usar por defecto: ''tablero_guardado.txt'')\n\n ===== ");
		scanf ("%s", tablero_guardado);
		
		fprintf (salida_partida,"\n\n                        Intruduzca nombre para guardar el tablero: (se recomienda usar por defecto: ''tablero_guardado.txt'')\n\n ===== ");
		fprintf (salida_partida,"%s", tablero_guardado);
		
		guardar_tablero(tablero, tablero_guardado);
		
		printf ("\n\n\n==========     Tablero Guardado \n\n");
		fprintf (salida_partida,"\n\n\n==========     Tablero Guardado \n\n");
	}
	
	else 
	{
		printf ("\n\n\n==========    Comando Invalido    \n\n");
		fprintf (salida_partida,"\n\n\n==========     Comando Invalido \n\n");
	}
}	
	while (direccion[0]!='a' && direccion[0]!='d' && direccion[0]!='w' && direccion[0]!='s'); 
	
	return outcome;
}

int comprobar_fin_usuarios (int tablero[F][C], int puntuacion_global_7, int puntuacion_global_8)
{
	int f, c, vida=0, dolares_restantes=0;
	
		for (f=0;f<=31;f++)
		{
			for (c=0;c<=C;c++)
			{	
				if ((tablero[f][c])==0 || tablero [f][c]==4)
				{
					dolares_restantes++;
				}
			}
		}
	printf ("\n                    Dolares Restantes por recoger\n                     --> %d $", dolares_restantes);
	fprintf (salida_partida,"\n                    Dolares Restantes por recoger\n                     --> %d $", dolares_restantes);
	
	if (dolares_restantes==0)
	{
		if (puntuacion_global_7<puntuacion_global_8)
		{
			return 8;
		}	
		
		if (puntuacion_global_7>puntuacion_global_8)
		{
			return 7;
		}
		
		if (puntuacion_global_7==puntuacion_global_8)
		{
			return 1;
		}
	}
	
	else 
		return 0;
}
