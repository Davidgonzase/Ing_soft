.data 

    #Exercise Header
    Practica:     .word     1               # Practical reference
    Ejercicio:    .word     2               # Exercise number
    Apartado:     .string   "1"             # Exercise selection

    Alumno_1:     .string   "CarlosJavier_AcevedoCutillas"       # Name alumn 1
    Alumno_2:     .string   "Alicia_FalconIbañez"              # Name alumn 2
    Alumno_3:     .string   "David_GonzalezSerrano"              # Name alumn 3
 
    cadena: .string "Pero soy yo dio!."
    condicion: .string "."
    condicionm: .string "a"
    condicionn: .string "{"
.text
begin:

    # Assembly code
    la s1,cadena
    lb t0,condicion
    lb t1,condicionm
    lb t2,condicionn
Salto: 
    lb s2,0(s1)
    beq s2,t0,Salir
    bge s2,t1,Con
    addi s1,s1,1
    j Salto
Con:
    blt s2,t2,Suma
    sb s2,0(s1)
    addi s1,s1,1
    j Salto
Suma:
    addi s2,s2,-32
    sb s2,0(s1)
    addi s1,s1,1
    j Salto
Salir:
    la a0,cadena
    li a7,4
    ecall
end:
    li a7,10
    ecall 