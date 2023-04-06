.data 

    #Exercise Header
    Practica:     .word     1               # Practical reference
    Ejercicio:    .word     3               # Exercise number
    Apartado:     .string   "1"             # Exercise selection

    Alumno_1:     .string   "CarlosJavier_AcevedoCutillas"       # Name alumn 1
    Alumno_2:     .string   "Alicia_FalconIbañez"              # Name alumn 2
    Alumno_3:     .string   "David_GonzalezSerrano"              # Name alumn 3
 
    w: .word 2020
    s: .string "Hola"
    j: .string "\n"
.text
begin:
    lw a1,w
    jal ra,imprimir_enteros
    addi a0,a0,-1
    la a1,s
    jal ra,nueva_linea
    addi a0,a0,-1
    jal ra,imprimir_string
    addi a0,a0,-1
    j end
imprimir_enteros:
mv a0,a1
li a7,1
ecall
jalr zero,ra,0
imprimir_string:
mv a0,a1
li a7,4
ecall
jalr zero,ra,0
nueva_linea:
la a0,j
li a7,4
ecall
jalr zero,ra,0
end:
    li a7,10
    ecall 