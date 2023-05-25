.data
    
    #Exercise Header
    Practica:     .word     4               # Practical reference
    Ejercicio:    .word     2               # Exercise number
    Apartado:     .string   ""             # Exercise selection
    
    Alumno_1:     .string   "Carlos Javier Acevedo Cutillas"       # Name alumn 1
    Alumno_2:     .string   "Alicia Falcon Ibanez"              # Name alumn 2
    Alumno_3:     .string   "David Gonzalez Serrano"              # Name alumn 3

.text 

length: .word 7    #Indicamos el tamaño de la lista

list:   .word 7    #Creamos la lista
        .word 5
        .word -4
        .word 100
        .word -250
        .word 73
        .word -50

main:
    la a1,list              #Asignamos la lista al registro a1 y el tamaño a a0
    lw a0,length
    jal print_array         #Imprimimos el array
    jal print_nuevalinea    #Hacemos un salto de linea
    jal bubblesort          #Ordenamos el array
    jal print_array         #Imprimimos el array ordenado
    j end                   #Fin del programa

bubblesort:
    begin_s:
    addi sp, sp, -28    #Asignamos espacio en la pila
    sw ra, 0(sp)        #Almacenamos el contenido de cada registro en la posicion indicada de la pila
    sw a1, 4(sp)
    sw a0, 8(sp)
    sw t0, 12(sp)
    sw t1, 16(sp)
    sw t2, 20(sp)
    sw t3, 24(sp)
    li t3,0            #Variable para saber si hemos hecho un cambio
    mv t1, a0          #Movemos el contenido de t1 a a0
    beq t1,zero,stop   #Si el contenido de t1 es igual a 0 finalizamos el bubblesort (se ha entregado un array vacio)
    li t0,1            #Si no se cumple la primera condicion cargamos 1 como inm en t0
    beq t1,t0,stop     #Si el contenido de t1 es igual a 1 (Contenido de t0) finalizamos el bubblesort (el array tiene 1 posicion)
    loop:
    lw t2,0(a1)        #Cargamos dos valores del array
    lw a0,4(a1)
    blt a0,t2 no_swap  #Si el contenido de a0 es menor que el de t2 no hacemos cambio
    swap:
    sw a0,0(a1)        #Hacemos un swap de los componentes
    sw t2,4(a1)        
    li t3,1            #Marcamos que se ha efectuado un cambio
    no_swap:
    addi t0,t0,1       #Aumentamos en uno el contador
    beq t1,t0,end_bs   #Si el contenido de t1 es igual al de t0 saltamos a end_bs (Tamaño de i alcanzado)
    addi a1,a1,4       #Pasamos a la siguiente posición de la lista
    j loop             #Saltamos a loop
    end_bs:
    mv a0,t1           #Movemos el contenido de a0 a t1
    addi a0,a0,-1      #Restamos 1 a el contenido de a0
    beq t3,zero,stop   #Si el contenido de t3 es igual al de t0 finalizamos el bubble(no se han efectuado cambios por lo que ya esta ordenado)
    lw a1, 4(sp)       #Cargamos la pila para la el bubble
    jal bubblesort     #Recursividad con el array y n-1
    stop:
    lw t3, 24(sp)      #Cargamos el contenido de la pila indicado en sus respectivos registros
    lw t2, 20(sp)
    lw t1, 16(sp)
    lw t0, 12(sp)
    lw a0, 8(sp)
    lw a1, 4(sp)
    lw ra, 0(sp)
    addi sp, sp, 28    #Devolvemos la pila a su estado original
    ret                #volvemos al programa principal

print_array:
    begin:
    addi sp, sp, -20    #Asignamos espacio en la pila
    sw ra, 0(sp)        #Almacenamos el contenido de cada registro en la posicion indicada de la pila
    sw a1, 4(sp)
    sw a0, 8(sp)
    sw t0, 12(sp)
    sw t1, 16(sp)
    li t0,0             #Variable contador que usaremos 
    mv t1, a0
    beq t1,t0,end_print
    print:
    lw a0,0(a1)            #Cargamos el contenido de la posicion actual de la lista en a0
    li a7,1                #Imprimimos por consola
    ecall 
    jal print_nuevalinea   #Hacemos un salto de linea
    addi a1,a1,4           #Pasamos a la siguiente posicion de la lista
    addi t0,t0,1           #Sumamos 1 al contenido de t0
    beq t1,t0,end_print    #Si el contenido de t1 es igual al de t0 finalizamos la impresion de la lista (i alcanzado)
    j print                #Saltamos a print

    end_print:
    lw t1, 16(sp)          #Cargamos el contenido de la pila indicado en sus respectivos registros
    lw t0, 12(sp)
    lw a0, 8(sp)
    lw a1, 4(sp)
    lw ra, 0(sp)
    addi sp, sp, 20        #Devolvemos la pila a su estado original
    ret                    #volvemos al programa principal


print_nuevalinea:
    addi sp, sp, -8        #Asignamos espacio en la pila
    sw ra, 0(sp)           #Almacenamos el contenido de cada registro en la posicion indicada de la pila
    sw a0, 4(sp) 
    li a0,10               #Cargamos 10 como inm en a0 (salto de linea en ascii)  
    li a7,11               #Imprimimos el salto de linea por consola
    ecall
    lw a0, 4(sp)           #Cargamos el contenido de la pila indicado en sus respectivos registros
    lw ra, 0(sp)
    addi sp, sp, 8         #Devolvemos la pila a su estado original
    ret                    #volvemos al programa principal


end:                       #Fin del programa
    li a7, 10
    ecall