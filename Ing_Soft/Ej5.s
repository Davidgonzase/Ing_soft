.data
    
    #Exercise Header
    Practica:     .word     4               # Practical reference
    Ejercicio:    .word     2               # Exercise number
    Apartado:     .string   ""             # Exercise selection
    
    Alumno_1:     .string   "Carlos Javier Acevedo Cutillas"       # Name alumn 1
    Alumno_2:     .string   "Alicia Falcon Ibanez"              # Name alumn 2
    Alumno_3:     .string   "David Gonzalez Serrano"              # Name alumn 3

.text 

main:
        li a0,7
        jal fact
        li a7,1
        ecall 
        j end
fact:                            
        li      a1, 3
        blt     a0, a1, LBB0_3
        addi    a2, a0, 1
LBB0_2:                                # =>This Inner Loop Header: Depth=1
        addi    a3, a2, -2
        addi    a2, a2, -1
        mul     a0, a3, a0
        bltu    a1, a2, LBB0_2
LBB0_3:
        ret
end:
    li a7,10
    ecall