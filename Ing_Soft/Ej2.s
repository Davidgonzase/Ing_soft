.data 

    #Exercise Header
    Practica:     .word     4               # Practical reference
    Ejercicio:    .word     1              # Exercise number
    Apartado:     .string   "1"             # Exercise selection

    Alumno_1:     .string   "CarlosJavier_AcevedoCutillas"       # Name alumn 1
    Alumno_2:     .string   "Alicia_FalconIbañez"              # Name alumn 2
    Alumno_3:     .string   "David_GonzalezSerrano"              # Name alumn 3
 
    

.text 

length: .word 7

list:   .word 7
        .word 5
        .word -4
        .word 100
        .word -250    
        .word 73
        .word -50
        
main:
    la a1,list
    lw a0,length
    jal print_array
    jal print_nuevalinea
    jal bubblesort
    jal print_array
    j end 
    
bubblesort:
    begin_s:
    addi sp, sp, -24
    sw ra, 0(sp)
    sw a1, 4(sp)
    sw a0, 8(sp)
    sw t0, 12(sp)
    sw t1, 16(sp)
    sw t2, 20(sp)
    li t0,0
    mv t1, a0
    beq t1,t0,end_bs
    li t0,1
    beq t1,t0,end_bs
    lw t2,0(a1)
    loop:
    lw a0,4(a1)
    blt a0,t2 no_swap
    swap:
    sw a0,0(a1)
    sw t2,4(a1)
    no_swap:
    addi t0,t0,1
    beq t1,t0,end_bs
    addi a1,a1,4
    j loop
    end_bs:
    lw t0,a1
    sw t2,0(a1)
    li t2,4
    mul t3,t3,t2
    li a1,t3
    sw t0,0(a1)
    mv a0,t1
    li a0,0
    addi a0,a0,zero(-1)
    jal bubblesort
    lw t3, 20(sp)
    lw t2, 20(sp)
    lw t1, 16(sp)
    lw t0, 12(sp)
    lw a0, 8(sp)
    lw a1, 4(sp)
    lw ra, 0(sp)
    addi sp, sp, 24
    ret    
    
print_array:
    begin:
    addi sp, sp, -20
    sw ra, 0(sp)
    sw a1, 4(sp)
    sw a0, 8(sp)
    sw t0, 12(sp)
    sw t1, 16(sp)
    li t0,0
    mv t1, a0
    beq t1,t0,end_print
    print:
    lw a0,0(a1)
    li a7,1
    ecall 
    jal print_nuevalinea
    addi a1,a1,4
    addi t0,t0,1
    beq t1,t0,end_print 
    j print
    
    end_print:
    lw t1, 16(sp)
    lw t0, 12(sp)
    lw a0, 8(sp)
    lw a1, 4(sp)
    lw ra, 0(sp)
    addi sp, sp, 20
    ret


print_nuevalinea:
    addi sp, sp, -8
    sw ra, 0(sp)
    sw a0, 4(sp) 
    li a0,10
    li a7,11
    ecall
    lw a0, 4(sp)
    lw ra, 0(sp)
    addi sp, sp, 8
    ret
    
end:
    li a7, 10
    ecall