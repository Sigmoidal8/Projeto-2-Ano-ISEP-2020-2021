#include <math.h>
#include <unistd.h>
#include <stdio.h>
#include <time.h>
#include <dirent.h>
#include <stdlib.h>
#include <string.h>
#include "scooter.h"

typedef struct {
	int vehicleID[30];
	int courierID[30];
	double hours[30];
}parkingLot;

int main(void){
	parkingLot v;
	int numberOfVehicles = 0;
    while(1){
		double fullCharge, currentCharge;
        int postPower = 50;
		int courierNIF, scooterNumber;
		
		if(numberOfVehicles < sizeof(v.vehicleID)/sizeof(v.vehicleID[0])) {
			
			DIR *d;
        struct dirent *dir;
        d = opendir("FilesC");
        char **list;
        char **temp;

        list = (char **)calloc(1, sizeof(char *));
		int i = 0;
        if (d){
            while ((dir = readdir(d)) != NULL){
                if (strcmp(dir->d_name, ".") != 0 && strcmp(dir->d_name, "..") != 0 && strcmp(dir->d_name, ".DS_Store")!=0)
                {
                    i++;
                    temp = (char **)realloc(list, i * sizeof(char *));
                    if (temp != NULL)
                    {
                        list = temp;
                        temp = NULL;
                    }
                    list[i - 1] = dir->d_name;
                }
            }
        }

        if(i == 2){ 
	
	char nome[60] = "FilesC/";
	char * nome2 = list[0];
	
	strcat(nome, nome2);

        FILE * data;

        data = fopen(nome,"r");	
        fscanf(data, "%lf %lf\n%d\n%d", &fullCharge, &currentCharge, &scooterNumber, &courierNIF);  
		fclose(data);
		
	char file[100] = "FilesC/";
	char * file2 = list[1];

	strcat(file, file2);

	remove(nome);
	remove(file);
		
        int fullChargeSeconds = (int) (fullCharge *3600);
        int currentChargeSeconds = (int) (currentCharge * 3600);

        int seconds = calculateChargeTime(fullChargeSeconds, currentChargeSeconds, postPower);
        double doubleSeconds = seconds;
        double hours = doubleSeconds/3600;


		if(numberOfVehicles > 0) {
			hours = hours * pow(2.0,numberOfVehicles);
		}
		
		if (v.vehicleID[0] > 0) {
			int j;
			for(j=0; j<numberOfVehicles; j++) {
				v.hours[j] = v.hours[j] * pow(2.0,numberOfVehicles);
			}
		} 

		v.vehicleID[numberOfVehicles] = scooterNumber;
		v.courierID[numberOfVehicles] = courierNIF;
		v.hours[numberOfVehicles] = hours;		
		
		numberOfVehicles++;	
              
        FILE * chargeTime[numberOfVehicles];
        FILE * chargeTimeFlag[numberOfVehicles];
        
		int k;
		for (k=0; k<numberOfVehicles; k++) {
			char text[21];
			time_t now = time(NULL);

			strftime(text, sizeof(text)-1, "%Y_%m_%d_%H_%M_%S", localtime(&now));
    
			

			char begin[60] ="FilesJava/estimate_";
			char * end = ".data";

			strcat(begin, text);
			strcat(begin, end);
    
			chargeTime[k] = fopen(begin,"w");
			fprintf(chargeTime[k],"%lf\n%d\n%d", v.hours[k],v.vehicleID[k],v.courierID[k]);
			fclose(chargeTime[k]);
			
			char * flag = ".flag";
			strcat(begin, flag);

			chargeTimeFlag[k] = fopen(begin,"w");
			fprintf(chargeTimeFlag[k],"%lf\n%d\n%d",v.hours[k],v.vehicleID[k],v.courierID[k]);
			fclose(chargeTimeFlag[k]);
			sleep(1); //Para os ficheiros terem nomes diferentes
		}
		
       } 
	free(list);  
        sleep(60);
		}
         
    }
    return 0;
}