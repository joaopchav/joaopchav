#include <iostream>
#include <unistd.h>
#include <sys/wait.h>
#include <vector>

using namespace std;

int main() {
    int n = 10; // tamanho do vetor
    int fd_maior[2]; // pipe para comunicação do maior valor
    int fd_menor[2]; // pipe para comunicação do menor valor
    int fd_soma[2]; // pipe para comunicação da soma parcial
    int pid; // id do processo
    vector<int> v(n); // vetor de valores
    int maior, menor, soma_parcial, soma_total = 0;

    // cria os pipes
    if (pipe(fd_maior) < 0 || pipe(fd_menor) < 0 || pipe(fd_soma) < 0) {
        cerr << "Erro ao criar pipes" << endl;
        exit(1);
    }

    // preenche o vetor com valores aleatórios
    srand(time(nullptr));
    for (int i = 0; i < n; i++) {
        v[i] = rand() % 100;
    }

    // cria os processos filhos
    for (int i = 0; i < 3; i++) {
        pid = fork();

        if (pid == 0) { // processo filho
            if (i == 0) { // calcula o maior valor
                close(fd_maior[0]); // fecha a leitura do pipe
                maior = v[0];
                for (int j = 1; j < n; j++) {
                    if (v[j] > maior) {
                        maior = v[j];
                    }
                }
                write(fd_maior[1], &maior, sizeof(int)); // escreve no pipe
                close(fd_maior[1]); // fecha a escrita do pipe
            } else if (i == 1) { // calcula o menor valor
                close(fd_menor[0]); // fecha a leitura do pipe
                menor = v[0];
                for (int j = 1; j < n; j++) {
                    if (v[j] < menor) {
                        menor = v[j];
                    }
                }
                write(fd_menor[1], &menor, sizeof(int)); // escreve no pipe
                close(fd_menor[1]); // fecha a escrita do pipe
            } else if (i == 2) { // calcula a soma parcial
                close(fd_soma[0]); // fecha a leitura do pipe
                soma_parcial = 0;
                for (int j = 0; j < n/2; j++) {
                    soma_parcial += v[j];
                }
                write(fd_soma[1], &soma_parcial, sizeof(int)); // escreve no pipe
                close(fd_soma[1]); // fecha a escrita do pipe
            }
            exit(0);
        }
    }

	close(fd_maior[1]); // fecha a escrita do pipe do maior valor
	close(fd_menor[1]); // fecha a escrita do pipe do menor valor
	close(fd_soma[1]); // fecha a escrita do pipe da soma parcial

	// lê os resultados dos pipes
	read(fd_maior[0], &maior, sizeof(int));
	read(fd_menor[0], &menor, sizeof(int));

	// lê a soma parcial dos processos filhos que realizam a soma
	for (int i = 0; i < 2; i++) {
		int soma_parcial_filho;
		read(fd_soma[0], &soma_parcial_filho, sizeof(int));
		soma_total += soma_parcial_filho;
	}

	// imprime os resultados
	cout << "Maior valor: " << maior << endl;
	cout << "Menor valor: " << menor << endl;
	cout << "Soma total: " << soma_total << endl;
}
	
