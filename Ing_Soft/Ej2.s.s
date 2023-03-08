.data 

    #Exercise Header
    Practica:     .word     1               # Practical reference
    Ejercicio:    .word     2               # Exercise number
    Apartado:     .string   "1"             # Exercise selection

    Alumno_1:     .string   "CarlosJavier_AcevedoCutillas"       # Name alumn 1
    Alumno_2:     .string   "Alicia_FalcónIbáñez"              # Name alumn 2
    Alumno_3:     .string   "David_GonzálezSerrano"              # Name alumn 3
 
    cadena: .string "ab.adis."
    condicion: .word 42
.text
begin:

    # Assembly code
    la s1,cadena
    lw t0,condicion
Salto: 
    lb s2,0(s1)
    beq s2,t0,Salir
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